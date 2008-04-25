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
import com.liceoval.businesslayer.entities.SecretariaAcademica;

import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;

import CRUD.Crud;

import Errores.NoItemFoundException;

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
        VO.SecretariaAcademica secretary;
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
                
                try
                {
                    student = qr.consultarUsuarioEstudiante(userName, password);
                }
                catch(NoItemFoundException ex)
                {
                    student = null;
                }
                
                if(student != null)
                {
                    usuario = new Estudiante();
                    usuario = EntityTranslator.translateEstudiante(student);
                }
                
                break;

            case ROLE_ANALIST:
                
                try
                {
                    analist = qr.consultarUsuarioAnalista(userName, password);
                }
                catch(NoItemFoundException ex)
                {
                    analist = null;
                }
                
                if(analist != null)
                {
                    usuario = new Analista();
                    usuario.setApellidos(analist.getIdUsuario().getApellidos());
                    usuario.setIdUsuario(analist.getIdUsuario().getIdUsuario().intValue());
                    usuario.setLogin(analist.getIdUsuario().getLogin());
                    usuario.setNombres(analist.getIdUsuario().getNombres());
                    usuario.setPassword(analist.getIdUsuario().getClave().toCharArray());
                    
                    ((Analista)usuario).setIdAnalista(analist.getIdAnalista().intValue());
                }
                                                
                break;

            case ROLE_TUTOR:
                
                try
                {
                    tutor = qr.consultarUsuarioTutor(userName, password);
                }
                catch(NoItemFoundException ex)
                {
                    tutor = null;
                }

                if(tutor != null)
                {
                    usuario = new Tutor();
                    usuario.setApellidos(tutor.getIdUsuario().getApellidos());
                    usuario.setIdUsuario(tutor.getIdUsuario().getIdUsuario().intValue());
                    usuario.setLogin(tutor.getIdUsuario().getLogin());
                    usuario.setNombres(tutor.getIdUsuario().getNombres());
                    usuario.setPassword(tutor.getIdUsuario().getClave().toCharArray());
                    
                    ((Tutor)usuario).setIdTutor(tutor.getIdTutor().intValue());
                }
                
                break;
                
            case ROLE_SECRETARY:
                
                try
                {
                    secretary = qr.consultarUsuarioSecretariaAcadémica(userName, password);
                }
                catch(NoItemFoundException ex)
                {
                    secretary = null;
                }
                
                if(secretary != null)
                {
                    usuario = new SecretariaAcademica();
                    usuario.setApellidos(secretary.getIdUsuario().getApellidos());
                    usuario.setIdUsuario(secretary.getIdUsuario().getIdUsuario());
                    usuario.setLogin(secretary.getIdUsuario().getLogin());
                    usuario.setNombres(secretary.getIdUsuario().getNombres());
                    usuario.setPassword(secretary.getIdUsuario().getClave().toCharArray());
                    
                    ((SecretariaAcademica)usuario).setIdSecretariaAcademica(secretary.getIdSecretariaAcademica().intValue());
                }
        }

        return usuario;

    }
}
