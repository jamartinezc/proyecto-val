/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.exceptions;

/** Una excepción para ser lanzada cuando se encuentran datos inválidos al
 *  momento de crear un estudiante que tiene el rol Estudiante.
 *
 *  @author Sergio
 */
public class DatosDeEstudianteInvalidosException extends Exception
{
    /** Crea una excepción con el mensaje predeterminado: "Los datos del
     *      estudiante o de su(s) acudiente(s) no son válidos."
     */
    
    public DatosDeEstudianteInvalidosException()
    {
        super("Los datos del estudiante o de su(s) acudiente(s) no son válidos.");
    }
    
    /** Crea una excepción con el mensaje especificado.
     * 
     *  @param message El mensaje para la excepción.
     */
    
    public DatosDeEstudianteInvalidosException(String message) { super(message); }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje de la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public DatosDeEstudianteInvalidosException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
