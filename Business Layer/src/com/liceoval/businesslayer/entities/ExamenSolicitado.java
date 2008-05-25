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
public class ExamenSolicitado {
    private Examen examen;
    private Analista analista;
    private Date fecha;
    private Estado estado;
        
    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Analista getAnalista() {
        return analista;
    }

    public void setAnalista(Analista analista) {
        this.analista = analista;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /** Devuelve el estado del examen.
     * 
     *  @return El estado del examen.
     */
    
    public Estado getEstado() { return estado; }

    /** Establece el estado del examen
     * 
     *  @param estado El estado del examen.
     */
    
    public void setEstado(Estado estado) { this.estado = estado; }
}