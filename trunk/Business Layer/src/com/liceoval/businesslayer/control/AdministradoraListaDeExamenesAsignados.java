/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import Errores.NoItemFoundException;
import VO.ExamenMes;
import com.liceoval.businesslayer.entities.Estado;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.ExamenSolicitado;
import com.liceoval.businesslayer.entities.Registro;
import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.entities.wrappers.ExamenSolicitadoWrapper;

import java.util.*;
import java.util.LinkedList;
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
        List<VO.ExamenSolicitado> examenesVoGanados, examenesVoNP ,examenesVoNE ,examenesVO = new LinkedList<VO.ExamenSolicitado>();
        try {
            examenesVoGanados = DAO.DaoExamenSolicitado.examenesSolicitadosDeAnalistaPorEstado(idAnalista, Estado.PENDIENTE_CALIFICAR.getIdEstado());
        } catch (NoItemFoundException e) {
            examenesVoGanados = new LinkedList<VO.ExamenSolicitado>();
        }
        
        try {
            examenesVoNP = DAO.DaoExamenSolicitado.examenesSolicitadosDeAnalistaPorEstado(idAnalista, Estado.NOTA_PENDIENTE.getIdEstado());
        } catch (NoItemFoundException e) {
            examenesVoNP = new LinkedList<VO.ExamenSolicitado>();
        }
            
        try {
            examenesVoNE = DAO.DaoExamenSolicitado.examenesSolicitadosDeAnalistaPorEstado(idAnalista, Estado.NOTA_EXAMEN.getIdEstado());
        } catch (NoItemFoundException e) {
            examenesVoNE = new LinkedList<VO.ExamenSolicitado>();
        }
            
            examenesVO.addAll(examenesVoGanados);
            examenesVO.addAll(examenesVoNP);
            examenesVO.addAll(examenesVoNE);
        
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
        //TODO verificar que el examen le haya sido asignado al analista que lo está calificando
        //DAO.DaoExamenSolicitado.consultarUno(idExamenSolicitado).getIdAnalista().getIdAnalista()
        //Inicializar los valores de nota pendiente y nota examinadero:
        float NPi=8.0f,NPf=8.4f,NEi=8.5f,NEf=8.9f,P=7.9f,G=9.0f;
        
       //inicializar los valores con la base de datos
       try {
            NPi = Float.parseFloat(DAO.DaoVariablesGlobales.consultarUno("NotaPendienteInferior").getValor());
            NPf = Float.parseFloat(DAO.DaoVariablesGlobales.consultarUno("NotaPendienteSuperior").getValor());
            NEi = Float.parseFloat(DAO.DaoVariablesGlobales.consultarUno("NotaExamenInferior").getValor());
            NEf = Float.parseFloat(DAO.DaoVariablesGlobales.consultarUno("NotaExamenSuperior").getValor());
            P = Float.parseFloat(DAO.DaoVariablesGlobales.consultarUno("NotaPerdidoSuperior").getValor());
            G = Float.parseFloat(DAO.DaoVariablesGlobales.consultarUno("NotaGanadoInferior").getValor());
            //avisar que se cometió un error
        } catch (NoItemFoundException e) {
            e.printStackTrace();
            NPi=8.0f;NPf=8.4f;NEi=8.5f;NEf=8.9f;P=7.9f;G=9.0f;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            NPi=8.0f;NPf=8.4f;NEi=8.5f;NEf=8.9f;P=7.9f;G=9.0f;
        }
        
        if(nota>=NPi && nota<=NPf){
            DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.NOTA_PENDIENTE.getIdEstado());
        }else if(nota>=NEi && nota<=NEf){
            DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.NOTA_EXAMEN.getIdEstado());
            
        }else if(nota>=G){
            DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.GANADO.getIdEstado());
            VO.ExamenSolicitado examenACalificar = DAO.DaoExamenSolicitado.consultarUno(idExamenSolicitado);
            int idEstudianteExamen = examenACalificar.getIdRegistro().getIdEstudiante().getIdEstudiante();
            int idMateriaExamen = examenACalificar.getIdRegistro().getIdMateria().getIdMateria();
            Calendar fechaExamen = Calendar.getInstance();
            fechaExamen.setTime(examenACalificar.getFecha());
            ExamenMes examenMes = null;
            int ganadosPorEstudiante=0;
            try {
                examenMes = DAO.DaoExamenMes.examenesMesDeMateriaDeEstudianteEnMes(idEstudianteExamen, fechaExamen.get(Calendar.MONTH), idMateriaExamen);
                ganadosPorEstudiante = examenMes.getGanados();
            } catch (NoItemFoundException e) {
                System.out.println("no se encontró examenMes con idE: "+idEstudianteExamen+" mes: "+fechaExamen.get(Calendar.MONTH)+" idMateria: "+idMateriaExamen);
            }
            try {
                DAO.DaoExamenMes.actualizarGanadosExamenMes(examenMes.getIdExamenMes(), ganadosPorEstudiante + 1);
            } catch (NoItemFoundException e) {
                System.out.println("no se encontro ExamenMes con idMateria: "+idMateriaExamen+" para"+(ganadosPorEstudiante+1));
            }
        }else if(nota<=P){
            DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.PERDIDO.getIdEstado());
        }
    }
    
    public static void asignarNoPresentado(int idExamenSolicitado) throws NoItemFoundException{
        DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.NO_PRESENTADO.getIdEstado());
    }
    
    public static boolean validarNota(String nota){
        return true;
    }
}
