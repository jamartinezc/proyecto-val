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
                com.liceoval.businesslayer.entities.Estado,
                com.liceoval.businesslayer.entities.Estudiante,
                com.liceoval.businesslayer.entities.ExamenSolicitado,
                com.liceoval.businesslayer.entities.Materia,
                com.liceoval.businesslayer.entities.MateriaPlaneada,
                com.liceoval.businesslayer.entities.PlaneacionAnual,
                com.liceoval.businesslayer.entities.Registro,
                com.liceoval.businesslayer.entities.Tutor,
                java.util.Arrays,
                java.util.Calendar,
                java.util.Iterator,
                java.util.LinkedList,
                java.util.HashMap"
	errorPage="" %>
        
<%@taglib uri="WEB-INF/cewolf.tld" prefix='cewolf' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Ver / Editar Planeación Anual</title>
<link rel="stylesheet" type="text/css" href="globals/main.css" />
</head>

<body>

    <%
        int meses;
        int idTutor;
        int[] planned;
        int[][] datos;
        int[][] totales;
        int idEstudiante = 0;
        int totalEvaluaciones;
        
        int totalGanadosGrado;
        int totalPlaneadosGrado;
        int totalGanadosMateria;
        int totalPlaneadosMateria;
        
        Calendar cal;
        Calendar calInicio;
        Calendar calExamen;
        
        Materia materia;
        Registro registro;
        Estudiante estudiante;
        PlaneacionAnual planeacionAnual;
        MateriaPlaneada materiaPlaneada;
        ExamenSolicitado examenSolicitado;
        
        String locationLinks;
        String studentString;
        
        StringBuffer fechaInicio;

        Iterator<int[]> examenMesIterator;
        Iterator<Materia> materiasIterator;
        Iterator<Registro> registrosIterator;
        Iterator<ExamenSolicitado> examenesIterator;
        Iterator<MateriaPlaneada> materiasPlaneadasIterator;

        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a> - <a class=\"nav-bar-link\" href=\"planeacionanual.jsp\">Planeación Anual</a>";
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
                            <td class="title-center" width="100%">Consultar / Editar Planeación Anual</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">
                                    
                                    <p style="text-align:center"><b>PLAN ANUAL POR MESES</b></p>

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
                                            %>
                                            
                                            <table align="center" border="1" bordercolor="#000000" cellpadding="5" cellspacing="0" style="border-collapse:collapse;font-size:7pt;">
                                                <tr><td colspan="15" style="padding-left:30px">
                                                    <p><strong>Nombre:</strong> <%=estudiante.getNombres() + " " + estudiante.getApellidos()%> <strong>Grado:</strong> <%=estudiante.getGrado().getNombre()%><br>
                                                    <strong>Fecha de inicio del grado:</strong> <%=fechaInicio.toString()%></p>
                                                </td></tr>
                                                
                                                <tr><td rowspan="2"><td rowspan="3"><img src="images/evaluaciones.png" /></td><td colspan="13" style="text-align:center;font-weight:bold">MES</td></tr>
                                                <tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th><th>8</th><th>9</th><th>10</th><th>11</th><th>12</th><th>Total</th></tr>
                                                <tr><td><strong>Materia</strong></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                                                
                                                <%
                                                
                                                materiasIterator = estudiante.getGrado().getMaterias().iterator();
                                                totalEvaluaciones = 0;
                                                totales = new int[12][2];
                                                totalGanadosGrado = 0;
                                                totalPlaneadosGrado = 0;
                                                
                                                for(i = 0; i < 12; i++)
                                                        Arrays.fill(totales[i], 0);
                                                                                               
                                                while(materiasIterator.hasNext())
                                                {
                                                    materia = materiasIterator.next();                                                                                                        
                                                    totalEvaluaciones = totalEvaluaciones + materia.getExamenes().size();
                                                    
                                                    datos = new int[12][2];
                                                    
                                                    for(i = 0; i < 12; i++)
                                                        Arrays.fill(datos[i], 0);
                                                    
                                                    %>
                                                    
                                                    <tr valign="middle"><td><strong><%=materia.getNombre()%></strong></td><td style="text-align:right"><%=materia.getExamenes().size()%></td>
                                                    
                                                    <%
                                                    
                                                    // Recuperar la planeación anual
                                                    planeacionAnual = estudiante.getPlaneacionAnual();
                                                    if(planeacionAnual != null)
                                                    {
                                                        materiasPlaneadasIterator = planeacionAnual.getMateriasPlaneadas().iterator();
                                                        materiaPlaneada = null;
                                                                                                                                                                        
                                                        // Localizar la materia actual en la planeación anual
                                                        while(materiasPlaneadasIterator.hasNext())
                                                        {
                                                            
                                                            materiaPlaneada = materiasPlaneadasIterator.next();
                                                            if(materiaPlaneada.getMateria().getCodigo() == materia.getCodigo())
                                                            {
                                                                // Materia localizada, abandonar el while.
                                                                break;
                                                            }
                                                            else materiaPlaneada = null;
                                                        }
                                                        
                                                        if(materiaPlaneada != null)
                                                        {
                                                            examenMesIterator = materiaPlaneada.getPlaneadosMes().iterator();
                                                            while(examenMesIterator.hasNext())
                                                            {
                                                                planned = examenMesIterator.next();
                                                                if(planned[0] < 12 && planned[0] >= 0)
                                                                {
                                                                    datos[planned[0]][0] = planned[1];
                                                                    totales[planned[0]][0] = totales[planned[0]][0] + planned[1];
                                                                }
                                                            }
                                                        }    
                                                    }
                                                    
                                                    // Localizar el registro de la asignatura
                                                    registrosIterator = estudiante.getRegistros().iterator();
                                                    registro = null;
                                                    
                                                    while(registrosIterator.hasNext())
                                                    {
                                                        registro = registrosIterator.next();
                                                        if(registro.getMateria().getCodigo() == materia.getCodigo())
                                                            break;
                                                        
                                                        else
                                                            registro = null;
                                                    }
                                                    
                                                    // Verificar si se ha encontrado el registro
                                                    if(registro != null)
                                                    {
                                                        // Recuperar los exámenes ganados
                                                        examenesIterator = registro.getExamenesSolicitados().iterator();
                                                        while(examenesIterator.hasNext())
                                                        {
                                                            examenSolicitado = examenesIterator.next();
                                                            if(examenSolicitado.getEstado().equals(Estado.GANADO))
                                                            {
                                                                calInicio = Calendar.getInstance();
                                                                calExamen = Calendar.getInstance();
                                                                
                                                                calInicio.setTime(estudiante.getFechaInicioGrado());
                                                                calExamen.setTime(examenSolicitado.getFecha());
                                                                
                                                                if(!(calExamen.before(calInicio)))
                                                                {
                                                                    meses = 0;
                                                                    while(calInicio.get(Calendar.MONTH) < calExamen.get(Calendar.MONTH) || calInicio.get(Calendar.YEAR) < calExamen.get(Calendar.YEAR))
                                                                        {
                                                                            calInicio.add(Calendar.MONTH, 1);
                                                                            meses++;
                                                                        }
                                                                    
                                                                    datos[meses][1] = datos[meses][1] + 1;
                                                                    totales[meses][1] = totales[meses][1] + 1;
                                                                }   
                                                            }
                                                        }
                                                    }
                                                    
                                                    totalGanadosMateria = 0;
                                                    totalPlaneadosMateria = 0;
                                                    
                                                    // Imprimir los datos
                                                    for(i = 0; i < 12; i++)
                                                    {
                                                        out.print("<td>");
                                                        if(datos[i][0] > 0)
                                                        {
                                                            totalPlaneadosMateria = totalPlaneadosMateria + datos[i][0];
                                                            out.print(datos[i][0]);
                                                            
                                                            if(datos[i][1] > 0)
                                                            {
                                                                totalGanadosMateria = totalGanadosMateria + datos[i][1];
                                                            %>

                                                                / <span style="color:#ff0000"><%=datos[i][1]%></span>

                                                            <%
                                                            }
                                                            
                                                        }
                                                        else
                                                            out.print("&nbsp;");
                                                        
                                                        out.print("</td>");
                                                    }
                                                    
                                                    out.print("<td>");
                                                    if(totalPlaneadosMateria > 0)
                                                    {
                                                        out.print(totalPlaneadosMateria);
                                                        
                                                        if(totalGanadosMateria > 0)
                                                        {
                                                        %>
                                                        
                                                            / <span style="color:#ff0000"><%=totalGanadosMateria%></span>
                                                        
                                                        <%
                                                        }
                                                    }
                                                    else
                                                        out.print("&nbsp;");
                                                        
                                                    out.println("</td></tr>");
                                                    
                                                    totalGanadosGrado = totalGanadosGrado + totalGanadosMateria;
                                                    totalPlaneadosGrado = totalPlaneadosGrado + totalPlaneadosMateria;
                                                }
                                                %>
                                                
                                                <tr><td><strong>Total</strong></td><td style="text-align:right"><%=totalEvaluaciones%></td>
                                                
                                                <%
                                                    for(i = 0; i < 12; i++)
                                                    {
                                                        if(totales[i][0] > 0)
                                                        {
                                                        %>

                                                            <td><%=totales[i][0]%>
                                                            
                                                        <%
                                                        
                                                            if(totales[i][1] > 0)
                                                            {
                                                            %>
                                                            
                                                            / <span style="color:#ff0000"><%=totales[i][1]%></span>
                                                            
                                                            <%
                                                            }
                                                            %>
                                                                </td>
                                                            <%
                                                        }
                                                        else
                                                        {  
                                                        %>

                                                            <td>&nbsp;</td>

                                                        <%
                                                        }
                                                    }
                                                
                                                    out.print("<td>");
                                                    if(totalPlaneadosGrado > 0)
                                                    {
                                                        out.print(totalPlaneadosGrado);
                                                        if(totalGanadosGrado > 0)
                                                        {
                                                        %>
                                                            
                                                            / <span style="color:#ff0000"><%=totalGanadosGrado%></span>
                                                            
                                                        <%
                                                        }
                                                    }
                                                    else
                                                        out.print("&nbps;");
                                                    
                                                    out.print("</td>");
                                                %>
                                                
                                                </tr>

                                            </table>
                                            
                                            <p style="text-align:center">En negro: <strong>Proyecto</strong> En Rojo: <strong>Realidad</strong></p>
                                            
                                            <%
                                            if(currentUser instanceof Estudiante)
                                            {
                                            %>
                                            
                                                <p style="text-align:right"><input type="button" onclick="window.open('edplanacionanual.jsp','_self')" value="Editar Planeación"></p>
                                            
                                            <%    
                                            }
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
                    rightPanelText.append("<p style=\"font-weight:bold;text-decoration:underline\">Planeación Anual</p>");

                    if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Estudiante"))
                    {
                        rightPanelText.append("<p>Esta página muestra su planeacion anual por meses. Para cada mes usted verá:.</p>");
                        rightPanelText.append("<ul>");
                        rightPanelText.append(" <li>La cantidad de exámenes planeados (negro)</li>");
                        rightPanelText.append(" <li>La cantidad de exámenes ganados (rojo)</li>");
                        rightPanelText.append("</ul>");
                        rightPanelText.append("<p>Haga clic en Editar Planeación para editar su planeación Anual.</p>");        
                    }
                    else if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Tutor"))
                    {
                        rightPanelText.append("<p>Esta página muestra la planeacion anual por meses del estudiante. Para cada mes usted verá:.</p>");
                        rightPanelText.append("<ul>");
                        rightPanelText.append(" <li>La cantidad de exámenes planeados (negro)</li>");
                        rightPanelText.append(" <li>La cantidad de exámenes ganados (rojo)</li>");
                        rightPanelText.append("</ul>");
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
