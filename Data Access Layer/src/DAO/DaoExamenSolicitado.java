/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Analista;
import VO.Estados;
import VO.Estudiante;
import VO.Examen;
import VO.ExamenSolicitado;
import VO.Registro;
import java.util.Date;
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
public class DaoExamenSolicitado {

    public static List<ExamenSolicitado> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("ExamenSolicitado.consultarExamenesSolicitados");
        List<ExamenSolicitado> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static ExamenSolicitado consultarUno(int idExamenSolicitado) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("ExamenSolicitado.consultarUnExamenSolicitado");
        query.setParameter("id", idExamenSolicitado);
        try{
            ExamenSolicitado item = (ExamenSolicitado) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static ExamenSolicitado eliminar(int idExamenSolicitado) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    ExamenSolicitado item = em.getReference(ExamenSolicitado.class, idExamenSolicitado);
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
    
    /*public static ExamenSolicitado crear(Date fecha, int idEstudiante, int idAnalista, int idRegistro, int idExamen) throws NoItemFoundException, PosibleDuplicationException{  
       EntityManager em = DaoEntityManagerFactory.getInstance();
       EntityTransaction tx = em.getTransaction();
       
       Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
       Analista analista = em.find(Analista.class, idAnalista);
       
       try
            {
                if()
                
                tx.begin();
                    em.persist(nuevo);
                tx.commit();
                return nuevo;
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
                em.clear();
            }
       
    }*/
    
}
