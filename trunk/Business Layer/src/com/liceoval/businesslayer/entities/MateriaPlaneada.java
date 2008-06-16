/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

import java.util.Vector;

/** Representa una materia planeada. Contiene la materia que fue planeada
 *  además
 *
 * @author Jorge
 */
public class MateriaPlaneada {
    private Materia materia;
    private Vector<int[]> planeadosMes;
    
    /** Crea una MateriaPlaneada y establece todos sus atributos en null,
     *  inicializa ambas listas a listas vacías.
     */
    
    public MateriaPlaneada()
    {
        planeadosMes = new Vector<int[]>();
        materia = null;
    }
    
    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    /** Devuelve un vector de arreglos de enteros, las dos primeras posiciones
     *  de cada arreglo contienen el número del mes y el número de examenes
     *  planeados en ese mes. Si no hay exámenes planeados la función retorna
     *  un vector vacío. La función nunca retorna <code>null</code>.
     * 
     *  @return Una lista de Integer con los exámenes planeados para cada mes.
     */
    
    public Vector<int[]> getPlaneadosMes() { return planeadosMes; }

    /** Establece vector de exámenes planeados por mes.
     * 
     *  @param planeadosMes Un vector de los exámenes planeados por mes.
     *  @throws NullPointerException Si planeadosMes es <code>null</code>.
     */
    
    public void setPlaneadosMes(Vector<int[]> planeadosMes)
    {
        if(planeadosMes == null) throw new NullPointerException();
        this.planeadosMes = planeadosMes;
    }
}
