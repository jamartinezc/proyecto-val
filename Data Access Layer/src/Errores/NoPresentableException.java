/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Errores;

/**
 *
 * @author David
 */
public class NoPresentableException extends Exception{
    public String Mensaje(){
        String pilas;
        pilas="¡¡¡No puede presentar el siguiente examen todavia!!!!";
        return pilas;
    }
}
