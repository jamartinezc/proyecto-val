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
public class Grado
{
    private int grado;
    private List<Materia> materias;

    public int getGrado() { return grado; }

    public void setGrado(int grado) { this.grado = grado; }

    public List<Materia> getMaterias() { return materias; }

    public void setMaterias(List<Materia> materias) { this.materias = materias; }
}
