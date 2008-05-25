/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.registro.exceptions;

/** Una Excepción para ser lanzada cuando se solicitan los registros de un
 *  estudiante que no puede ser localizado en la base de datos.
 *
 *  @author Sergio
 */
public class EstudianteNoEncontradoException extends RegistroException
{
    /** Crea una excepción con el mensaje predeterminado: "No se encuentra el
     *  estudiante especificado"
     */
    
    public EstudianteNoEncontradoException()
    {
        super("No se encuentra el estudiante especificado");
    }
    
    /** Crea una excepción con el mensaje especificado.
     * 
     *  @param message El mensaje para la excepción.
     */
    
    public EstudianteNoEncontradoException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificada.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public EstudianteNoEncontradoException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
