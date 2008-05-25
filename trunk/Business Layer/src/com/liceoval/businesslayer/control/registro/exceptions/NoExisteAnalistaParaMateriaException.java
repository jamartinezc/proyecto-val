/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.registro.exceptions;

/** Esta Excepción es lanzada cuando se intenta agregar un axamen al registro
 *  del estudiante y no se puede localizar en analista que corresponde a la
 *  materia del examen.
 *
 * @author Sergio
 */

public class NoExisteAnalistaParaMateriaException extends RegistroException
{
    /** Crea una excepción en el mensaje predeterminado: "No existe un analista
     *  asignado a la materia especificada"
     */
    
    public NoExisteAnalistaParaMateriaException()
    {
        super("Imposible hacer la solicitud de examen. No existe un analista asignado a la materia especificada.");
    }
    
    /** Crea una excepción con el mensaje especificado
     * 
     *  @param message El mensaje para la excepción
     */
    
    public NoExisteAnalistaParaMateriaException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mesnsaje y la causa especificados
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepción.
     */
    
    public NoExisteAnalistaParaMateriaException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
