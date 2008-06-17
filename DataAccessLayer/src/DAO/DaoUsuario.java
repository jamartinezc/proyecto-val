/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoUsuario {

    public static List<Usuario> consultarTodos() {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Usuario.consultarUsuarios");
        List<Usuario> items = query.getResultList();
        em.close();
        return items;
    }
    
    public static Usuario consultarUno(int idUsuario) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Usuario.consultarUnUsuario");
        query.setParameter("id", idUsuario);
        try{
            Usuario item = (Usuario) query.getSingleResult();
            em.close();
            return item;
        }
        catch(NoResultException noResult){
            em.close();
            throw new NoItemFoundException();
        }
    }
    
    public static List<Usuario> consultarUsuario(String usuario, String password) throws NoItemFoundException{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager(); 
            EntityTransaction tx = em.getTransaction();           
            
            try
            {
                tx.begin();

                Query q = em.createNamedQuery("Usuario.findByLoginAndByClave");
                q.setParameter("log", usuario);
                q.setParameter("pass", password);
                List<Usuario> lista = q.getResultList();
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
    
    public static List<Usuario> consultarUsuarioPorLogin(String usuario) throws NoItemFoundException{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager(); 
            EntityTransaction tx = em.getTransaction();           
            
            try
            {
                tx.begin();

                Query q = em.createNamedQuery("Usuario.findByLogin");
                q.setParameter("login", usuario);
                List<Usuario> lista = q.getResultList();
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
    
    public static boolean loginExiste(String log)
    {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            
            try
            {
                tx.begin();
                Query q = em.createNamedQuery("Usuario.findByLogin");
                q.setParameter("login", log);
                List<Usuario> usuario = q.getResultList();
                tx.commit();
                em.close();
                if(usuario.size()==1){
                    return false;
                }
                else{
                    return true;
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
    
    public static Usuario crear(String nombre, String apellido, String login, String clave)throws PosibleDuplicationException{
                       
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            if(loginExiste(login)){
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

                    return usuario;
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
                throw new PosibleDuplicationException();
            }
            
        }
    
    
    public static Usuario eliminar(int idUsuario) throws NoItemFoundException{  
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
         
            try
            {
                Usuario usuario = em.find(Usuario.class, idUsuario);
                
                if(usuario!=null){
                    tx.begin();
                    em.remove(usuario);
                    tx.commit();
                    return usuario;
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
    
    public static Usuario actualizar(int idUsuario, String nombre, String apellido, String login, String clave) throws NoItemFoundException{  
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();          
            try
            {
                Usuario usuario = em.find(Usuario.class, idUsuario);
                if(usuario!=null){
                    tx.begin();
                
                    usuario.setNombres(nombre);
                    usuario.setApellidos(apellido);
                    usuario.setLogin(login);
                    usuario.setClave(clave);

                    tx.commit();

                    return usuario;
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
    
    //busca un usuario por appelido o nombre
    public static List<Usuario> buscarUsuario(String comodin) throws NoItemFoundException{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            
                try
                {
                    tx.begin();
                    Query q = em.createQuery("select p from Usuario p where UPPER(p.nombres) LIKE :nombre OR UPPER(p.apellidos) LIKE :nombre ORDER BY p.nombres");
                    q.setParameter("nombre", "%"+comodin+"%");
                    List<Usuario> lista = q.getResultList();
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
