/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.exceptions;

/** Una Excepción para ser lanzada cuando se intenta crear un estudiante con
 *  un código que ya existe en la base de datos.
 *
 *  @author Sergio
 */
public class CodigoDeEstudianteYaExisteException extends Exception
{
    /** Crea una excepción con el mensaje predeterminado: "El código del
     *  estudiante ya ha sido registrado en la base de datos."
     */
    
    public CodigoDeEstudianteYaExisteException()
    {
        super("El código del estudiante ya ha sido registrado en la base de datos.");
    }
    
    /** Crea una excepción con el mensaje especificado.
     * 
     *  @param message El mensaje para la excepción.
     */
    
    public CodigoDeEstudianteYaExisteException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public CodigoDeEstudianteYaExisteException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
