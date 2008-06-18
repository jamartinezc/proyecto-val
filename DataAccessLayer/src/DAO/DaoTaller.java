/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Taller;
import VO.Tutor;
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
public class DaoTaller {

    public static List<Taller> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Taller.consultarTalleres");
        List<Taller> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static Taller consultarUno(int idTaller) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Taller.findByIdTaller");
        query.setParameter("id", idTaller);
        try{
            Taller item = (Taller) query.getSingleResult();
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
        
    public static List<Taller> consultarTallerDeTutor(int idTutor) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createNamedQuery("Taller.consultarTallerDeTutor");
        
        Tutor tutor = em.find(Tutor.class, idTutor);
        
        query.setParameter("idT", tutor);
        List<Taller> item;
       
        item = query.getResultList();
        em.close();
        
        if(item.size()>0)
        {
            return item;
        }
        else{
            throw new NoItemFoundException();
        }
     
    }
    
}
