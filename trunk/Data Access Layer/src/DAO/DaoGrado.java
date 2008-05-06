/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Grado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoGrado {
    public static List<Grado> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Grado.consultarGrados");
        List<Grado> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Grado consultarUno(int idExamen) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Grado.findByIdGrado");
        query.setParameter("id", idExamen);
        try{
            Grado item = (Grado) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
}
