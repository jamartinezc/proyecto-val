package com.liceoval.businesslayer.control;

import CRUD.Crud;
import com.liceoval.businesslayer.entities.Estudiante;
import java.util.Collection;

/**
 *
 * @author Jorge
 */
public class AdministradoraSolicitudesExamen {
    
    public void SolicitarExamen(Estudiante estudiante, int codExamen, int codigoMateria){
        Crud driverDB;
        driverDB = new Crud();
        
        VO.Registro registro;
        //traer registro
        int idEstudiante = estudiante.getIdUsuario();
        registro = driverDB.consultarRegistroEstudianteMateria(idEstudiante, codigoMateria);
        
        if(registro == null){
            //crear registro
        }
        
        Collection<VO.ExamenSolicitado> examenesSolicitados = registro.getExamenSolicitadoCollection();
        
        /* pasarle el examenesSolicitados  a la controladora de registro, esta me
         * mandaráel registro actualizado, lo convierto a VO.Registro y lo mando a
         * la BD
         */
    }
    
    

}
