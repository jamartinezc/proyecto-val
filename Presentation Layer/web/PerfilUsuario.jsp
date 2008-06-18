<%-- 
    Document   : ListaUsuarios
    Created on : 20/04/2008, 01:35:52 AM
    Author     : Administrador
--%>

<%@ page
	contentType="text/html; charset=utf-8"
	language="java"
	import="java.util.Iterator,
                java.util.List,
                java.util.LinkedList,
                com.liceoval.businesslayer.control.AdministradoraDeUsuarios,
                com.liceoval.businesslayer.entities.Usuario,
                com.liceoval.businesslayer.entities.Materia,
                com.liceoval.businesslayer.control.rcp.RCPRegistros,
                com.liceoval.businesslayer.entities.Taller,
                com.liceoval.businesslayer.control.exceptions.NoSeEncuentraElUsuarioException,
                com.liceoval.businesslayer.entities.Estudiante,
                com.liceoval.businesslayer.entities.wrappers.UsuarioWrapper,
                com.liceoval.businesslayer.entities.Grado,
                java.util.Calendar,
                java.util.Date,
                com.liceoval.businesslayer.entities.Padre"
	errorPage="" %>

<%
    UsuarioWrapper usuarioWrapper = null;
    String action;
    int userId = 0;

    String estudianteCheckString = "";
    String tutorCheckString = "";
    String analistaCheckString = "";
    String secretariaCheckString = "";
    
    final String checkString = "checked=\"checked\"";
    final String disableString = "disabled=\"disabled\"";
    
    String nombresString = "";
    String apellidosString = "";
    String loginString = "";
    String claveString = "";

    String codigoString = "";
    int gradoInteger = 0;
    int tallerInteger = 0;
    String fechaString = "";
    String acudienteCCString = "";
    String acudienteNombresString = "";
    String acudienteApellidosString = "";
    String acudienteCorreoString = "";
    
    String nombresDisableString = disableString;
    String apellidosDisableString = disableString;
    String loginDisableString = disableString;
    String claveDisableString = disableString;
    
    String codigoDisableString = disableString;
    String gradoDisableString = disableString;
    String tallerDisableString = disableString;
    String fechaDisableString = disableString;
    String acudienteCCDisableString = disableString;
    String acudienteNombresDisableString = disableString;
    String acudienteApellidosDisableString = disableString;
    String acudienteCorreoDisableString = disableString;
    
    List<Padre> acudientes;
    Iterator<Padre> acudientesIterator;
    Padre acudiente;
    
    Date fechaInicioGrado;
    Calendar calendar;
    String year;
    String month;
    String day;

    String retrievedParameter;
    
    String titleText = "Crear";
    String formAction = "GuardarUsuario?action=new";
    String submitDisableString = disableString;
    String submitText = "Agregar";
    
    boolean hasRole = false;
    
    // Verifica la acción a realizar
    action = request.getParameter("action");
    if(action == null) action = "new";
    
    if(!(action.equals("edit") || action.equals("new")))
    {
        action = "new";
    }
    
    // Si la acción es edit:
    if(action.equals("edit"))
    {
        // Recuperar el Id de usuario
        try
        {
            userId = Integer.parseInt(request.getParameter("userid"));
            
            try
            {    
                // Cargar los datos del usuario desde la base de datos.
                usuarioWrapper = AdministradoraDeUsuarios.cargarUsuario(userId);
            }
            catch(NoSeEncuentraElUsuarioException nseeuEx)
            {
                // No se encontró el usuario en la base de datos. Asumimos que
                // el usuario pasó basuro por la cadena de consulta y asumimos
                // que se va a crear un nuevo usuario
                action="new";
            }
            
        }
        catch(NumberFormatException nfEx)
        {
            // Como el Id de usuario especificado no es válido entonces
            // asumimos que el usuario pasó basura, interpretamos como si
            // fuermaos a crear un nuevo usuario
            action = "new";
        }
    }
    
    if(action.equals("edit") && usuarioWrapper != null)
    {   
        // Verificar que el usuario tenga al menos un rol
        hasRole = hasRole | usuarioWrapper.isAnalista();
        hasRole = hasRole | usuarioWrapper.isEstudiante();
        hasRole = hasRole | usuarioWrapper.isSecretariaAcademica();
        hasRole = hasRole | usuarioWrapper.isTutor();
        
        if(usuarioWrapper.isAnalista()) analistaCheckString = checkString;
        if(usuarioWrapper.isEstudiante()) estudianteCheckString = checkString;
        if(usuarioWrapper.isTutor()) tutorCheckString = checkString;
        if(usuarioWrapper.isSecretariaAcademica()) secretariaCheckString = checkString;

        nombresString = usuarioWrapper.getUsuario().getNombres();
        apellidosString = usuarioWrapper.getUsuario().getApellidos();
        loginString = usuarioWrapper.getUsuario().getLogin();
        claveString = usuarioWrapper.getUsuario().getPassword().toString();

        nombresDisableString = "";
        apellidosDisableString = "";
        loginDisableString = "";
        claveDisableString = "";

        if(usuarioWrapper.isEstudiante())
        {
            codigoString = String.valueOf(((Estudiante)usuarioWrapper.getUsuario()).getCodigo());
            gradoInteger = ((Estudiante)usuarioWrapper.getUsuario()).getGrado().getIdGrado();
            tallerInteger = ((Estudiante)usuarioWrapper.getUsuario()).getTaller().getIdTaller();

            fechaInicioGrado = ((Estudiante)usuarioWrapper.getUsuario()).getFechaInicioGrado();
            calendar = Calendar.getInstance();
            calendar.setTime(fechaInicioGrado);

            year = String.valueOf(calendar.get(Calendar.YEAR));
            month = String.valueOf(calendar.get(Calendar.MONTH)+1);
            if(month.length() < 2) month = "0" + month;
            day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            if(day.length() < 2) day = "0" + day;

            fechaString = year +
                "-" + month +
                "-" + day;

            acudientes = ((Estudiante)usuarioWrapper.getUsuario()).getPadres();
            if(acudientes != null)
            {
                acudientesIterator = acudientes.iterator();
                if(acudientesIterator.hasNext())
                {
                    acudiente = acudientesIterator.next();
                    acudienteCCString = String.valueOf(acudiente.getIdPadre());
                    acudienteNombresString = acudiente.getNombres();
                    acudienteApellidosString = acudiente.getApellidos();
                    acudienteCorreoString = acudiente.getCorreo();
                }
            }

            codigoDisableString = "";
            gradoDisableString = "";
            tallerDisableString = "";
            fechaDisableString = "";
            acudienteCCDisableString = "";
            acudienteNombresDisableString = "";
            acudienteApellidosDisableString = "";
            acudienteCorreoDisableString = "";
        }

        titleText="Actualizar";
        formAction="GuardarUsuario?action=edit&userid=" + String.valueOf(userId);
        
        if(hasRole)
        {
            submitDisableString="";
        }
        
        submitText = "Guardar";
    }
    else
    {
        retrievedParameter = request.getParameter("Estudiante");
        if(retrievedParameter != null && retrievedParameter.equals("ON"))
            estudianteCheckString = checkString;
        
        retrievedParameter = request.getParameter("Tutor");
        if(retrievedParameter != null && retrievedParameter.equals("ON"))
            tutorCheckString = checkString;
        
        retrievedParameter = request.getParameter("Analista");
        if(retrievedParameter != null && retrievedParameter.equals("ON"))
            analistaCheckString = checkString;
        
        retrievedParameter = request.getParameter("Secretaria");
        if(retrievedParameter != null && retrievedParameter.equals("ON"))
            secretariaCheckString = checkString;
        
        retrievedParameter = request.getParameter("Nombres");
        if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
            nombresString = retrievedParameter;
        
        retrievedParameter = request.getParameter("Apellidos");
        if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
            apellidosString = retrievedParameter;
        
        retrievedParameter = request.getParameter("Login");
        if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
            loginString = retrievedParameter;
        
        retrievedParameter = request.getParameter("codigo");
        if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
            codigoString = retrievedParameter;

        calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        year = String.valueOf(calendar.get(Calendar.YEAR));
        month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        if(month.length() < 2) month = "0" + month;
        day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if(day.length() < 2) day = "0" + day;

        fechaString = year +
            "-" + month +
            "-" + day;
        
        if(estudianteCheckString == checkString)
        {
            retrievedParameter = request.getParameter("codigo");
            if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
                codigoString = retrievedParameter;
            
            retrievedParameter = request.getParameter("grado");
            if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
            {
                try{ gradoInteger = Integer.parseInt(retrievedParameter); }
                catch(NumberFormatException nfEx) { }
            }
            
            retrievedParameter = request.getParameter("taller");
            if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
            {
                try{ tallerInteger = Integer.parseInt(retrievedParameter); }
                catch(NumberFormatException nfEx) { }
            }
            
            retrievedParameter = request.getParameter("fecha");
            if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
                fechaString = retrievedParameter;
            
            retrievedParameter = request.getParameter("pidentificacion");
            if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
                acudienteCCString = retrievedParameter;
            
            retrievedParameter = request.getParameter("pnombres");
            if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
                acudienteNombresString = retrievedParameter;
            
            retrievedParameter = request.getParameter("papellidos");
            if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
                acudienteApellidosString = retrievedParameter;
            
            retrievedParameter = request.getParameter("pcorreo");
            if(retrievedParameter != null && !retrievedParameter.trim().equals(""))
                acudienteCorreoString = retrievedParameter;
        }
    }
