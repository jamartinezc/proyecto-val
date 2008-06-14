/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import Errores.NoItemFoundException;
import com.liceoval.businesslayer.entities.Estado;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.ExamenSolicitado;
import com.liceoval.businesslayer.entities.Registro;
import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.entities.wrappers.ExamenSolicitadoWrapper;

import java.util.*;
/**
 *
 * @author Jorge
 */
public class AdministradoraListaDeExamenesAsignados {
    
    public static List<ExamenSolicitadoWrapper> generarListaDeExamenesACalificar(Integer idAnalista) throws NoItemFoundException{
        
        LinkedList<ExamenSolicitadoWrapper> examenesACalificar = new LinkedList<ExamenSolicitadoWrapper>();
        ExamenSolicitado examenNN;
        VO.Estudiante estudianteDeExamenVO;
        Estudiante estudianteDeExamen;
        ExamenSolicitadoWrapper examen;
        
        VO.Analista analista = DAO.DaoAnalista.consultarUno(idAnalista);
                    //Collection<VO.ExamenSolicitado> examenesVO = analista.getExamenSolicitadoCollection();
        Collection<VO.ExamenSolicitado> examenesVO = null;//DAO.DaoAnalista.consultarExamenesSolicitadosPendienteCalificarDeAnalista(idAnalista);
        
        VO.Registro registroVO = new VO.Registro();
        Registro registro = new Registro();
        
        Iterator<VO.ExamenSolicitado> itExamenes = examenesVO.iterator();
        VO.ExamenSolicitado examenVO;
        while(itExamenes.hasNext()){
            examenVO = itExamenes.next();
            examenNN = EntityTranslator.translateExamenSolicitado(examenVO);
            
            estudianteDeExamenVO = examenVO.getIdRegistro().getIdEstudiante();
            estudianteDeExamen = EntityTranslator.translateEstudiante(estudianteDeExamenVO);
            registroVO = examenVO.getIdRegistro();
            registro = EntityTranslator.translateRegistro(registroVO);
            
            examen = new ExamenSolicitadoWrapper();
            examen.setExamenSolicitado(examenNN);
            examen.setEstudiante(estudianteDeExamen);
            examen.setRegistro(registro);
            examenesACalificar.add(examen);
        }
        
        return examenesACalificar;
        
    }
    
    public static void guardarNota(int idExamenSolicitado, float nota) throws NoItemFoundException {
        
        DAO.DaoExamenSolicitado.actualizarNotaDeExamenSolicitado(idExamenSolicitado, nota);
        //TODO actualizar la cantidad de examenes presentados
        
        //int[] ganadosPerdidos = DUMMY.DAO.DaoExamenMes.setExamenesPresentadosYganados();
        
        //Inicializar los valores de nota pendiente y nota examinadero:
        float NPi=8.0f,NPf=8.4f,NEi=8.5f,NEf=8.9f,P=7.9f,G=9.0f;
        
       //inicializar los valores con la base de datos
        
        if(nota>=NPi && nota<=NPf){
            DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.NOTA_PENDIENTE.getIdEstado());
        }else if(nota>=NEi && nota<=NEf){
            DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.NOTA_EXAMEN.getIdEstado());
            //TODO actualizar la cantidad de examenes ganados
        }else if(nota>=G){
            DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.GANADO.getIdEstado());
            //TODO actualizar la cantidad de examenes ganados
        }else if(nota<=P){
            DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.PERDIDO.getIdEstado());
        }
    }
    
    public static void asignarNoPresentado(int idExamenSolicitado) throws NoItemFoundException{
        DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.NO_PRESENTADO.getIdEstado());
    }
}
