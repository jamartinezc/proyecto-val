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
    private int idExamenSolicitado;
    private Examen examen;
    private Analista analista;
    private Date fecha;
    private Estado estado;
    private float nota;
        
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

    /** Devuelve la nota del examen.
     * 
     *  @return La nota del examen.
     */
    
    public float getNota() { return nota; }

    /** Establece la nota del examen.
     * 
     *  @param nota La nota del examen.
     */
    
    public void setNota(float nota) { this.nota = nota; }

    /** Devuelve el ID del examen solicitado
     * 
     *  @return El ID del exmaen solicitado.
     */
    
    public int getIdExamenSolicitado() { return idExamenSolicitado; }

    /** Establece el ID del examen solicitado
     * 
     *  @param idExamenSolicitado El ID del examen solicitado.
     */
    
    public void setIdExamenSolicitado(int idExamenSolicitado) { this.idExamenSolicitado = idExamenSolicitado; }
}