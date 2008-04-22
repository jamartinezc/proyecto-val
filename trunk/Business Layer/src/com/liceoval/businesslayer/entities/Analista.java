/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

/**
 *
 * @author Sergio
 */
public class Analista extends Usuario
{
    private int idAnalista;
    
    public String getRol(){ return "Analista"; }

    public int getIdAnalista() { return idAnalista; }

    public void setIdAnalista(int idAnalista) { this.idAnalista = idAnalista; }
    
}
