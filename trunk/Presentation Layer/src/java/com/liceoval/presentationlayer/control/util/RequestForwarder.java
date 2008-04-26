/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.presentationlayer.control.util;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Hace Forwarding de un request a otra pàgina.
 *
 *  @author Angela
 */
public class RequestForwarder
{
    /** Realiza desvío de peticiones HTTP, si el desvío de la petición falla entonces
     *  se hace un desvío a través del cliente (explorador Web)
     * 
     *  @param request El Request HTTP
     *  @param response El response HTTP
     *  @param url La URL a la que se debe hacer el desvío de petición.
     */
    
    public static void forwardRequest(HttpServletRequest request, HttpServletResponse response, String url)
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
}
