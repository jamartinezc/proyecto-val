/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.presentationlayer.control.login;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.liceoval.businesslayer.control.login.ControladorDeLogIn;
import com.liceoval.businesslayer.entities.Usuario;

/** Se encarga de los procesos de Log-in y Log-out del sistema, autentica los
 *  usuarios contra la capa de negocios de la aplicación y crea las variables
 *  de sesión correspondientes.
 *
 * @author Sergio
 */
public class LoginManager extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        String user;
        String password;
        String role;
        String action;
        
        RequestDispatcher rd;
        
        // Verificar que se ha recibido una acción
        action = request.getParameter("action");
        if(action == null)
        {
            // Establecer la variable de error de log-in y desviar la petición
            setLogInError("No se especificó una acción para el Administrador de Inicio de Sesión", request);
            requestForward(request, response, "index.jsp");
            return;
        }
        
        // Verificar si se trata de un Log-in
        if(action.equals("login"))
        {
            // Obtener los parámetros de inicio de sesión
            user = request.getParameter("user");
            password = request.getParameter("password");
            role = request.getParameter("role");
           
            if(user == null || password == null || role == null)
            {
                // Establecer la variable de error de log-in y desviar la petición
                setLogInError("Hace falta el nombre de usuario, la contraseña o el rol de inicio de sesión.", request);
                requestForward(request, response, "index.jsp");
            }
            
            // Realizar el Log-in
            doLogIn(user, password, role, request);
        }
        else
        {
            // Asumir que se trata de un log-out y hacer log-out
            doLogOut(request);
        }
        
        // Desviar la petición
        requestForward(request, response, "index.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        // Simplemente llamar a doPost y pasarle el request y el response.
        doPost(request, response);
    }
    
    /** Elimina la variable de sesión que contiene la información del usuario.
     * 
     *  @param request El HttpServletRequest que se debe usar para obtener la sesion.
     */
    
    private void doLogOut(HttpServletRequest request)
    {
        // Verificar si existe un usuario registrado en la sesión
        if(request.getSession().getAttribute("currentUser") != null)
        {
            // Remover el usuario de la sesión
            request.getSession().removeAttribute("currentUser");
        }
    }
    
    /** Autentica al usuario especificado contra la capa de negocios y crea la variable de
     *  sesión que mantiene la información del usuario a lo largo de toda la sesión.
     * 
     *  @param user El nombre del usuario
     *  @param password La conraseña del usuario
     *  @param role El rol que el usuario ha seleccionado para iniciar sesión
     *  @param request El HttpServletRequest que se debe usar para obtener la sesión.
     */
    
    private void doLogIn(String user, String password, String role, HttpServletRequest request)
    {
        ControladorDeLogIn loginController;
        Usuario logedUser;
        int roleConstant;

        // Convertir la cadena del rol a una constante de ControladorDeLogIn
        if(role.equals("tutor"))
        {
            roleConstant = ControladorDeLogIn.ROLE_TUTOR;
        }
        else if(role.equals("analist"))
        {
            roleConstant = ControladorDeLogIn.ROLE_ANALIST;
        }
        else if(role.equals("secretary"))
        {
            roleConstant = ControladorDeLogIn.ROLE_SECRETARY;
        }
        else
        {
            // Si no coincide con ninguna de las anteriores asumir que se
            // trata de un estudiante.
            
            roleConstant = ControladorDeLogIn.ROLE_STUDENT;
        }
        
        // Crear un controlador de Log-in para manejar la petición.
        loginController = new ControladorDeLogIn();
        
        // Intentar realizar el log-in contra la capa de negocios
        logedUser = loginController.doLogin(user, password, roleConstant);
        
        if(logedUser == null)
        {
            // Establecer el error de inicio de Sesión
            setLogInError("El nombre de usuario o contraseña son incorrectos o bien el usuario no cuenta con los permisos necesarios para iniciar sesión usando el rol especificado.", request);
        }
        else
        {
            // Establecer el usuario actual
            request.getSession().setAttribute("currentUser", logedUser);
        }
    }
    
    /** Realiza desvío de peticiones HTTP, si el desvío de la petición falla entonces
     *  se hace un desvío a través del cliente (explorador Web)
     * 
     *  @param request El Request HTTP
     *  @param response El response HTTP
     *  @param url La URL a la que se debe hacer el desvío de petición.
     */
    
    private void requestForward(HttpServletRequest request, HttpServletResponse response, String url)
    {
        // Variables locales:
            RequestDispatcher rd;
                            
        // Obtener un RequestDispatcher para desvier la petición
        rd = request.getRequestDispatcher(url);

        try
        {
            // Disviar la petición
            rd.forward(request, response);
        }
        catch(Exception ex)
        {
            try
            {
                // Ha fallado el desvío de la petición así que usaremos el http-equiv refresh
                // para hacer que el navegador desvíe la petición. Perderemos los objetos request
                // y response pero.... no hay de otra.
                response.getWriter().println("<html>");
                response.getWriter().println("<head>");
                response.getWriter().println("<meta http-equiv=\"refresh\" content=\"0;URL=" + url + "\">");
                response.getWriter().println("</head>");
                response.getWriter().println("</html>");
                return;
            }
            catch(IOException ioException)
            {
                // Alguna idea?
            }
        }
    }
    
    /** Almacena el último error de inicio se sesión en la sesión del usuario.
     * 
     *  @param error La cadena del error.
     */
    
    private void setLogInError(String error, HttpServletRequest request)
    {
        // Establecer la variable de error de log-in y desviar la petición
        request.getSession().setAttribute("loginError", error);
    }
}
