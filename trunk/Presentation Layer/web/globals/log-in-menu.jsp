<%
	// Esta página proporciona el menú y el cuadro de Log-in de acuerdo con si el usuario
	// se encuentra logeado o no y del rol que esté desempeñando el usuario que se ha logeado.
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
                <td width="180" class="title-center">Iniciar Sesión</td>
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
                        
                        <p>Contraseña:<br />
                        <input type="password" name="password" /></p>
                        <p>Rol:<br />
						<%@include file="role-list.jsp" %>
                        <!--<select name="role">
                        	<option value="student" selected="selected">Estudiante</option>
                            <option value="tutor">Tutor</option>
                            <option value="analist">Analista</option>
                            <option value="secretary">Secretaria Académica</option>
						</select>-->
                        <p style="text-align:right"><input value="Iniciar Sesión" type="submit" name="send" /></p>
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
                <td width="180" class="title-center">Menú</td>
                <td><img src="images/title-right.png" /></td></tr>
            <tr><td class="cont-outer" colspan="3">
                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                    <tr><td class="cont-inner"><ul>
                    	
    
    <%
        if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Analista"))
        {
    %>
    
            <!-- Menú del Analista -->
            <li><a href="listaexamenesasignados.jsp">Ver/Calificar exámenes asignados</a></li>
    
    <%      
        }
        else if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Estudiante"))
        {
    %>
    
            <!-- Menú del Estudiante -->
            <li><a href="registro.jsp">Consultar Registro</a></li>
            <li><a href="ficharendimiento.jsp">Consultar Ficha de Rendimiento</a></li>
            <li><a href="planeacionanual.jsp">Consultar/Editar planeación anual</a></li>
            <li><a href="planeacionanual.jsp">Consultar/Editar planeación semanal</a></li>
            <li><a href="solicitudexamen.jsp">Solicitar examen</a></li>

    <%
        }
        else if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.SecretariaAcademica"))
        {
    %>
    
            <!-- Menú de la Secretaria Académica -->
            <li><a href="listaexamenesdia">Ver lista de exámenes del día</a></li>
    
    <%
        }
        else if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.Tutor"))
        {
    %>
    
            <!-- Menú del Tutor -->
			<li><a href="estudiantestaller.jsp">Ver lista de estudiantes del taller</a></li>
            <li><a href="listaexamenessolicitados.jsp">Ver lista de exámenes solicitados</a></li>
    
    <%
        }
    %>

                    	<li><a href="change-role.jsp">Cambiar Rol</a></li>
                        <li><a href="loginmanager?action=logout">Cerrar Sesión</a></li></ul></td></tr></table>
            </td></tr>
    	</table>

    <%
    }
    %>