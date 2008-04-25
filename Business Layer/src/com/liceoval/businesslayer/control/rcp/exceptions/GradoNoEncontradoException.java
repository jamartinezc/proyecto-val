/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.rcp.exceptions;

/** Una Exception lanzada por la RCP de registros cuando no encuentra el grado
 *  especificado en una llamada a getMaterias(Grado)
 *
 * @author Sergio
 */
public class GradoNoEncontradoException extends RCPException
{
    /** Crea una GradoNoEncontradoException con el mensaje predeterminado:
     *  "No se encuentra el grado especificado"
     */
    
    public GradoNoEncontradoException()
    {
        super("No se encuentra el grado especificado");
    }
    
    /** Crea una GradoNoEncontradoException con el mensaje especificado.
     * 
     *  @param message El mensaje para la excepción.
     */
    
    public GradoNoEncontradoException(String message)
    {
        super(message);
    }
    
    /** Crea una GradoNoEncontradoException con el mensaje y la causa
     *  especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public GradoNoEncontradoException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
