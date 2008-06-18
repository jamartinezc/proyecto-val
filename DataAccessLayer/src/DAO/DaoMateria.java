/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Analista;
import VO.Grado;
import VO.Materia;
import java.util.Collection;
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
public class DaoMateria {

    public static List<Materia> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Materia.consultarMaterias");
        List<Materia> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static Materia consultarUno(int idMateria) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Materia.findByIdMateria");
        query.setParameter("id", idMateria);
        try{
            Materia item = (Materia) query.getSingleResult();
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
    
    public static List<Materia> materiasDeAnalista(int idAnalista) throws NoItemFoundException{
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        
        Analista analista = em.find(Analista.class, idAnalista);
        em.close();

        if(analista!=null){
            return (List<Materia>) analista.getMateriaCollection();
        }
        else{
            throw new NoItemFoundException();
        }
            
    }
    
    public static Collection materiasDeGrado(int idGrado)throws NoItemFoundException{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            Grado grado = em.find(Grado.class, idGrado);
            em.close();
            return grado.getMateriaCollection();
        
    }
    
}
