<%-- 
    Document   : confirmacionSolicitud
    Created on : 27-abr-2008, 21:51:13
    Author     : jaguar
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="java.util.List,
         java.util.Iterator,
         Errores. NoItemFoundException,
         com. liceoval. businesslayer. entities. Examen ,
         Errores. PosibleDuplicationException,
         com. liceoval. businesslayer. entities. Estudiante,
         com.liceoval.businesslayer.control.AdministradoraDeUsuarios,
         com.liceoval.businesslayer.entities.Usuario,
         com. liceoval. businesslayer. control. registro. exceptions. EstudianteNoPuedeRegistrarMasExamenesException,
         com. liceoval. businesslayer. control. registro. exceptions. InsersionDeExamenException,
         com. liceoval. businesslayer. control. registro. exceptions. NoExisteAnalistaParaMateriaException,
         com. liceoval. businesslayer. control. registro. exceptions. RegistroException,
         com. liceoval. businesslayer. control. registro. exceptions. RegistroNoEncontradoException,
         com. liceoval. businesslayer. control. registro. exceptions. RegistroNoExisteYNoPuedeSerCreadoException,
         Errores. NoPresentableException,
         Errores. NoItemFoundException,
         Errores. PosibleDuplicationException, 
         Errores. UltimoTemaException,
         com. liceoval. businesslayer. control. registro. exceptions. ZonaHorariaIncorrectaException,
         com. liceoval. businesslayer. control. AdministradoraSolicitudesExamen"
	errorPage="" %>
         <!--,com.liceoval.businesslayer.control.registro.exceptions.InvalidProcedureCallOrArgumentException",com.liceoval.businesslayer.control.registro.exceptions.RegistroNoExisteYNoPuedeSerCreadoException,com.liceoval.businesslayer.control.registro.exceptions.NoExisteAnalistaParaMateriaException,com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoPuedeRegistrarMasExamenesException,com.liceoval.businesslayer.control.registro.exceptions.ZonaHorariaIncorrectaException,com.liceoval.businesslayer.control.registro.exceptions.InsersionDeExamenException"-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Confirmación de Solicitud de Exámen</title>
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
                 <%if(currentUser != null){%>
                <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Informacion del examen</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                            <tr><td class="cont-inner">
                                
                                <%
                                    try{
                                    int codigoMateria = Integer.parseInt((String)(request.getParameter("Materia")));
                                    Estudiante estudiante = (Estudiante)session.getAttribute("currentUser");
                                    Examen examen = AdministradoraSolicitudesExamen.getSiguienteExamen(estudiante.getCodigo(), codigoMateria);
                                 %>
                                <p style="text-align:center"><b>¿Está seguro que desea solicitar el siguiente examen?</b></p>
                                    Materia: <%=(request.getParameter("NombreMateria"))%><br>
                                 <p>Código del examen: <%=examen.getCodigo()%></p>
                                 <p>Tema del examen: <%=examen.getTema()%></p>-->
                                <form name="Examen" action="rtasolicitud.jsp" method="POST" enctype="application/x-www-form-urlencoded">
                                <input type="hidden" name="NombreMateria" value="<%=(request.getParameter("NombreMateria"))%>" >
                                <input type="hidden" name="materia" value="<%=request.getParameter("Materia")%>" />
                                <input type="hidden" name="codigo" value="<%=examen.getCodigo()%>" />
                                <input type="hidden" name="tema" value="<%=examen.getTema()%>" />-->
                                
                                <p style="text-align:center"><input type="submit" value="Aceptar" />
                                <%}catch(NoPresentableException e){
                                    %>
                                    <p><b>¡No puede presentar exámenes de esta materia!</b><br>
                                    <br>
                                    <p>Verifique que no tiene Nota Pendiente, Nota Examinadero o un examen pendiente por presentar.</p><br>
                                    <%
                                }catch(NoItemFoundException e){/* TODO manejar la exception de ser necesario*/}%>
                                <input type="button" value="Cancelar" onclick="history.go(-1);" /></p>
                                </form>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <%}%>
        <td>
        	<!-- Menú derecho -->
                 <%rightContent = "Esta página le pide la confirmación de la solicitud que acaba de realizar, si está de acuerdo presione aceptar, de lo contrario presione cancelar y retornará a la página de solicitudes.";%>
            <%@include file="globals/right-menu.jsp"%>
        </td>
    </body>
</html>