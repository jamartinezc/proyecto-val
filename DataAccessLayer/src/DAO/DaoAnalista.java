/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Analista;
import VO.ExamenSolicitado;
import VO.Materia;
import VO.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoAnalista {
    
    public static List<Analista> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createNamedQuery("Analista.consultarAnalistas");
        List<Analista> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static Analista consultarUno(int idAnalista) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createNamedQuery("Analista.consultarUnAnalista");
        query.setParameter("id", idAnalista);
        try{
            Analista item = (Analista) query.getSingleResult();
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
    
    public static Analista eliminar(int idAnalista) throws NoItemFoundException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        
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
    
    public static Analista crear(int idUsuario) throws NoItemFoundException, PosibleDuplicationException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        
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
                throw new NoItemFoundException();
            }
            catch(NoResultException noResult){
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
    
    //Retorna el analista de cierta materia
    public static Analista analistaDeMateria(int idMateria) throws NoItemFoundException{
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        
        Materia materia = em.find(Materia.class, idMateria);
        em.close();
        if(materia!=null){
            return materia.getIdAnalista();
        }
        else{
            throw new NoItemFoundException();
        }       
        
    }
    
    public static Analista consultarAnalista(int idUsuario) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction tx = em.getTransaction();
        
        Usuario usuario = em.find(Usuario.class, idUsuario);
        try
            {
                tx.begin();
                Query q = em.createQuery("select p from Analista p where p.idUsuario = :id");
                q.setParameter("id", usuario);
                List<Analista> lista = q.getResultList();
                tx.commit();
                if(lista.size()>0){
                    return lista.get(0);
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
