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
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoVariablesGlobales {

    public static List<VariablesGlobales> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("VariablesGlobales.consultarVariablesGlobales");
        List<VariablesGlobales> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static VariablesGlobales consultarUno(String idVariablesGlobales) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("VariablesGlobales.findByIdVariablesGlobales");
        query.setParameter("id", idVariablesGlobales);
        try{
            VariablesGlobales item = (VariablesGlobales) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static VariablesGlobales crear(String campo, String Valor) throws PosibleDuplicationException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
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
            em.clear();
            throw new PosibleDuplicationException();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            em.clear();
        }
    }
    
    public static VariablesGlobales eliminar(String campo) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
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
                em.clear();
                throw new NoItemFoundException();
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }
                em.clear();
            }
    }
    
    public static VariablesGlobales actualizar(String campo, String valor) throws NoItemFoundException{  
            EntityManager em = DaoEntityManagerFactory.getInstance();
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

                em.clear();
            }
    }
    
}
