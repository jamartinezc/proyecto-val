/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Padre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoPadre {

    public static List<Padre> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Padre.consultarPadres");
        List<Padre> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Padre consultarUno(int idPadre) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Padre.consultarUnPadre");
        query.setParameter("id", idPadre);
        try{
            Padre item = (Padre) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    /*
    public static Analista eliminar(int idAnalista) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    Analista item = em.getReference(Analista.class, idAnalista);
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
    
    public static Analista crear(int idUsuario) throws NoItemFoundException, PosibleDuplicationException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                Usuario usuario = em.getReference(Usuario.class, idUsuario);
                Query query = em.createNamedQuery("Analista.consultarPorIdUsuario");
                query.setParameter("id", usuario);
                
                if(query.getResultList().size() != 0)
                {
                    throw new PosibleDuplicationException();
                }
                
                Analista nuevo = new Analista();
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
    */
}
