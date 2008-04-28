/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.exceptions;

/**
 *
 * @author Sergio
 */
public class NoSeEncuentraElUsuarioException extends Exception
{
    /** Crea una NoSeEncuentraElUsuarioException con el mensaje predetermina:
     *  "No se encunentra el usuario especificado".
     */
    
    public NoSeEncuentraElUsuarioException()
    {
        super("No se encuentra el usuario especificado");
    }
    
    /** Crea una NoSeEncuentraElUsuarioException con el mensaje especificado
     * 
     *  @param message El mensaje para la excepción.
     */
    
    public NoSeEncuentraElUsuarioException(String message)
    {
        super(message);
    }
    
    /** Crea una NoSeEncuentraElUsuarioException con el mensaje y la causa
     *  especificada.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la Excepción.
     */
    
    public NoSeEncuentraElUsuarioException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
