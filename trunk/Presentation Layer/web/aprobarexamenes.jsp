<%-- 
    Document   : estudiantestaller
    Created on : 20/04/2008, 01:35:52 AM
    Author     : LiliSer
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="com.liceoval.businesslayer.control.registro.ControladoraDeRegistro,
                java.util.LinkedList,
                java.util.Enumeration"
	errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Consultar Registro</title>
<link rel="stylesheet" type="text/css" href="globals/main.css" />
</head>

<body>

    <%    
        int examenId;
        String parameter;
        String locationLinks;
        boolean errorOcurred;
        Enumeration parameterNames;
        LinkedList<Integer> aprobeList;
        LinkedList<Integer> rejectList;
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a> - <a class=\"nav-bar-link\" href=\"listaexemenessolicitados.jsp\">Lista de Exámenes Solicitados</a>";
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
		allowedUsers.add("com.liceoval.businesslayer.entities.Tutor");
                %>
                
                <%@include file="globals/bad-login.jsp" %>

                <%
                if(currentUser != null && allowed == true)
                {
                %>
                    <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Examenes pendientes por aprobar</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">
                                    
                                    <%
                                        parameterNames = request.getParameterNames();
                                        aprobeList = new LinkedList<Integer>();
                                        rejectList = new LinkedList<Integer>();
                                        errorOcurred = false;
                                        
                                        while(parameterNames.hasMoreElements())
                                        {
                                            parameter = (String)parameterNames.nextElement();
                                            try
                                            {
                                                examenId = Integer.parseInt(parameter);
                                                if(request.getParameter(parameter) != null)
                                                {
                                                    if(request.getParameter(parameter).equals("A"))
                                                    {
                                                        aprobeList.add(new Integer(examenId));
                                                    }
                                                    else if(request.getParameter(parameter).equals("R"))
                                                    {
                                                        rejectList.add(new Integer(examenId));
                                                    }
                                                }
                                                
                                                errorOcurred = !(ControladoraDeRegistro.aprobarRechazarExamenes(aprobeList, rejectList));
                                            }
                                            catch(NumberFormatException nfEx) { errorOcurred = true; }
                                        }
                                    %>
                                    
                                    <%
                                        if(!errorOcurred)
                                        {
                                        %>
                                        
                                            <table border = "0" cellpadding="0" cellspacing="0" width = "95%" align="center">
                                                <tr><td class="login-warning"><strong>Mensaje</strong>
                                                    <p>Los cambios han sido aplicados éxitosamente sobre los exámenes selccionados.</p></td></tr></table>
                                        
                                        <%  
                                        }
                                        else
                                        {
                                        %>
                                        
                                            <table border = "0" cellpadding="0" cellspacing="0" width = "95%" align="center">
                                                <tr><td class="login-warning"><strong>Mensaje</strong>
                                                    <p>Uno o más de los exámenes seleccionados no pudieron ser actualizados. Por favor verifique la <a href="listaexamenessolicitados.jsp">Lista de exámenes pendientes por aprobar</a> y corrobore que la selección es la correcta.</p></td></tr></table>
                                        
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
        
            <%
                // Texto de ayuda para está página
                StringBuffer rightPanelText;
                rightPanelText = new StringBuffer();
                
                if(currentUser != null)
                {                
                    rightPanelText.append("<p style=\"font-weight:bold;text-decoration:underline\">Exámenes por aprobar</p>");

                    rightPanelText.append("<p>Esta página muestra los exámenes que los estudiantes a su cargo han solicitado. Usted puede elegir aprobar o rechazar un exámen de acuerdo con el nivel de conocimientos del estudiante en cuestión.</p>");
                    rightPanelText.append("<p>Los exámenes marcados como <span style=\"color:#ff0000;font-weight:bold\">Vencidos</span> son exámenes que estaban programados para una fecha anterior o para el día de hoy. Estos exámenes serán reprogramados para el siguiente día si son aprobados.</p>");
                }
                else
                {
                    rightPanelText.append("<p style=\"font-weight:bold;text-decoration:underline\">Acceso no autorizado</p>");
                    rightPanelText.append("<p>Usted ha ingresado a un área restringida, debe iniciar sesión en el sistema para poder ver el contenido de está página. Use el panel de la izquierda para iniciar sesión con su nombre de usuario y contraseña.</p>");
                }

                rightContent = rightPanelText.toString();
            %>
        
            <!-- Menú derecho -->
            <%@include file="globals/right-menu.jsp" %>
        
        </td></tr>
	</table>

</body>
</html>
