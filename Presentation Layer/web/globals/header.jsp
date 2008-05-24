<%@page import="com.liceoval.businesslayer.entities.Usuario" %>

    <%
    Usuario currentUser;
    String currentUserName;
    String currentUserRole;
    String parrafoDeAyuda = null;//aca se personaliza la ayuda en cada seccion, asignandole el parrafo con las instrucciones de ayuda
       
    currentUser = (Usuario)session.getAttribute("currentUser");
    %>

    <table border="0" cellpading="0" cellspacing="0" width="100%">
    <tr valign="top"><td class="system-logo"><img src="images/system-logo.jpg" /></td>
    <td class="system-info" style="text-align:right">
        
        <%
        if(currentUser != null)
        {
        %>
            Bienvenido <strong><%=currentUser.getNombres() + " " + currentUser.getApellidos()%></strong> [ <a href="loginmanager?action=logout">Cerrar Sesión</a> ]<br />
            Su rol actual es <strong><%=currentUser.getRol()%></strong> [ <a href="change-role.jsp">Cambiar Rol</a> ]
        <%
        }
        else
        {
        %>
            &nbsp;
        <%
        }
        %>  
    
    </td></tr>
    <tr height="25" valign="middle"><td class="nav-bar" colspan="2">Usted se encuentra en: <%=locationLinks%></td></tr></table>
    