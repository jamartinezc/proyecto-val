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
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoRegistro {

    public static List<Registro> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Registro.consultarRegistros");
        List<Registro> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Registro consultarUno(int idRegistro) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Registro.consultarUnRegistro");
        query.setParameter("idRegistro", idRegistro);
        try{
            Registro item = (Registro) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static Registro eliminar(int idRegistro) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
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
    
    public static Registro crear(int idEstudiante, boolean activo, int idMateria, int vecesDevuelta) throws NoItemFoundException, PosibleDuplicationException, MateriaDeOtroGradoException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
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
