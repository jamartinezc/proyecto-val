/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

import java.util.List;

/**
 *
 * @author Sergio
 */
public class Taller
{
    private int idTaller;
    private Tutor tutor;
    
    /** Devuelve el tutor del taller.
     * 
     *  @return El tutor del taller.
     */
    
    public Tutor getTutor() { return tutor; }

    /** Establece el tutor del taller.
     * 
     *  @param tutor El tutor del taller.
     */
    
    public void setTutor(Tutor tutor) { this.tutor = tutor; }

    /** Devuelve el ID del taller
     * 
     *  @return El ID del taller.
     */
    
    public int getIdTaller() { return idTaller; }

    /** Establece el ID de taller
     * 
     *  @param idTaller El id de taller.
     */
    
    public void setIdTaller(int idTaller) { this.idTaller = idTaller; }
}
