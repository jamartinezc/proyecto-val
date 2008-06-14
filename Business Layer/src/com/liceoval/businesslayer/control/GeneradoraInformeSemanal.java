/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author jaguar
 */
public class GeneradoraInformeSemanal {
    public static void generarInformeSemanal(){
        Calendar fecha = Calendar.getInstance();//fecha de hoy
        //obtener el mes anterior, el que se acaba de completar, el del informe.
        fecha.add(Calendar.MONTH, -1);
        
        String mesInforme = fecha.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es"));//cambiar por fecha factory
        int mes = fecha.get(Calendar.MONTH);
        String añoInforme = fecha.getDisplayName(Calendar.YEAR, Calendar.LONG, new Locale("es"));
        List<VO.Estudiante> estudiantes = DAO.DaoEstudiante.consultarTodos();
        //consultar estudiantes por curso y contar los examenes del curso antes de empezar
        Iterator<VO.Estudiante> itEstudiantes = estudiantes.iterator();
        
        //borrar while(itEstudiantes.hasNext()){
    }
}
