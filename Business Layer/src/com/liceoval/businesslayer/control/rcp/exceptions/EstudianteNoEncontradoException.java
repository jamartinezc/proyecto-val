/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.rcp.exceptions;

/** Una excepción para ser lanzada cuando se solicita un estudiante que no existe.
 *
 *  @author Sergio
 */

public class EstudianteNoEncontradoException extends RCPException
{
    /** Crea una excepción con el mensaje predeterminado: "No se encuentra el
     *  estudiante especificado."
     */
    
    public EstudianteNoEncontradoException()
    {
        super("No se encuentra el estudiante especificado");
    }
    
    /** Crea una excepción con el mensaje especificado.
     * 
     *  @param message El mensaje para la excepción
     */
    
    public EstudianteNoEncontradoException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción
     *  @param cause La causa de la excepción
     */
    
    public EstudianteNoEncontradoException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
