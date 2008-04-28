<%-- 
    Document   : ListaUsuarios
    Created on : 20/04/2008, 01:35:52 AM
    Author     : Administrador
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="java.util.LinkedList, java.util.List,java.util.Iterator,com.liceoval.businesslayer.control.AdministradoraDeUsuarios, com.liceoval.businesslayer.entities.Usuario"
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
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a> &gt; <a class=\"nav-bar-link\" href=\"ListaUsuarios.jsp\">Administrar Usuarios</a>";
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
                            <td class="title-center" width="100%">Listado de Usuarios</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">
                                    
                                    <%
                                        if(session.getAttribute("userCreated") != null)
                                        {
                                    %>

                                        <p><center><table border="0" cellpadding="0" cellspacing="0" width="80%">
                                            <tr><td class="login-warning"><%=session.getAttribute("userCreated")%></td></tr>
                                        </table></center></p>

                                    <%
                                        session.removeAttribute("userCreated");
                                        }
                                    %>
                                    <br>
                                    <p><b><center>ESTA SECCIÓN LE PERMITIRÁ CREAR, MODIFICAR O ELIMINAR UN USUARIO DEL SISTEMA</center></b></p>
                                    <br>
                                    <ul>
                                        <li><b>CREAR USUARIOS:</b> Pulse el botón "<b><u>Crear Usuario</u></b>" y digite los campos cuidadosamente.</li>
                                        <li><b>MODIFICAR USUARIOS:</b> Pulse el nombre del Usuario al que desea hacerle modificaciones y actualice los datos necesarios.</li>
                                        <li><b>ELIMINAR USUARIOS:</b> Pulse el texto "<u><b>Eliminar Usuario</b></u>" situado en frente del Usuario que desea eliminar y confirme su decisión.</li>
                                     
                                    </ul>
                                <br>
                                        <p style="text-align:center"><input type="button" onclick="window.open('PerfilUsuario.jsp?action=new', '_self')" value="Crear Usuario" /></p>
                                        <p><table border="0" cellpadding="3" cellspacing="2">

                                <%
        AdministradoraDeUsuarios admin=new AdministradoraDeUsuarios();
        List lista;
        lista=admin.solicitarListaDeUsuarios();
        Iterator it;
        Usuario user;
        it=lista.iterator();
        String nombreUsuario;
        %>
        <ol>
        <%
        while(it.hasNext())
        {
            user=(Usuario)it.next();
            nombreUsuario = user.getNombres() + " " + user.getApellidos();
            nombreUsuario = nombreUsuario.replace(" ", "+");
        %>

        <tr><td width="100%"><a href="PerfilUsuario.jsp?action=edit&userid=<%=user.getIdUsuario()%>"><%=user.getNombres() + " " + user.getApellidos()%></a></td><td nowrap="nowrap"><a href="eliminarusuario.jsp?userid=<%=user.getIdUsuario()%>&username=<%=nombreUsuario%>">Eliminar Usuario</a></tr>

        <%
        }
        %>
    </table></p>
                                         

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
