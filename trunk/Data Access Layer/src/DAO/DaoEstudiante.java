/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Analista;
import VO.Estudiante;
import VO.Grado;
import VO.Taller;
import VO.Usuario;
import java.util.Calendar;
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
public class DaoEstudiante {
    public static List<Estudiante> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Estudiante.consultarEstudiantes");
        List<Estudiante> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Estudiante consultarUno(int idEstudiante) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Estudiante.consultarUnEstudiante");
        query.setParameter("id", idEstudiante);
        try{
            Estudiante item = (Estudiante) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static List<Estudiante> consultarEstudiantesConExamenParaAnalista(Integer idAnalista){
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Analista analista = em.find(Analista.class, idAnalista);
        Query query = em.createQuery("SELECT e.idEstudiante FROM ExamenSolicitado e WHERE e.idAnalista =:idA");
        query.setParameter("idA", analista);

        List<Estudiante> lista = query.getResultList();
        
        return lista;

    }
    
    public static Estudiante eliminar(int idEstudiante) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    Estudiante item = em.getReference(Estudiante.class, idEstudiante);
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
    
    public static Estudiante crear(int idEstudiante, int idGrado, int idTaller, Calendar fechaInicioGrado, int idUsuario) throws NoItemFoundException, PosibleDuplicationException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                Usuario usuario = em.getReference(Usuario.class, idUsuario);
                Query query = em.createNamedQuery("Estudiante.consultarPorIdUsuario");
                query.setParameter("id", usuario);
                if(query.getResultList().size() != 0)throw new PosibleDuplicationException();
                
                query = em.createNamedQuery("Estudiante.consultarUnEstudiante");
                query.setParameter("id", idEstudiante);
                if(query.getResultList().size() != 0)throw new PosibleDuplicationException();
                
                
                Taller taller = em.find(Taller.class, idTaller);
                if(taller==null)throw new NoItemFoundException();
                
                Grado grado = em.find(Grado.class, idGrado);
                if(grado==null)throw new NoItemFoundException();
                
                Estudiante nuevo = new Estudiante();
                nuevo.setIdUsuario(usuario);
                nuevo.setIdTaller(taller);
                nuevo.setFechaInicioGrado(fechaInicioGrado.getTime());
                nuevo.setIdGrado(grado);
                nuevo.setIdEstudiante(idEstudiante);
                
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
