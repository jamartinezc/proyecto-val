package com.liceoval.businesslayer.control;

import com.liceoval.businesslayer.entities.Usuario;
import java.util.List;

import CRUD.Crud;
import Errores.*;
import java.util.LinkedList;

/**
 *
 * @author Jorge
 */

public class AdministradoraDeUsuarios {
    
    /**
     * 
     * @return una lista List&lt;Usuario&gt; conteniendo los usuarios del
     * sistema registrados en al base de datos.
     */
    public List<Usuario> solicitarListaDeUsuarios(){
        Crud driverDB;
        driverDB = new Crud();
        
        List<VO.Usuario> listaTemp = driverDB.listaUsuarios();
        List<Usuario> listaUsuarios = new LinkedList();
        
        for(int i=0;i<listaTemp.size();i++){
            VO.Usuario usuario = listaTemp.get(i);
            Usuario nuevoUsuario = new Usuario();
            
            nuevoUsuario.setIdUsuario( usuario.getIdUsuario() );
            nuevoUsuario.setNombres( usuario.getNombres() );
            nuevoUsuario.setApellidos( usuario.getApellidos() );
            nuevoUsuario.setLogin( usuario.getLogin() );
            nuevoUsuario.setPassword(  (usuario.getClave()).toCharArray()  );
            
            listaUsuarios.add(nuevoUsuario);
        }
        
        return listaUsuarios;
    }
    
    /**
     * 
     * @param nombres String con los nombres del usuario
     * @param apellidos String con los apellidos del usuario
     * @param login String con el login de usuario
     * @param clave String con la password del usuario
     * @return true
     * @throws java.lang.Exception
     */
    public boolean crearUsuario(String nombres, String apellidos, String login, String clave) throws PosibleDuplicationException, NoItemFoundException{
        Crud driverDB;
        driverDB = new Crud();
        
        if(nombres!=null && apellidos!=null && login!=null && clave!=null){
            VO.Usuario usuarioCreado = driverDB.crearUsuario(nombres, apellidos, login, clave);
            if(usuarioCreado != null){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
        
    }
    
    /**
     * 
     * @param usuario objeto usuairo con el ID de usuario y con los datos como
     * se quiere que estén despues de la modificacion.
     * @return <p>true si se modificó satisfacroriamente </p>
     * <p>false si hubo un error y no se pudo modificar
     * @throws java.lang.Exception si el usuario no existe.
     */
    public boolean modificarUsuario(Usuario usuario) throws NoItemFoundException{
        Crud driverDB;
        driverDB = new Crud();
        
        if(usuario != null){
            int idUsuario = usuario.getIdUsuario();
            String nombres = usuario.getNombres();
            String apellidos = usuario.getApellidos();
            String login = usuario.getLogin();
            String clave = usuario.getPassword().toString();
            
            VO.Usuario usuarioModificado = driverDB.actualizarUsuario(idUsuario, nombres, apellidos, login, clave);
            if(usuarioModificado != null){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
        
    }
    
    /**
     * 
     * @param usuario objeto usuario con el ID de usuario del usuario que se
     * quiere eliminar.
     * @return <p>True: si se ha eliminado satisfactoriamente el usuario</p>
     * <p>False: si no se ha podido eleminar el usuario</p>
     * @throws java.lang.Exception si el usuario no existe.
     */
    public void eliminarUsuario(Usuario usuario) throws NoItemFoundException{
        Crud driverDB;
        driverDB = new Crud();
            driverDB.eliminarUsuario( usuario.getIdUsuario() );
        
    }
}