%>

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
                    document.GuardarUsuario.Estudiante.checked = false;
                    document.GuardarUsuario.Secretaria.checked = false;

                    document.GuardarUsuario.Estudiante.disabled = true;
                    document.GuardarUsuario.Secretaria.disabled = true;
                }    
                else
                {
                    document.GuardarUsuario.Estudiante.disabled = false;
                    document.GuardarUsuario.Secretaria.disabled = false;
                }    
                    
                
                if(document.GuardarUsuario.Analista.checked == true)
                {
                    document.GuardarUsuario.Estudiante.checked = false;
                    document.GuardarUsuario.Secretaria.checked = false;

                    document.GuardarUsuario.Estudiante.disabled = true;
                    document.GuardarUsuario.Secretaria.disabled = true;
                    return
                }
                else
                {
                    if(document.GuardarUsuario.Tutor.checked == false)
                    {
                        document.GuardarUsuario.Estudiante.disabled = false;
                        document.GuardarUsuario.Secretaria.disabled = false;
                    }
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
                    if(document.GuardarUsuario.Tutor.checked == false && document.GuardarUsuario.Analista.checked == false)
                    {
                        document.GuardarUsuario.Estudiante.disabled = false;
                    }
                    document.GuardarUsuario.Tutor.disabled = false;
                    document.GuardarUsuario.Analista.disabled = false;
                }

	}
         
      

