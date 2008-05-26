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
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class DaoUsuario {
    public static List<Usuario> consultarTodos() {   
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Usuario.consultarUsuarios");
        List<Usuario> items = query.getResultList();
        em.clear();
        return items;
    }
    
    public static Usuario consultarUno(int idUsuario) throws NoItemFoundException{
        EntityManager em = DaoEntityManagerFactory.getInstance();
        Query query = em.createNamedQuery("Usuario.consultarUnUsuario");
        query.setParameter("id", idUsuario);
        try{
            Usuario item = (Usuario) query.getSingleResult();
            em.clear();
            return item;
        }
        catch(NoResultException noResult){
            em.clear();
            throw new NoItemFoundException();
        }
    }
    
    public static List<Usuario> consultarUsuario(String usuario, String password) throws NoItemFoundException{
            EntityManager em = DaoEntityManagerFactory.getInstance();    
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
    
    public static boolean loginExiste(String log)
    {
            EntityManager em = DaoEntityManagerFactory.getInstance();
            EntityTransaction tx = em.getTransaction();
            
            try
            {
                tx.begin();
                Query q = em.createNamedQuery("Usuario.findByLogin");
                q.setParameter("login", log);
                List<Usuario> usuario = q.getResultList();
                tx.commit();
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
                em.clear();
            }
    }
    
    public static Usuario crear(String nombre, String apellido, String login, String clave)throws PosibleDuplicationException{
                       
            EntityManager em = DaoEntityManagerFactory.getInstance();
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

                    em.clear();
                }
            }
            else{
                throw new PosibleDuplicationException();
            }
            
        }
    
    /*
    public static Analista eliminar(int idAnalista) throws NoItemFoundException{  
        EntityManager em = DaoEntityManagerFactory.getInstance();
        EntityTransaction tx = em.getTransaction();
        try
            {
                tx.begin();
                    Analista item = em.getReference(Analista.class, idAnalista);
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
    */
}
