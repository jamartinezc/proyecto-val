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
import java.util.Collection;
import java.util.Iterator;
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
public class DaoAnalista {
    
    public static List<Analista> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Analista.consultarAnalistas");
        List<Analista> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Analista consultarUno(int idAnalista) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Analista.consultarUnAnalista");
        query.setParameter("id", idAnalista);
        try{
            Analista item = (Analista) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
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
    
    //Retorna el analista de cierta materia
    public static Analista analistaDeMateria(int idMateria) throws NoItemFoundException{
        
        EntityManager em = DaoEntityManagerFactory.getInstance();
        
        Materia materia = em.find(Materia.class, idMateria);
        em.clear();
        if(materia!=null){
            return materia.getIdAnalista();
        }
        else{
            throw new NoItemFoundException();
        }       
        
    }
    
    public static Analista consultarAnalista(int idUsuario) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance(); 
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

                em.clear();
            }
    }
    
    public static List<ExamenSolicitado> consultarExamenesSolicitadosPendienteCalificarDeAnalista(int idAnalista) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance(); 
        EntityTransaction tx = em.getTransaction();
        
        Analista analista = em.find(Analista.class, idAnalista);
        em.clear();
        
        List<ExamenSolicitado> examenes = (List<ExamenSolicitado>) analista.getExamenSolicitadoCollection();

        for(int i=0;i<analista.getExamenSolicitadoCollection().size();i++)
        {
            if(examenes.get(i).getIdEstado().getIdEstado()!=8) examenes.remove(i);
        }
        if(examenes.size()==0) throw new NoItemFoundException();
        
        return examenes;
    }
    
}
