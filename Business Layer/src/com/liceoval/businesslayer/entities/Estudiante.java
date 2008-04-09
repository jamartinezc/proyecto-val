/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Sergio
 */
public class Estudiante extends Usuario
{
    private int codigo;
    private Grado grado;
    private Taller taller;
    private Date fechaInicioGrado;
    private List<Registro> registros;
    private List<Padre> padres;
    private PlaneacionAnual planeacionAnual;
    private List<PlaneacionSemanal> planeacionesSemanales;

    public String getRol() { return "Estudiante"; }
    
    public int getCodigo() { return codigo; }

    public void setCodigo(int codigo) { this.codigo = codigo; }

    public Grado getGrado() { return grado; }

    public void setGrado(Grado grado) { this.grado = grado; }

    public Taller getTaller() { return taller; }

    public void setTaller(Taller taller) { this.taller = taller; }

    public Date getFechaInicioGrado() { return fechaInicioGrado; }

    public void setFechaInicioGrado(Date fechaInicioGrado) { this.fechaInicioGrado = fechaInicioGrado; }

    public List<Registro> getRegistros() { return registros; }

    public void setRegistros(List<Registro> registros) { this.registros = registros; }

    public List<Padre> getPadres() { return padres; }

    public void setPadres(List<Padre> padres) { this.padres = padres; }

    public PlaneacionAnual getPlaneacionAnual() { return planeacionAnual; }

    public void setPlaneacionAnual(PlaneacionAnual planeacionAnual) { this.planeacionAnual = planeacionAnual; }

    public List<PlaneacionSemanal> getPlaneacionesSemanales() { return planeacionesSemanales; }

    public void setPlaneacionesSemanales(List<PlaneacionSemanal> planeacionesSemanales) { this.planeacionesSemanales = planeacionesSemanales; }
    
}
