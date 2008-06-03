<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="com.liceoval.businesslayer.entities.Usuario,
                com.liceoval.businesslayer.entities.Analista,
                com.liceoval.businesslayer.control.AdministradoraListaDeExamenesAsignados,
                com.liceoval.businesslayer. entities. wrappers. ExamenSolicitadoWrapper,
                Errores.NoItemFoundException,
                java.util.List,
                java.util.Iterator,
                java.util.Date,
                java.util.Calendar"
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
            <%if(currentUser != null)
        {
        %>
        
        <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Exámenes asignados</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                            <tr><td class="cont-inner">
                                    <p style="text-align:center"><b>ESTA SECCIÓN LE PERMITIRÁ CALIFICAR EXÁMENES ASIGNADOS A USTED.</b></p>
                                    <br>
                                    <ol>
                                        <li>Paso uno.</li>
                                        <li>Paso dos.</li>
                                        <li>Paso tres.</li>
                                    </ol>
                                <br>
                                    <!--| ESTUDIANTE | EXÁMEN | FECHA | NOTA |-->
                                    <%try{
                                        Integer idAnalista = ((Analista)currentUser).getIdAnalista();
                                        List<ExamenSolicitadoWrapper> listaExamenes = AdministradoraListaDeExamenesAsignados.generarListaDeExamenesACalificar(idAnalista);
                                        Iterator<ExamenSolicitadoWrapper> itListaExamenes = listaExamenes.iterator();
                                        ExamenSolicitadoWrapper examen;
                                        %>
                                        <ul>
                                          <form name="listaExamenes" action="" method="POST" enctype="application/x-www-form-urlencoded" accept-charset="ISO-8859-1">
                                            <table border="1" class="label" width="92%">
                                                <thead>
                                                    <tr>
                                                        <th>COD ESTUDIANTE</th>
                                                        <th>NOMBRE ESTUDIANTE</th>
                                                        <th>EXÁMEN</th>
                                                        <th>FECHA</th>
                                                        <th>NOTA</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                        <%            
                                        while(itListaExamenes.hasNext()){
                                            examen=itListaExamenes.next();
                                            Date fecha = examen.getExamenSolicitado().getFecha();
                                            //meterle Date translator
                                            %>
                                                    <tr>
                                                        <td><%=examen.getEstudiante().getCodigo()%></td>
                                                        <td><%=examen.getEstudiante().getNombres()+" "+examen.getEstudiante().getApellidos()%></td>
                                                        <td><%=examen.getExamenSolicitado().getExamen().getTema()%></td>
                                                        <td><%=fecha%></td>
                                                        <td><input type="text" name="<%="nota"+1%>" value="<%=examen.getExamenSolicitado().getNota()%>" size="3" /></td>
                                                    </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>
                                            <p style="text-align:center">
                                                <input type="submit" value="Enviar cambios" />
                                                <input type="button" value="Deshacer cambios" onclick="" />
                                            </p>
                                          <!--VOY ACÁ-->
                                          </form>
                                        </ul>
                                    <%}catch(NoItemFoundException e){
                                        %><p style="text-align:center"><b>USTED NO TIENE EXAMENES PENDIENTES PARA CALIFICAR</b></p><%
                                    }%>
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
