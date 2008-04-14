/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CRUD;

import VO.Analista;
import VO.Estudiante;
import VO.Materia;
import VO.Registro;
import VO.SecretariaAcademica;
import VO.Tutor;
import VO.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jaguar
 */
public class Crud {

    public List<Usuario> consultarUsuario(String usuario, String password) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Usuario p where p.login = :log AND p.clave = :pass");
                q.setParameter("log", usuario);
                q.setParameter("pass", password);
                List<Usuario> lista = q.getResultList();
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
    
    public Estudiante consultarUsuarioEstudiante(String usuario, String password) {
        
            List lista = this.consultarUsuario(usuario, password);
            
            if(lista.size()>0)
            {           
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                EntityManager pm = emf.createEntityManager();            
                Usuario usu;
                usu = (Usuario) lista.get(0);
                try
                {
                    tx.begin();

                    Query q = pm.createQuery("select p from Estudiante p where p.idUsuario = :usuario");
                    q.setParameter("usuario", usu);
                    List<Estudiante> result = q.getResultList();
                    tx.commit();
                    try
                    {
                        return result.get(0);
                    }
                    catch(ArrayIndexOutOfBoundsException err)
                    {
                        return null;
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
            else
            {
                return null;
            }
    }

    public Analista consultarUsuarioAnalista(String usuario, String password) {
        
            List lista = this.consultarUsuario(usuario, password);
            
            if(lista.size()>0)
            {           
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                EntityManager pm = emf.createEntityManager();            
                Usuario usu;
                usu = (Usuario) lista.get(0);
                try
                {
                    tx.begin();

                    Query q = pm.createQuery("select p from Analista p where p.idUsuario = :usuario");
                    q.setParameter("usuario", usu);
                    List<Analista> result = q.getResultList();

                    tx.commit();
                    try
                    {
                        return result.get(0);
                    }
                    catch(ArrayIndexOutOfBoundsException err)
                    {
                        return null;
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
            else
            {
                return null;
            }
    }
    
    public Tutor consultarUsuarioTutor(String usuario, String password) {
        
            List lista = this.consultarUsuario(usuario, password);
            
            if(lista.size()>0)
            {           
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                EntityManager pm = emf.createEntityManager();            
                Usuario usu;
                usu = (Usuario) lista.get(0);
                try
                {
                    tx.begin();

                    Query q = pm.createQuery("select p from Tutor p where p.idUsuario = :usuario");
                    q.setParameter("usuario", usu);
                    List<Tutor> result = q.getResultList();

                    tx.commit();
                    try
                    {
                        return result.get(0);
                    }
                    catch(ArrayIndexOutOfBoundsException err)
                    {
                        return null;
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
            else
            {
                return null;
            }
    }

    public SecretariaAcademica consultarUsuarioSecretariaAcadémica(String usuario, String password) {
        
            List lista = this.consultarUsuario(usuario, password);
            
            if(lista.size()>0)
            {           
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                EntityManager pm = emf.createEntityManager();            
                Usuario usu;
                usu = (Usuario) lista.get(0);
                try
                {
                    tx.begin();

                    Query q = pm.createQuery("select p from SecretariaAcademica p where p.idUsuario = :usuario");
                    q.setParameter("usuario", usu);
                    List<SecretariaAcademica> result = q.getResultList();

                    tx.commit();
                    try
                    {
                        return result.get(0);
                    }
                    catch(ArrayIndexOutOfBoundsException err)
                    {
                        return null;
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
            else
            {
                return null;
            }
    }
    
    public List<Materia> consultarMaterias(){
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Materia p");
                List<Materia> lista = q.getResultList();
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
    
    public Estudiante getEstudiante(int idEstudiante){
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Estudiante p where p.idEstudiante = :estudiante");
                q.setParameter("estudiante", idEstudiante);
                List<Estudiante> lista = q.getResultList();
                tx.commit();
                return lista.get(0);
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
    
    public Materia getMateria(int codigoMateria){
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Materia p where p.idMateria = :materia");
                q.setParameter("materia", codigoMateria);
                List<Materia> lista = q.getResultList();
                tx.commit();
                return lista.get(0);
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
    
    public List<Registro> consultarRegistroEstudianteMateria(int idEstudiante, int codigoMateria){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();  
            
            Estudiante estudiante;
            Materia materia;
            estudiante = this.getEstudiante(idEstudiante);
            materia = this.getMateria(codigoMateria);
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Registro p where p.idEstudiante = :estudiante AND p.idMateria = :materia");
                q.setParameter("estudiante", estudiante);
                Query setParameter = q.setParameter("materia", materia);
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
    
    public boolean crearRegistro(int idEstudiante, int codigoMateria){
        
        List<Registro> repetido = this.consultarRegistroEstudianteMateria(idEstudiante, codigoMateria);

        if(repetido.size()==0)
            {           
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                EntityManager pm = emf.createEntityManager();            
                try
                {
                    tx.begin();
                    
                    Estudiante estudiante;
                    Materia materia;
                    estudiante = this.getEstudiante(idEstudiante);
                    materia = this.getMateria(codigoMateria);
                    
                    Registro registro = new Registro();
                    registro.setActivo(true);
                    registro.setIdEstudiante(estudiante);  
                    registro.setIdMateria(materia);
                    registro.setVecesDevuelta(0);
                    em.persist(registro);
                    
                    tx.commit();
                    
                    
                    
                    return true;
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
            else
            {
                return false;
            }
        
    }
    
}
