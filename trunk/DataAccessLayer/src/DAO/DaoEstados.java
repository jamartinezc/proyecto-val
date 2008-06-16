/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Estados;
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
public class DaoEstados {

    public static List<Estados> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Estados.consultarEstados");
        List<Estados> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static Estados consultarUno(int idEstado) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Estados.findByIdEstado");
        query.setParameter("id", idEstado);
        try{
            Estados item = (Estados) query.getSingleResult();
            em.close();
            return item;
        }
        catch(NoResultException noResult){
            em.close();
            throw new NoItemFoundException();
        }
    }
    
}
