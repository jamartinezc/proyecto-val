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
    /*
    public static Analista eliminar(int idAnalista) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    Analista item = em.getReference(Analista.class, idAnalista);
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
    
    public static ExamenSolicitado crear(Date fecha, int idEstudiante, int idAnalista, int idRegistro, int idExamen) throws NoItemFoundException, PosibleDuplicationException{  
       
    }
    */
}
