/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Analista;
import VO.Estados;
import VO.Examen;
import VO.ExamenSolicitado;
import VO.Registro;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoExamenSolicitado {

    public static List<ExamenSolicitado> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        em.close();
        Query query = em.createNamedQuery("ExamenSolicitado.consultarExamenesSolicitados");
        List<ExamenSolicitado> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static ExamenSolicitado consultarUno(int idExamenSolicitado) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("ExamenSolicitado.consultarUnExamenSolicitado");
        query.setParameter("id", idExamenSolicitado);
        try{
            ExamenSolicitado item = (ExamenSolicitado) query.getSingleResult();
            em.close();
            return item;
        }
        catch(NoResultException noResult){
            em.close();
            throw new NoItemFoundException();
        }
    }
    
    public static ExamenSolicitado consultarExamenSolicitadoEspecifico(int idAnalista, int idRegistro, int idExamen) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        
        Analista analista = em.find(Analista.class, idAnalista);
        Registro registro = em.find(Registro.class, idRegistro);
        Examen examen = em.find(Examen.class, idExamen);
        
        Query query = em.createNamedQuery("ExamenSolicitado.consultarExamenSolicitadoEspecifico");
        
        query.setParameter("analis", analista);
        query.setParameter("reg", registro);
        query.setParameter("exam", examen);

        List<ExamenSolicitado> item = query.getResultList();
        em.close();
        if(item.size()>0){
            return item.get(0);
        }
        else{
            return null;
        }
    }
    
    public static ExamenSolicitado eliminar(int idExamenSolicitado) throws NoItemFoundException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    ExamenSolicitado item = em.getReference(ExamenSolicitado.class, idExamenSolicitado);
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
    
    public static ExamenSolicitado crear(Date fecha, int idAnalista, int idRegistro, int idExamen) throws NoItemFoundException, PosibleDuplicationException{  
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
       EntityManager em = emf.createEntityManager();
       EntityTransaction tx = em.getTransaction();
       
       try{
           Analista analista = em.getReference(Analista.class, idAnalista);
           Registro registro = em.getReference(Registro.class, idRegistro);
           Examen examen = em.getReference(Examen.class, idExamen);
           Estados estado = em.getReference(Estados.class, 3);
           
           ExamenSolicitado nuevo = new ExamenSolicitado();
           nuevo.setFecha(fecha);
           nuevo.setIdAnalista(analista);
           nuevo.setIdEstado(estado);
           nuevo.setIdExamen(examen);
           nuevo.setIdRegistro(registro);
           nuevo.setNota(0);
           
           tx.begin();
               em.persist(nuevo);
           tx.commit();
           return nuevo;
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
    
    public static ExamenSolicitado actualizarFecha(int idExamenSolicitado, Date fecha) throws NoItemFoundException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                
                tx.begin();
                    ExamenSolicitado cambiado = DaoExamenSolicitado.consultarUno(idExamenSolicitado);
                    cambiado.setFecha(fecha);
                    em.merge(cambiado);
                tx.commit();
                return cambiado;
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
    
    //busca un usuario por appelido o nombre (en DAO USUARIO)
    public static List<ExamenSolicitado> consultarExamenesSolicitadosEntreFechas(Date desde, Date hasta) throws NoItemFoundException{
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction(); 
            
            
            Estados estado = em.find(Estados.class, 4);
                try
                {
                    tx.begin();
                    Query q = em.createQuery("select p from ExamenSolicitado p where p.fecha > :d AND p.fecha < :h AND p.idEstado = :e");
                    q.setParameter("d", desde);
                    q.setParameter("h", hasta);
                    q.setParameter("e", estado);
                    List<ExamenSolicitado> lista = q.getResultList();
                    tx.commit();
                    if(lista.size()>0){
                        return lista;
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
    
    public static ExamenSolicitado actualizarEstadoDeExamenSolicitado(int idExamenSolicitado, int idEstado) throws NoItemFoundException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();          
            try
            {
                ExamenSolicitado examen = em.find(ExamenSolicitado.class, idExamenSolicitado);
                Estados estado = em.find(Estados.class, idEstado);
                if(examen!=null){
                    tx.begin();
                        examen.setIdEstado(estado);
                    tx.commit();
                    
                    return examen;
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
    
    public static ExamenSolicitado actualizarNotaDeExamenSolicitado(int idExamenSolicitado, float nota) throws NoItemFoundException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();          
            try
            {
                ExamenSolicitado examen = em.find(ExamenSolicitado.class, idExamenSolicitado);
                if(examen!=null){
                    tx.begin();
                        examen.setNota(nota);
                    tx.commit();
                    
                    return examen;
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
    
    public static List<ExamenSolicitado> examenesSolicitadosPorEstudiantesDeTutor(int idTutor) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT e FROM ExamenSolicitado e JOIN e.idRegistro  registro JOIN registro.idEstudiante estudiante JOIN estudiante.idTaller taller JOIN taller.idTutor tutor WHERE tutor.idTutor =:idT");
        query.setParameter("idT", idTutor);
        List<ExamenSolicitado> item = query.getResultList();
       
        em.close();
        
        if(item.size()>0)
        {
            return item;
        }
        else{
            throw new NoItemFoundException();
        }
     
    }
    
    public static List<ExamenSolicitado> examenesSolicitadosPorTema (int idEstudiante, int idExamen)throws NoItemFoundException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();           
            
            Examen control = em.find(Examen.class, idExamen);
            
            try
            {
                tx.begin();
                Query q = em.createQuery("select p from ExamenSolicitado p JOIN p.idRegistro r JOIN r.idEstudiante s WHERE p.idExamen = :exam AND s.idEstudiante =:student");
                q.setParameter("exam", control);
                q.setParameter("student", idEstudiante);
                List<ExamenSolicitado> lista = q.getResultList();
                tx.commit();
                
                if(lista.size()>0){
                    return lista;
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
    
    public static List<ExamenSolicitado> examenesSolicitadosDeAnalistaPorEstado(int idAnalista, int idEstado)throws NoItemFoundException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();           
            
            Analista analista = em.find(Analista.class, idAnalista);
            Estados estado = em.find(Estados.class, idEstado);
            
            em.clear();
            
            try
            {
                tx.begin();
                    Query q = em.createQuery("select p from ExamenSolicitado p WHERE p.idEstado = :etat AND p.idAnalista =:analiste");
                    q.setParameter("etat", estado);
                    q.setParameter("analiste", analista);
                    List<ExamenSolicitado> lista = q.getResultList();
                tx.commit();
                
                if(lista.size()>0){
                    return lista;
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
