/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import com.liceoval.businesslayer.entities.Usuario;
import java.util.List;

import CRUD.Crud;
/**
 *
 * @author Jorge
 */
public class AdministradoraDeUsuarios {
    
    public List<Usuario> solicitarListaDeUsuarios(){
        Crud driverDB;
        driverDB = new Crud();
        
        List<VO.Usuario> listaTemp = driverDB.listaUsuarios();
        List<Usuario> listaUsuarios = null;
        
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
    
    public void crearUsuario(String nombres, String apellidos, String login, String clave){
        Crud driverDB;
        driverDB = new Crud();
        
        if(nombres!=null && apellidos!=null && login!=null && clave!=null){
            driverDB.crearUsuario(nombres, apellidos, login, clave);
        }
        
    }
    
    public void modificarUsuario(Usuario usuario){
        Crud driverDB;
        driverDB = new Crud();
        
        if(usuario != null){
            int idUsuario = usuario.getIdUsuario();
            String nombres = usuario.getNombres();
            String apellidos = usuario.getApellidos();
            String login = usuario.getLogin();
            String clave = usuario.getPassword().toString();
            
            driverDB.actualizarUsuario(idUsuario, nombres, apellidos, login, clave);
        }
        
    }
    
    public void eliminarUsuario(Usuario usuario){
        Crud driverDB;
        driverDB = new Crud();
        
        if(usuario != null){
            
            driverDB.eliminarUsuario( usuario.getIdUsuario() );
        }
        
    }
}
