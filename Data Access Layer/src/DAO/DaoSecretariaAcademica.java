/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.SecretariaAcademica;
import VO.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoSecretariaAcademica {
    public static List<SecretariaAcademica> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("SecretariaAcademica.consultarSecretariasAcademicas");
        List<SecretariaAcademica> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static SecretariaAcademica consultarUno(int idSecretariaAcademica) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("SecretariaAcademica.findByIdSecretariaAcademica");
        query.setParameter("id", idSecretariaAcademica);
        try{
            SecretariaAcademica item = (SecretariaAcademica) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static SecretariaAcademica eliminar(int idSecretariaAcademica) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    SecretariaAcademica item = em.getReference(SecretariaAcademica.class, idSecretariaAcademica);
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
    
    public static SecretariaAcademica crear(int idUsuario) throws NoItemFoundException, PosibleDuplicationException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                Usuario usuario = em.getReference(Usuario.class, idUsuario);
                Query query = em.createNamedQuery("SecretariaAcademica.consultarPorIdUsuario");
                query.setParameter("id", usuario);
                
                if(query.getResultList().size() != 0)
                {
                    throw new PosibleDuplicationException();
                }
                
                SecretariaAcademica nuevo = new SecretariaAcademica();
                nuevo.setIdUsuario(usuario);

                tx.begin();
                    em.persist(nuevo);
                tx.commit();   

                em.clear();
                return nuevo;
            }
            catch(NonUniqueResultException error){
                    throw new PosibleDuplicationException();
                }
            catch(EntityNotFoundException noResult){
                em.clear();
                throw new NoItemFoundException();
            }
            catch(NoResultException noResult){
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
}
