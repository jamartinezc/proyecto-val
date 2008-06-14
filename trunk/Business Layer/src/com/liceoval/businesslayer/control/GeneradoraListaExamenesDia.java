/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import CRUD.Crud;
import Errores.NoItemFoundException;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.ExamenSolicitado;
import com.liceoval.businesslayer.entities.Registro;
import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.entities.wrappers.ExamenSolicitadoWrapper;
import com.liceoval.businesslayer.control.registro.ControladoraDeRegistro;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jorge
 */
public class GeneradoraListaExamenesDia {
    
    
    
public static LinkedList<ExamenSolicitadoWrapper> generarListaExamenesDia() throws NoItemFoundException{
        Date desde, hasta;
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        desde = calendario.getTime();//el dia de hoy
        hasta = ControladoraDeRegistro.programarExamen();//el dia de mañana
        List<VO.ExamenSolicitado> listaExamenesVO = DAO.DaoExamenSolicitado.consultarExamenesSolicitadosEntreFechas(desde, hasta);//consultar examenes de la fecha desde ya que hasta está ocn hora 00:00:00

        LinkedList<ExamenSolicitadoWrapper> listaExamenes = new LinkedList();

        VO.ExamenSolicitado examenVO;
        VO.Estudiante EstudianteDelExamenVO;
        ExamenSolicitado examenNN;
        Estudiante EstudianteDelExamen;
        Registro registroDelExamen;
        ExamenSolicitadoWrapper examen = new ExamenSolicitadoWrapper();
        Iterator<VO.ExamenSolicitado> ExamVoIterador = listaExamenesVO.iterator();
        while(ExamVoIterador.hasNext()){
            
            examenVO = ExamVoIterador.next();
            examenNN = EntityTranslator.translateExamenSolicitado(examenVO);
            EstudianteDelExamenVO = examenVO.getIdRegistro().getIdEstudiante();
            EstudianteDelExamen = EntityTranslator.translateEstudiante(EstudianteDelExamenVO);
            registroDelExamen  = EntityTranslator.translateRegistro(examenVO.getIdRegistro());
            
            examen.setEstudiante(EstudianteDelExamen);
            examen.setExamenSolicitado(examenNN);
            examen.setRegistro(registroDelExamen);
            
            listaExamenes.add(examen);
        }
        
        return listaExamenes;
    }

    public static void main(String[] args) {
        try {
            generarListaExamenesDia();
        } catch (NoItemFoundException ex) {
            Logger.getLogger(GeneradoraListaExamenesDia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
