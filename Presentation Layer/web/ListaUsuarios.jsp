<%-- 
    Document   : ListaUsuarios
    Created on : 20/04/2008, 01:35:52 AM
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
import="java.util.List,java.util.Iterator,com.liceoval.businesslayer.control.AdministradoraDeUsuarios, com.liceoval.businesslayer.entities.Usuario"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Usuarios</title>
    </head>
    <body>
        
        <%
        AdministradoraDeUsuarios admin=new AdministradoraDeUsuarios();
        List lista;
        lista=admin.solicitarListaDeUsuarios();
        int i,n;
        Iterator it;
        Usuario user;
        it=lista.iterator();
        n=lista.size();
        %>
        <!--ol--!>
        <%
        while(it.hasNext())
        {
            user=(Usuario)it.next();
        %>
        <!--<li><!--%=user.getNombres() + user.getApellidos()%></li>-->
        <%
        }
        %>
    <!--/ol-->>
    </body>
</html>
