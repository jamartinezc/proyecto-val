/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.registro.exceptions;

/**
 *  Exepcion lanzada cuando se intenta calcular la nota definitiva a un registro activo
 * 
 * @author Jorge
 */
public class NotaNoCalculableException extends RegistroException{
    
    /**
     * Crea una exepcion con el mensaje "No se ha terminado la materia."
     */
    public NotaNoCalculableException(){
        super("No se ha terminado la materia.");
    }
    
    /**
     * Crea una excepcion con el mensaje especificado por la variable mensaje
     * @param mensaje mensaje a asigna a la excepcion.
     */
    public NotaNoCalculableException(String mensaje){
        super(mensaje);
    }
    
    /** Crea una excepcion con el mensaje y la causa especificados
     * 
     *  @param message El mensje para la excepcion.
     *  @param cause La causa de la excepcion.
     */
    public NotaNoCalculableException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
