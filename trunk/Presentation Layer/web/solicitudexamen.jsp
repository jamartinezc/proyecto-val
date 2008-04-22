<%-- 
    Document   : solicitudexamen
    Created on : 21-abr-2008, 18:41:48
    Author     : Liliana
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="java.util.List,java.util.Iterator,com.liceoval.businesslayer.control.AdministradoraDeUsuarios, com.liceoval.businesslayer.entities.Usuario"
	errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Solicitud de Exámen</title>
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

	<%if(currentUser != null)
        {
        %>
                <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Formato de Solicitud</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            
                <%}%>
    </body>
</html>
