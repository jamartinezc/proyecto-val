/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.registro.exceptions;

/** Una excepción que se lanza cuando se solicita insertar un examen en un registro
 *  inexistente y no se puede crear el registro pues el estudiante ya tiene tres
 *  registros activos.
 *
 * @author Sergio
 */
public class RegistroNoExisteYNoPuedeSerCreadoException extends RegistroException
{
    /** Crea una excepción con el mensaje predetermina: "El registro no existe
     *  y no puede ser creado"
     */
    
    public RegistroNoExisteYNoPuedeSerCreadoException()
    {
        super("El registro no existe y no puede ser creado debido a que usted ya está viendo 3 materias");
    }
    
    /** Crea una excepción con el mensaje especificado
     * 
     *  @param message El mensaje para la excepción
     */
    
    public RegistroNoExisteYNoPuedeSerCreadoException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especficiados
     * 
     *  @param message El mensaje para la excepción
     *  @param cause La causa de la excepción
     */
    
    public RegistroNoExisteYNoPuedeSerCreadoException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
