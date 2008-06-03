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
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoPadre {

    public static List<Padre> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Padre.consultarPadres");
        List<Padre> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Padre consultarUno(int idPadre) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Padre.consultarUnPadre");
        query.setParameter("idPadre", idPadre);
        try{
            Padre item = (Padre) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    public static Padre crear(String nombres, String apellidos, String correo, int idEstudiante) throws NoItemFoundException, PosibleDuplicationException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
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
                em.clear();
                throw new PosibleDuplicationException();
            }
            catch(EntityNotFoundException noResult){
                em.clear();
                throw new NoItemFoundException();
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }
                em.clear();
            }
    }
    
    public static Padre eliminar(int idPadre) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
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
                em.clear();
                throw new NoItemFoundException();
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }
                em.clear();
            }
    }
    
}
