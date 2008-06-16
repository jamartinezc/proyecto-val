<%-- 
    Document   : estudiantestaller
    Created on : 20/04/2008, 01:35:52 AM
    Author     : LiliSer
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="com.liceoval.businesslayer.control.rcp.exceptions.TutorNoEncontradoException,
                com.liceoval.businesslayer.control.rcp.RCPRegistros,
                com.liceoval.businesslayer.entities.Estado,
                com.liceoval.businesslayer.entities.Estudiante,
                com.liceoval.businesslayer.entities.ExamenSolicitado,
                com.liceoval.businesslayer.entities.Registro,
                com.liceoval.businesslayer.entities.Tutor,
                com.liceoval.businesslayer.entities.wrappers.TallerWrapper,
                java.util.Calendar,
                java.util.Date,
                java.util.Iterator,
                java.util.LinkedList,
                java.util.List"
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
        String locationLinks;
                
        Iterator registrosIterator;
        Iterator examenesSolicitadosIterator;
        Iterator estudiantesIterator;
        Iterator talleresIterator;
        
        ExamenSolicitado examenSolicitado;
        List<TallerWrapper> talleres;
        Estudiante estudiante;
        TallerWrapper taller;
        Registro registro;
        Tutor tutor;
        
        Calendar cal;
        boolean pastDue;
        boolean addStudent;
        boolean addAsignment;
        boolean studentsAdded;
        StringBuffer studentTable;
        StringBuffer asignmentTable;
        
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

                                    <p style="font-weight:bold;text-transform:uppercase;text-align:center">Esta página muestra los exámenes solicitados pendientes por aprobar.</p>

                                    <p>La siguiente lista muestra los exámenes que han solicitado los estudiantes que usted tiene a su cargo. Usted puede decidir aprobar o rechazar las solicitudes de exámenes marcando la casilla correspondiente. Cuando termine haga clic en el botón Aceptar.</p>
                                    
                                    <%
                                    tutor = (Tutor)currentUser;
                                    try
                                    {
                                        talleres = RCPRegistros.getTalleresYEstudiantes(tutor.getIdTutor());
                                        talleresIterator = talleres.iterator();
                                        while(talleresIterator.hasNext())
                                        {
                                            taller = (TallerWrapper)talleresIterator.next();
                                            %>
                                            
                                            <p style="font-weight:bold">Taller: <%=taller.getTaller().getIdTaller()%></p>
                                            
                                            <%
                                            if(taller.getEstudiantes().size() == 0)
                                            {
                                            %>
                                            
                                                <p style="text-indent:20px">No hay estudiantes registrados para este taller</p>
                                            
                                            <%
                                            }
                                            else
                                            {
                                                estudiantesIterator = taller.getEstudiantes().iterator();
                                                studentsAdded = false;
                                                %>
                                                
                                                <form action="aprobarexamenes.jsp" method="post" enctype="application/x-www-form-urlencoded">
        	                                        <table border="0" cellpadding="4" cellspacing="2">
                                                
                                                <%
                                                while(estudiantesIterator.hasNext())
                                                {                                                    
                                                    estudiante = (Estudiante)estudiantesIterator.next();
                                                    studentTable = new StringBuffer();
                                                    addStudent = false;
                                                    
                                                    studentTable.append("<tr><td colspan=\"2\" style=\"font-weight:bold\">");
                                                    studentTable.append(estudiante.getNombres());
                                                    studentTable.append(" ");
                                                    studentTable.append(estudiante.getApellidos());
                                                    studentTable.append("</td></tr>");
                                                    
                                                    if(estudiante.getRegistros().size() > 0)
                                                    {
                                                        registrosIterator = estudiante.getRegistros().iterator();
                                                        while(registrosIterator.hasNext())
                                                        {
                                                            registro = (Registro)registrosIterator.next();
                                                            asignmentTable = new StringBuffer();
                                                            addAsignment = false;
                                                            
                                                            asignmentTable.append("<tr><td colspan=\"2\" style=\"padding-left:20px;font-weight:bold\">");
                                                            asignmentTable.append(registro.getMateria().getNombre());
                                                            asignmentTable.append("</td></tr>");
                                                            
                                                            if(registro.getExamenesSolicitados().size() > 0)
                                                            {
                                                                examenesSolicitadosIterator = registro.getExamenesSolicitados().iterator();
                                                                while(examenesSolicitadosIterator.hasNext())
                                                                {
                                                                    examenSolicitado = (ExamenSolicitado)examenesSolicitadosIterator.next();
                                                                    pastDue = false;
                                                                    
                                                                    Date today = new Date();
                                                                    if(examenSolicitado.getFecha().compareTo(today) <= 0)
                                                                        pastDue = true;
                                                                    
                                                                    if(examenSolicitado.getEstado().equals(Estado.PENDIENTE_APROBAR))
                                                                    {
                                                                        asignmentTable.append("<tr valign=\"center\"><td style=\"padding-left:35px\">");
                                                                        
                                                                        if(pastDue)
                                                                            asignmentTable.append("<span style=\"color:#ff0000;font-weight:bold\">¡Vencido!</span> ");
                                                                        
                                                                        cal = Calendar.getInstance();
                                                                        cal.setTime(examenSolicitado.getFecha());
                                                                        
                                                                        asignmentTable.append(examenSolicitado.getExamen().getTema());
                                                                        asignmentTable.append("<br>Programado para: ");
                                                                        asignmentTable.append(cal.get(Calendar.DAY_OF_MONTH));
                                                                        asignmentTable.append("/");
                                                                        asignmentTable.append(cal.get(Calendar.MONTH) + 1);
                                                                        asignmentTable.append("/");
                                                                        asignmentTable.append(cal.get(Calendar.YEAR));
                                                                        asignmentTable.append("</td><td><input type=\"radio\" value=\"A\" name=\"");
                                                                        asignmentTable.append(examenSolicitado.getIdExamenSolicitado());
                                                                        asignmentTable.append("\"> Aprobar <input type=\"radio\" value=\"R\" name=\"");
                                                                        asignmentTable.append(examenSolicitado.getIdExamenSolicitado());
                                                                        asignmentTable.append("\"> Rechazar</td></tr>");
                                                                        
                                                                        addAsignment = true;
                                                                        
                                                                    }
                                                                }
                                                            }
                                                            
                                                            if(addAsignment == true)
                                                            {
                                                                studentTable.append(asignmentTable);
                                                                addStudent = true;
                                                            }
                                                        }
                                                    }
                                                    
                                                    if(addStudent)
                                                        out.println(studentTable);

                                                    studentsAdded = studentsAdded | addStudent;
                                                }
                                                
                                                if(studentsAdded)
                                                {
                                                %>
                                                            <tr><td style="text-align:center" colspan="2"><input type="submit" value="Aceptar" /></td></tr>
                                                <%
                                                }
                                                %>
							</table>
                                                </form>
                                                
                                                <%
                                            }
                                        }
                                    }
                                    catch(TutorNoEncontradoException tneEx)
                                    {
                                    %>
                                    
                                        <table border = "0" cellpadding="0" cellspacing="0" width = "95%" align="center">
                                            <tr><td class="login-warning"><strong>Mensaje:</strong>
                                                <p>Usted no tiene talleres a su cargo. Es posible que aún no hayan sido asignados.</p>
                                                <p>Si usted cree que debe tener talleres a su cargo por favor contacte al administrador del sistema y notifíquele del problema.</td></tr></table>
                                    
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
