/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.exceptions;

/** Una Excepción para ser lanzada cuando se intenta crear un padre para el
 *  estudiante pero ya existe un padre con la misma cédula registrado en la
 *  base de datos.
 *
 * @author Sergio
 */
public class PadreYaExisteException extends Exception
{
    /** Crea una excepción con el mensaje predeterminado: "Ya existe un padre
     *  con ese número de identificación registrado en la base de datos."
     */
    
    public PadreYaExisteException()
    {
        super("Ya existe un padre con ese número de identificación registrado " +
                "en la base de datos.");
    }
    
    /** Crea una excepción con el mensaje especificado.
     * 
     *  @param message El mensaje para la excepción.
     */
    
    public PadreYaExisteException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public PadreYaExisteException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
