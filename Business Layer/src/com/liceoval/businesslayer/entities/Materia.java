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
public class Materia
{
    private int codigo;
    private String nombre;
    private Tutor tutor;
    private Analista analista;
    private List<Examen> exmanes;

    public int getCodigo() { return codigo; }

    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Tutor getTutor() { return tutor; }

    public void setTutor(Tutor tutor) { this.tutor = tutor; }

    public Analista getAnalista() { return analista; }

    public void setAnalista(Analista analista) { this.analista = analista; }

    public List<Examen> getExmanes() { return exmanes; }

    public void setExmanes(List<Examen> exmanes) { this.exmanes = exmanes; }
}
