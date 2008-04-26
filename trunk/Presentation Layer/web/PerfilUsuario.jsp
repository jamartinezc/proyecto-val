<%-- 
    Document   : ListaUsuarios
    Created on : 20/04/2008, 01:35:52 AM
    Author     : Administrador
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="java.util.Iterator,java.util.List,java.util.LinkedList,java.util.Iterator,com.liceoval.businesslayer.control.AdministradoraDeUsuarios, com.liceoval.businesslayer.entities.Usuario,com.liceoval.businesslayer.entities.Materia,com.liceoval.businesslayer.control.rcp.RCPRegistros,com.liceoval.businesslayer.entities.Taller"
	errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Liceo V.A.L. - Sistema de Información Académico - Perfil de Usuarios</title>
<link rel="stylesheet" type="text/css" href="globals/main.css" />

<script language="javascript" type="text/javascript">

	function enabledisable()
	{
		if(document.GuardarUsuario.Estudiante.checked == true||document.GuardarUsuario.Secretaria.checked == true||document.GuardarUsuario.Tutor.checked == true||document.GuardarUsuario.Analista.checked == true)
                    {
                        document.GuardarUsuario.Enviar.disabled=false;
                        document.GuardarUsuario.Nombres.disabled = false;
                        document.GuardarUsuario.Apellidos.disabled = false;
                        document.GuardarUsuario.Login.disabled = false;
                        document.GuardarUsuario.Clave.disabled = false;
                        document.GuardarUsuario.Cclave.disabled = false;
                        
                    }
                    else{
                        document.GuardarUsuario.Enviar.disabled=true;
                        document.GuardarUsuario.Nombres.disabled = true;
			document.GuardarUsuario.Apellidos.disabled = true;
                        document.GuardarUsuario.Login.disabled = true;
             		document.GuardarUsuario.Clave.disabled = true;
                        document.GuardarUsuario.Cclave.disabled = true;
                }
                
                 if(document.GuardarUsuario.Estudiante.checked == true)
		{
                        document.GuardarUsuario.codigo.disabled = false;
                        document.GuardarUsuario.grado.disabled = false;
                        document.GuardarUsuario.taller.disabled = false;
                        document.GuardarUsuario.fecha.disabled = false;
                        document.GuardarUsuario.pidentificacion.disabled = false;
                        document.GuardarUsuario.pnombres.disabled = false;
                        document.GuardarUsuario.papellidos.disabled = false;
                        document.GuardarUsuario.pcorreo.disabled = false;
                        
                        document.GuardarUsuario.Analista.checked = false;
                        document.GuardarUsuario.Tutor.checked = false;
                        document.GuardarUsuario.Secretaria.checked = false;
                        
                        document.GuardarUsuario.Analista.disabled = true;
                        document.GuardarUsuario.Tutor.disabled = true;
                        document.GuardarUsuario.Secretaria.disabled = true;
                        return
		}
                 else
                     {
			document.GuardarUsuario.codigo.disabled = true;
			document.GuardarUsuario.grado.disabled = true;
                        document.GuardarUsuario.taller.disabled = true;
             		document.GuardarUsuario.fecha.disabled = true;
                        document.GuardarUsuario.pidentificacion.disabled = true;
			document.GuardarUsuario.pnombres.disabled = true;
                        document.GuardarUsuario.papellidos.disabled = true;
             		document.GuardarUsuario.pcorreo.disabled = true;
                        
                        document.GuardarUsuario.Analista.disabled = false;
                        document.GuardarUsuario.Tutor.disabled = false;
                        document.GuardarUsuario.Secretaria.disabled = false;

                      }
                      
                if(document.GuardarUsuario.Tutor.checked == true)
                {
                    document.GuardarUsuario.tallert.disabled = false;
                    
                    document.GuardarUsuario.Estudiante.checked = false;
                    document.GuardarUsuario.Secretaria.checked = false;

                    document.GuardarUsuario.Estudiante.disabled = true;
                    document.GuardarUsuario.Secretaria.disabled = true;
                }    
                else
                {
                    document.GuardarUsuario.tallert.disabled = true;
                                        
                    document.GuardarUsuario.Estudiante.disabled = false;
                    document.GuardarUsuario.Secretaria.disabled = false;
                }    
                    
                
                if(document.GuardarUsuario.Analista.checked == true)
                {
                    document.GuardarUsuario.materia.disabled = false;
                    
                    document.GuardarUsuario.Estudiante.checked = false;
                    document.GuardarUsuario.Secretaria.checked = false;

                    document.GuardarUsuario.Estudiante.disabled = true;
                    document.GuardarUsuario.Secretaria.disabled = true;
                    return
                }
                else
                {
                    document.GuardarUsuario.materia.disabled = true;
                    
                    document.GuardarUsuario.Estudiante.disabled = false;
                    document.GuardarUsuario.Secretaria.disabled = false;
                }
                
                if(document.GuardarUsuario.Secretaria.checked == true)
                {
                    document.GuardarUsuario.Estudiante.checked = false;
                    document.GuardarUsuario.Analista.checked = false;
                    document.GuardarUsuario.Tutor.checked = false;

                    document.GuardarUsuario.Estudiante.disabled = true;
                    document.GuardarUsuario.Tutor.disabled = true;
                    document.GuardarUsuario.Analista.disabled = true;
                    return;
                }
                else
                {
                    document.GuardarUsuario.Estudiante.disabled = false;
                    document.GuardarUsuario.Tutor.disabled = false;
                    document.GuardarUsuario.Analista.disabled = false;
                }

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

                <%
                LinkedList<String> allowedUsers;
                allowedUsers = new LinkedList<String>();
                allowedUsers.add("com.liceoval.businesslayer.entities.SecretariaAcademica");
                %>
                
                <%@include file="globals/bad-login.jsp" %>
                
                <%
				if(currentUser != null && allowed == true)
                {
                %>
                    <table border="0" cellpadding="0" cellspacing="0" width="550">
                        <tr height="30"><td><img src="images/title-left.png" /></td>
                            <td class="title-center" width="100%">Creación de Usuarios</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">
                                
                                <form name="GuardarUsuario" action="GuardarUsuario" method="POST">
                                <br />
                                <p style="text-align:justify"><b>ESTE FORMULARIO LE PERMITIRÁ AGREGAR USUARIOS DE TIPO ESTUDIANTE, TUTOR, ANALISTA Y SECRETARÍA ACADÉMICA.</b></p>
                                <br>
                                
								<%
                                if(session.getAttribute("crearError") != null)
                                {
                                %>
								    
                                <table align="center" border="0" cellpading="0" cellspacing="0" width="80%">
									<tr><td class="login-error"><%=session.getAttribute("crearError")%></td></tr></table><br />&nbsp;
									
                                 <%
                                 session.removeAttribute("crearError");
                                 }
                                 %>
                                 
                                 <b><u>IMPORTANTE</u></b><br>
                                 <ul>
                                     <li><i>Una vez seleccionados los roles, se activarán los campos correspondientes (Numerales 1-5).</i></li>
                                     <li><i>El rol Secretaria Académica <u>NO</u> contiene campos adicionales, solamente es necesario llenar los datos generales (Numeral 2).</i></li>
                                     <li><i>No ingrese caracteres diferentes a números en los campos de códigos e identificaciones.</i></li>
                                     <li><i>Recuerde que todos los campos son <b><u>OBLIGATORIOS</u></b></i></li>
                                 </ul>
                                <br>
                                <center><img src="images/Writing_emoticon_by_eburt.gif" width="30" height="30" alt="Writing_emoticon_by_eburt"/></center>
                                <br><br>
                                
                                <ol>
                                <li>Seleccione los roles del nuevo usuario:</li>
                                <br>
                                    <input type="checkbox" name="Estudiante" value="ON" onchange="enabledisable()"/>Estudiante<br />
                                    <input type="checkbox" name="Tutor" value="ON" onchange="enabledisable()"/>Tutor<br />
                                    <input type="checkbox" name="Analista" value="ON" onchange="enabledisable()"/>Analista<br />
                                    <input type="checkbox" name="Secretaria" value="ON" onchange="enabledisable()"/>Secretaría Académica<br /><br />
                                    
                                    <li>Datos generales del <b>Usuario</b>:</li><br>
                                    
                                    <table>
                                    <tr><td>Nombres:</td><td><input type="text" maxlength="30" name="Nombres" value="" size="30"disabled="disabled" /></td></tr>
                                    <tr><td>Apellidos:</td><td><input type="text" maxlength="30" name="Apellidos" value="" size="30"disabled="disabled" /></td></tr>
                                    <tr><td>Login:</td><td><input type="text" maxlength="20" name="Login" value="" size="20"disabled="disabled" /></td></tr>
                                    <tr><td>Clave:</td><td><input type="password" maxlength="14" name="Clave" value="" size="20"disabled="disabled" /></td></tr>
                                    <tr><td>Confirmar Clave:</td><td><input type="password" maxlength="14" name="Cclave" value="" size="20"disabled="disabled" /></td></tr>
                                    </table>
                                    
                                   <br>
                                 
                                 <li>Datos Adicionales de <b>Estudiante</b>:
                                 <br>
                                 <table>
                                 <tr><td>Código:</td><td><input type="text" maxlength="3" name="codigo" value="" size="10" disabled="disabled" /></td></tr>
                                 <tr><td>Grado:</td><td><input type="text" name="grado" value="" size="10" disabled="disabled" /></td></tr>
                                 <tr><td>Taller:</td><td>
                                 <select name="taller" disabled="disabled">
                                        
                                        <%
                                        List<Taller> taller;
                                        Iterator ite;
                                        Taller ta;
                                        taller=RCPRegistros.getTalleres();
                                        ite=taller.iterator();
                                        while(ite.hasNext())
                                            {
                                                ta=(Taller)ite.next();
                                          %>
                                          <option value="<%=ta.getIdTaller()%>"><%=ta.getIdTaller()%></option>
                                          <%
                                            }
                                          %>
                                 </select>
                                 </td></tr>
                                 <tr><td>Fecha Inicio Grado:</td><td><input type="text" maxlength="10" name="fecha" value="" size="10" disabled="disabled" />(aaaa-mm-dd)</td></tr>
                                 </table>
                                 <br>
                                 <ul>
                                     <li><b><i>Datos del Acudiente</i></b></li>
                                     
                                     <table>
                                     <tr><td>Identificación(CC):</td><td><input type="text" maxlength="10" name="pidentificacion" value="" size="15" disabled="disabled" /></td></tr>
                                     <tr><td>Nombres:</td><td><input type="text" name="pnombres" maxlength="30" value="" size="30" disabled="disabled" /></td></tr>
                                     <tr><td>Apellidos:</td><td><input type="text" name="papellidos" maxlength="30" value="" size="30" disabled="disabled" /></td></tr>
                                     <tr><td>Correo Electrónico:</td><td><input type="text" maxlength="20" name="pcorreo" value="" size="30" disabled="disabled" /></td></tr>
                                     </table><br>
                                     
                                 </ul>
                                </li>
                                
                                <li>Datos Adicionales de <b>Tutor</b></li><br>
                                    
                                    <table>
                                    <tr><td>Taller:</td><td>
                                    
                                     <select name="tallert" disabled="disabled">
                                        
                                        <%
                                        taller=RCPRegistros.getTalleres();
                                        ite=taller.iterator();
                                        while(ite.hasNext())
                                            {
                                                ta=(Taller)ite.next();
                                          %>
                                          <option value="<%=ta.getIdTaller()%>"><%=ta.getIdTaller()%></option>
                                          <%
                                            }
                                          %>
                                          <option value="CT">Crear Taller...</option>
                                        </select>
                                    
                                    </td></tr>
                                    </table><br>
                                
                                <li>Datos Adicionales de <b>Analista</b></li><br>
                                    <table>
                                    <tr><td>Materia:</td><td>
                                        
                                        <select name="materia" multiple="multiple" disabled="disabled" size="5">
                                        
                                        <%
                                        List<Materia> mat;
                                        Iterator it;
                                        Materia materia;
                                        mat=RCPRegistros.getMaterias();
                                        it=mat.iterator();
                                        while(it.hasNext())
                                            {
                                                materia=(Materia)it.next();
                                          %>
                                          <option value="<%=materia.getCodigo()%>"><%=materia.getCodigo() + "-" + materia.getNombre()%></option>
                                          <%
                                            }
                                          %>
                                        </select>     
                                        
                                    </td></tr>
                                    </table>
                                 
                                    <br /><br />
                                    <center><input type="submit" value="Agregar" name="Enviar" disabled="disabled" /> <input type="reset" value="Limpiar" name="Limpiar" /></center>
                                </ol>
                                
                                </form>

                                </td></tr></table>
                        </td></tr>
                    </table>
                <%
			}
		%>
                
            </td><td>
        
        	<!-- Menú derecho -->
            <%@include file="globals/right-menu.jsp" %>
        
        </td></tr>
	</table>

</body>
</html>
