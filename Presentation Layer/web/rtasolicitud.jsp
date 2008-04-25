 <%-- 
    Document   : rtasolicitud
    Created on : 23/04/2008, 01:57:44 PM
    Author     : Liliana
 --%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="java.util.List,java.util.Iterator, Errores. NoItemFoundException, Errores. PosibleDuplicationException, com. liceoval. businesslayer. entities. Estudiante,com.liceoval.businesslayer.control.AdministradoraDeUsuarios, com.liceoval.businesslayer.entities.Usuario, com. liceoval. businesslayer. control. registro. exceptions. EstudianteNoPuedeRegistrarMasExamenesException, com.liceoval.businesslayer.entities.Usuario, com. liceoval. businesslayer. control. registro. exceptions. InsersionDeExamenException, com.liceoval.businesslayer.entities.Usuario, com. liceoval. businesslayer. control. registro. exceptions. NoExisteAnalistaParaMateriaException, com.liceoval.businesslayer.entities.Usuario, com. liceoval. businesslayer. control. registro. exceptions. RegistroException, com.liceoval.businesslayer.entities.Usuario, com. liceoval. businesslayer. control. registro. exceptions. RegistroNoEncontradoException, com.liceoval.businesslayer.entities.Usuario, com. liceoval. businesslayer. control. registro. exceptions. RegistroNoExisteYNoPuedeSerCreadoException, com.liceoval.businesslayer.entities.Usuario, com. liceoval. businesslayer. control. registro. exceptions. ZonaHorariaIncorrectaException, com. liceoval. businesslayer. control. AdministradoraSolicitudesExamen"
	errorPage="" %>
         <!--,com.liceoval.businesslayer.control.registro.exceptions.InvalidProcedureCallOrArgumentException",com.liceoval.businesslayer.control.registro.exceptions.RegistroNoExisteYNoPuedeSerCreadoException,com.liceoval.businesslayer.control.registro.exceptions.NoExisteAnalistaParaMateriaException,com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoPuedeRegistrarMasExamenesException,com.liceoval.businesslayer.control.registro.exceptions.ZonaHorariaIncorrectaException,com.liceoval.businesslayer.control.registro.exceptions.InsersionDeExamenException"-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
                
                   <%if(currentUser != null){
                try{%> 
                <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Información</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">
                                <ol>
                                <p>
                          <%
                          
                            AdministradoraSolicitudesExamen admin = new AdministradoraSolicitudesExamen();
                            String materia = (String)(request.getParameter("Materia"));
                            %><p><%= request.getParameterNames().nextElement() %></p><%
                            int codigoMateria = Integer.parseInt(materia);
                            Estudiante estudiante = (Estudiante)currentUser;
                            //mchernandezo mlkiop99     id 290
                            admin.SolicitarExamen(estudiante, 14, codigoMateria);
                          }catch(NoItemFoundException e1){
                                String mensajeError = e1.getMessage();
                                %>
                                <%@include file="globals/error-solicitud-increable.jsp" %>
                          <%}catch(NumberFormatException e2){
                              String mensajeError = e2.getMessage();
                                %>
                                <%@include file="globals/error-solicitud-increable.jsp" %>
                                <%}%>
                                </p>
                                
                                </ol>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        <%}%>
        <td>
        
        	<!-- Menú derecho -->
            <%@include file="globals/right-menu.jsp" %>
        
        </td>
     </table>
   </body>
</html>
