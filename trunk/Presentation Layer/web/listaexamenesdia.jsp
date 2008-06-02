<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="com.liceoval.businesslayer.entities.Usuario,
                com.liceoval.businesslayer.control.GeneradoraListaExamenesDia,
                Errores.NoItemFoundException,
                java.util.LinkedList,
                com.liceoval.businesslayer.entities.wrappers.ExamenSolicitadoWrapper,
                java.util.Iterator"
	errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Ver/Calificar exámenes asignados</title>
<link rel="stylesheet" type="text/css" href="globals/main.css" />
</head>
<style type="text/css">
	.label input{
	background: #EAF7FF;
	border: 1px solid #0066CC
	}
</style>
<body>

    <%
        String locationLinks;
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a>";
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
            <%if(currentUser != null){
        %>
        
        <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Exámenes Solicitados</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                            <tr><td class="cont-inner">
                                    <p style="text-align:center"><b>ESTA SECCIÓN LE PERMITIRÁ VER LOS EXÁMENES APROVADOS PARA EL DIA DE HOY.</b></p>
                                    
                                <br>
                                <%try{
                                    LinkedList listaExamenes = GeneradoraListaExamenesDia.generarListaExamenesDia();
                                    Iterator<ExamenSolicitadoWrapper> itListaExamenes = listaExamenes.iterator();
                                    ExamenSolicitadoWrapper examenWrapped;
                                    %>
                                <!--| CÓDIGO | MATERIA | TEMA | CANTIDAD |-->
                                <ul>
                                    <table border="1" width="92%">
                                        <thead>
                                            <tr>
                                                <th>CÓDIGO ESTUDIANTE</th>
                                                <th>NOMBRE ESTUDIANTE</th>
                                                <th>MATERIA</th>
                                                <th>CÓDIGO</th>
                                                <th>TEMA</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        <%while(itListaExamenes.hasNext()){
                                            examenWrapped = itListaExamenes.next();
                                            String nombre = examenWrapped.getEstudiante().getNombres()+" "+examenWrapped.getEstudiante().getApellidos();
                                            String codEstudiante = Integer.toString(examenWrapped.getEstudiante().getCodigo());
                                            String Materia = examenWrapped.getRegistro().getMateria().getNombre();
                                            String codigo = Integer.toString(examenWrapped.getExamenSolicitado().getExamen().getCodigo());
                                            String tema = examenWrapped.getExamenSolicitado().getExamen().getTema();
                                            %>
                                            <tr>
                                                <td><%=codEstudiante%></td>
                                                <td><%=nombre%></td>
                                                <td><%=Materia%></td>
                                                <td><%=codigo%></td>
                                                <td><%=tema%></td>
                                            </tr>
                                            <%}%>

                                        </tbody>
                                    </table>
                                </ul>
                                <%}catch(NoItemFoundException e){%>
                                        <p style="text-align:center"><b>NO HAY EXAMENES SOLICITADOS PARA EL DIA DE HOY</b></p>
                                    <%}%>
                                <!--VOY ACÁ-->
                            </td>
                            </tr>
                        </table>
                    </td>
                </tr>
         </table>
        
        
        <%}%>
            </td><td>
        
        	<!-- Menú derecho -->
            <%@include file="globals/right-menu.jsp" %>
        
        </td></tr>
	</table>

</body>
</html>