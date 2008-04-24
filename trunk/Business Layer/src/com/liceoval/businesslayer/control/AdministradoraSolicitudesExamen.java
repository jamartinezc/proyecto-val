package com.liceoval.businesslayer.control;


import Errores.NoItemFoundException;
import com.liceoval.businesslayer.control.registro.ControladoraDeRegistro;
import com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoPuedeRegistrarMasExamenesException;
import com.liceoval.businesslayer.control.registro.exceptions.InsersionDeExamenException;
import com.liceoval.businesslayer.control.registro.exceptions.NoExisteAnalistaParaMateriaException;
import com.liceoval.businesslayer.control.registro.exceptions.RegistroNoExisteYNoPuedeSerCreadoException;
import com.liceoval.businesslayer.control.registro.exceptions.ZonaHorariaIncorrectaException;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.exceptions.InvalidProcedureCallOrArgumentException;


/**
 *
 * @author Jorge
 */
public class AdministradoraSolicitudesExamen {
    
    public void SolicitarExamen(Estudiante estudiante, int codExamen, int codigoMateria) throws NoItemFoundException, InvalidProcedureCallOrArgumentException, RegistroNoExisteYNoPuedeSerCreadoException, NoExisteAnalistaParaMateriaException, EstudianteNoPuedeRegistrarMasExamenesException, ZonaHorariaIncorrectaException, InsersionDeExamenException{

        int idEstudiante = estudiante.getCodigo();
        ControladoraDeRegistro.agregarExamen(codExamen, idEstudiante, codigoMateria);
        
    }
    
}
