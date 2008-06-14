/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Estudiante;
import VO.PlaneacionSemanal;
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
public class DaoPlaneacionSemanal {

    public static List<PlaneacionSemanal> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("PlaneacionSemanal.consultarPlaneacionSemanal");
        List<PlaneacionSemanal> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static PlaneacionSemanal consultarUno(int idPlaneacionSemanal) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("PlaneacionSemanal.findByIdPlaneacionSemanal");
        query.setParameter("id", idPlaneacionSemanal);
        try{
            PlaneacionSemanal item =  (PlaneacionSemanal) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static PlaneacionSemanal crear(int semana, int idEstudiante) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
            
                Estudiante estudiante = em.getReference(Estudiante.class, idEstudiante);            
                em.clear();

                PlaneacionSemanal nuevo = new PlaneacionSemanal();
                nuevo.setIdEstudiante(estudiante);
                nuevo.setSemana(semana);
                
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
    
    public static PlaneacionSemanal eliminar(int idPlaneacionSemanal) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    PlaneacionSemanal item = em.getReference(PlaneacionSemanal.class, idPlaneacionSemanal);
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
