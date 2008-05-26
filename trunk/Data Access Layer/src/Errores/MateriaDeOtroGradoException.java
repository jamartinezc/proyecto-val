/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Errores;

/**
 *
 * @author David
 */
public class MateriaDeOtroGradoException extends Exception{
public String Mensaje(){
        String pilas;
        pilas="La Materia no corresponde al grado correcto.";
        return pilas;
    }
}
