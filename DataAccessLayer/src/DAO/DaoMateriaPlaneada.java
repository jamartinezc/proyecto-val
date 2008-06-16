/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Materia;
import VO.MateriaPlaneada;
import VO.PlaneacionAnual;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoMateriaPlaneada {

    public static List<MateriaPlaneada> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("MateriaPlaneada.consultarMateriasPlaneadas");
        List<MateriaPlaneada> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static MateriaPlaneada consultarUno(int idMateriaPlaneada) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("MateriaPlaneada.findByIdMateriaPlaneada");
        query.setParameter("id", idMateriaPlaneada);
        try{
            MateriaPlaneada item = (MateriaPlaneada) query.getSingleResult();
            em.close();
            return item;
        }
        catch(NoResultException noResult){
            em.close();
            throw new NoItemFoundException();
        }
    }
    
    public static MateriaPlaneada eliminar(int idMateriaPlaneada) throws NoItemFoundException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    MateriaPlaneada item = em.getReference(MateriaPlaneada.class, idMateriaPlaneada);
                    em.remove(item);
                tx.commit();
                return item;
            }
            catch(EntityNotFoundException noResult){
                em.close();
                throw new NoItemFoundException();
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }
                em.close();
            }
    }
    
    public static MateriaPlaneada crear(int idMateria, int idPlaneacionAnual) throws NoItemFoundException, PosibleDuplicationException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                Materia materia = em.getReference(Materia.class, idMateria);
                PlaneacionAnual plan= em.getReference(PlaneacionAnual.class, idPlaneacionAnual);
                Query query = em.createNamedQuery("MateriaPlaneada.consultarPorIdMAteriaIdPlan");
                query.setParameter("idM", idMateria);
                query.setParameter("idP", plan);
                
                if(query.getResultList().size() != 0)
                {
                    throw new PosibleDuplicationException();
                }
                
                MateriaPlaneada nuevo = new MateriaPlaneada();
                nuevo.setIdMateria(materia);
                nuevo.setIdPlaneacionAnual(plan);

                tx.begin();
                    em.persist(nuevo);
                tx.commit();   

                return nuevo;
            }
            catch(NonUniqueResultException error){
                    throw new PosibleDuplicationException();
                }
            catch(EntityNotFoundException noResult){
                em.close();
                throw new NoItemFoundException();
            }
            catch(NoResultException noResult){
                em.close();
                throw new NoItemFoundException();
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }
                em.close();
            }
    }
    
}
