/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

/**
 *
 * @author Jorge
 */
public class Registro
{
    private Materia materia;
    private boolean activo;
    private int vecesDevuelta;

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getVecesDevuelta() {
        return vecesDevuelta;
    }

    public void setVecesDevuelta(int vecesDevuelta) {
        this.vecesDevuelta = vecesDevuelta;
    }
    
    
}
