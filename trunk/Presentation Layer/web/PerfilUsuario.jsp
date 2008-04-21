<%-- 
    Document   : ListaUsuarios
    Created on : 20/04/2008, 01:35:52 AM
    Author     : Administrador
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="java.util.List,java.util.Iterator,com.liceoval.businesslayer.control.AdministradoraDeUsuarios, com.liceoval.businesslayer.entities.Usuario"
	errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Lista de Usuarios</title>
<link rel="stylesheet" type="text/css" href="globals/main.css" />
</head>

<body>

    <%
        String locationLinks;
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a>";
    %>

    <%@include file="globals/header.jsp" %>
    
	<!-- Tabla principal del diseño-->
    <table style="padding-top:20px" border="0" cellpadding="0" cellspacing="0" align="center">
    	<tr valign="top"><td>
            
            <!-- Menú o cuadro de log-in -->
            <%@include file="globals/log-in-menu.jsp" %>
            
            <p style="font-size:5pt">&nbsp;</p>
            
            <!-- Libre acceso -->
            <%@include file="globals/libre.jsp" %>
            
            </td><td class="center-padding">
                
                <%@include file="globals/login-warning.jsp" %>
                <%@include file="globals/login-error.jsp" %>

                <%
                if(currentUser != null)
                {
                %>
                    <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Listado de Usuarios</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">
                                
                                <form name="GuardarUsuario" action="GuardarUsuario" method="POST">
                                <b>Todos los campos son <u>OBLIGATORIOS</u></b><br /><br />
                                    
                                    <table>
                                    <tr><td>Nombre:</td><td><input type="text" name="Nombre" value="" size="30" /></td></tr>
                                    <tr><td>Apellidos:</td><td><input type="text" name="Apellidos" value="" size="30" /></td><td><input type="submit" value="Agregar" name="Enviar" /></td></tr>
                                    <tr><td>Login:</td><td><input type="text" name="Login" value="" size="20" /></td></tr>
                                    <tr><td>Clave:</td><td><input type="password" name="Clave" value="" size="20" /></td><td><input type="reset" value="Limpiar" name="Limpiar" /></td></tr>
                                    <tr><td>Confirmar Clave:</td><td><input type="password" name="Cclave" value="" size="20" /></td></tr>
                                    
                                    </table>
                                   
                                </form>

                                </td></tr></table>
                        </td></tr>
                    </table>
                <%
				}
				%>
                
            </td><td>
        
        	<!-- Menú derecho -->
            <%@include file="globals/right-menu.jsp" %>
        
        </td></tr>
	</table>

</body>
</html>
