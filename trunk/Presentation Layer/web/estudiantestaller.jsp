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
                com.liceoval.businesslayer.entities.Estudiante,
                com.liceoval.businesslayer.entities.Tutor,
                com.liceoval.businesslayer.entities.wrappers.TallerWrapper,
                java.util.Iterator,
                java.util.LinkedList,
                java.util.List
                "
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
        
        List<TallerWrapper> talleres;
        Iterator estudiantesIterator;
        Iterator talleresIterator;
        Estudiante estudiante;
        TallerWrapper taller;
        Tutor tutor;
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a> - <a class=\"nav-bar-link\" href=\"estudiantestaller.jsp\">Lista de Estudiantes</a>";
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
                            <td class="title-center" width="100%">Lista de Estudiantes</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">

                                    <p style="font-weight:bold;text-transform:uppercase;text-align:center">Esta página muestra la lista de estudiantes de los taller de los cuales es usted tutor</p>
                                    
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
                                                %>
                                                
                                                <ul style="list-style-type:none">
                                                
                                                <%
                                                while(estudiantesIterator.hasNext())
                                                {
                                                    estudiante = (Estudiante)estudiantesIterator.next();
                                                    %>
                                                    
                                                    <li><strong><a href="registro.jsp?studentId=<%=estudiante.getCodigo()%>"><%= estudiante.getNombres() + " " + estudiante.getApellidos()%></a></strong><br><a href="ficharendimiento.jsp?studentId=<%=estudiante.getCodigo()%>">[ Ficha de Rendimiento ]</a></li>
                                                    
                                                    <%
                                                }
                                                %>
                                                
                                                </ul>
                                                
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
                    rightPanelText.append("<p style=\"font-weight:bold;text-decoration:underline\">Lista de Estudiantes</p>");

                    rightPanelText.append("<p>Esta página le muestra la lista de estudiantes de los talleres de los cuales es usted tutor. Puede hacer clic en el nombre de un estudiante para ver su registro académico</p>");
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
