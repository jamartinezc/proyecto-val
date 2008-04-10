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
    private int estado;
    
    public static final int ESTADO_PEND_APROVACION = 0;
    public static final int ESTADO_PEND_PRESENTACION = 1;
    public static final int ESTADO_NO_PRESENTADO = 3;
    public static final int ESTADO_APROBADO = 4;
    public static final int ESTADO_NOTA_PENDIENTE = 5;
    public static final int ESTADO_NOTA_EXAMEN = 6;

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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
