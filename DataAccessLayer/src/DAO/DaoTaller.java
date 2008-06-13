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
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoTaller {

    public static List<Taller> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Taller.consultarTalleres");
        List<Taller> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Taller consultarUno(int idTaller) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Taller.findByIdTaller");
        query.setParameter("id", idTaller);
        try{
            Taller item = (Taller) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
        
    public static List<Taller> consultarTallerDeTutor(int idTutor) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();

        Query query = em.createNamedQuery("Taller.consultarTallerDeTutor");
        
        Tutor tutor = em.find(Tutor.class, idTutor);
        
        query.setParameter("idT", tutor);
        List<Taller> item;
       
        item = query.getResultList();
        em.clear();
        
        if(item.size()>0)
        {
            return item;
        }
        else{
            throw new NoItemFoundException();
        }
     
    }
    
}
