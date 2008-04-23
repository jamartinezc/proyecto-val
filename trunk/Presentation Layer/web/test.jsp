<%-- 
    Document   : test.jsp
    Created on : 23/04/2008, 04:28:22 PM
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
    import="com.liceoval.businesslayer.control.rcp.RCPRegistros, com.liceoval.businesslayer.entities.Materia, java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<Materia> materias;
            materias = RCPRegistros.getMaterias();
        %>
        
        <%=materias.get(0).getNombre()%>
    </body>
</html>
