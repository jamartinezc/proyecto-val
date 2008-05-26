<%-- 
    Document   : solicitudexamen
    Created on : 21-abr-2008, 18:41:48
    Author     : Liliana
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="java.util.List,java.util.Iterator, com. liceoval. businesslayer. control. AdministradoraSolicitudesExamen,com.liceoval.businesslayer.entities.Materia, com. liceoval. businesslayer. entities. Examen,com. liceoval. businesslayer. entities. Estudiante, com.liceoval.businesslayer.control.AdministradoraDeUsuarios, com.liceoval.businesslayer.entities.Usuario, com.liceoval.businesslayer.control.registro.exceptions.*"
	errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Solicitud de Examen</title>
<link rel="stylesheet" type="text/css" href="globals/main.css" />
<script language="javascript" type="text/javascript">
    function getExamen(){
        
        indice = document.Solicitud.Materia.selectedIndex;
        document.Solicitud.NombreMateria.value = document.Solicitud.Materia.options[indice].text;
		
        }
        
    function getIdExamen(){
        document.Solicitud.codExamenSiguiente.value = document.Solicitud.Materia.vaule;
        }
                                        </script>
</head>
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
                            <td class="title-center" width="100%">Formato de Solicitud</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">
                                    <form name="Solicitud" action="confirmacionSolicitud.jsp" method="POST" enctype="application/x-www-form-urlencoded">
                                <br />
                                
                                <p style="text-align:center"><b>ESTE FORMULARIO LE PERMITIRÁ REALIZAR UNA SOLICITUD DE EXÁMEN.</b></p>
                                <br>
                                                                
                                 
                                 <ul>
                                      <li><i><b>Seleccione la materia en la que desea hacer su solicitud </b></i></li>
                                 
                                         
                                   <br>
                                    
                                    <table>
                                    <tr><td>Materia:</td></tr>
                                    <td> 
                                    <input type="hidden" name="NombreMateria">
                                    <select name="Materia"  >
                                    <%int idGrado = ((Estudiante)currentUser).getGrado().getIdGrado();
                                    List<Materia> materias = com.liceoval.businesslayer.control.rcp.RCPRegistros.getMaterias(idGrado);
                                    int i=0;
                                    do{%>
                                        <option value ="<%=materias.get(i).getCodigo()%>"><%=materias.get(i).getCodigo()+" : "+materias.get(i).getNombre()%></option>
                                    <%
                                    i++;
                                    }while(i<materias.size());%>
                                    </select>
                                    </td>                        
                                    </table>
                                    </ul>
                                    <br /><br />
                                    <p style="text-align:center"><input type="submit" value="Solicitar" name="solicitar" onClick="getExamen()"/></p>
                                
                                </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            
                <%}%>
                <td>
        
        	<!-- Menú derecho -->
                 <%rightContent = "Para solicitar un examen seleccione  en el menú \"Materia\" la materia de la cual desea solicitar un examen, y presione la tecla Solicitar. Esto le permitirá solicitar el siguiente examen de esa materia.<br>"+
                         "Recuerde que: <br>"+
                         "Solo puede solicitar un examen si no tiene NP o NE.<br>"+
                         "Para abrir un registro nuevo tiene que estar cursando menos de 3 materias.<br><br>"+
                         "Para abrir un registro de una materia solicite un examen de esa materia.";%>
            <%@include file="globals/right-menu.jsp" %>
        
        </td>
     </table>
    </body>
</html>
