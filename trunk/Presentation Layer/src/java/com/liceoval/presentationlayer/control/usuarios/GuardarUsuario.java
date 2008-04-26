

package com.liceoval.presentationlayer.control.usuarios;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liceoval.businesslayer.entities.Usuario;

import com.liceoval.presentationlayer.control.util.RequestForwarder;
import com.liceoval.presentationlayer.control.util.ErrorSetter;

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
        
        String Nombres,Apellidos,Login,Clave,Cclave,fecha,codigo,pnombres,papellidos,pidentificacion,pcorreo,Estudiante,Analista,Secretaria,Tutor;
        Nombres=request.getParameter("Nombres");
        Apellidos=request.getParameter("Apellidos");
        Login=request.getParameter("Login");
        Clave=request.getParameter("Clave");
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
