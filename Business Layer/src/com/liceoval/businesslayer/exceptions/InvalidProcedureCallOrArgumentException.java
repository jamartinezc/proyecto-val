/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.exceptions;

/** Excepción para ser lanzada cuando se realiza una llamada a un procedimiento
 *  con argumentos inválidos.
 *
 * @author Sergio
 */

public class InvalidProcedureCallOrArgumentException extends Exception
{
    /** Crea una excepción con el mensaje predeterminado: "Llamada a procedimiento
     *  o argumento inválido" */
    
    public InvalidProcedureCallOrArgumentException()
    {
        super("Llamada a procedimiento o argumento inválido");
    }
    
    /** Crea una excepción con el mensaje especificado.
     * 
     *  @param message El mensaje para la excepción.
     */
    
    public InvalidProcedureCallOrArgumentException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public InvalidProcedureCallOrArgumentException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
