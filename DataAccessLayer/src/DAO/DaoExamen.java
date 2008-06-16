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
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoExamen {

    public static List<Examen> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Examen.consultarExamenes");
        List<Examen> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Examen consultarUno(int idExamen) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Examen.findByIdExamen");
        query.setParameter("id", idExamen);
        try{
            Examen item = (Examen) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static List<Examen> consultarExamenesMateria(int idMateria) {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Materia uy = em.find(Materia.class, idMateria);
        Query query = em.createQuery("SELECT e FROM Examen e WHERE e.idMateria =:M");
        query.setParameter("M", uy);
        List<Examen> items = query.getResultList();
        em.clear();
        return items;
    }
    
}
