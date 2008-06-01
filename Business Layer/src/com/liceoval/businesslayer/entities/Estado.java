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

    // Estados
    
    public static final Estado GANADO = new Estado(1, "Ganado");
    public static final Estado PERDIDO = new Estado(2, "Perdido");
    public static final Estado PENDIENTE_APROBAR = new Estado(3, "Pendiente/Aprobar");
    public static final Estado PENDIENTE_PRESENTAR = new Estado(4, "Pendiente/Presentar");
    public static final Estado NO_PRESENTADO = new Estado(5, "No Presentado");
    public static final Estado NOTA_EXAMEN = new Estado(6, "Nota Examen");
    public static final Estado NOTA_PENDIENTE = new Estado(7, "Nota Pendiente");
    public static final Estado PENDIENTE_CALIFICAR = new Estado(8, "Pendiente/Calificar");
    
    /** Crea un objeto Estado vacío. Establece idEstado a 0 y nmbre a
     *  <code>null</code>
     */
    
    public Estado()
    {
        idEstado = 0;
        nombre = null;
    }
    
    /** Crea un objeto Estado con el id de estado y el nombre especificados
     * 
     *  @param idEstado El id de estado.
     *  @param nombre El nombre del estado.
     */
    
    private Estado(int idEstado, String nombre)
    {
        this.idEstado = idEstado;
        this.nombre = nombre;
    }
    
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
    
    /** Compara el objeto con otro objeto y devuelve verdadero si los objetos
     *  son iguales o falso si no.
     * 
     *  @param e El objeto con el cual hacer la comparación.
     *  @return Verdadero si los objetos son iguales, falso si no.
     */
    
    @Override
    public boolean equals(Object e)
    {
        Estado st;
        
        // Verificar que se trate de una instancia de la misma clase
        if(!(e instanceof Estado)) return false;
        
        // Hacer casting del objeto.
        st = (Estado)e;
        
        // Si tanto el nombre como el id de estado coinciden entonces devolver
        // verdadero si alguno de ellos no coincide devolver falso.
        if(idEstado == st.idEstado && nombre.equals(st.nombre)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idEstado;
        hash = 59 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        return hash;
    }
}
