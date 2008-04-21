/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Errores;

/**
 *
 * @author David
 */
public class PosibleDuplicationException extends Exception{
    public String Mensaje(){
        String pilas;
        pilas="Se evitó una duplicación de registros en la Base de Datos";
        return pilas;
    }
}