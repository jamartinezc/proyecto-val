<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="com.liceoval.businesslayer.entities.Usuario"
	errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Bienvenido</title>
<link rel="stylesheet" type="text/css" href="globals/main.css" />
</head>

<body>

    <%
        String locationLinks;
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a>";
    %>

    <%@include file="globals/header.jsp" %>
    
	<!-- Tabla principal del diseño-->
    <table style="padding-top:20px" border="0" cellpadding="0" cellspacing="0" width="90%" align="center">
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
                
                <%@include file="globals/login-error.jsp" %>
                
            </td><td>
        
        	<!-- Menú derecho -->
            <%@include file="globals/right-menu.jsp" %>
        
        </td></tr>
	</table>

</body>
</html>
