/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Errores;

/**
 *
 * @author David
 */
public class NoItemFoundException extends Exception{
    public String Mensaje(){
        String pilas;
        pilas="No se encontró el ítem requerido";
        return pilas;
    }
}
