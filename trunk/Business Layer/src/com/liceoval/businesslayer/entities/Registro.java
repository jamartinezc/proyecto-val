/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Registro
{
    private Materia materia;
    private boolean activo;
    private int vecesDevuelta;
    private List<ExamenSolicitado> examenesSolicitados;

    public Registro()
    {
        examenesSolicitados = new LinkedList<ExamenSolicitado>();
    }
    
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

    /** Devuelve la lista de exámenes solicitados.
     *  Si no hay exámenes solicitados la función devuelve una lista vacía,
     *  la función nunca devuelve <code>null</code>.
     * 
     *  @return Una lista de ExamenSolicitado
     */
    
    public List<ExamenSolicitado> getExamenesSolicitados() { return examenesSolicitados; }

    /*** Asigna la lista de exámenes solicitados.
     * 
     *  @param examenesSolicitados La lista de examenes solicitados. El parámetro no
     *  puede ser <code>null</code> o se producirá una NullPointerException.
     */
    
    public void setExamenesSolicitados(List<ExamenSolicitado> examenesSolicitados)
    {
        if(examenesSolicitados == null)
        {
            // Lanzar la excepción.
            throw new NullPointerException();
        }
        
        this.examenesSolicitados = examenesSolicitados;
    }
    
    
}
