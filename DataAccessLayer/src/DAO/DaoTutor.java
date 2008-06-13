/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Tutor;
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
public class DaoTutor {

    public static List<Tutor> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Tutor.consultarTutores");
        List<Tutor> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Tutor consultarUno(int idTutor) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Tutor.consultarUnTutor");
        query.setParameter("id", idTutor);
        try{
            Tutor item = (Tutor) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static Tutor eliminar(int idTutor) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    Tutor item = em.getReference(Tutor.class, idTutor);
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
    
    public static Tutor crear(int idUsuario) throws NoItemFoundException, PosibleDuplicationException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                Usuario usuario = em.getReference(Usuario.class, idUsuario);
                Query query = em.createNamedQuery("Tutor.consultarPorIdUsuario");
                query.setParameter("id", usuario);
                
                if(query.getResultList().size() != 0)
                {
                    throw new PosibleDuplicationException();
                }
                
                Tutor nuevo = new Tutor();
                nuevo.setIdUsuario(usuario);

                tx.begin();
                    em.persist(nuevo);
                tx.commit();   

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
