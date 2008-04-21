/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.registro.exceptions;

/** Una excepción que se lanza si la zona horaria programada en la
 *  configuración del sistema no retorna ningún ID de zona horaria válido.
 *
 *  @author Sergio
 */
public class ZonaHorariaIncorrectaException extends RegistroException
{
    /** Crea una excepción con el mensaje de excepción predeterminado:
     *  Zona horaria incorrecta
     */
    
    public ZonaHorariaIncorrectaException()
    {
        super("Zona horaria incorrecta");
    }
    
    /** Crea una excepción con el mensaje de error especificado
     * 
     *  @param message El mensaje de error para la excepción.
     */
    
    public ZonaHorariaIncorrectaException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje de error y la causa especificada
     * 
     *  @param message El mensaje de error para la excepción.
     *  @param cause La causa del error.
     */
    
    public ZonaHorariaIncorrectaException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
