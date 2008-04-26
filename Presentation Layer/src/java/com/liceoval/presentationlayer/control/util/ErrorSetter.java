/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.presentationlayer.control.util;

import javax.servlet.http.HttpServletRequest;

/** Establece un error en el request.
 *
 *  @author Angela
 */
public class ErrorSetter
{
    public static void setError(String attribute, String error, HttpServletRequest request)
    {
        // Establecer la variable de error de log-in y desviar la petición
        request.getSession().setAttribute(attribute, error);
    }
}
