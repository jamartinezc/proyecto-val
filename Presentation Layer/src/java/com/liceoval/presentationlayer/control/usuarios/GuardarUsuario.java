package com.liceoval.presentationlayer.control.usuarios;

import com.liceoval.businesslayer.control.AdministradoraDeUsuarios;
import com.liceoval.businesslayer.control.exceptions.CodigoDeEstudianteYaExisteException;
import com.liceoval.businesslayer.control.exceptions.LoginYaExisteException;
import com.liceoval.businesslayer.control.exceptions.NoSeEncuentraElUsuarioException;
import com.liceoval.businesslayer.control.exceptions.PadreYaExisteException;
import com.liceoval.businesslayer.control.exceptions.PeticionEjecutadaParcialmenteException;
import com.liceoval.businesslayer.control.exceptions.RegistroDuplicadoInesperadoException;

import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.Grado;
import com.liceoval.businesslayer.entities.Usuario;
import com.liceoval.businesslayer.entities.Padre;
import com.liceoval.businesslayer.entities.Taller;
import com.liceoval.businesslayer.entities.wrappers.UsuarioWrapper;

import com.liceoval.presentationlayer.control.util.RequestForwarder;
import com.liceoval.presentationlayer.control.util.ErrorSetter;
import com.liceoval.presentationlayer.control.util.Validaciones;

import java.util.Date;
import java.util.LinkedList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Crea y actualiza usuarios en el sistema.
 * 
 *  @author Angela Franco
 */

