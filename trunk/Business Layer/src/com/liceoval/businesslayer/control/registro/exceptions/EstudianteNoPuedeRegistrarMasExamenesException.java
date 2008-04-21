/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.registro.exceptions;

/** Una excepción que se lanza en caso de que el estado de uno de los exámenes
 *  del estudiante cumpla con las condiciones necesarias para impedir el
 *  registro de otro examen.
 *
 * @author Sergio
 */
public class EstudianteNoPuedeRegistrarMasExamenesException extends RegistroException
{
    /** Crea una excepción con el mensaje predeterminado */
    
    public EstudianteNoPuedeRegistrarMasExamenesException()
    {
        super("El estudiante no puede registrar máz exámenes, ya tiene examenes registrados en estado \"Pendiente\"");
    }
    
    /** Crea una excepción con el mensaje especificado
     * 
     *  @param message El mensaje para la excepción.
     */
    
    public EstudianteNoPuedeRegistrarMasExamenesException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public EstudianteNoPuedeRegistrarMasExamenesException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
