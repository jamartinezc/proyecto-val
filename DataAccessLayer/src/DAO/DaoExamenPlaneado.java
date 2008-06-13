/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.ExamenPlaneado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoExamenPlaneado {

    public static List<ExamenPlaneado> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("ExamenPlaneado.consultarExamenes");
        List<ExamenPlaneado> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static ExamenPlaneado consultarUno(int idExamenP) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("ExamenPlaneado.findByIdExamenPlaneado");
        query.setParameter("id", idExamenP);
        try{
            ExamenPlaneado item = (ExamenPlaneado) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
}
