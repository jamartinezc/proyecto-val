/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.registro.exceptions;

/** Excepción para ser lanzada cuando se solicita un registro y este no existe
 *  en la base de datos.
 *
 *  @author Sergio
 */
public class RegistroNoEncontradoException extends RegistroException
{
    /** Crea una excepción con el mensaje predeterminado: "No se encuentra el
     *  registro especificado"
     */
    
    public RegistroNoEncontradoException()
    {
        super("No se encuentra el registro especificado");
    }
    
    /** Crea una excepción con el mensaje especificado
     * 
     *  @param message El mensaje para la excepción
     */
    
    public RegistroNoEncontradoException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados
     * 
     *  @param message El mensje para la excepción
     *  @param cause La causa de la excepción
     */
    
    public RegistroNoEncontradoException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
