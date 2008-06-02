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
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoMateria {
    public static List<Materia> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Materia.consultarMaterias");
        List<Materia> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Materia consultarUno(int idMateria) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Materia.findByIdMateria");
        query.setParameter("id", idMateria);
        try{
            Materia item = (Materia) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static List<Materia> materiasDeAnalista(int idAnalista) throws NoItemFoundException{
        
        EntityManager em = DaoEntityManagerFactory.getInstance();
        
        Analista analista = em.find(Analista.class, idAnalista);
        em.clear();

        if(analista!=null){
            return (List<Materia>) analista.getMateriaCollection();
        }
        else{
            throw new NoItemFoundException();
        }
            
    }
    
    public static Collection materiasDeGrado(int idGrado)throws NoItemFoundException{
            EntityManager em = DaoEntityManagerFactory.getInstance();
            Grado grado = em.find(Grado.class, idGrado);
            em.clear();
            return grado.getMateriaCollection();
        
    }
}
