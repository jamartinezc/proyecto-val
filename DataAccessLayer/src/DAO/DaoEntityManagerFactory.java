/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author David
 */
public class DaoEntityManagerFactory {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
    private static final EntityManager em = emf.createEntityManager();;
            
    private DaoEntityManagerFactory(){
        
    }
    
    public static final EntityManager getInstance() {
       return em;
     }
    
    public static void cerrarConexion() {
        em.clear();
        em.close();
        emf.close();
     }
    
}
