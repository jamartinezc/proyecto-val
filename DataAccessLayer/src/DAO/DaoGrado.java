/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Grado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoGrado {

    public static List<Grado> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Grado.consultarGrados");
        List<Grado> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static Grado consultarUno(int idExamen) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Grado.findByIdGrado");
        query.setParameter("id", idExamen);
        try{
            Grado item = (Grado) query.getSingleResult();
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
    
}
