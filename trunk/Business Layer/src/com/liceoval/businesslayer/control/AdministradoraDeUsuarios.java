package com.liceoval.businesslayer.control;

import com.liceoval.businesslayer.control.exceptions.NoSeEncuentraElUsuarioException;
import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.entities.Usuario;
import com.liceoval.businesslayer.entities.wrappers.UsuarioWrapper;
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
    public static List<Usuario> solicitarListaDeUsuarios(){
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
    public static boolean crearUsuario(String nombres, String apellidos, String login, String clave) throws PosibleDuplicationException, NoItemFoundException{
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
    public static boolean modificarUsuario(Usuario usuario) throws NoItemFoundException{
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
    
    /** Elimina el usuario específicado de la base de datos.
     * 
     *  @param idUsuario El ID del usuario.
     *  @throws com.liceoval.businesslayer.control.exceptions.NoSeEncuentraElUsuarioException Si
     *      no existe un usuario con la ID especificada en la base de datos.
     */
    
    public static void eliminarUsuario(int idUsuario) throws NoSeEncuentraElUsuarioException
    {
        Crud driverDB;
        driverDB = new Crud();
        NoSeEncuentraElUsuarioException nseeuEx;
        
        try
        {
            driverDB.eliminarUsuario(idUsuario);
        }
        catch(NoItemFoundException nifEx)
        {
            nseeuEx = new NoSeEncuentraElUsuarioException(
                "No se encuentra el usuario especificado", nifEx);
            throw nseeuEx;
        }
    }
    
    /** Devuelve un usuario específico de acuerdo con la ID de usuario
     *  especificada. El usuario devuelto está envuelto en una clase
     *  UsuarioWrapper para manejar el problema de los múltiples roles.
     * 
     *  @param idUsuario El ID del usuario que se desea recuperar. Este
     *      objeto puede ser de clase Usuario o puede ser de clase Estudiante.
     *  @return Un objeto de clase UsuarioWrapper con la información del
     *      usuario especificado.
     *  @throws com.liceoval.businesslayer.control.exceptions.NoSeEncuentraElUsuarioException; Si
     *      no se puede encontrar un usuario con el ID especificado en la base
     *      de datos.
     */
    
    public static UsuarioWrapper cargarUsuario(int idUsuario) throws NoSeEncuentraElUsuarioException
    {
        NoSeEncuentraElUsuarioException nseeuEx;
        UsuarioWrapper usuarioWrapper;
        VO.Usuario user;
        Usuario usuario;
        Crud crud;
        
        crud = new Crud();
        
        try
        {
            user = crud.consultarUsuario(idUsuario);
        }
        catch(NoItemFoundException nifEx)
        {
            nseeuEx = new NoSeEncuentraElUsuarioException(
                "No se encuentra el usuario especificado", nifEx);
            throw nseeuEx;
        }
        
        // Verificar si se trata de un estudiante
        if(user.getEstudiante() != null)
        {
            // Obtener un objeto estudiante a partir del usuario.            
            usuario = EntityTranslator.translateEstudiante(user.getEstudiante());
                        
            usuarioWrapper = new UsuarioWrapper(usuario);
            usuarioWrapper.setRolEstudiante(true);
        }
        else
        {
            // Traducir el usuario y meterlo en el Wrapper.
            usuario = EntityTranslator.translateUsuario(user);
            usuarioWrapper = new UsuarioWrapper(usuario);
        }
        
        // Verificar que roles cumple el usuario
        if(user.getAnalistaCollection() != null)
        {
            if(user.getAnalistaCollection().size() > 0)
                usuarioWrapper.setRolAnalista(true);
        }
        
        if(user.getTutorCollection() != null)
        {
            if(user.getTutorCollection().size() > 0)
                usuarioWrapper.setRolTutor(true);
        }
        
        if(user.getSecretariaAcademicaCollection() != null)
        {
            if(user.getSecretariaAcademicaCollection().size() > 0)
                usuarioWrapper.setRolSecretariaAcademica(true);
        }
        
        // Devolver el Wrapper del usuario.
        return usuarioWrapper;
    }
    
    /** Verifica si el estudiante especificado pertenece al taller del tutor
     *  especificado.
     * 
     *  @param idEstudiante El id del estudiante que se quiere confirmar.
     *  @param idTutor El id del tutor que se quiere confirmar.
     *  @return Verdadero si el estudiante pertenece al taller del tutor, falso si no.
     */
    
    public static boolean confirmarEstudianteTallerTutor(int idEstudiante, int idTutor)
    {
        Crud crud;
        
        crud = new Crud();
        return crud.estudianteDelTallerDeTutor(idEstudiante, idTutor);
    }
}