public class GuardarUsuario extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        // ErrorSetter.setError("createError", "Solicitud recibida", request);
        // RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
        
        int idPadre = 0;
        int idGrado = 0;
        int idTaller = 0;
        int idUsuario = 0;
        int idEstudiante = 0;
        
        String invalidDataMessage;
        String causeString;
        
        Padre padre;
        Usuario usuario;
        Usuario currentUser;
        Estudiante estudiante;
        Grado gradoEstudiante;
        Taller tallerEstudiante;
        LinkedList<Padre> padres;
        UsuarioWrapper usuarioWrapper;
        SimpleDateFormat dateFormater;
        Date fechaInicioGrado = new Date();
                                
        currentUser=(Usuario)request.getSession().getAttribute("currentUser");
        if(currentUser==null)
        {
            RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
            return;
        }
        else
        {
            if(currentUser.getClass().getName().equals("com.liceoval.businesslayer.entities.SecretariaAcademica")==false)
            {
                RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                return;
            }
        }
        
        // Recibir Parámetros
        String userid, action, msj="", Nombres, Apellidos, Login, Clave, Cclave, fecha, grado, taller, codigo, pnombres, papellidos, pidentificacion, pcorreo, Estudiante, Analista, Secretaria, Tutor;
        
        action=request.getParameter("action");
        if(action == null || action.trim().equals(""))
            action = "new";
        
        userid=request.getParameter("userid");
        if(action.equals("edit"))
        {
            try { idUsuario = Integer.parseInt(userid); }
            catch(NumberFormatException nfEx)
            {
                ErrorSetter.setError("createError", "La petición se ha malformado. El ID de usuario recibido no es númerico.", request);
                RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                return;
            }
        }
        
        Estudiante = request.getParameter("Estudiante");
        Analista = request.getParameter("Analista");
        Tutor = request.getParameter("Tutor");
        Secretaria = request.getParameter("Secretaria");
        
        if(Secretaria == null) Secretaria = "OFF";
        if(Estudiante == null) Estudiante = "OFF";
        if(Analista == null) Analista = "OFF";
        if(Tutor == null) Tutor = "OFF";
        
        if(Estudiante.equals("ON") || Analista.equals("ON") || Tutor.equals("ON") || Secretaria.equals("ON"))
        {
            Nombres = request.getParameter("Nombres");
            Apellidos = request.getParameter("Apellidos");
            Login = request.getParameter("Login");
            Clave = request.getParameter("Clave");
            Cclave = request.getParameter("Cclave");
            
            codigo = request.getParameter("codigo");
            grado = request.getParameter("grado");
            taller = request.getParameter("taller");
            fecha = request.getParameter("fecha");
            
            pidentificacion = request.getParameter("pidentificacion");
            pnombres = request.getParameter("pnombres");
            papellidos = request.getParameter("papellidos");
            pcorreo = request.getParameter("pcorreo");
            
            // Valida los datos básicos del usuario.
            
            if(Nombres != null)
            {
                Nombres = Nombres.trim();
                if(Nombres.compareTo("")==0)
                {
                    msj=msj+"<p>* El campo <b>Nombre</b> está en blanco.</p>";
                }
                
                if(Validaciones.nombres(Nombres)==false)
                {
                    msj=msj+"<p>* El <b>Nombre</b> ingresado contiene caracteres diferentes a letras.</p>";
                }
            }
            else
            {
                msj=msj+"<p>* El campo <b>Nombre</b> está en blanco.</p>";
            }
            
            if(Apellidos != null)
            {
                Apellidos = Apellidos.trim();
                if(Apellidos.compareTo("")==0)
                {
                    msj=msj+"<p>* El campo <b>Apellido</b> está en blanco.</p>";
                }
                
                if(Validaciones.nombres(Apellidos)==false)
                {
                    msj=msj+"<p>* El <b>Apellido</b> ingresado contiene caracteres diferentes a letras.</p>";
                }
            }
            else
            {
                msj=msj+"<p>* El campo <b>Apellido</b> está en blanco.</p>";
            }

            if(Login != null)
            {
                Login = Login.trim();
                if(Login.compareTo("")==0)
                {
                    msj=msj+"<p>* El campo <b>Login</b> está en blanco.</p>";
                }
                
                if(Validaciones.nombres(Login)==false)
                {
                    msj=msj+"<p>* El <b>Login</b> ingresado contiene caracteres diferentes a letras.</p>";
                }
            }
            else
            {
                msj=msj+"<p>* El campo <b>Login</b> está en blanco.</p>";
            }
            
            if(Clave != null && Cclave != null)
            {
                Clave = Clave.trim();
                Cclave = Cclave.trim();
                
                if(Clave.compareTo("") == 0 || Cclave.compareTo("") == 0 || Validaciones.clave(Clave, Cclave) == false)
                {
                    msj=msj+"<p>* <b>Clave</b> y/o <b>Confirmar Clave</b> están en blanco o son diferentes.</p>";
                }
            }
            else
            {
                msj=msj+"<p>* <b>Clave</b> y/o <b>Confirmar Clave</b> están en blanco o son diferentes.</p>";
            }

            if(Estudiante.equals("ON"))
            {
                // Validar los datos del estudiante
                if(codigo != null)
                {
                    codigo = codigo.trim();
                    try
                    {
                        idEstudiante = Integer.parseInt(codigo);
                        if(idEstudiante <= 0) msj=msj+"<p>* El campo <b>Código</b> contiene datos inválidos. (Negativos).</p>";
                    }
                    catch(NumberFormatException nfEx)
                    {
                        msj=msj+"<p>* El campo <b>Código</b> contiene datos no númericos.</p>";
                    }
                }
                else
                {
                    msj=msj+"<p>* El campo <b>Código</b> está en blanco.</p>";
                }
                
                invalidDataMessage = "<p>* Se ha malformado la petición. El campo <b>Grado</b> contiene datos ilegales.</p>";
                
                if(grado != null)
                {
                    grado = grado.trim();
                    try
                    {
                        idGrado = Integer.parseInt(grado);
                        if(idGrado <= 0)
                        {
                            msj=msj+invalidDataMessage;
                        }
                    }
                    catch(NumberFormatException nfEx)
                    {
                        msj=msj+invalidDataMessage;
                    }
                }
                else
                {
                    msj=msj+invalidDataMessage;
                }

                invalidDataMessage = "<p>* Se ha malformado la petición. El campo <b>Taller</b> contiene datos ilegales.</p>";
                
                if(taller != null)
                {
                    taller = taller.trim();
                    try
                    {
                        idTaller = Integer.parseInt(taller);
                        if(idTaller <= 0)
                        {
                            msj=msj+invalidDataMessage;
                        }
                    }
                    catch(NumberFormatException nfEx)
                    {
                        msj=msj+invalidDataMessage;
                    }
                }
                else
                {
                    msj=msj+invalidDataMessage;
                }
                
                if(fecha != null)
                {
                    fecha = fecha.trim();
                    if(fecha.equals(""))
                    {
                        msj=msj+"<p>* El campo <b>Fecha Inicio Grado</b> está en blanco";
                    }
                    else
                    {
                        dateFormater = new SimpleDateFormat("yyyy-MM-dd");
                        try
                        {
                            fechaInicioGrado = dateFormater.parse(fecha);
                        }
                        catch(ParseException pEx)
                        {
                            msj=msj+"<p>* El formato de la fecha en <b>Fecha Inicio Grado</b> es inválido.";
                        }
                    }
                }
                else
                {
                    msj=msj+"<p>* El campo <b>Fecha Inicio Grado</b> está en blanco";
                }
                
                if(pidentificacion != null)
                {
                    pidentificacion = pidentificacion.trim();
                    try
                    {
                        idPadre = Integer.parseInt(pidentificacion);
                        if(idPadre <= 0) msj=msj+"<p>* El campo <b>Identifcación del Acudiente</b> contiene datos inválidos. (Negativos).</p>";
                    }
                    catch(NumberFormatException nfEx)
                    {
                        msj=msj+"<p>* El campo <b>Identifcación del Acudiente</b> contiene datos no númericos.</p>";
                    }
                }
                else
                {
                    msj=msj+"<p>* El campo <b>Identifcación del Acudiente</b> está en blanco.</p>";
                }
                                
                if(pnombres != null)
                {
                    pnombres = pnombres.trim();
                    if(pnombres.compareTo("")==0)
                    {
                        msj=msj+"<p>* El campo <b>Nombre del Acudiente</b> está en blanco.</p>";
                    }

                    if(Validaciones.nombres(pnombres)==false)
                    {
                        msj=msj+"<p>* El <b>Nombre del Acudiente</b> ingresado contiene caracteres diferentes a letras.</p>";
                    }
                }
                else
                {
                    msj=msj+"<p>* El campo <b>Nombre del Acudiente</b> está en blanco.</p>";
                }
                
                if(papellidos != null)
                {
                    papellidos = papellidos.trim();
                    if(papellidos.compareTo("")==0)
                    {
                        msj=msj+"<p>* El campo <b>Apellidos del Acudiente</b> está en blanco.</p>";
                    }

                    if(Validaciones.nombres(papellidos)==false)
                    {
                        msj=msj+"<p>* El <b>Apellidos del Acudiente</b> ingresado contiene caracteres diferentes a letras.</p>";
                    }
                }
                else
                {
                    msj=msj+"<p>* El campo <b>Apellidos del Acudiente</b> está en blanco.</p>";
                }
                
                if(pcorreo != null)
                {
                    pcorreo = pcorreo.trim();
                    if(pcorreo.compareTo("")==0)
                    {
                        msj=msj+"<p>* El campo <b>Correo electrónico del Acudiente</b> está en blanco.</p>";
                    }

                    if(Validaciones.correo(pcorreo)==false)
                    {
                        msj=msj+"<p>* El <b>Correo electrónico del Acudiente</b> ingresado contiene caracteres diferentes a letras.</p>";
                    }
                }
                else
                {
                    msj=msj+"<p>* El campo <<b>Correo electrónico del Acudiente</b> está en blanco.</p>";
                }
            }
            
            // Verifica si ha sucedido algún error en la validación de los datos básicos.
            if(msj.compareTo("")!=0)
            {
                msj=msj+"<p style=\"text-align:center\"><b>*****Verifique nuevamente los datos ingresados*****</b></center></p>";
                
                ErrorSetter.setError("createError", msj, request);
                RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                return;
            }
            
            usuarioWrapper = new UsuarioWrapper();
            
            usuario = new Usuario();
            usuario.setLogin(Login);
            usuario.setNombres(Nombres);
            usuario.setApellidos(Apellidos);
            usuario.setPassword(Clave.toCharArray());
            
            usuarioWrapper.setUsuario(usuario);
            
            // Verificar si se trata de un estudiante
            if(Estudiante.equals("ON"))
            {
                estudiante = new Estudiante();
                
                estudiante.setApellidos(usuario.getApellidos());
                estudiante.setLogin(usuario.getLogin());
                estudiante.setNombres(usuario.getNombres());
                estudiante.setPassword(usuario.getPassword());
                
                estudiante.setCodigo(idEstudiante);
                estudiante.setFechaInicioGrado(fechaInicioGrado);
                
                gradoEstudiante = new Grado();
                gradoEstudiante.setIdGrado(idGrado);
                
                estudiante.setGrado(gradoEstudiante);
                
                padres = new LinkedList<Padre>();
                padre = new Padre();
                
                padre.setApellidos(papellidos);
                padre.setCorreo(pcorreo);
                padre.setIdPadre(idPadre);
                padre.setNombres(pnombres);
                
                padres.add(padre);
                
                estudiante.setPadres(padres);
                
                tallerEstudiante = new Taller();
                tallerEstudiante.setIdTaller(idTaller);
                
                estudiante.setTaller(tallerEstudiante);
                
                usuarioWrapper.setUsuario(estudiante);
                usuarioWrapper.setRolEstudiante(true);
            }
            
            if(Analista.equals("ON")) usuarioWrapper.setRolAnalista(true);
            if(Tutor.equals("ON")) usuarioWrapper.setRolTutor(true);
            if(Secretaria.equals("ON")) usuarioWrapper.setRolSecretariaAcademica(true);
            
            if(action.equals("new"))
            {
                try
                {
                    AdministradoraDeUsuarios.crearUsuario(usuarioWrapper);
                    
                    ErrorSetter.setError("userCreated", "<p>Se ha creado exitosamente el " +
                            "usuario <b>" + Nombres + " " + Apellidos + "</b>.</p>", request);
                    RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                    return;
                }
                catch(LoginYaExisteException lyeEx)
                {
                    ErrorSetter.setError("createError", "El login <b>" + Login + "</b>. ya existe en la base de datos.", request);
                    RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                    return;
                }
                catch(CodigoDeEstudianteYaExisteException cdeyaEx)
                {
                    ErrorSetter.setError("userCreated", "<p>Se ha creado exitosamente el " +
                            "usuario <b>" + Nombres + " " + Apellidos + "</b>. Sin embargo no se " +
                            "pudo registrar como estudiante ya que el código ya existe en la " +
                            "base de datos.</p>\n", request);
                    RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                    return;
                }
                catch(PadreYaExisteException pyeEx)
                {
                    ErrorSetter.setError("userCreated", "<p>Se ha creado exitosamente el " +
                            "usuario <b>" + Nombres + " " + Apellidos + "</b>. Sin embargo no se " +
                            "ha podido almacenar la información de su acudiente ya que un acudiente " +
                            "con la misma identificación ya ha sido registrado en la base de datos.</p>\n", request);
                    RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                    return;
                }
                catch(PeticionEjecutadaParcialmenteException pepEx)
                {
                    causeString = "";
                    if(pepEx.getCause() != null)
                    {
                        causeString = "<p><b>Causa:</b><br>" + pepEx.getCause().toString() + "</p>";
                    }

                    ErrorSetter.setError("userCreated", "<p>Se ha creado exitosamente el " +
                            "usuario <b>" + Nombres + " " + Apellidos + "</b> Sin embargo no se " +
                            "pudieron establecer los permisos de acceso.</p>\n" +
                            causeString, request);
                    RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                    return;
                }
                catch(NullPointerException npEx)
                {
                    ErrorSetter.setError("createError", "<p>Se ha malformado la petición, la creación de usuario " +
                            " ha fallado debido a la falla en la transferencia de los datos de uno de los " +
                            "campos.</p>\n", request);
                    RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                    return;
                }
            }
            else if(action.equals("edit"))
            {
                try
                {
                    usuarioWrapper.getUsuario().setIdUsuario(idUsuario);
                    AdministradoraDeUsuarios.modificarUsuario(usuarioWrapper);

                    ErrorSetter.setError("userCreated", "<p>Se ha actualizado exitosamente el " +
                            "usuario <b>" + Nombres + " " + Apellidos + "</b>.</p>", request);
                    RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                    return;
                }
                catch(NoSeEncuentraElUsuarioException nseeuEx)
                {
                    ErrorSetter.setError("createError", "<p>No se puede actualizar el usuario. "+
                            "La base de datos no puede encontrar al usuario especificado.</p>\n", request);
                    RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
                    return;
                }
                catch(RegistroDuplicadoInesperadoException rdiEx)
                {
                    ErrorSetter.setError("userCreated", "<p>Se ha actualizado exitosamente el " +
                            "usuario <b>" + Nombres + " " + Apellidos + "</b>, sin embargo "+
                            "no se pudieron establecer uno a todos los roles debido a un" +
                            "error inesperado de la base de datos</p>\n<p><b>Causa:</b><br>" +
                            rdiEx.toString() + "</p>", request);
                    RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                    return;
                }
                catch(PeticionEjecutadaParcialmenteException pepEx)
                {
                    causeString = "";
                    if(pepEx.getCause() != null)
                    {
                        causeString = "<p><b>Causa:</b><br>" + pepEx.getCause().toString() + "</p>";
                    }

                    ErrorSetter.setError("userCreated", "<p>Se ha actualizado exitosamente el " +
                            "usuario <b>" + Nombres + " " + Apellidos + "</b> Sin embargo no se " +
                            "pudieron establecer uno o la totalidad de los roles.</p>\n" +
                            causeString, request);
                    RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                    return;
                }
                catch(CodigoDeEstudianteYaExisteException cdeyaEx)
                {
                    ErrorSetter.setError("userCreated", "<p>Se ha actualizado exitosamente el " +
                            "usuario <b>" + Nombres + " " + Apellidos + "</b>. Sin embargo no se " +
                            "pudo registrar como estudiante ya que el código ya existe en la " +
                            "base de datos.</p>\n", request);
                    RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                    return;
                }
                catch(PadreYaExisteException pyeEx)
                {
                    ErrorSetter.setError("userCreated", "<p>Se ha actualizado exitosamente el " +
                            "usuario <b>" + Nombres + " " + Apellidos + "</b>. Sin embargo no se " +
                            "ha podido almacenar la información de su acudiente ya que un acudiente " +
                            "con la misma identificación ya ha sido registrado en la base de datos.</p>\n", request);
                    RequestForwarder.forwardRequest(request, response, "ListaUsuarios.jsp");
                    return;
                }
            }
        }
        else
        {
            ErrorSetter.setError("createError", "No se puede crear el usuario. No se ha escogido un rol para el mismo.", request);
            RequestForwarder.forwardRequest(request, response, "PerfilUsuario.jsp");
            return;
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        // Simplemente llamar a doPost y pasarle el request y el response.
        doPost(request, response);
    }    
}
