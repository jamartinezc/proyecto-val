/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Estudiante;
import VO.Padre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoPadre {

    public static List<Padre> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Padre.consultarPadres");
        List<Padre> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static Padre consultarUno(int idPadre) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Padre.consultarUnPadre");
        query.setParameter("idPadre", idPadre);
        try{
            Padre item = (Padre) query.getSingleResult();
            return item;
        }
        catch(NoResultException noResult){
            throw new NoItemFoundException();
        }
        finally
        {
             em.close();
        }
    }
    public static Padre crear(String nombres, String apellidos, String correo, int idEstudiante) throws NoItemFoundException, PosibleDuplicationException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
            
                Estudiante estudiante = em.getReference(Estudiante.class, idEstudiante);            
                em.clear();
                
                Query query = em.createNamedQuery("Padre.consultarPorIdEstudiante");
                query.setParameter("estudiante", estudiante);
                List<Padre> repetido = query.getResultList();
                if(repetido.size()==1) throw new PosibleDuplicationException();
                em.clear();

                Padre nuevo = new Padre();
                nuevo.setApellidos(apellidos);
                nuevo.setNombres(nombres);
                nuevo.setCorreo(correo);
                nuevo.setIdEstudiante(estudiante);

                
                tx.begin();
                    em.persist(nuevo);
                tx.commit();   

                return nuevo;
            }
            catch(PosibleDuplicationException uy ){
                throw new PosibleDuplicationException();
            }
            catch(EntityNotFoundException noResult){
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
    
    public static Padre eliminar(int idPadre) throws NoItemFoundException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    Padre item = em.getReference(Padre.class, idPadre);
                    em.remove(item);
                tx.commit();
                return item;
            }
            catch(EntityNotFoundException noResult){
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
