/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.login;

import com.liceoval.businesslayer.entities.Usuario;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.Analista;
import com.liceoval.businesslayer.entities.Tutor;
import com.liceoval.businesslayer.entities.Grado;

import CRUD.Crud;

/** Esta clase controla el acceso al Sistema. Se encarga de recibir el nombre de
 *  usuario, la contraseña y el Rol de usuario y validar el acceso contra la
 *  base de datos de la aplicación.
 *
 * @author Sergio
 */
public class ControladorDeLogIn
{
    /** Rol de Estudiante */
    public static final int ROLE_STUDENT = 0;
    
    /** Rol de Tutor */
    public static final int ROLE_TUTOR = 1;
    
    /** Rol de Analista */
    public static final int ROLE_ANALIST = 2;
    
    /** Rol de Secretaria Académica */
    public static final int ROLE_SECRETARY = 3;
    
    /** Valida el acceso de un usuario controla la base de datos de la aplicación.
     * 
     * @param userName El nombre del usuario (Login)
     * @param password La contraseña (Clave) del usuario.
     * @param role El rol que cumple el usuario. Debe ser una de estas tres constantes:
     *  <p style="padding-left:20px">ROLE_STUDENT<br />
     *      ROLE_TUTOR<br />
     *      ROLE_ANALIST<br />
     *      ROLE_SECRETARY<br /></p>
     * 
     * @return El usuario que se ha logeado o <code>null</code> si el Log-in ha
     *  fallado.
     */
    
    public Usuario doLogin(String userName, String password, int role)
    {
        VO.Estudiante student;
        VO.Analista analist;
        VO.Tutor tutor;
        Crud qr;
        
        Usuario usuario = null;
        Grado grado;
        
        qr = new Crud();
        
        switch(role)
        {
            case ROLE_STUDENT:
                student = qr.consultarUsuarioEstudiante(userName, password);
                
                if(student != null)
                {
                    usuario = new Estudiante();
                    usuario.setApellidos(student.getIdUsuario().getApellidos());
                    usuario.setIdUsuario(student.getIdUsuario().getIdUsuario().intValue());
                    usuario.setLogin(student.getIdUsuario().getLogin());
                    usuario.setNombres(student.getIdUsuario().getNombres());
                    usuario.setPassword(student.getIdUsuario().getClave().toCharArray());
                  
                    ((Estudiante)usuario).setCodigo(student.getIdEstudiante().intValue());
                    ((Estudiante)usuario).setFechaInicioGrado(student.getFechaInicioGrado());

                    grado = new Grado();
                    grado.setGrado(student.getIdGrado().getIdGrado());
                    grado.setMaterias(null);

                    ((Estudiante)usuario).setGrado(grado);    
                }
                
                break;

            case ROLE_ANALIST:
                analist = qr.consultarUsuarioAnalista(userName, password);
                
                if(analist != null)
                {
                    usuario = new Analista();
                    usuario.setApellidos(analist.getIdUsuario().getApellidos());
                    usuario.setIdUsuario(analist.getIdUsuario().getIdUsuario().intValue());
                    usuario.setLogin(analist.getIdUsuario().getLogin());
                    usuario.setNombres(analist.getIdUsuario().getNombres());
                    usuario.setPassword(analist.getIdUsuario().getClave().toCharArray());
                }
                                                
                break;

            case ROLE_TUTOR:
                tutor = qr.consultarUsuarioTutor(userName, password);

                if(tutor != null)
                {
                    usuario = new Tutor();
                    usuario.setApellidos(tutor.getIdUsuario().getApellidos());
                    usuario.setIdUsuario(tutor.getIdUsuario().getIdUsuario().intValue());
                    usuario.setLogin(tutor.getIdUsuario().getLogin());
                    usuario.setNombres(tutor.getIdUsuario().getNombres());
                    usuario.setPassword(tutor.getIdUsuario().getClave().toCharArray());
                }
                
                break;
        }

        return usuario;

    }
}
