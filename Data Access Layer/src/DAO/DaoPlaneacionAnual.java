/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Estudiante;
import VO.PlaneacionAnual;
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
public class DaoPlaneacionAnual {

    public static List<PlaneacionAnual> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("PlaneacionAnual.consultarExamenesPlaneados");
        List<PlaneacionAnual> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static PlaneacionAnual consultarUno(int idExamenP) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("PlaneacionAnual.findByIdPlaneacionAnual");
        query.setParameter("id", idExamenP);
        try{
            PlaneacionAnual item = (PlaneacionAnual) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static PlaneacionAnual crear(int idEstudiante) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
            
                Estudiante estudiante = em.getReference(Estudiante.class, idEstudiante);            
                em.clear();

                PlaneacionAnual nuevo = new PlaneacionAnual();
                nuevo.setIdEstudiante(estudiante);
                
                tx.begin();
                    em.persist(nuevo);
                tx.commit();   

                return nuevo;
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
    
    public static PlaneacionAnual eliminar(int idPlaneacionAnual) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    PlaneacionAnual item = em.getReference(PlaneacionAnual.class, idPlaneacionAnual);
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
