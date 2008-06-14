/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Jorge
 */
public class MateriaPlaneada {
    private Materia materia;
    private Map<Integer, Integer> planeadosMes;
    private Map<Integer, Integer> ganadosMes;
    
    /** Crea una MateriaPlaneada y establece todos sus atributos en null,
     *  inicializa ambas listas a listas vacías.
     */
    
    public MateriaPlaneada()
    {
        planeadosMes = new HashMap<Integer, Integer>();
        ganadosMes = new HashMap<Integer, Integer>();
        materia = null;
    }
    
    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    /** Devuelve el mapa de exámenes planeados en el mes. Si no hay
     *  exámenes planeados la función retorna un mapa vacío. La función nunca
     *  retorna <code>null</code>.
     * 
     *  @return Una lista de Integer con los exámenes planeados para cada mes.
     */
    
    public Map<Integer, Integer> getPlaneadosMes() { return planeadosMes; }

    /** Establece el mapa de exámenes planeados por mes.
     * 
     *  @param planeadosMes Un mapa de los exámenes planeados por mes.
     *  @throws NullPointerException Si planeadosMes es <code>null</code>.
     */
    
    public void setPlaneadosMes(Map<Integer, Integer> planeadosMes)
    {
        if(planeadosMes == null) throw new NullPointerException();
        this.planeadosMes = planeadosMes;
    }

    /** Devuelve el mapa de exámenes ganados por mes. La clave del mapa es el
     *  mes al que corresponden los exámenes.
     * 
     *  @return Un mapa de Integer que representa la cantidad de exámenes
     *      ganados por mes. Si no hay exámenes registrados para ningún mes
     *      la función devuelve un mapa vacío. La función nunca devuelve
     *      <code>null</code>.
     */
    
    public Map<Integer, Integer> getGanadosMes() { return ganadosMes; }
    
    /** Establece el mapa de exámenes ganados por mes.
     * 
     *  @param ganadosMes Un mapa de Integer (la cantidad de exámenes ganados
     *      en cada mes.
     *  @throws NullPointerException Si ganadosMes es <code>null</code>.
     */
    
    public void setGanadosMes(Map<Integer, Integer> ganadosMes)
    {
        if(ganadosMes == null) throw new NullPointerException();
        this.ganadosMes = ganadosMes;
    }
}
