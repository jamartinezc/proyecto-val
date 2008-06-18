/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Examen;
import VO.Materia;
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
public class DaoExamen {

    public static List<Examen> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Examen.consultarExamenes");
        List<Examen> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static Examen consultarUno(int idExamen) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Examen.findByIdExamen");
        query.setParameter("id", idExamen);
        try{
            Examen item = (Examen) query.getSingleResult();
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
    
    public static List<Examen> consultarExamenesMateria(int idMateria) {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Materia uy = em.find(Materia.class, idMateria);
        Query query = em.createQuery("SELECT e FROM Examen e WHERE e.idMateria =:M");
        query.setParameter("M", uy);
        List<Examen> items = query.getResultList();
        em.close();
        return items;
    }
    
}
