<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="com.liceoval.businesslayer.entities.Usuario,
                com.liceoval.businesslayer.entities.wrappers.ExamenSolicitadoWrapper,
                com.liceoval.businesslayer.entities.ExamenSolicitado,
                com.liceoval.businesslayer.control.AdministradoraListaDeExamenesAsignados,
                Errores.NoItemFoundException,
                java.util.Enumeration"
	errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Bienvenido</title>
<link rel="stylesheet" type="text/css" href="globals/main.css" />
</head>

<body>

    <%
        String locationLinks;
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a>";
        boolean errorEnNotas = false;
    %>

    <%@include file="globals/header.jsp" %>
    
	<!-- Tabla principal del diseño-->
    <table style="padding-top:20px" border="0" cellpadding="0" cellspacing="0" align="center">
    	<tr valign="top"><td>
        	
            <!-- Tabla del menú izquierdo-->
            <!--
            <table width="200" cellpadding="0" cellspacing="0">
            	<tr height="30"><td><img src="images/title-left.png" /></td>
                	<td width="180" class="title-center">Hola</td>
                    <td><img src="images/title-right.png" /></td></tr>
                    
				<tr><td class="cont-outer" colspan="3">
                	<table border="0" cellspacing="0" cellpadding="0">
                    	<tr><td class="cont-inner">Este es el cuadro de Log-in, haga log-in aquí por favor, y cuando termine se dará
                         cuenta de que no sirve para nada por que todavía no está implementado :P</td></tr></table>
				</td></tr>
			</table>-->
            
            <!-- Menú o cuadro de log-in -->
            <%@include file="globals/log-in-menu.jsp" %>
            
            <p style="font-size:5pt">&nbsp;</p>
            
            <!-- Libre acceso -->
            <%@include file="globals/libre.jsp" %>
            
            </td><td class="center-padding">
                
                <%@include file="globals/login-error.jsp" %>
                <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Exámenes asignados</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                            <tr><td class="cont-inner" style="padding-right:45px">
                                <p style="text-align:left"><b><ul>Resultado de la asignación de notas:</ul></b></p>
                                    <br>
                                    <ul>
                                        <%
                                            Enumeration<String> notas = request.getParameterNames();
                                            boolean HuboUnerror=false;
                                            while(notas.hasMoreElements()){
                                                String examenId = notas.nextElement();
                                                String nota = request.getParameter(examenId);
                                                if( !nota.equals("") ){
                                                    
                                                    try{
                                                    int idExamenSolicitado = Integer.parseInt(examenId);
                                                    float notaExamen = Float.parseFloat(nota);
                                                    if(notaExamen>=0 && notaExamen<=10){
                                                        AdministradoraListaDeExamenesAsignados.guardarNota(idExamenSolicitado, notaExamen);
                                                        }
                                                    else{
                                                        HuboUnerror=true;
                                                         %>
                                                        <p style="text-align:justify">
                                                            <b>ERROR: </b>Solo puede ingresar valores entre 0 y 10. Porfavor vuelva consultar la lista de examenes asignados y califique los que no se pudieron calificar.
                                                        </p>
                                                        <%
                                                    }
                                                    }catch(NumberFormatException e){
                                                        HuboUnerror=true;
                                                         %>
                                                        <p style="text-align:justify">
                                                            <b>ERROR: </b>Solo puede ingresar valores numéricos en las notas. Porfavor vuelva consultar la lista de examenes asignados y califique los que no se pudieron calificar.
                                                        </p>
                                                        <%
                                                    }catch(NoItemFoundException e){
                                                        HuboUnerror=true;
                                                        %>
                                                        <p style="text-align:justify">
                                                            <b>ERROR: </b><%=e.Mensaje()%> un exámen que intenta calificar ya ha sido calificado.
                                                        </p>
                                                        <%
                                                    }
                                                    
                                                }else{
                                                        HuboUnerror=true;
                                                         %>
                                                        <p style="text-align:justify">
                                                            <b>ERROR: </b>Dejó examenes sin calificar. Porfavor vuelva consultar la lista de examenes asignados y califique los que no se pudieron calificar.
                                                        </p>
                                                        <%
                                                }
                                            }
                                            if( !HuboUnerror ){
                                            %>
                                                        <p style="text-align:justify">
                                                            Las notas fueron guardadas con éxito.
                                                        </p>
                                          <%}%>
                                    </ul>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
         </table>
            </td><td>
        
        	<!-- Menú derecho -->
            <%@include file="globals/right-menu.jsp" %>
        
        </td></tr>
	</table>

</body>
</html>