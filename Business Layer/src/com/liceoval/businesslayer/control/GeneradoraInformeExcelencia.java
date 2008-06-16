/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import Errores.NoItemFoundException;
import VO.Estudiante;
import VO.ExamenMes;
import VO.ExamenSolicitado;
import VO.ExcelenciaTaller;
import com.liceoval.businesslayer.entities.Estado;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class GeneradoraInformeExcelencia {
    private static boolean informeBloqueado;
    
    public static void generarInforme(){
        String[] informe;
        informeBloqueado=true;
        
        Calendar fecha = Calendar.getInstance();//fecha de hoy
        //obtener el mes anterior, el que se acaba de completar, el del informe.
        fecha.add(Calendar.MONTH, -1);
        
        String mesInforme = fecha.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es"));//cambiar por fecha factory
        int mes = fecha.get(Calendar.MONTH);
        String añoInforme = String.valueOf(fecha.get(Calendar.YEAR));
        
        try {
            //borrar todos los informes
            DAO.DaoExcelenciaTaller.eliminarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
            //si no existen no hay ningun problema.
        }
        List<VO.Taller> listaTalleres = DAO.DaoTaller.consultarTodos();
        Iterator<VO.Taller> itListaTalleres = listaTalleres.iterator();
        
        while(itListaTalleres.hasNext()){
            VO.Taller taller = itListaTalleres.next();
            
            VO.Estudiante MejorEstudianteTaller = new VO.Estudiante();
            MejorEstudianteTaller.setIdEstudiante(Integer.MAX_VALUE);
            int ganadosEstudianteMejor=0,presentadosEstudianteMejor=0;
            int presentadosEstudiante=0, ganadosEstudiante=0, presentadosTaller=0, ganadosTaller=0;
            
            //recorrer todos los estudiantes y guardar sus presentados y ganados
            Collection<VO.Estudiante> estudiantesTaller = taller.getEstudianteCollection();
            Iterator<Estudiante> itEstudiantesTaller = estudiantesTaller.iterator();
            while(itEstudiantesTaller.hasNext()){
                Estudiante estudianteTaller = itEstudiantesTaller.next();
                
                presentadosEstudiante=0;
                ganadosEstudiante=0;
                int idEstudiante = estudianteTaller.getIdEstudiante();
                List<ExamenMes> examenesEnElMes;
                try {
                    examenesEnElMes = DAO.DaoExamenMes.examenesMesDeEstudianteEnMes(idEstudiante, mes);
                } catch (NoItemFoundException ex) {
                    examenesEnElMes = new LinkedList<ExamenMes>();
                }
                //agregar examenes ganados de cada estudiante
                Iterator<ExamenMes> itExamenesEnElMes = examenesEnElMes.iterator();
                while(itExamenesEnElMes.hasNext()){
                    ExamenMes examen = itExamenesEnElMes.next();
                    ganadosEstudiante += examen.getGanados();
                    ganadosTaller += examen.getGanados();
                }
                Date desde, hasta;
                Calendar calendario = Calendar.getInstance();
                calendario.set(Calendar.HOUR_OF_DAY, 0);
                calendario.set(Calendar.MINUTE, 0);
                calendario.set(Calendar.SECOND, 0);
                calendario.set(Calendar.DATE, 1);
                hasta = calendario.getTime();//el mes de hoy
                calendario.add(Calendar.MONTH, -1);
                desde = calendario.getTime();//el el mes anterior
                List<VO.ExamenSolicitado> listaExamenesVO;
                try {
                    //el dia de mañana
                    listaExamenesVO = DAO.DaoExamenSolicitado.consultarExamenesSolicitadosEntreFechas(desde, hasta); //consultar examenes de la fecha desde ya que hasta está ocn hora 00:00:00
                    //presentadosEstudiante +=
                } catch (NoItemFoundException ex) {
                    listaExamenesVO = new LinkedList<VO.ExamenSolicitado>();
                }
                Iterator<ExamenSolicitado> itListaExamenesVO = listaExamenesVO.iterator();
                while(itListaExamenesVO.hasNext()){
                    ExamenSolicitado examen = itListaExamenesVO.next();
                    if(examen.getIdEstado().getIdEstado() != Estado.NO_PRESENTADO.getIdEstado()){
                        presentadosTaller++;
                        presentadosEstudiante++;
                    }
                }
                if(ganadosEstudianteMejor < ganadosEstudiante){
                    MejorEstudianteTaller=estudianteTaller;
                    ganadosEstudianteMejor = ganadosEstudiante;
                    presentadosEstudianteMejor = presentadosEstudiante;
                }
                
            }
            System.out.println("iterando por:"+taller.getIdTaller());
            try {

                DAO.DaoExcelenciaTaller.crear(taller.getIdTaller(), MejorEstudianteTaller.getIdEstudiante(), presentadosEstudianteMejor, ganadosEstudianteMejor, presentadosTaller, ganadosTaller);
            } catch (NoItemFoundException ex) {
            }
        }
        informeBloqueado=false;
    }
    
    public static String[][] consultarInformeExcelencia(){
        
        String[][] informeResultante = new String[0][6];
        if( !informeBloqueado ){
            List<VO.ExcelenciaTaller> tablaInforme = DAO.DaoExcelenciaTaller.consultarTodos();
            informeResultante=new String[tablaInforme.size()][6];
            Iterator<VO.ExcelenciaTaller> itTablaInforme = tablaInforme.iterator();
            int i=0;
            while(itTablaInforme.hasNext()){
                VO.ExcelenciaTaller registroInforme = itTablaInforme.next();
                informeResultante[i][0]=registroInforme.getIdTaller().toString();
                informeResultante[i][1]=registroInforme.getIdEstudiante().getIdUsuario().getNombres()+" "+registroInforme.getIdEstudiante().getIdUsuario().getApellidos();
                informeResultante[i][2]=String.valueOf(registroInforme.getPresentados());
                informeResultante[i][3]=String.valueOf(registroInforme.getGanados());
                informeResultante[i][4]=String.valueOf(registroInforme.getPresentadosTaller());
                informeResultante[i][5]=String.valueOf(registroInforme.getGanadosTaller());
                i++;
            }
        }
        return informeResultante;
    }
    
    public static void main(String[] args) {
        //generarInforme();
    }
}
