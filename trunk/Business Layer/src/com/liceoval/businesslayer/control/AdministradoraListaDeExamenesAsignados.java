/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import CRUD.Crud;
import Errores.NoItemFoundException;
import com.liceoval.businesslayer.entities.Analista;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.ExamenSolicitado;
import com.liceoval.businesslayer.entities.Registro;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.entities.wrappers.ExamenSolicitadoWrapper;
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
        ExamenSolicitadoWrapper examen = new ExamenSolicitadoWrapper();
        
        VO.Analista analista = DAO.DaoAnalista.consultarUno(idAnalista);
        Collection<VO.ExamenSolicitado> examenesVO = analista.getExamenSolicitadoCollection();
        
        VO.Registro registroVO = new VO.Registro();
        Registro registro = new Registro();
        
        Iterator<VO.ExamenSolicitado> itExamenes = examenesVO.iterator();
        VO.ExamenSolicitado examenVO;
        while(itExamenes.hasNext()){
            examenVO = itExamenes.next();
            examenNN = EntityTranslator.translateExamenSolicitado(examenVO);
            
            estudianteDeExamenVO = examenVO.getIdEstudiante();
            estudianteDeExamen = EntityTranslator.translateEstudiante(estudianteDeExamenVO);
            registroVO = examenVO.getIdRegistro();
            registro = EntityTranslator.translateRegistro(registroVO);
            
            examen.setExamenSolicitado(examenNN);
            examen.setEstudiante(estudianteDeExamen);
            examen.setRegistro(registro);
            examenesACalificar.add(examen);
        }
        
        return examenesACalificar;
        
    }
    
}
