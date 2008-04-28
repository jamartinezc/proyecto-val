/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

/**
 *
 * @author Sergio
 */
public class Padre
{
    private int idPadre;
    private String nombres;
    private String apellidos;
    private String correo;

    /** Devuelve los nombres del padre.
     * 
     *  @return Los nombres del padre.
     */
    
    public String getNombres() { return nombres; }

    /** Establece los nombre del padre.
     * 
     *  @param nombres Los nombre del padre.
     */
    
    public void setNombres(String nombres) { this.nombres = nombres; }

    /** Devuelve los apellidos del padre.
     * 
     *  @return Los apellidos del padre.
     */
    
    public String getApellidos() { return apellidos; }

    /** Establece los apellidos del padre.
     * 
     *  @param apellidos Los apellidos del padre.
     */
    
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    /** Devuelve el correo electrónico del padre
     * 
     *  @return El correo electrónico del padre.
     */
    
    public String getCorreo() { return correo; }

    /** Establece el correo electrónico del padre.
     * 
     *  @param correo El correo electrónico del padre.
     */
    
    public void setCorreo(String correo) { this.correo = correo; }

    /** Devuelve el ID del padre.
     * 
     *  @return El ID del padre.
     */
    
    public int getIdPadre() { return idPadre; }

    /** Establece el ID del padre.
     * 
     *  @param idPadre El ID del padre.
     */
    
    public void setIdPadre(int idPadre) { this.idPadre = idPadre; }
}
