/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

import java.util.Date;

/**
 *
 * @author jorge
 */
public class ExamenPlaneado {
    
    private Date fecha;
    private Examen examen;
    private Estado estado;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    /** Devuelve el estado actual del examen.
     * 
     *  @return El estado actual del examen.
     */
    
    public Estado getEstado() { return estado; }

    /** Establece el estado actual del examen
     * 
     *  @param estado El estado del examen.
     */
    
    public void setEstado(Estado estado) { this.estado = estado; }
}