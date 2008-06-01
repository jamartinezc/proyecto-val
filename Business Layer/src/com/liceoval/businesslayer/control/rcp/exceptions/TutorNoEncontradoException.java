/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.rcp.exceptions;

/** Una excepción que se lanza cuando se intenta recuperar información
 *  relacionada con un tutor que no existe.
 *
 *  @author Sergio
 */
public class TutorNoEncontradoException extends RCPException
{
    /** Crea una excepción con el mensaje predeterminado: "No se encuentra el
     *  turor especificado"
     */
    
    public TutorNoEncontradoException()
    {
        super("No se encuentra el tutor especificado");
    }
    
    /** Crea una excepción con el mensaje especificado.
     * 
     *  @param message El mensaje para la excepción
     */
    
    public TutorNoEncontradoException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public TutorNoEncontradoException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
