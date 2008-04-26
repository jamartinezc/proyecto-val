/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Errores;

/**
 *
 * @author David
 */
public class UltimoTemaException extends Exception{
    public String Mensaje(){
        String pilas;
        pilas="El alumno ya vió todos los temas de esa Materia";
        return pilas;
    }
}
