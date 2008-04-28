package com.liceoval.businesslayer.control;


import CRUD.Crud;
import Errores.NoItemFoundException;
import Errores.NoPresentableException;
import Errores.UltimoTemaException;
import com.liceoval.businesslayer.control.registro.ControladoraDeRegistro;
import com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoPuedeRegistrarMasExamenesException;
import com.liceoval.businesslayer.control.registro.exceptions.InsersionDeExamenException;
import com.liceoval.businesslayer.control.registro.exceptions.NoExisteAnalistaParaMateriaException;
import com.liceoval.businesslayer.control.registro.exceptions.RegistroNoExisteYNoPuedeSerCreadoException;
import com.liceoval.businesslayer.control.registro.exceptions.ZonaHorariaIncorrectaException;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.Examen;
import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.exceptions.InvalidProcedureCallOrArgumentException;


/**
 *
 * @author Jorge
 */
public class AdministradoraSolicitudesExamen {
    
    public static void SolicitarExamen(Estudiante estudiante, int codExamen, int codigoMateria) throws NoItemFoundException, InvalidProcedureCallOrArgumentException, RegistroNoExisteYNoPuedeSerCreadoException, NoExisteAnalistaParaMateriaException, EstudianteNoPuedeRegistrarMasExamenesException, ZonaHorariaIncorrectaException, InsersionDeExamenException{

        int idEstudiante = estudiante.getCodigo();
        ControladoraDeRegistro.agregarExamen(codExamen, idEstudiante, codigoMateria);
        
    }
    
    public static Examen getSiguienteExamen(Estudiante estudiante, int codigoMateria) throws UltimoTemaException, NoItemFoundException, NoPresentableException{
        
        int idEstudiante = estudiante.getCodigo();
        Crud driverDB = new Crud();
        VO.Examen examen = driverDB.getSiguienteExamenDeMateria(codigoMateria, idEstudiante);
        
        Examen examenR = EntityTranslator.translateExamen(examen);
        
        return examenR;
        
    }
        
}
