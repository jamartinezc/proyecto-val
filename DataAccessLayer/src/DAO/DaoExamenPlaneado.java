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
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoExamenPlaneado {

    public static List<ExamenPlaneado> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("ExamenPlaneado.consultarExamenes");
        List<ExamenPlaneado> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static ExamenPlaneado consultarUno(int idExamenP) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("ExamenPlaneado.findByIdExamenPlaneado");
        query.setParameter("id", idExamenP);
        try{
            ExamenPlaneado item = (ExamenPlaneado) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static List<ExamenPlaneado> examenPlaneadoMateriaEstudiante(int idEstudiante, int idMateria) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        try{
            Materia materia = em.getReference(Materia.class, idMateria);
            Estudiante estudiante = em.getReference(Estudiante.class, idEstudiante);
            Query query = em.createQuery("SELECT e FROM ExamenPlaneado e JOIN e.idPlaneacionSemanal p JOIN e.idExamen t JOIN t.idMateria m WHERE t.idMateria = :idM AND p.idEstudiante = :idE");
            query.setParameter("idM", materia);
            query.setParameter("idE", estudiante);

            List<ExamenPlaneado> item = query.getResultList();
            em.clear();
            if(item.size()>0){
                return item;
            }
            else{
                throw new NoItemFoundException();
            }
        }
        catch(EntityNotFoundException uy){
            em.clear();
            throw new NoItemFoundException();
        }
        
        
    }
    
}
