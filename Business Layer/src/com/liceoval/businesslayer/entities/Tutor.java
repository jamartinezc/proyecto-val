/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

/**
 *
 * @author Sergio
 */

public class Tutor extends Usuario
{
    private int idTutor;
    
    public String getRol() { return "Tutor"; }

    public int getIdTutor() { return idTutor; }

    public void setIdTutor(int idTutor) { this.idTutor = idTutor; }
}
