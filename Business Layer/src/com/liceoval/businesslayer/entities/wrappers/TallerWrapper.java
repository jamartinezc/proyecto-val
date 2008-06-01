/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities.wrappers;

import com.liceoval.businesslayer.entities.Taller;
import com.liceoval.businesslayer.entities.Estudiante;

import java.util.List;
import java.util.LinkedList;

/** Un Wrapper para la clase taller para poder recuperar adicionalmente la lista
 *  de estudiantes de ese taller.
 * 
 *  @author Sergio
 */

public class TallerWrapper
{
    private List<Estudiante> estudiantes;
    private Taller taller;
    
    /** Crea una TallerWarapper con una lista de estudiantes vacía y con el
     *  taller establecido en null.
     */
    
    public TallerWrapper() { estudiantes = new LinkedList<Estudiante>(); }

    /** Crea un objeto TallerWrapper con el taller y la lista de estudiantes
     *  especificados. Si el taller es null o la lista de estudiantes es null
     *  se crea un TallerWrapper con una lista vacía.
     * 
     *  @param taller El taller.
     *  @param estudiantes La lista de estudiantes.
     */
    
    public TallerWrapper(Taller taller, List<Estudiante> estudiantes)
    {
        this.taller = taller;
        
        if(taller == null || estudiantes == null)
            estudiantes = new LinkedList<Estudiante>();
        else this.estudiantes = estudiantes;
    }
    
    /** Devuelve la lista de estudiantes actual. Si no hay estudiantes la función
     *  retorna una lista vacía. Esta función NUNCA retorna null.
     * 
     *  @return La lista de estudiantes.
     */
    
    public List<Estudiante> getEstudiantes() { return estudiantes; }

    /** Establece la lista de estudiantes.
     * 
     *  @param estudiantes La lista de estudiantes.
     *  @throws java.lang.NullPointerException Si estudiantes es <code>null</code>
     */
    
    public void setEstudiantes(List<Estudiante> estudiantes) throws NullPointerException
    {
        if(estudiantes == null) throw new NullPointerException();
        this.estudiantes = estudiantes;
    }

    /** Devuelve el taler.
     * 
     *  @return El taller.
     */
    
    public Taller getTaller() { return taller; }

    /** Establece el taller actual. Un valor de <code>null</code> provocará que
     *  la lista de estudiantes sea eliminada y se convierta en una lista vacía.
     * 
     *  @param taller El taller.
     */
    
    public void setTaller(Taller taller)
    {
        this.taller = taller;
        if(taller == null) estudiantes.clear();
    }
    
}
