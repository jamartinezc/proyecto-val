/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Estudiante;
import VO.ExamenMes;
import VO.Materia;
import VO.MateriaPlaneada;
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
public class DaoExamenMes {

    public static List<ExamenMes> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("ExamenMes.consultarExamenesMes");
        List<ExamenMes> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static ExamenMes consultarUno(int idExamenMes) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("ExamenMes.findByIdExamenMes");
        query.setParameter("id", idExamenMes);
        try{
            ExamenMes item = (ExamenMes) query.getSingleResult();
            em.close();
            return item;
        }
        catch(NoResultException noResult){
            em.close();
            throw new NoItemFoundException();
        }
    }
    
    public static ExamenMes eliminar(int idExamenMes) throws NoItemFoundException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    ExamenMes item = em.getReference(ExamenMes.class, idExamenMes);
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
    
    public static ExamenMes crear(int mes, int planeados, int ganados, int idMateriaPlaneada) throws NoItemFoundException, PosibleDuplicationException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                MateriaPlaneada materia = em.getReference(MateriaPlaneada.class, idMateriaPlaneada);
                Query query = em.createNamedQuery("ExamenMes.consultarPorIdMateriaPlaneadaYMes");
                query.setParameter("mois", mes);
                query.setParameter("idMP", materia);
                
                if(query.getResultList().size() != 0)
                {
                    throw new PosibleDuplicationException();
                }
                
                ExamenMes nuevo = new ExamenMes();
                nuevo.setGanados(ganados);
                nuevo.setIdMateriaPlaneada(materia);
                nuevo.setMes(mes);
                nuevo.setplaneados(planeados);

                tx.begin();
                    em.persist(nuevo);
                tx.commit();   

                return nuevo;
            }
            catch(NonUniqueResultException error){
                    em.clear();
                    throw new PosibleDuplicationException();
                }
            catch(EntityNotFoundException noResult){
                em.close();
                throw new NoItemFoundException();
            }
            catch(NoResultException noResult){
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
    
    public static List<ExamenMes> examenesMesDeEstudianteEnMes(int idEstudiante, int mes) throws NoItemFoundException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                Estudiante estudiante = em.getReference(Estudiante.class, idEstudiante);
                tx.begin();
                    Query query = em.createQuery("SELECT e FROM ExamenMes e JOIN e.idMateriaPlaneada p JOIN p.idPlaneacionAnual a WHERE a.idEstudiante= :est AND e.mes = :mois");
                    query.setParameter("est", estudiante);
                    query.setParameter("mois", mes);
                    List<ExamenMes> lista = query.getResultList();
                tx.commit();   

                if(lista.size()>0){
                    return lista;
                }
                else{
                    throw new NoItemFoundException();
                }
        }
        catch(EntityNotFoundException uy){
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
    
    public static ExamenMes examenesMesDeMateriaDeEstudianteEnMes(int idEstudiante, int mes, int idMateria) throws NoItemFoundException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                Estudiante estudiante = em.getReference(Estudiante.class, idEstudiante);
                Materia materia = em.getReference(Materia.class, idMateria);
                tx.begin();
                    Query query = em.createQuery("SELECT e FROM ExamenMes e JOIN e.idMateriaPlaneada p JOIN p.idPlaneacionAnual a WHERE a.idEstudiante= :est AND e.mes = :mois AND p.idMateria = :M");
                    query.setParameter("est", estudiante);
                    query.setParameter("mois", mes);
                    query.setParameter("M", materia);
                    ExamenMes lista = (ExamenMes) query.getSingleResult();
                tx.commit();   
                
                return lista;
                
        }
        catch(NoResultException ey){
            em.close();
            throw new NoItemFoundException();
        }
        catch(EntityNotFoundException uy){
            em.close();
            throw new NoItemFoundException();
        }
        catch(NonUniqueResultException au){
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
    
    public static ExamenMes actualizarGanadosExamenMes(int idExamenMes, int ganados) throws NoItemFoundException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();          
            try
            {
                ExamenMes examen = em.find(ExamenMes.class, idExamenMes);
                if(examen!=null){
                    tx.begin();
                        examen.setGanados(ganados);
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
    
}
