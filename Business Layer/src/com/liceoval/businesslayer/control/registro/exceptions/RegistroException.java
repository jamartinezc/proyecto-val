/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.registro.exceptions;

/** Un clase abstracta de la cual heredan todas las excepciones de la
 *  ControladoraDeRegistro, esta clase permite hacer un catch de
 *  RegistroException en caso de ser necesario.
 *
 *  @author Sergio
 */
public abstract class RegistroException extends Exception
{
    /** Crea una excepción con el mensaje predeterminado. */
    
    public RegistroException()
    {
        super("Excepción en la ControladoraDeRegistro");
    }
    
    /** Crea una excepción con el mensaje especificado
     * 
     *  @param message El mensaje para la excepción
     */
    
    public RegistroException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados
     * 
     *  @param message El mensaje para la excepción
     *  @param cause La causa de la excepción
     */
    
    public RegistroException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
