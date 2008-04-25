/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sergio
 */
public class PlaneacionAnual
{
    private List<MateriaPlaneada> materiasPlaneadas;

    /** Crea un objeto con una lista de materias planeadas vacía */
    
    public PlaneacionAnual()
    {
        // Crea el objeto con una lista de Materias Planeadas vacía.
        materiasPlaneadas = new LinkedList<MateriaPlaneada>();
    }
        
    /** Devuelve la lista de materias planeadas. Si aún no hay materias planeadas
     *  el método retorna una lista vacía. Nunca retorna <code>null</code>
     * 
     *  @return La lista de materias planeadas.
     */
    
    public List<MateriaPlaneada> getMateriasPlaneadas() { return materiasPlaneadas; }

    /** Establece la lista de materias planeadas.
     * 
     *  @param materiasPlaneadas La lista de materias planeadas. Si el parámetro
     *  es null se produce una NullPointerExeption.
     */
    
    public void setMateriasPlaneadas(List<MateriaPlaneada> materiasPlaneadas)
    {
        if(materiasPlaneadas == null) throw new NullPointerException();
        this.materiasPlaneadas = materiasPlaneadas;
    }
}
