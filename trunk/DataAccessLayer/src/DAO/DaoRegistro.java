/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.MateriaDeOtroGradoException;
import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Estudiante;
import VO.Materia;
import VO.Registro;
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
public class DaoRegistro {

    public static List<Registro> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Registro.consultarRegistros");
        List<Registro> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static Registro consultarUno(int idRegistro) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Registro.consultarUnRegistro");
        query.setParameter("idRegistro", idRegistro);
        try{
            Registro item = (Registro) query.getSingleResult();
            em.close();
            return item;
        }
        catch(NoResultException noResult){
            em.close();
            throw new NoItemFoundException();
        }
    }
    
    public static Registro eliminar(int idRegistro) throws NoItemFoundException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    Registro item = em.getReference(Registro.class, idRegistro);
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
    
    public static Registro crear(int idEstudiante, boolean activo, int idMateria, int vecesDevuelta) throws NoItemFoundException, PosibleDuplicationException, MateriaDeOtroGradoException{  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
                Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
                if(estudiante==null) throw new NoItemFoundException();
                        
                Materia materia = em.find(Materia.class, idMateria);
                if(materia==null) throw new NoItemFoundException();
                   
                Query query = em.createNamedQuery("Registro.consultarRegistroAlumnoMateria");
                query.setParameter("idE", estudiante);
                query.setParameter("idM", materia);
                List<Registro> lista = query.getResultList();
                if(lista.size()>0) throw new PosibleDuplicationException();
                
                if(estudiante.getIdGrado().getIdGrado() != materia.getIdGrado().getIdGrado()) throw new MateriaDeOtroGradoException();
                
                em.clear();
                
            try{
                Registro nuevo = new Registro();
                nuevo.setActivo(activo);
                nuevo.setIdEstudiante(estudiante);
                nuevo.setIdMateria(materia);
                nuevo.setVecesDevuelta(vecesDevuelta);
                
                tx.begin();
                    em.persist(nuevo);
                tx.commit();
                
                return nuevo;
                
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
    
    public static Registro desactivarRegistro(int idRegistro) throws NoItemFoundException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();           
            try
            {
                Registro registro = em.find(Registro.class, idRegistro);
                if(registro!=null){
                    tx.begin();
                
                    registro.setActivo(false);
                    tx.commit();
                    
                    return registro;
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
    
    public static List<Registro> consultarRegistrosActivosInactivos(int idEstudiante, boolean activo) throws NoItemFoundException{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction(); 
            
            Estudiante estudiante;
            estudiante = em.find(Estudiante.class, idEstudiante);
            
            if(estudiante!=null){
                try
                {
                    tx.begin();
                    Query q = em.createQuery("select p from Registro p where p.idEstudiante = :estudiante AND p.activo = :estado");
                    q.setParameter("estudiante", estudiante);
                    q.setParameter("estado", activo);
                    List<Registro> lista = q.getResultList();
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
            else{
                throw new NoItemFoundException();
            }
    }
    
    public static Registro consultarRegistroEstudianteMateria(int idEstudiante, int codigoMateria) throws NoItemFoundException{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            
            Estudiante estudiante;
            Materia materia;
            estudiante = em.find(Estudiante.class, idEstudiante);
            materia = em.find(Materia.class, codigoMateria);
            
            try
            {
                tx.begin();
                Query q = em.createQuery("select p from Registro p where p.idEstudiante = :estudiante AND p.idMateria = :materia");
                q.setParameter("estudiante", estudiante);
                q.setParameter("materia", materia);
                List<Registro> lista = q.getResultList();
                tx.commit();
                if(lista.size()==1)
                {
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
