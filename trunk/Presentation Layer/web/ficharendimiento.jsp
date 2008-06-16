<%-- 
    Document   : ConsultarRegistro
    Created on : 20/04/2008, 01:35:52 AM
    Author     : LiliSer
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="com.liceoval.businesslayer.control.AdministradoraDeUsuarios,
                com.liceoval.businesslayer.control.rcp.exceptions.EstudianteNoEncontradoException,
                com.liceoval.businesslayer.control.rcp.RCPRegistros,
                com.liceoval.businesslayer.entities.Estudiante,
                com.liceoval.businesslayer.entities.Materia,
                com.liceoval.businesslayer.entities.Tutor,
                java.util.Calendar,
                java.util.Iterator,
                java.util.LinkedList"
	errorPage="" %>
        
<%@taglib uri="WEB-INF/cewolf.tld" prefix='cewolf' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Consultar Ficha de Rendimiento</title>
<link rel="stylesheet" type="text/css" href="globals/main.css" />
</head>

<body>

    <%
        int idTutor;
        Calendar cal;
        Materia materia;
        StringBuffer fechaInicio;
        int idEstudiante = 0;
        int totalEvaluaciones;
        String locationLinks;
        String studentString;
        Estudiante estudiante;
        Iterator<Materia> materiasIterator;
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a> - <a class=\"nav-bar-link\" href=\"ficharendimiento.jsp\">Ficha de Rendimiento</a>";
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
                allowedUsers.add("com.liceoval.businesslayer.entities.Estudiante");
                allowedUsers.add("com.liceoval.businesslayer.entities.Tutor");
                %>
                
                <%@include file="globals/bad-login.jsp" %>

                <%
                if(currentUser != null && allowed == true)
                {
                %>
                    <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Ficha de Rendimiento</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">
                                    
                                    <p style="text-align:center"><b>FICHA DE RENDIMIENTO</b></p>

                                    <%                               
                                    if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Estudiante"))
                                    {
                                        idEstudiante = ((Estudiante)currentUser).getCodigo();
                                    }

                                    if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Tutor"))
                                    {
                                        studentString = request.getParameter("studentId");
                                        if(studentString != null && !studentString.equals(""))
                                        {
                                            try { idEstudiante = Integer.parseInt(studentString); }
                                            catch(NumberFormatException nfEx) { idEstudiante = 0; }
                                        }
                                        else idEstudiante = 0;

                                        // Recuperar la Id del tutor y confirmar que
                                        // el estudiante pertenece al taller que el
                                        // tutor tiene a su cargo

                                        idTutor = ((Tutor)currentUser).getIdTutor();
                                        if(!(AdministradoraDeUsuarios.confirmarEstudianteTallerTutor(idEstudiante, idTutor)))
                                        {
                                            %>

                                            <table border = "0" cellpadding="0" cellspacing="0" width = "95%" align="center">
                                                <tr><td class="login-warning"><strong>¡Hay un problema!</strong>
                                                    <p>El estudiante especificado no pertenece al taller del cual es usted tutor. Usted únicamente puede ver los registros de sus estudiantes.</p></td></tr></table>

                                            <p style="text-align:right"><input type="button" onclick="window.open('estudiantestaller.jsp', '_self')" value="Ver lista de estudiantes del taller" /></p>

                                            <%

                                            idEstudiante = 0;
                                        }
                                    }
                                    
                                    if(idEstudiante != 0)
                                    {
                                        try
                                        {
                                            estudiante = RCPRegistros.getEstudiante(idEstudiante);

                                            cal = Calendar.getInstance();
                                            cal.setTime(estudiante.getFechaInicioGrado());

                                            fechaInicio = new StringBuffer();
                                            fechaInicio.append(cal.get(Calendar.DAY_OF_MONTH));
                                            fechaInicio.append("/");
                                            fechaInicio.append(cal.get(Calendar.MONTH) +1 );
                                            fechaInicio.append("/");
                                            fechaInicio.append(cal.get(Calendar.YEAR));
                                            
                                            materiasIterator = estudiante.getGrado().getMaterias().iterator();
                                            totalEvaluaciones = 0;
                                            
                                            while(materiasIterator.hasNext())
                                            {
                                                materia = materiasIterator.next();
                                                totalEvaluaciones = totalEvaluaciones + materia.getExamenes().size();
                                            }
                                            
                                            %>
                                            
                                            	<table border="1" cellpadding="4" cellspacing="2" bordercolor="#000000" style="border-collapse:collapse" align="center">
                                                    <tr><td><strong>Apellidos:</strong></td><td colspan="2"><%=estudiante.getApellidos()%></td><td><strong>Nombres:</strong></td><td><%=estudiante.getNombres()%></td><td><strong>Código</strong></td><td><%=estudiante.getCodigo()%></td></tr>
                                                    <tr><td><strong>Grado:</strong></td><td><%=estudiante.getGrado().getNombre()%></td><td><strong>Total evaluaciones del grado:</strong></td><td><%=totalEvaluaciones%></td><td><strong>Fecha de inicio:</strong></td><td colspan="2"><%=fechaInicio.toString()%></td></tr>
                                                    <tr><td colspan="7">
                                                    
                                                        <jsp:useBean id="graficaRendimiento" class="com.liceoval.presentationlayer.control.rendimiento.GeneradoraGraficoRendimiento"/>
                                                        <jsp:useBean id="postProcesador" class="com.liceoval.presentationlayer.control.rendimiento.PostProcesadoraGraficoRendimiento"/>
                                                        <cewolf:chart 
                                                            id="grafica" 
                                                            title="Gráfica de Rendimiento" 
                                                            type="xy" 
                                                            xaxislabel="Mes" 
                                                            yaxislabel="Evaluaciones Ganadas / Planeadas">
                                                            <cewolf:chartpostprocessor id="postProcesador"></cewolf:chartpostprocessor>
                                                            <cewolf:data>
                                                                <cewolf:producer id="graficaRendimiento">
                                                                    <cewolf:param name="idEstudiante" value='<%=new Integer(idEstudiante)%>' />
                                                                </cewolf:producer>
                                                            </cewolf:data>
                                                        </cewolf:chart>

                                                        <p style="text-align:center"><cewolf:img chartid="grafica" renderer="cewolf" width="500" height="400"/></p>
                                                        
                                                        <p style="text-align:center">Evaluaciones Ganadas <span style="color:#ff0000;font-weight:bold">(Rojo)</span> Vs. Planeadas <strong>(Negro)</strong></p>
                                                    
                                                    </td></tr>
                                                </table>
                                            
                                            <%
                                        }
                                        catch(EstudianteNoEncontradoException eneEx)
                                        {
                                        %>
                                            <table border = "0" cellpadding="0" cellspacing="0" width = "95%" align="center">
                                                <tr><td class="login-error"><strong>Error en la consulta</strong>
                                                    <p>La base de datos no puede localizar la información del estudiante. Por favor reporte este problema al administrador.</p></td></tr></table>
                                        <%
                                        }
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
                    rightPanelText.append("<p style=\"font-weight:bold;text-decoration:underline\">Ficha de Rendimiento</p>");

                    if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Estudiante"))
                    {
                        rightPanelText.append("<p>Esta página le permite visualizar su informe de rendimiento.</p>");
                        rightPanelText.append("<p>Su informe de rendimiento muestra una gráfica con las evaluaciones Planeadas (en negro) Vs. las evaluaciones Ganadas (en rojo).</p>");
                    }
                    else if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Tutor"))
                    {
                        rightPanelText.append("<p>Esta página le permite visualizar el informe de rendimiento de uno de sus estudiantes.</p>");
                        rightPanelText.append("<p>El informe de rendimiento muestra una gráfica con las evaluaciones Planeadas (en negro) Vs. las evaluaciones Ganadas (en rojo) del estudiante..</p>");
                    }
                    else
                    {
                        rightPanelText.append("<p style=\"color:#ff0000\">¡Usuario incorrecto!</p>");
                        rightPanelText.append("<p>Su rol actual no le permite visualizar esta página. Debe <a href=\"change-role.jsp\">Cambiar de rol</a> para hacer uso de este servicio.</p>");
                    }
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
