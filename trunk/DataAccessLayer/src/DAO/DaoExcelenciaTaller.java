/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Estudiante;
import VO.ExcelenciaTaller;
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
public class DaoExcelenciaTaller {

    public static List<ExcelenciaTaller> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("ExcelenciaTaller.consultarExcelenciaTaller");
        List<ExcelenciaTaller> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static ExcelenciaTaller consultarUno(int idExcelenciaTaller) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("ExcelenciaTaller.findByIdExcelenciaTaller");
        query.setParameter("id", idExcelenciaTaller);
        try{
            ExcelenciaTaller item = (ExcelenciaTaller) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static ExcelenciaTaller crear(int idTaller, int idEstudiante, int presentados, int ganados, int presentadosTaller, int ganadosTaller) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
            
                Estudiante estudiante = em.getReference(Estudiante.class, idEstudiante);            
                em.clear();

                ExcelenciaTaller nuevo = new ExcelenciaTaller();
                nuevo.setIdTaller(idTaller);
                nuevo.setIdEstudiante(estudiante);
                nuevo.setGanados(ganados);
                nuevo.setPresentados(presentados);
                nuevo.setPresentadosTaller(presentadosTaller);
                nuevo.setGanadosTaller(ganadosTaller);
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
    
    public static ExcelenciaTaller eliminar(int idExcelenciaTaller) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    ExcelenciaTaller item = em.getReference(ExcelenciaTaller.class, idExcelenciaTaller);
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
    
    public static void eliminarTodos() {  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
            Query query = em.createQuery("Delete FROM ExcelenciaTaller");
                tx.begin();
                    query.executeUpdate();
                tx.commit();
                em.clear();
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
