<%-- 
    Document   : ConsultarRegistro
    Created on : 20/04/2008, 01:35:52 AM
    Author     : LiliSer
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="java.util.Iterator,java.util.List,
            java.util.LinkedList,java.util.Iterator,
            java.util.Date,            
            java.util.Calendar,
            java.util.TimeZone,
            java.util.SimpleTimeZone,
            java.util.GregorianCalendar,
            com.liceoval.businesslayer.entities.Materia,
            com.liceoval.businesslayer.entities.Registro,
            com.liceoval.businesslayer.entities.Estudiante,
            com.liceoval.businesslayer.entities.ExamenSolicitado,
            com.liceoval.businesslayer.entities.Tutor,
            com.liceoval.businesslayer.control.AdministradoraDeUsuarios,
            com.liceoval.businesslayer.control.rcp.RCPRegistros,
            com.liceoval.businesslayer.control.registro.ControladoraDeRegistro,
            com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoEncontradoException"
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
        int tutorId;
        int matId = 0;
        Materia materia;
        String recordId;
        String matString;
        Registro registro;
        int studentId = 0;
        String studentString;
        Estudiante estudiante;
        int printedRecords = 0;
        List<Registro> registros;
        Iterator examenesIterator;
        Iterator registrosIterator;
        List<ExamenSolicitado> examenes;
        ExamenSolicitado examenSolicitado;
        
        Calendar cal;
        String[] ids;
        TimeZone tz;
        Date fechaExamen;
        String fechaFormateada = null;    
    
        String locationLinks;
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a> - <a class=\"nav-bar-link\" href=\"registro.jsp\">Consulta de Registro</a>";
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
                            <td class="title-center" width="100%">Consultar Registro</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">

                                <%                               
                                if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Estudiante"))
                                {
                                %>
                                    <p style="text-align:center"><b>ESTA PÁGINA LE PERMITIRÁ CONSULTAR SUS REGISTROS</b></p>
                                    
                                <%
                                    estudiante = (Estudiante)currentUser;
                                
                                    try
                                    {
                                        registros = ControladoraDeRegistro.getRegistros(estudiante.getCodigo(), true);
                                        registrosIterator = registros.iterator();

                                        matString = request.getParameter("matId");
                                        if(!(matString == null || matString.equals("")))
                                        {
                                            try { matId = Integer.parseInt(matString); }
                                            catch(NumberFormatException nfeEx) { matId = 0; }
                                        }

                                        if(matId == 0)
                                        {
                                        %>
                                        
                                            <p>Por favor seleccione la materia para al cual desea ver el registro:</p>
                                            
                                            <ul style="list-style-type:none">
                                        
                                        <%
                                        }
                                        
                                        while(registrosIterator.hasNext())
                                        {
                                            registro = (Registro)registrosIterator.next();
                                            materia = registro.getMateria();
                                            if(matId != 0)
                                            {
                                                if(materia.getCodigo() == matId)
                                                {   
                                                %>
                                                
                                                    <p style="text-transform:uppercase"><strong><%=materia.getCodigo()%> - <%=materia.getNombre()%></strong></p>
                                                
                                                <%
                                                    examenes = registro.getExamenesSolicitados();
                                                    if(examenes.size() > 0)
                                                    {
                                                    %>
                                                    
                                                    	<p><strong>Exámenes en el registro:</strong></p>
                                                    
                                                        <table border="1" cellpadding="3" cellspacing="2" style="border-collapse:collapse">
                                                            <thead><tr><th>Código</th><th>Tema</th><th>Fecha</th><th>Estado</th><th>Nota</th></tr></thead>
                                                            <tbody>
                                                            <%
                                                                examenesIterator = examenes.iterator();
                                                                while(examenesIterator.hasNext())
                                                                {
                                                                    examenSolicitado = (ExamenSolicitado)examenesIterator.next();
                                                                    fechaExamen = examenSolicitado.getFecha();
                                                                    
                                                                    ids = TimeZone.getAvailableIDs(-5*60*60*1000);
                                                                    if(ids.length > 0)
                                                                    {
                                                                        tz = new SimpleTimeZone(-5*60*60*1000, ids[0]);
                                                                        cal = new GregorianCalendar(tz);
                                                                        cal.setTime(fechaExamen);
                                                                        
                                                                        fechaFormateada = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(cal.get(Calendar.MONTH)+ 1) + "/" + String.valueOf(cal.get(Calendar.YEAR));
                                                                    }
                                                                    else
                                                                    {
                                                                        fechaFormateada = fechaExamen.toString();                                                                        
                                                                    }
                                                                    %>
                                                                    
                                                                    <tr><td style="text-align:right"><%=examenSolicitado.getExamen().getCodigo()%></td>
                                                                        <td><%=examenSolicitado.getExamen().getTema()%></td>
                                                                        <td><%=fechaFormateada%></td>
                                                                        <td><%=examenSolicitado.getEstado().getNombre()%></td>
                                                                        <td style="text-align:right"><%=examenSolicitado.getNota()%></td></tr>
                                                                    
                                                                    <%
                                                                }
                                                            %>
                                                            </tbody>
                                                        </table>
                                                    
                                                    <%
                                                    }
                                                    else
                                                    {
                                                    %>
                                                    
                                                        <p>No hay elementos que mostrar para esta asignatura</p>
                                                    
                                                    <%
                                                    }
                                                    printedRecords++;
                                                }
                                            }
                                            else
                                            {
                                            %>
                                            	                                                
                                                <li><a href="registro.jsp?matId=<%=materia.getCodigo()%>"><%=materia.getCodigo()%> - <%=materia.getNombre()%></a></li>
                                            
                                            <%
                                            }
                                        }
                                        
                                        if(matId != 0 && printedRecords == 0)
                                        {
                                        %>
                                        
                                            <p>No hay registros que mostrar para la asignatura especificadaa</p>
                                        
                                        <%
                                        }
                                        
                                        if(matId == 0)
                                        {
                                        %>
                                        
                                            </ul>
                                        
                                        <%
                                        }
                                    }
                                    catch(EstudianteNoEncontradoException eneEx)
                                    {
                                %>
                                    <table border = "0" cellpadding="0" cellspacing="0" width = "95%" align="center">
                                        <tr><td class="login-error"><strong>Error en la consulta</strong>
                                            <p>La base de datos no puede localizar los registros del estudiante. Por favor reporte este problema al administrador.</p></td></tr></table>
                                <%
                                    }
                                }
                                
                                if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Tutor"))
                                {
                                %>
                                    
                                    <p style="text-align:center"><b>ESTE FORMULARIO LE PERMITIRÁ CONSULTAR LOS REGISTROS DE SUS ESTUDIANTES</b></p>
                                
                                <%
                                    studentString = request.getParameter("studentId");
                                    if(studentString != null && !studentString.equals(""))
                                    {
                                        try { studentId = Integer.parseInt(studentString); }
                                        catch(NumberFormatException nfEx) { studentId = 0; }
                                    }
                                    else studentId = 0;
                                    
                                    // Recuperar la Id del tutor
                                    tutorId = ((Tutor)currentUser).getIdTutor();
                                    if(AdministradoraDeUsuarios.confirmarEstudianteTallerTutor(studentId, tutorId))
                                    {
                                        estudiante = RCPRegistros.getEstudiante(studentId);
                                        %>
                                        
                                        <p>Registro para el estudiante: <strong><%=estudiante.getNombres() + " " + estudiante.getApellidos()%></strong></p>
                                        
                                        <%
                                        
                                        try
                                        {
                                            registros = ControladoraDeRegistro.getRegistros(studentId, true);
                                            registrosIterator = registros.iterator();

                                            matString = request.getParameter("matId");
                                            if(!(matString == null || matString.equals("")))
                                            {
                                                try { matId = Integer.parseInt(matString); }
                                                catch(NumberFormatException nfeEx) { matId = 0; }
                                            }

                                            if(matId == 0)
                                            {
                                            %>

                                                <p>Por favor seleccione la materia para al cual desea ver el registro:</p>

                                                <ul style="list-style-type:none">

                                            <%
                                            }

                                            while(registrosIterator.hasNext())
                                            {
                                                registro = (Registro)registrosIterator.next();
                                                materia = registro.getMateria();
                                                if(matId != 0)
                                                {
                                                    if(materia.getCodigo() == matId)
                                                    {   
                                                    %>

                                                        <p style="text-transform:uppercase"><strong><%=materia.getCodigo()%> - <%=materia.getNombre()%></strong></p>

                                                    <%
                                                        examenes = registro.getExamenesSolicitados();
                                                        if(examenes.size() > 0)
                                                        {
                                                        %>

                                                            <p><strong>Exámenes en el registro:</strong></p>

                                                            <table border="1" cellpadding="3" cellspacing="2" style="border-collapse:collapse">
                                                                <thead><tr><th>Código</th><th>Tema</th><th>Fecha</th><th>Estado</th><th>Nota</th></tr></thead>
                                                                <tbody>
                                                                <%
                                                                    examenesIterator = examenes.iterator();
                                                                    while(examenesIterator.hasNext())
                                                                    {
                                                                        examenSolicitado = (ExamenSolicitado)examenesIterator.next();
                                                                        fechaExamen = examenSolicitado.getFecha();

                                                                        ids = TimeZone.getAvailableIDs(-5*60*60*1000);
                                                                        if(ids.length > 0)
                                                                        {
                                                                            tz = new SimpleTimeZone(-5*60*60*1000, ids[0]);
                                                                            cal = new GregorianCalendar(tz);
                                                                            cal.setTime(fechaExamen);

                                                                            fechaFormateada = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(cal.get(Calendar.MONTH)+ 1) + "/" + String.valueOf(cal.get(Calendar.YEAR));
                                                                        }
                                                                        else
                                                                        {
                                                                            fechaFormateada = fechaExamen.toString();                                                                        
                                                                        }
                                                                        %>

                                                                        <tr><td style="text-align:right"><%=examenSolicitado.getExamen().getCodigo()%></td>
                                                                            <td><%=examenSolicitado.getExamen().getTema()%></td>
                                                                            <td><%=fechaFormateada%></td>
                                                                            <td><%=examenSolicitado.getEstado().getNombre()%></td>
                                                                            <td style="text-align:right"><%=examenSolicitado.getNota()%></td></tr>

                                                                        <%
                                                                    }
                                                                %>
                                                                </tbody>
                                                            </table>

                                                        <%
                                                        }
                                                        else
                                                        {
                                                        %>

                                                            <p>No hay elementos que mostrar para esta asignatura</p>

                                                        <%
                                                        }
                                                        printedRecords++;
                                                    }
                                                }
                                                else
                                                {
                                                %>

                                                    <li><a href="registro.jsp?studentId=<%=studentId%>&matId=<%=materia.getCodigo()%>"><%=materia.getCodigo()%> - <%=materia.getNombre()%></a></li>

                                                <%
                                                }
                                            }

                                            if(matId != 0 && printedRecords == 0)
                                            {
                                            %>

                                                <p>No hay registros que mostrar para la asignatura especificadaa</p>

                                            <%
                                            }

                                            if(matId == 0)
                                            {
                                            %>

                                                </ul>

                                            <%
                                            }
                                        }
                                        catch(EstudianteNoEncontradoException eneEx)
                                        {
                                        %>
                                            <table border = "0" cellpadding="0" cellspacing="0" width = "95%" align="center">
                                                <tr><td class="login-error"><strong>Error en la consulta</strong>
                                                    <p>La base de datos no puede localizar los registros del estudiante. Por favor reporte este problema al administrador.</p></td></tr></table>
                                        <%
                                        }
                                    }
                                    else
                                    {
                                    %>
                                    
                                        <table border = "0" cellpadding="0" cellspacing="0" width = "95%" align="center">
                                            <tr><td class="login-warning"><strong>¡Hay un problema!</strong>
                                                <p>El estudiante especificado no pertenece al taller del cual es usted tutor. Usted únicamente puede ver los registros de sus estudiantes.</p></td></tr></table>
                                                
                                        <p style="text-align:right"><input type="button" onclick="window.open('estudiantestaller.jsp', '_self')" value="Ver lista de estudiantes del taller" /></p>
                                    
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
                    rightPanelText.append("<p style=\"font-weight:bold;text-decoration:underline\">Consulta de Registro</p>");

                    if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Estudiante"))
                    {
                        rightPanelText.append("<p>Esta página le permite visualizar los registros para las diferentes asignaturas que en este momento está cursando y le proporciona información sobre el estado de sus exámenes y calificaciones.</p>");
                        rightPanelText.append("<p>Para hacer uso de la página seleccione la asignatura de la cual desea ver el registro. Si no aparecen asignaturas en la lista significa que usted no posee registros activos.</p>");
                    }
                    else if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Tutor"))
                    {
                        rightPanelText.append("<p>Esta página le permite visualizar los registros de uno de sus estudiantes. Seleccione una de las materias que el estudiante está viendo en el momento para ver el registro correspondiente.</p>");
                        rightPanelText.append("<p>Si no hay materias en la lista significa que el estudiante aún no ha abierto un registro para una de sus asignaturas.</p>");
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
