/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.exceptions;

/**
 *  Exepcion lanzada cuando ocurre un error en el envio del correo electronico 
 *  de un informe mensual/semanal, esto puede ser debido a un error en el servidor de
 *  correo electronico, en las variables usadas para la coneccion, o a la
 *  coneccion de internet entre otros, por ello debe especificarse su causa usando 
 * la constructora apropiada.
 * @author Jorge
 */
public class ErrorEnviandoInformeException extends RuntimeException {
    
    private ErrorEnviandoInformeException(){
        super("ha ocurrido un error con el envio del correo electronico.");
    }
    
    private ErrorEnviandoInformeException(String mensaje){
        super(mensaje);
    }
    
    /**
     * Crea un ErrorEnviandoInformeException con el mensaje y casua especificados
     * @param mensaje mensaje para complementar información de la excepcion.
     * @param causa causa de la excepcion.
     */
    public ErrorEnviandoInformeException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }

}
