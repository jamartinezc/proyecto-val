/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.exceptions;

/** Una excepción para ser lanzada cuando al tratar de crear un registro par
 *  para un usuario en una de las otras tablas (Estudiante, Analista, Tutor o
 *  SecreatariaAcademica) se produce una PosibleDuplicationException cuando
 *  no debería suceder.
 *
 *  @author Sergio
 */

public class RegistroDuplicadoInesperadoException extends Exception
{
    /** Crea una excepción con el mensaje de error predeterminado: "Error
     *      inesperado de la base de datos. Se ha encontrado un registro
     *      duplicado donde no debería existir. Por favor contacte al
     *      administrador del sistema y notifíquele del problema.
     */

    public RegistroDuplicadoInesperadoException()
    {
        super("Error inesperado de la base de datos. Se ha encontrado un " +
            "registro duplicado donde no debería existir. Por favor contacte " +
            "al administrador del sistema y notifíquele del problema.");
    }
    
    /** Crea una excepción con el mensaje especificado.
     * 
     *  @param message El mensaje de error.
     */
    
    public RegistroDuplicadoInesperadoException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public RegistroDuplicadoInesperadoException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
