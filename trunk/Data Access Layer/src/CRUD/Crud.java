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
import VO.Taller;
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

    //retorna una lista de los usuarios con cierto login y contraseña
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
    
    //retorna un estudiante
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

    //retorna un Analista
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
    
    //retorna un Tutor
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

    //retorna una secretaria académica
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
    
    //retorna una lista de materias
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
    
    //retorna un estudiante
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
    
    //retorna una materia
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
    
    //consulta si un estudiante tiene cierta materia registrada
    public Registro consultarRegistroEstudianteMateria(int idEstudiante, int codigoMateria){
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
                if(lista.size()==1)
                {
                    return lista.get(0);
                }
                else{
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
    
    public List<Registro> consultarRegistrosActivosInactivos(int idEstudiante, boolean activo){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();  
            
            Estudiante estudiante;
            estudiante = this.getEstudiante(idEstudiante);
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Registro p where p.idEstudiante = :estudiante AND p.activo = :estado");
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
    
    //crea un registro
    public boolean crearRegistro(int idEstudiante, int codigoMateria){
        
        Registro repetido = this.consultarRegistroEstudianteMateria(idEstudiante, codigoMateria);

        if(repetido==null)
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
    
    //Consultar talleres
    public Taller consultarTaller(int idTaller)
    {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();  
            
            try
            {
                Taller taller;
                tx.begin();
                Query q = pm.createQuery("select p from Taller p where p.idTaller = :taller");
                q.setParameter("taller", idTaller);
                List<Taller> lista = q.getResultList();
                taller = lista.get(0);
                tx.commit();
                return taller;
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
    
    //Retorna todos los usuarios
    public List<Usuario> listaUsuarios() {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Usuario p");
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
    
    //Crear un usuario
    public boolean crearUsuario(String nombre, String apellido, String login, String clave){
                       
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            try
            {
                tx.begin();

                Usuario usuario = new Usuario();
                usuario.setNombres(nombre);
                usuario.setApellidos(apellido);
                usuario.setLogin(login);
                usuario.setClave(clave);
                
                em.persist(usuario);

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
    
    
   
}
