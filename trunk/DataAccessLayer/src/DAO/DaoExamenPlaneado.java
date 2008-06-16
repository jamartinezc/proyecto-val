/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import VO.Estudiante;
import VO.ExamenPlaneado;
import VO.Materia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoExamenPlaneado {

    public static List<ExamenPlaneado> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("ExamenPlaneado.consultarExamenes");
        List<ExamenPlaneado> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static ExamenPlaneado consultarUno(int idExamenP) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("ExamenPlaneado.findByIdExamenPlaneado");
        query.setParameter("id", idExamenP);
        try{
            ExamenPlaneado item = (ExamenPlaneado) query.getSingleResult();
            em.close();
            return item;
        }
        catch(NoResultException noResult){
            em.close();
            throw new NoItemFoundException();
        }
    }
    
    public static List<ExamenPlaneado> examenPlaneadoMateriaEstudiante(int idEstudiante, int idMateria) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        try{
            Materia materia = em.getReference(Materia.class, idMateria);
            Estudiante estudiante = em.getReference(Estudiante.class, idEstudiante);
            Query query = em.createQuery("SELECT e FROM ExamenPlaneado e JOIN e.idPlaneacionSemanal p JOIN e.idExamen t JOIN t.idMateria m WHERE t.idMateria = :idM AND p.idEstudiante = :idE");
            query.setParameter("idM", materia);
            query.setParameter("idE", estudiante);

            List<ExamenPlaneado> item = query.getResultList();
            em.close();
            if(item.size()>0){
                return item;
            }
            else{
                throw new NoItemFoundException();
            }
        }
        catch(EntityNotFoundException uy){
            em.close();
            throw new NoItemFoundException();
        }
        
        
    }
    
}
