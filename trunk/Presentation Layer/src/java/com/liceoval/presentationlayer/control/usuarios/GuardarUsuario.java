

package com.liceoval.presentationlayer.control.usuarios;

import Errores.*;
import com.liceoval.businesslayer.control.AdministradoraDeUsuarios;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liceoval.businesslayer.entities.Usuario;

import com.liceoval.presentationlayer.control.util.RequestForwarder;
import com.liceoval.presentationlayer.control.util.ErrorSetter;
import com.liceoval.presentationlayer.control.util.Validaciones;

public class GuardarUsuario extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        // ErrorSetter.setError("createError", "Solicitud recibida", request);
        // RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
        
        Usuario user;
        user=(Usuario)request.getSession().getAttribute("currentUser");
        if(user==null)
        {
            RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
            return;
        }
        else
        {
            if(user.getClass().getName().equals("com.liceoval.businesslayer.entities.SecretariaAcademica")==false)
            {
                RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                return;
            }
        }
        
        // Recibir Parámetros
        
        String userid,action,msj="",Nombres,Apellidos,Login,Clave,Cclave,fecha,codigo,pnombres,papellidos,pidentificacion,pcorreo,Estudiante,Analista,Secretaria,Tutor;
        
        action=request.getParameter("action");
        userid=request.getParameter("userid");
        Estudiante=request.getParameter("Estudiante");
        Analista=request.getParameter("Analista");
        Tutor=request.getParameter("Tutor");
        Secretaria=request.getParameter("Secretaria");
        
        
                if(Estudiante!=null||Analista!=null||Tutor!=null||Secretaria!=null)
                {
                    Nombres=request.getParameter("Nombres");
                    Apellidos=request.getParameter("Apellidos");
                    Login=request.getParameter("Login");
                    Clave=request.getParameter("Clave");
                    Cclave=request.getParameter("Cclave");
                    
                                       
                        if(Validaciones.nombres(Nombres)==false)
                        {
                            msj=msj+"<p>* El <b>Nombre</b> ingresado contiene caracteres diferentes a letras.</p>";
                        }
                        if(Nombres.compareTo("")==0)
                        {
                            msj=msj+"<p>* El <b>Nombre</b> ingresado está en blanco.</p>";
                        }
                        if(Validaciones.nombres(Apellidos)==false)
                        {
                            msj=msj+"<p>* El <b>Apellido</b> ingresado contiene caracteres diferentes a letras.</p>";
                        }
                        if(Apellidos.compareTo("")==0)
                        {
                            msj=msj+"<p>* El <b>Apellido</b> ingresado está en blanco.</p>";
                        }
                        if(Validaciones.nombres(Login)==false)
                        {
                            msj=msj+"<p>* El <b>Login</b> ingresado contiene caracteres diferentes a letras.</p>";
                        }
                        if(Login.compareTo("")==0)
                        {
                            msj=msj+"<p>* El <b>Login</b> ingresado está en blanco.</p>";
                        }
                        if(Validaciones.clave(Clave, Cclave)==false)
                        {
                            msj=msj+"<p>* <b>Clave</b> y/o <b>Confirmar Clave</b> están en blanco o son diferentes.</p>";
                            msj=msj+"<p><center><b>*****Verifique nuevamente los datos ingresados*****</b></center></p>";
                        }

                        if(msj.compareTo("")!=0)
                        {
                            ErrorSetter.setError("createError", msj, request);
                            RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                            return;
                        }
                        //else
                        
                            AdministradoraDeUsuarios admin=new AdministradoraDeUsuarios();
                            if(action.compareTo("edit")!=0)
                            {
                                    try
                                    {
                                        
                                        admin.crearUsuario(Nombres, Apellidos, Login, Clave);
                                    }
                                    catch(PosibleDuplicationException pd)
                                    {
                                        ErrorSetter.setError("createError", "El usuario ingresado ya se encuentra registrado", request);
                                        RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                                        return;
                                    }
                                    catch(NoItemFoundException ni)
                                    {
                                        ErrorSetter.setError("createError", "No se pudo ingresar el usuario (NIFE)", request);
                                        RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                                        return;
                                    }

                                    ErrorSetter.setError("userCreated", "Se ha creado exitosamente el usuario <b>" + Nombres + " " + Apellidos + "</b>", request);
                                    RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                                    return;
                            }
                            
                            else
                            {
                                Usuario usuario=new Usuario();
                                usuario.setNombres(Nombres);
                                usuario.setApellidos(Apellidos);
                                usuario.setLogin(Login);
                                char [] c=Clave.toCharArray();
                                int id=Integer.parseInt(userid);
                                usuario.setIdUsuario(id);
                                usuario.setPassword(c);
                                try
                                {
                                    admin.modificarUsuario(usuario);
                                }
                                catch (Exception ex)
                                {
                                    ErrorSetter.setError("createError", "No se pudo actualizar el usuario (EX)", request);
                                    RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                                    return;
                                }
                                ErrorSetter.setError("userCreated", "Se ha actualizado exitosamente el usuario <b>" + Nombres + " " + Apellidos + "</b>", request);
                                RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                                return;
                            }
                        

                    }
        
        fecha=request.getParameter("fecha");
        codigo=request.getParameter("codigo");
        pnombres=request.getParameter("pnombres");
        papellidos=request.getParameter("papellidos");
        pidentificacion=request.getParameter("pidentificacion");
        Estudiante=request.getParameter("Estudiante");
        Analista=request.getParameter("Analista");
        Tutor=request.getParameter("Tutor");
        Secretaria=request.getParameter("Secretaria");
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        // Simplemente llamar a doPost y pasarle el request y el response.
        doPost(request, response);
    }    
}