</script>


</head>

<body onload="enabledisable()">

    <%
        String locationLinks;
        
        locationLinks="<a class=\"nav-bar-link\" href=\"index.jsp\">Inicio</a> - <a class=\"nav-bar-link\" href=\"ListaUsuarios.jsp\">Administrar Usuarios</a>";
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
                            <td class="title-center" width="100%"><%=titleText%> Usuario</td>
                            <td><img src="images/title-right.png" /></td></tr>
                            
                        <tr><td class="cont-outer" colspan="3">
                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                <tr><td class="cont-inner">
                                
                                <form name="GuardarUsuario" action="<%=formAction%>" method="POST" accept-charset="ISO-8859-1">
                                <br />
                                <p style="text-align:center"><b>ESTE FORMULARIO LE PERMITIRÁ AGREGAR USUARIOS DE TIPO ESTUDIANTE, TUTOR, ANALISTA Y SECRETARÍA ACADÉMICA.</b></p>
                                <br>
                                
                                <%
                                if(session.getAttribute("createError") != null)
                                {
                                %>
								    
                                <table align="center" border="0" cellpading="0" cellspacing="0" width="80%">
									<tr><td class="login-error"><%=session.getAttribute("createError")%></td></tr></table><br />&nbsp;
									
                                <%
                                session.removeAttribute("createError");
                                }
                                %>
                                 
                                 <b><u>IMPORTANTE</u></b><br>
                                 <ul>
                                     <li><i>Una vez seleccionados los roles, se activarán los campos correspondientes (Numerales 1-3).</i></li>
                                     <li><i>Los roles diferentes a Estudiante <u>NO</u> contienen campos adicionales, solamente es necesario llenar los datos generales (Numeral 2).</i></li>
                                     <li><i>No ingrese caracteres diferentes a números en los campos de códigos e identificaciones.</i></li>
                                     <li><i>Recuerde que todos los campos son <b><u>OBLIGATORIOS</u></b></i></li>
                                 </ul>
                                <br>
                                <center><img src="images/Writing_emoticon_by_eburt.gif" width="30" height="30" alt="Writing_emoticon_by_eburt"/></center>
                                <br><br>
                                
                                <ol>
                                <li>Seleccione los roles del nuevo usuario:</li>
                                <br>
                                    <input type="checkbox" name="Estudiante" value="ON" <%=estudianteCheckString%> onchange="enabledisable()"/>Estudiante<br />
                                    <input type="checkbox" name="Tutor" value="ON" <%=tutorCheckString%> onchange="enabledisable()"/>Tutor<br />
                                    <input type="checkbox" name="Analista" value="ON" <%=analistaCheckString%> onchange="enabledisable()"/>Analista<br />
                                    <input type="checkbox" name="Secretaria" value="ON" <%=secretariaCheckString%> onchange="enabledisable()"/>Secretaría Académica<br /><br />
                                    
                                    <li>Datos generales del <b>Usuario</b>:</li><br>
                                    
                                    <table>
                                    <tr><td>Nombres:</td><td><input type="text" maxlength="30" name="Nombres" value="<%=nombresString%>" size="30" <%=nombresDisableString%> /></td></tr>
                                    <tr><td>Apellidos:</td><td><input type="text" maxlength="30" name="Apellidos" value="<%=apellidosString%>" size="30" <%=apellidosDisableString%> /></td></tr>
                                    <tr><td>Login:</td><td><input type="text" maxlength="20" name="Login" value="<%=loginString%>" size="20" <%=loginDisableString%> /></td></tr>
                                    <tr><td>Clave:</td><td><input type="password" maxlength="14" name="Clave" value="<%=claveString%>" size="20" <%=claveDisableString%> /></td></tr>
                                    <tr><td>Confirmar Clave:</td><td><input type="password" maxlength="14" name="Cclave" value="<%=claveString%>" size="20" <%=claveDisableString%> /></td></tr>
                                    </table>
                                    
                                   <br>
                                 
                                 <li>Datos Adicionales de <b>Estudiante</b>:
                                 <br>
                                 <table>
                                 <tr><td>Código:</td><td><input type="text" maxlength="3" name="codigo" value="<%=codigoString%>" size="10" <%=codigoDisableString%> /></td></tr>
                                 <tr><td>Grado:</td><td><select name="grado" <%=gradoDisableString%>>

                                    <%
                                    List<Grado> grados;
                                    Iterator<Grado> gradosIterator;
                                    Grado grado;
                                    
                                    grados = RCPRegistros.getGrados();
                                    gradosIterator = grados.iterator();
                                    
                                    while(gradosIterator.hasNext())
                                    {
                                        grado = gradosIterator.next();
                                        
                                        if(grado.getIdGrado() == gradoInteger)
                                        {
                                    %>
                                            
                                            <option value="<%=grado.getIdGrado()%>" selected="selected"><%=grado.getNombre()%></option>
                                            
                                    <%
                                        }
                                        else
                                        {
                                    %>
                                            
                                            <option value="<%=grado.getIdGrado()%>"><%=grado.getNombre()%></option>
                                            
                                    <%
                                        }
                                    }
                                    %>
                                    
                                 </select></td></tr>
                                 <tr><td>Taller:</td><td>
                                 <select name="taller" <%=tallerDisableString%>>
                                        
                                        <%
                                        List<Taller> taller;
                                        Iterator ite;
                                        Taller ta;
                                        taller=RCPRegistros.getTalleres();
                                        ite=taller.iterator();
                                        while(ite.hasNext())
                                            {
                                                ta=(Taller)ite.next();
                                                if(ta.getIdTaller() == tallerInteger)
                                                {
                                          %>
                                                    
                                                    <option value="<%=ta.getIdTaller()%>" selected="selected"><%=ta.getIdTaller()%></option>
                                                    
                                          <%
                                                }
                                                else
                                                {
                                          %>
                                          
                                                    <option value="<%=ta.getIdTaller()%>"><%=ta.getIdTaller()%></option>
                                          
                                          <%        
                                                }
                                             }
                                          %>
                                 </select>
                                 </td></tr>
                                 <tr><td>Fecha Inicio Grado:</td><td><input type="text" maxlength="10" name="fecha" value="<%=fechaString%>" size="10" <%=fechaDisableString%> /> (aaaa-mm-dd)</td></tr>
                                 </table>
                                 <br>
                                 <ul>
                                     <li><b><i>Datos del Acudiente</i></b></li>
                                     
                                     <table>
                                     <tr><td>Identificación(CC):</td><td><input type="text" maxlength="10" name="pidentificacion" value="<%=acudienteCCString%>" size="15" <%=acudienteCCDisableString%> /></td></tr>
                                     <tr><td>Nombres:</td><td><input type="text" name="pnombres" maxlength="30" value="<%=acudienteNombresString%>" size="30" <%=acudienteNombresDisableString%> /></td></tr>
                                     <tr><td>Apellidos:</td><td><input type="text" name="papellidos" maxlength="30" value="<%=acudienteApellidosString%>" size="30" <%=acudienteApellidosDisableString%> /></td></tr>
                                     <tr><td>Correo Electrónico:</td><td><input type="text" maxlength="150" name="pcorreo" value="<%=acudienteCorreoString%>" size="30" <%=acudienteCorreoDisableString%> /></td></tr>
                                     </table><br>
                                     
                                 </ul>
                                </li>
                                 
                                    <br /><br />
                                    <center><input type="submit" value="<%=submitText%>" name="Enviar" <%=submitDisableString%> /> <input type="reset" value="Limpiar" name="Limpiar" /></center>
                                </ol>
                                
                                </form>

                                </td></tr></table>
                        </td></tr>
                    </table>
                <%
                }
		%>
                
            </td><td>
        
            <%
                // Texto de ayuda para está página
                StringBuffer rightPanelText;
                rightPanelText = new StringBuffer();
                
                if(currentUser != null)
                {                
                    rightPanelText.append("<p style=\"font-weight:bold;text-decoration:underline\">Perfil de Usuario</p>");

                    rightPanelText.append("<p>Esta página le permite introducir o modificar los datos de un usuario, para hacerlo:.</p>");
                    rightPanelText.append("<ol>");
                    rightPanelText.append(" <li>Seleccione el Rol o Roles que desempeñara el usuario.<br>&nbsp;<br><i>Recuerde que existen combinaciones de roles que no son válidas. Las casillas se activaran o desactivaran de forma automática y solo le permitirán seleccionar los roles compatibles.</i><br>&nbsp;</li>");
                    rightPanelText.append(" <li>Llene los campos indicados para el rol o roles escogidos. Los roles diferentes a estudiante <u>NO</u> necesitan información en los campos del numeral 3.<br>&nbsp;</li>");
                    rightPanelText.append(" <li>Una vez introducidos los datos haga clic en <b>Agregar</b> para agregar el nuevo usuario.</li>");
                    rightPanelText.append("</ol>");
                    rightPanelText.append("<p>Si se presenta algún error al momento de crear el nuevo usuario aparecra un mensaje en la parte superior de la página indicandole los campos que debe corregir.</p>");
                }
                else
                {
                    rightPanelText.append("<p style=\"font-weight:bold;text-decoration:underline\">Acceso no autorizado</p>");
                    rightPanelText.append("<p>Usted ha ingresado a un área restringida, debe iniciar sesión en el sistema para poder ver el contenido de está página. Use el panel de la izquierda para iniciar sesión con su nombre de usuario y contraseña.</p>");
                }

                rightContent = rightPanelText.toString();
            %>
        
            <!-- Menú derecho -->
            <%@include file="globals/right-menu.jsp" %>
        
        </td></tr>
	</table>

</body>
</html>
