/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.registro.exceptions;

/** Una Excepción para ser lanzada si falla la insersión del examen una vez que
 *  se han superado todas las verificaciones de la capa de negocios. Este error
 *  se atribuye directamente a la capa de acceso a datos.
 *
 * @author Sergio
 */
public class InsersionDeExamenException extends RegistroException
{
    /** Crea una excepción con el mensaje predeterminado: "Falló la insersión del
     *  examen. Falla en la capa de Acceso a Datos"
     */
    
    public InsersionDeExamenException()
    {
        super("Falló la solicitud del examen. Falla en la capa de Acceso a Datos");
    }
    
    /** Crea una excepción en el mensaje especificado
     * 
     *  @param message El mensaje para la excepción
     */
    
    public InsersionDeExamenException(String message)
    {
        super(message);
    }
    
    /** Crea una excepción con el mensaje y la causa especificados.
     * 
     *  @param message El mensaje para la excepción.
     *  @param cause La causa de la excepcion
     */
    
    public InsersionDeExamenException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
