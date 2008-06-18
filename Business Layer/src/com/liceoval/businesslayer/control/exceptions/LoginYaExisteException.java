/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.exceptions;

/** Una excepción para ser lanzada cuando se intenta crear un usuario con un
 *  nombre de inicio de sesión que ya existe en la base de datos.
 *
 *  @author Sergio
 */

public class LoginYaExisteException extends Exception
{
    /** Crea una excepción con el mensaje predeterminado: "El login ya existe" */
    public LoginYaExisteException() { super("El login ya existe"); }
    
    /** Crea una excepción con el mensaje de error especificado.
     * 
     *  @param message El mensaje para la excepción.
     */
    
    public LoginYaExisteException(String message) { super(message); }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public LoginYaExisteException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
