/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.rcp.exceptions;

/** Excepción abstracta básica para las excepciones lanzadas por la RCP de
 *  registros.
 *
 *  @author Sergio
 */
public abstract class RCPException extends Exception
{
    /** Crea una RCPException con el mensaje de error predeterminado: "Excepción
     *  de la RCP de Registros
     */
    
    public RCPException()
    {
        super("Excepción de la RCP de Registros");
    }
    
    /** Crea una RCPException con el mensaje de excepción especificado.
     * 
     *  @param message Mensaje para la excepción.
     */
    
    public RCPException(String message)
    {
        super(message);
    }
    
    /** Crea una RCPException con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public RCPException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
