<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="com.liceoval.businesslayer.entities.Usuario,
        com.liceoval.businesslayer.control.GeneradoraInformeExcelencia"
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
                            <td class="title-center" width="100%">Bienvenido</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                            <tr><td class="cont-inner" style="padding-right:45px">
                                    <p style="text-align:center"><b>BIENVENIDO AL SISTEMA DE INFORMACIÓN ACADÉMICA DEL LICEO V.A.L.</b></p>
                                    <br>
                                    <ul>
                                        <p style="text-align:justify">
                                            <b>Bienvenido Visitante</b><br>
                                            Si usted es un usuario activo del Liceo V.A.L por favor inicie su sesión.<br>
                                            De lo contrario puede unicamente consultar el informe de exelencia que se encuentra a continuación.
                                        </p>
                                    </ul>
                                
                                </td>
                            </tr>
                            
                            <tr>
                                <td class="cont-inner" >
                                    <% 
                                    String[][] informe = GeneradoraInformeExcelencia.consultarInformeExcelencia();
                                    %>
                                    <p style="text-align:center">
                                        <b>
                                            INFORME DE EXCELENCIA POR TALLER<br><br>
                                            TALLERES
                                        </b>
                                    
                                        <ul>
                                            <table border="1" width="92%" style="text-align:center">
                                                <thead>
                                                    <tr>
                                                        <th>TALLER</th>
                                                        <th>PRESENTADOS POR EL TALLER</th>
                                                        <th>GANADOS POR EL TALLER</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%for(int i=0;i<informe.length;i++){%>
                                                        <tr>
                                                            <td><%=informe[i][0]%></td>
                                                            <td><%=informe[i][4]%></td>
                                                            <td><%=informe[i][5]%></td>
                                                        </tr>
                                                    <%}%>
                                                </tbody>
                                                </table>
                                         </ul>
                                    </p>
                                    
                                        
                                    <p style="text-align:center">
                                        <b><br><br>MEJORES ESTUDIANTES DE CADA TALLER<br></b>
                                        <ul>
                                            <table border="1" width="92%" style="text-align:center">
                                                <thead>
                                                    <tr>
                                                        <th>TALLER</th>
                                                        <th>ESTUDIANTE</th>
                                                        <th>EXÁMENES PRESENTADOS</th>
                                                        <th>EXÁMENES GANADOS</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%for(int i=0;i<informe.length;i++){%>
                                                        <tr>
                                                            <td><%=informe[i][0]%></td>
                                                            <td><%=informe[i][1]%></td>
                                                            <td><%=informe[i][2]%></td>
                                                            <td><%=informe[i][3]%></td>
                                                        </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>
                                        </ul>
                                    </p>
                                    <!--
                                    <p style="text-align:left">
                                        <br><br>
                                        <ul>
                                            <b>TALLER CON MAS GANADOS:</b>
                                            <table border="0">
                                                <thead>
                                                    <tr>
                                                        <th>TALLER</th>
                                                        <th>PRESENTADOS</th>
                                                        <th>GANADOS</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>CINCO</td>
                                                        <td>240</td>
                                                        <td>140</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </ul>
                                    </p>
                                    <p style="text-align:left">
                                        <br>
                                        <ul>
                                            <b>TALLER CON MEJOR RENDIMIENTO:</b>
                                            <table border="0">
                                                <thead>
                                                    <tr>
                                                        <th>TALLER</th>
                                                        <th>PRESENTADOS</th>
                                                        <th>GANADOS</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>CINCO</td>
                                                        <td>80</td>
                                                        <td>78</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </ul>
                                    </p>
                                    <p style="text-align:left">
                                        <br> 
                                            <ul>
                                                <b>MEJOR ESTUDIANTE:</b>
                                            <table border="0">
                                            <thead>
                                                <tr>
                                                    <th>TALLER</th>
                                                    <th>NOMBRE</th>
                                                    <th>PRESENTADOS</th>
                                                    <th>GANADOS</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>TRES</td>
                                                    <td>JORGE MARTÍNEZ</td>
                                                    <td>35</td>
                                                    <td>30</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        </ul>
                                    </p>
                                    -->
                                    <!--ACÁ VOY-->
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
