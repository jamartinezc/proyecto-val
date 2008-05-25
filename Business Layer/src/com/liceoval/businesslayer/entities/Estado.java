/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

/** Representa el estado de un Examen Solicitado o Planeado.
 *
 *  @author Sergio
 */

public class Estado
{
    private int idEstado;
    private String nombre;

    /** Devuelve el ID del estado.
     * 
     *  @return El ID del estado.
     */
    
    public int getIdEstado() { return idEstado; }

    /** Establece el ID del estado.
     * 
     *  @param idEstado El ID del estado.
     */
    
    public void setIdEstado(int idEstado) { this.idEstado = idEstado; }

    /** Devuelve el nombre del estado.
     * 
     *  @return El nombre del estado.
     */
    
    public String getNombre() { return nombre; }

    /** Establece el nombre del estado.
     * 
     *  @param nombre El nombre del estado.
     */
    
    public void setNombre(String nombre) { this.nombre = nombre; }
}
