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
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoExamenSolicitado {

    public static List<ExamenSolicitado> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        em.clear();
        Query query = em.createNamedQuery("ExamenSolicitado.consultarExamenesSolicitados");
        List<ExamenSolicitado> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static ExamenSolicitado consultarUno(int idExamenSolicitado) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("ExamenSolicitado.consultarUnExamenSolicitado");
        query.setParameter("id", idExamenSolicitado);
        try{
            ExamenSolicitado item = (ExamenSolicitado) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static ExamenSolicitado consultarExamenSolicitadoEspecifico(int idAnalista, int idRegistro, int idExamen) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        
        Analista analista = em.find(Analista.class, idAnalista);
        Registro registro = em.find(Registro.class, idRegistro);
        Examen examen = em.find(Examen.class, idExamen);
        
        Query query = em.createNamedQuery("ExamenSolicitado.consultarExamenSolicitadoEspecifico");
        
        query.setParameter("analis", analista);
        query.setParameter("reg", registro);
        query.setParameter("exam", examen);

        List<ExamenSolicitado> item = query.getResultList();
        em.clear();
        if(item.size()>0){
            return item.get(0);
        }
        else{
            return null;
        }
    }
    
    public static ExamenSolicitado eliminar(int idExamenSolicitado) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
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
    
    public static ExamenSolicitado crear(Date fecha, int idAnalista, int idRegistro, int idExamen) throws NoItemFoundException, PosibleDuplicationException{  
       EntityManager em = DaoEntityManagerFactory.getInstance();
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
            em.clear();
        }
       
    }
    
    public static ExamenSolicitado actualizarFecha(int idExamenSolicitado, Date fecha) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
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
    
    //busca un usuario por appelido o nombre (en DAO USUARIO)
    public static List<ExamenSolicitado> consultarExamenesSolicitadosEntreFechas(Date desde, Date hasta) throws NoItemFoundException{
            
            EntityManager em = DaoEntityManagerFactory.getInstance();
            EntityTransaction tx = em.getTransaction(); 
            
                try
                {
                    tx.begin();
                    Query q = em.createQuery("select p from ExamenSolicitado p where p.fecha > :d AND p.fecha < :h AND p.idEstado = 4");
                    q.setParameter("d", desde);
                    q.setParameter("h", hasta);
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

                    em.clear();
                }
    }
    
    public static ExamenSolicitado actualizarEstadoDeExamenSolicitado(int idExamenSolicitado, int idEstado) throws NoItemFoundException{
        
            EntityManager em = DaoEntityManagerFactory.getInstance(); 
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

                em.clear();
            }
        }
    
    public static ExamenSolicitado actualizarNotaDeExamenSolicitado(int idExamenSolicitado, float nota) throws NoItemFoundException{
        
            EntityManager em = DaoEntityManagerFactory.getInstance(); 
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

                em.clear();
            }
        }
    
    public static List<ExamenSolicitado> examenesSolicitadosPorEstudiantesDeTutor(int idTutor) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();

        Query query = em.createQuery("SELECT e FROM ExamenSolicitado e JOIN e.idRegistro  registro JOIN registro.idEstudiante estudiante JOIN estudiante.idTaller taller JOIN taller.idTutor tutor WHERE tutor.idTutor =:idT");
        query.setParameter("idT", idTutor);
        List<ExamenSolicitado> item = query.getResultList();
       
        em.clear();
        
        if(item.size()>0)
        {
            return item;
        }
        else{
            throw new NoItemFoundException();
        }
     
    }
    
    public static List<ExamenSolicitado> examenesSolicitadosPorTema (int idEstudiante, int idExamen)throws NoItemFoundException{
        
            EntityManager em = DaoEntityManagerFactory.getInstance();
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

                em.clear();
            }   
        
    }
   
}
