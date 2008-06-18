/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.exceptions;

/** Una excepción para ser lanzada cuando se solicita crear un usuario, el usuario
 *  es creado pero no se pueden establecer los permisos de acceso para el usuario.
 *  (Roles).
 *
 *  @author Sergio
 */
public class PeticionEjecutadaParcialmenteException extends Exception
{
    /** Crea una excepción con el mensaje de error predeterminado. */
    
    public PeticionEjecutadaParcialmenteException()
    {
        super("El usuario ha sido creado, sin embargo no se pudieron establecer" +
            " los privilegios de acceso para el mismo.");
    }
    
    /** Crea una excepción con el mensaje especificado.
     * 
     *  @param message El mensaje de error.
     */
    
    public PeticionEjecutadaParcialmenteException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public PeticionEjecutadaParcialmenteException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
