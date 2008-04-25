/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author Sergio
 */
public class Grado
{
    private int idGrado;
    private String nombre;
    private List<Materia> materias;
    
    /** Crea un grado con una lista de materias vacía */
    
    public Grado()
    {
        materias = new LinkedList<Materia>();
    }

    public int getIdGrado() { return idGrado; }

    public void setIdGrado(int idGrado) { this.idGrado = idGrado; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    /** Devuelve la lista de materias del grado. Si el grado aún no
     *  tiene materias registradas el método retorna una lista vacía,
     *  el método nunca devuelve <code>null</code>.
     * 
     *  @return Una lista de materias.
     */
    
    public List<Materia> getMaterias() { return materias; }

    /** Establece la lista de materias del grado.
     * 
     *  @param materias La lista de materias del grado. La lista no puede ser
     *  un <code>null</code> o se producira un NullPointerException.
     */
    
    public void setMaterias(List<Materia> materias)
    {
        if(materias == null) throw new NullPointerException();
        this.materias = materias;
    }
}
