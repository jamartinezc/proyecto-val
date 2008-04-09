/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CRUD;

import VO.*;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author Sergio
 */
public class CRUD {

    //CRUD Usuario
    
        public List consultarUsuario(String login, String password) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Data");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Usuario p where p.login = :name  AND p.clave = :pass");
                q.setParameter("name", login);
                q.setParameter("pass", password);
                List<Usuario> lista = q.getResultList();
               
                tx.commit();
                return lista;
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
