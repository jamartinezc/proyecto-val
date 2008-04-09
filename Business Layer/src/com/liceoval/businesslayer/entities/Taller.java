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
public class Taller
{
    private List<Estudiante> estudiantes;
    private Tutor tutor;

    public List<Estudiante> getEstudiantes() { return estudiantes; }

    public void setEstudiantes(List<Estudiante> estudiantes) { this.estudiantes = estudiantes; }

    public Tutor getTutor() { return tutor; }

    public void setTutor(Tutor tutor) { this.tutor = tutor; }
}
