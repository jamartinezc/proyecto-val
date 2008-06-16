/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.VariablesGlobales;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoVariablesGlobales {

    public static List<VariablesGlobales> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("VariablesGlobales.consultarVariablesGlobales");
        List<VariablesGlobales> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static VariablesGlobales consultarUno(String idVariablesGlobales) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("VariablesGlobales.findByIdVariablesGlobales");
        query.setParameter("id", idVariablesGlobales);
        try{
            VariablesGlobales item = (VariablesGlobales) query.getSingleResult();
            em.close();
            return item;
        }
        catch(NoResultException noResult){
            em.close();
            throw new NoItemFoundException();
        }
    }
    
    public static VariablesGlobales crear(String campo, String Valor) throws PosibleDuplicationException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try
        {
            VariablesGlobales nuevo = new VariablesGlobales();
            nuevo.setCampo(campo);
            nuevo.setValor(Valor);

            tx.begin();
                em.persist(nuevo);
            tx.commit();   

            return nuevo;
        }
        catch(PersistenceException uy)
        {
            em.close();
            throw new PosibleDuplicationException();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            em.close();
        }
    }
    
    public static VariablesGlobales eliminar(String campo) throws NoItemFoundException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    VariablesGlobales item = em.getReference(VariablesGlobales.class, campo);
                    em.remove(item);
                tx.commit();
                return item;
            }
            catch(EntityNotFoundException noResult){
                em.close();
                throw new NoItemFoundException();
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }
                em.close();
            }
    }
    
    public static VariablesGlobales actualizar(String campo, String valor) throws NoItemFoundException{  
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();          
            try
            {
                VariablesGlobales global = em.find(VariablesGlobales.class, campo);
                if(global!=null){
                    tx.begin();
                
                    global.setValor(valor);

                    tx.commit();

                    return global;
                }
                else{
                    throw new NoItemFoundException();
                }
                
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
    }
    
}
