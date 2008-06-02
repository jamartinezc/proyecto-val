/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities.wrappers;

import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.ExamenSolicitado;
import com.liceoval.businesslayer.entities.Registro;

/**
 *
 * @author Jorge
 */
public class ExamenSolicitadoWrapper {
    private ExamenSolicitado examenSolicitado;
    private Estudiante estudiante;
    private Registro registro;

    public ExamenSolicitado getExamenSolicitado() {
        return examenSolicitado;
    }

    public void setExamenSolicitado(ExamenSolicitado examenSolicitado) throws NullPointerException{
        if(examenSolicitado == null) throw new NullPointerException();
        this.examenSolicitado = examenSolicitado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) throws NullPointerException{
        if(estudiante == null) throw new NullPointerException();
        this.estudiante = estudiante;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro)  throws NullPointerException{
        if(registro == null) throw new NullPointerException();
        this.registro = registro;
    }
}
