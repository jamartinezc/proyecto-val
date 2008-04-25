/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

import java.util.LinkedList;
import java.util.List;

/** Representa la planeación de exámenes para una semana del periodo académico.
 *
 * @author Sergio
 */
public class PlaneacionSemanal
{
    /** La semana a la que corresponde la planeación semanal */
    private int semana;
    
    /** Una lista de Examenes Planeados con todos los exámenes planeados
     *  para la semana
     */
    
    private List<ExamenPlaneado> examenesPlaneados;
    
    /** Crea un objeto con una lista de Examenes Planeados vacía */
    public PlaneacionSemanal()
    {
        // Crea una lista vacía.
        examenesPlaneados = new LinkedList<ExamenPlaneado>();
    }

    /** Devuelve la semana a la que corresponde esta planeación semanal
     * 
     *  @return La semana a la que corresponde la planeación.
     */
    
    public int getSemana() { return semana; }

    /** Establece la semana a la que corresponde la planeación semanal.
     * 
     *  @param semana La semana a la que pertenece la planeación.
     */
    
    public void setSemana(int semana) { this.semana = semana; }

    /** Devuelve una lista de Exámenes Planeados en la semana. Si no hay
     *  examenes planeados en la semana el método devuelve una lista vacía
     *  pero nunca <code>null</code>
     * 
     *  @return La lista de Exámenes Planeados
     */
    
    public List<ExamenPlaneado> getExamenesPlaneados() { return examenesPlaneados; }

    /** Establece la lista de exámenes planeados para la semana.
     * 
     *  @param examenesPlaneados La lista de exámenes planeados. El parámetro
     *  no puede ser <code>null</code> o se producirá una NullPointerException.
     */
    
    public void setExamenesPlaneados(List<ExamenPlaneado> examenesPlaneados)
    {
        if(examenesPlaneados == null) throw new NullPointerException();
        this.examenesPlaneados = examenesPlaneados;
    }
}
