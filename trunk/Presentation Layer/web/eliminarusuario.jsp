<%-- 
    Document   : eliminarusuario.jsp
    Created on : 27/04/2008, 10:00:49 PM
    Author     : Sergio
--%>

<%@page
    contentType="text/html"
    pageEncoding="UTF-8"
    import="java.util.LinkedList,
            com.liceoval.businesslayer.control.AdministradoraDeUsuarios, 
            com.liceoval.businesslayer.control.exceptions.NoSeEncuentraElUsuarioException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Administrar Usuarios</title>
<link rel="stylesheet" type="text/css" href="globals/main.css" />
</head>

<body>

    <%
        String locationLinks;
        
        boolean errorOcurred = false;
        String errorString = "";
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a> &gt; <a class=\"nav-bar-link\" href=\"ListaUsuarios.jsp\">Administrar Usuarios</a>";
    %>

    <%@include file="globals/header.jsp" %>
    
	<!-- Tabla principal del diseño-->
    <table style="padding-top:20px" border="0" cellpadding="0" cellspacing="0" align="center">
    	<tr valign="top"><td>
        	
            <!-- Tabla del menú izquierdo-->
            <!--
            <table width="200" cellpadding="0" cellspacing="0">
            	<tr height="30"><td><img src="images/title-left.png" /></td>
                	<td width="180" class="title-center">Hola</td>
                    <td><img src="images/title-right.png" /></td></tr>
                    
				<tr><td class="cont-outer" colspan="3">
                	<table border="0" cellspacing="0" cellpadding="0">
                    	<tr><td class="cont-inner">Este es el cuadro de Log-in, haga log-in aquí por favor, y cuando termine se dará
                         cuenta de que no sirve para nada por que todavía no está implementado :P</td></tr></table>
				</td></tr>
			</table>-->
            
            <!-- Menú o cuadro de log-in -->
            <%@include file="globals/log-in-menu.jsp" %>
            
            <p style="font-size:5pt">&nbsp;</p>
            
            <!-- Libre acceso -->
            <%@include file="globals/libre.jsp" %>
            
            </td><td class="center-padding">
                
                <%@include file="globals/login-warning.jsp" %>
                <%@include file="globals/login-error.jsp" %>
                
                <%
                LinkedList<String> allowedUsers;
                allowedUsers = new LinkedList<String>();
                allowedUsers.add("com.liceoval.businesslayer.entities.SecretariaAcademica");
                %>
                
                <%@include file="globals/bad-login.jsp" %>

                <%
		if(currentUser != null && allowed == true)
                {
                %>
                    <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Eliminar Usuario</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                            <tr><td class="cont-inner">
                                
                            <%
                            String confirm;
                            String nombreUsuario;
                            int userID;
                            
                            try
                            {
                                userID = Integer.parseInt(request.getParameter("userid"));
                                confirm = request.getParameter("confirm");
                                
                                // Antes de permitir la eliminación de un usuario
                                // verificar si se trata de si mismo, y en ese caso
                                // evitar la eliminación.
                                if(currentUser.getIdUsuario() == userID)
                                {
                                %>
                                
                                    <p><table border="0" cellpadding="0" cellspacing="0" width="80%" align="center">
                                        <tr><td class="login-error"><b>¡Error!</b>
                                        <p>Imposible eliminar al usuario especificado. Por seguridad un usuario <b>NO PUEDE</b> eliminarse a si mismo.</p></td></tr>
                                    </table></p>

                                    <p style="text-align:right"><input type="button" onclick="window.open('ListaUsuarios.jsp', '_self')" value="Volver" /></p>
                                
                                <%  
                                }
                                else
                                {
                                    if(confirm != null)                                
                                    {
                                        if(confirm.equals("yes"))
                                        {
                                            // Intentar eliminar el usuario... algun dia...
                                            AdministradoraDeUsuarios.eliminarUsuario(userID);
                            %>
                            
                                            <p><table border="0" cellpadding="0" cellspacing="0" width="80%" align="center">
                                                    <tr><td class="login-warning"><b>¡Enhorabuena!</b>
                                                        <p>El usuario se ha eliminado exitosamente.</p></td></tr>
                                            </table></p>

                                            <p style="text-align:right"><input type="button" onclick="window.open('ListaUsuarios.jsp', '_self')" value="Volver" /></p>
                            
                            <%
                                        }
                                    }

                                    if(confirm == null || confirm.equals("yes") == false)
                                    {
                                        nombreUsuario = request.getParameter("username");
                                        if(nombreUsuario != null)
                                        {
                                            nombreUsuario = nombreUsuario.replace("+", " ");
                            %>

                                            <p><table border="0" cellpadding="0" cellspacing="0" width="80%" align="center">
                                                <tr><td class="login-warning"><b>¡Advertencia!</b>
                                                <p>Está a punto de eliminar el usuario <b><%=nombreUsuario%></b>. ¿Está seguro que desea continuar?</p></td></tr>
                                            </table></p>

                                            <p style="text-align:right"><input type="button" value="Si" onclick="window.open('eliminarusuario.jsp?userid=<%=userID%>&confirm=yes', '_self')"> <input type="button" value="No" onclick="window.open('ListaUsuarios.jsp', '_self')"></p>

                            <%
                                        }
                                        else
                                        {
                                            errorOcurred = true;
                                            errorString="<b>Error:</b><p>La petición se ha malformado, faltan parámetros o no es posible recuperar todos los encabezados.</p>";
                                        }
                                    }
                                }
                            }
                            catch(NumberFormatException nfEx)
                            {
                                errorOcurred = true;
                                errorString = "<b>Error:</b><p>La petición se ha malformado. El ID de usuario especificado es inválido.</p>";
                            }
                            catch(NoSeEncuentraElUsuarioException nifEx)
                            {
                                errorOcurred = true;
                                errorString = "<b>Error</b><p>No se encuentra el usuario especificado en la base de datos.</p>";
                            }
                            %>

                            <%
                            if(errorOcurred == true)
                            {
                            %>

                                <p><table border="0" cellpadding="0" cellspacing="0" width="80%" align="center">
                                    <tr><td class="login-error"><%=errorString%></td></tr>
                                </table></p>
                                
                                <p style="text-align:right"><input type="button" onclick="window.open('ListaUsuarios.jsp', '_self')" value="Volver" /></p>
  
                            <%
                            }
                            %>

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
