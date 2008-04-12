<%
	// Esta p�gina proporciona el men� y el cuadro de Log-in de acuerdo con si el usuario
	// se encuentra logeado o no y del rol que est� desempe�ando el usuario que se ha logeado.
%>
   
    <!-- %@page import="com.liceoval.businesslayer.entities.Usuario" % -->

    <%
    // Usuario currentUser;
    // String currentUserName;
    // String currentUserRole;
       
    // currentUser = (Usuario)session.getAttribute("currentUser");
    %>   
   
    <%
    if(currentUser == null)
    {
    %>
        
        <!-- Cuadro de Log-in -->
        <table width="200" cellpadding="0" cellspacing="0">
            <tr height="30"><td><img src="images/title-left.png" /></td>
                <td width="180" class="title-center">Iniciar Sesi�n</td>
                <td><img src="images/title-right.png" /></td></tr>
            <tr><td class="cont-outer" colspan="3">
                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                    <tr><td class="cont-inner">
                    <form action="loginmanager?action=login" method="post" enctype="application/x-www-form-urlencoded">
                        
                        <p>Nombre de usuario:<br />
                        
                        <%
                        if(request.getParameter("user") == null)
                        {
                        %>

                            <input type="text" name="user" />
                            
                        <%
                        }
                        else
                        {
                        %>
                        
                            <input type="text" name="user" value="<%= request.getParameter("user") %>" />
                        
                        <%
                        }
                        %>
                        
                        <p>Contrase�a:<br />
                        <input type="password" name="password" /></p>
                        <p>Rol:<br />
						<%@include file="role-list.jsp" %>
                        <!--<select name="role">
                        	<option value="student" selected="selected">Estudiante</option>
                            <option value="tutor">Tutor</option>
                            <option value="analist">Analista</option>
                            <option value="secretary">Secretaria Acad�mica</option>
						</select>-->
                        <p style="text-align:right"><input value="Iniciar Sesi�n" type="submit" name="send" /></p>
                    </form></td></tr></table>
            </td></tr>
        </table>
        
    <%
    }
    else
    {
    %>
    
    	<table width="200" cellpadding="0" cellspacing="0">
            <tr height="30"><td><img src="images/title-left.png" /></td>
                <td width="180" class="title-center">Men�</td>
                <td><img src="images/title-right.png" /></td></tr>
            <tr><td class="cont-outer" colspan="3">
                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                    <tr><td class="cont-inner"><ul>
                    	
    
    <%
        if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Analista"))
        {
    %>
    
            <!-- Men� del Analista -->
            <li><a href="listaexamenesasignados.jsp">Ver/Calificar ex�menes asignados</a></li>
    
    <%      
        }
        else if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Estudiante"))
        {
    %>
    
            <!-- Men� del Estudiante -->
            <li><a href="registro.jsp">Consultar Registro</a></li>
            <li><a href="ficharendimiento.jsp">Consultar Ficha de Rendimiento</a></li>
            <li><a href="planeacionanual.jsp">Consultar/Editar planeaci�n anual</a></li>
            <li><a href="planeacionanual.jsp">Consultar/Editar planeaci�n semanal</a></li>
            <li><a href="solicitudexamen.jsp">Solicitar examen</a></li>

    <%
        }
        else if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.SecretariaAcademica"))
        {
    %>
    
            <!-- Men� de la Secretaria Acad�mica -->
            <li><a href="listaexamenesdia">Ver lista de ex�menes del d�a</a></li>
    
    <%
        }
        else if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Tutor"))
        {
    %>
    
            <!-- Men� del Tutor -->
			<li><a href="estudiantestaller.jsp">Ver lista de estudiantes del taller</a></li>
            <li><a href="listaexamenessolicitados.jsp">Ver lista de ex�menes solicitados</a></li>
    
    <%
        }
    %>

                    	<li><a href="change-role.jsp">Cambiar Rol</a></li>
                        <li><a href="loginmanager?action=logout">Cerrar Sesi�n</a></li></ul></td></tr></table>
            </td></tr>
    	</table>

    <%
    }
    %>