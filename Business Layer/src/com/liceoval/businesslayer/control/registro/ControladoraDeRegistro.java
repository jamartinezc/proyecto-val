package com.liceoval.businesslayer.control.registro;

import com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoEncontradoException;
import com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoPuedeRegistrarMasExamenesException;
import com.liceoval.businesslayer.control.registro.exceptions.InsersionDeExamenException;
import com.liceoval.businesslayer.control.registro.exceptions.NoExisteAnalistaParaMateriaException;
import com.liceoval.businesslayer.control.registro.exceptions.RegistroNoEncontradoException;
import com.liceoval.businesslayer.control.registro.exceptions.RegistroNoExisteYNoPuedeSerCreadoException;
import com.liceoval.businesslayer.control.registro.exceptions.ZonaHorariaIncorrectaException;

import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.entities.Registro;
import com.liceoval.businesslayer.exceptions.InvalidProcedureCallOrArgumentException;

import CRUD.Crud;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;


/** Esta clase se encarga de controlar, validar y administrar todas las
 *  operaciones relacionadas con los Registros de los Estudiantes.
 * 
 *  Nota: Esta clase aún no está implementada, solo es una interface pública
 *  para poder empezar a construir los componentes de la capa de presentación.
 * 
 *  @see com.liceoval.businesslayer.entities.Registro
 *  @see com.liceoval.businesslayer.entities.Estudiante
 *  @see com.liceoval.businesslayer.entities.Materia
 *  @see com.liceoval.businesslayer.entities.ExamenSolicitado
 *
 *  @author Sergio 
 */

public class ControladoraDeRegistro
{   
    String[] states = null;
    
    /** Agrega un examen al registro del estudiante para la materia especificada.
     *  Si el estudiante no tiene un registro para la materia especificada la función
     *  intenta crear un registro para esa materia.
     * 
     *  @param idExamen El id (código) del examen que debe ser agregado.
     *  @param idEstudiante El id (código) del estudiante para el cual se debe agregar el examen.
     *  @param idMateria El id (código) de la materia a la cual pertenece el examen.
     * 
     *  @throws com.liceoval.businesslayer.exceptions.InvalidProcedureCallOrArgumentException Si idEstudiante, idExamen o idMateria son negativos o cero.
     *  @throws com.liceoval.businesslayer.control.registro.exceptions.RegistroNoExisteYNoPuedeSerCreadoException Si el registro del estudiante para la materia especificada no existe y no puede ser creado por que el estudiante ya tiene tres registros activos.
     *  @throws com.liceoval.businesslayer.control.registro.exceptions.NoExisteAnalistaParaMateriaException Si no es posible encontrar un analista al cual asignar el examen. (La materia especificada no tiene un analista correspondiente)
     *  @throws com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoPuedeRegistrarMasExamenesException Si el estudiante no puede registrar más exámenes para la materia especificada por que tiene exámenes pendientes por presentar, pendientes por calificar o con Nota Examen o Nota Pendiente.
     *  @throws com.liceoval.businesslayer.control.registro.exceptions.ZonaHorariaIncorrectaException Si la zona horaria configurada para el sistema no es correcta o no se puede hayar un ID de sona horaria correspondiente.
     *  @throws com.liceoval.businesslayer.control.registro.exceptions.InsersionDeExamenException Si falla la insersión del examen una vez que se han hecho todas las verificaciones en la capa de negocio. En este caso la excepción se presenta en la Capa de Acceso a Datos.
     */
    
    public static void agregarExamen(int idExamen, int idEstudiante, int idMateria) throws InvalidProcedureCallOrArgumentException, RegistroNoExisteYNoPuedeSerCreadoException, NoExisteAnalistaParaMateriaException, EstudianteNoPuedeRegistrarMasExamenesException, ZonaHorariaIncorrectaException, InsersionDeExamenException
    {
        EstudianteNoPuedeRegistrarMasExamenesException estEx;
        RegistroNoExisteYNoPuedeSerCreadoException regEx;
        InvalidProcedureCallOrArgumentException ipcEx;
        NoExisteAnalistaParaMateriaException anEx;
        VO.ExamenSolicitado examenSolicitado;
        InsersionDeExamenException examEx;
        List<VO.Registro> registros;
        VO.Registro registro;
        VO.Analista analista;
        Crud crud;
                
        Iterator iterator;

        // Variables para el manejo de la fecha
        ZonaHorariaIncorrectaException timeEx;
        boolean fechaLista;
        SimpleTimeZone tz;
        Date fechaExamen;
        int diaSemana;
        Calendar cal;
        String[] ids;
        
        // Validar los datos de entrada:
        
        if(idExamen <= 0)
        {
            ipcEx = new InvalidProcedureCallOrArgumentException(
                "Arguemnto inválido. idExamen es negativo o cero");
            throw ipcEx;
        }
        
        if(idEstudiante <= 0 || idMateria <= 0)
        {
            ipcEx = new InvalidProcedureCallOrArgumentException(
                "Argumento inválido. idEstudiante o idMateria son 0 o negativos");
            throw ipcEx;
        }
        
        // Obtener el registro correspondiente al estudiante y a la materia
        crud = new Crud();
        
        try
        {
            registro = crud.consultarRegistroEstudianteMateria(idEstudiante, idMateria);
        }
        catch(NoItemFoundException nifEx)
        {
            try
            {
                // Verificar cuantos registros activos tiene el estudiante
                registros = crud.consultarRegistrosActivosInactivos(idEstudiante, true);
                if(registros.size() == 3)
                {
                    regEx = new RegistroNoExisteYNoPuedeSerCreadoException(
                        "El registro no existe y no puede ser creado, el estudiante ya tiene tres registros activos");
                    throw regEx;
                }
                
                registros = null;
                System.gc();
                
                // Crear el nuevo registro
                registro = crud.crearRegistro(idEstudiante, idMateria);
            }
            catch(NoItemFoundException nifEx2)
            {
                regEx = new RegistroNoExisteYNoPuedeSerCreadoException(
                    "El registro no existe y no puede ser creado, no existe un estudiante con código " + idEstudiante, nifEx2);
                throw regEx;
            }
            catch(PosibleDuplicationException pdEx)
            {
                // Esté código no tiene sentido: Si el registro no existe ¿como puede estar duplicado?
                regEx = new RegistroNoExisteYNoPuedeSerCreadoException(
                    "El registro no existe y no puede ser creado, el estudiante ya tiene un registro para la materia especificada.", pdEx);
                throw regEx;
            }
        }
        
        // En este punto de la ejecución "se supone" que registro debe contener o bien
        // el registro recuperado o bien el registro recien creado pero si sigue siendo
        // null entonces pues hubo un error inesperado.
        if(registro == null)
        {
            regEx = new RegistroNoExisteYNoPuedeSerCreadoException(
                "El reegistro no existe y no puede ser creado debido a un error inesperado en la base de datos");
            throw regEx;
        }
        
        // Iterar a través del registro y verificar que ninguno de los exámenes
        // asociados a este tenga estado NotaPendiente o NotaExamen.
        iterator = registro.getExamenSolicitadoCollection().iterator();
        while(iterator.hasNext())
        {
            examenSolicitado = (VO.ExamenSolicitado)iterator.next();
            if(examenSolicitado.getIdEstado().getNombre().equals("Pendiente/Aprobar"))
            {
                estEx = new EstudianteNoPuedeRegistrarMasExamenesException(
                    "No se puede completar la solicitud por que el estudiante tiene un examen pendiente por aprobar");
                throw estEx;
            }
            if(examenSolicitado.getIdEstado().getNombre().equals("Pendiente/Presentar"))
            {
                estEx = new EstudianteNoPuedeRegistrarMasExamenesException(
                    "No se puede completar la solicitud por que el estudiante tiene un examen pendiente por presentar");
                throw estEx;
            }
            if(examenSolicitado.getIdEstado().getNombre().equals("Nota Examen"))
            {
                estEx = new EstudianteNoPuedeRegistrarMasExamenesException(
                    "No se puede completar la solicitud por que el estudiante tiene un examen con Nota Examen");
                throw estEx;
            }
            if(examenSolicitado.getIdEstado().getNombre().equals("Nota Pendiente"))
            {
                estEx = new EstudianteNoPuedeRegistrarMasExamenesException(
                    "No se puede completar la solicitud por que el estudiante tiene un examen con Nota Nota Pendiente");
                throw estEx;
            }
        }
        
        try
        {
            // Recuperar el analista que corresponde a la materia
            analista = crud.analistaDeMateria(idMateria);
        }
        catch(NoItemFoundException infEx)
        {
            anEx = new NoExisteAnalistaParaMateriaException(
                "No existe un analista asociado a la materia especificada", infEx);
            throw anEx;
        }

        // Recuperar las IDs soportadas para la zona horaria GMT-5
        ids = TimeZone.getAvailableIDs(-5*60*60*1000);
        if(ids.length == 0)
        {
            timeEx = new ZonaHorariaIncorrectaException(
                "La zona horaria configurada en el sistema es incorrecta o no se encuentra un ID correspondiente");
            throw timeEx;
        }
        
        // Crear una zona horaria para GMT-5 (Bogotá, Lima Quito) y un calendario
        // gregoriano que utilice esa Zona Horaria como base
        tz = new SimpleTimeZone(-5*60*60*1000, ids[0]);
        cal = new GregorianCalendar(tz);
        
        // Obtener la fecha actual del sistema y asignarla al calendario. El examen
        // se programara entonces el siguiente día habil.
        fechaExamen = new Date();
        cal.setTime(fechaExamen);
        fechaLista = false;
        
        // Aumentar un día calendario hasta que se encuentre un día habil.
        while(!fechaLista)
        {
            /** @todo: Mejorar esta parte del código para contemplar los días festivos. */
            
            // Sumar uno día calendario
            cal.add(Calendar.DAY_OF_MONTH, 1);
            diaSemana = cal.get(Calendar.DAY_OF_WEEK);
            if(!(diaSemana == Calendar.SUNDAY || diaSemana == Calendar.SATURDAY))
                fechaLista = true;
        }
        
        // Recuperar la fecha del calendario
        fechaExamen = cal.getTime();
        
        try
        {
            // Crear el nuevo examen solicitado
            crud.crearExamenSolicitado(fechaExamen, idEstudiante, analista.getIdAnalista().intValue(), registro.getIdRegistro().intValue(), idExamen);
        }
        catch(NoItemFoundException nifEx)
        {
            examEx = new InsersionDeExamenException(
                "Falló la insersión del examen. No se encuentra el estudiante, la materia o el examen especificado.", nifEx);
            throw examEx;
        }
        catch(PosibleDuplicationException nifEx)
        {
            examEx = new InsersionDeExamenException(
                "Falló la insersión del examen. El estudiante ya ha solicitado este examen anteriormente.", nifEx);
            throw examEx;
        }
        catch(Exception ex)
        {
            examEx = new InsersionDeExamenException(
                "Falló la insersión del examen. La capa de acceso a datos reportó el siguiente error", ex);
            throw examEx;
        }
    }
    
    /** Devuelve el registro de un estudiante para la materia especificada.
     * 
     *  @param idEstudiante El id del estudiante.
     *  @param idMateria El id de la materia.
     *  @return Un objeto de clase Registro para el estudiante y la materia
     *      especificados.
     *  @throws com.liceoval.businesslayer.control.registro.exceptions.RegistroNoEncontradoException Si
     *      no existe un registro para el estudiante y la materia especificados.
     */
    
    public static Registro getRegistro(int idEstudiante, int idMateria) throws RegistroNoEncontradoException
    {
        RegistroNoEncontradoException regEx;
        Registro registro;
        VO.Registro reg;

        Crud crud;
        
        crud = new Crud();
        
        try
        {
            // Intentar recuperar el registro del estudiante y la materia especificados.
            reg = crud.consultarRegistroEstudianteMateria(idEstudiante, idMateria);
        }
        catch(NoItemFoundException nifEx)
        {
            regEx = new RegistroNoEncontradoException(
                "No se encuentra el registro para el estudiante y materia especificados", nifEx);
            throw regEx;
        }

        // Convertir el registro en un registro de la aplicación.
        registro = EntityTranslator.translateRegistro(reg);
        
        return registro;
    }
    
    /** Devuelve los registros activos o inactivos del estudiante especificado.
     * 
     *  @param idEstudiante El id del estudiante del cual se deben localizar los registros.
     *  @param activo Determina si se deben recuperar solo los registros activos.
     *  @return La lista de registros del estudiante especificado.
     *  @throws com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoEncontradoException
     *      Si la base de datos no puede localizar al estudiante especificado.
     */
    
    public static List<Registro> getRegistros(int idEstudiante, boolean activo) throws EstudianteNoEncontradoException
    {
        EstudianteNoEncontradoException eneEx;
        List<VO.Registro> records;
        List<Registro> registros;
        Crud crud;
        
        crud = new Crud();
        
        try
        {
            records = crud.consultarRegistrosActivosInactivos(idEstudiante, activo);
            registros = EntityTranslator.translateRegistros(records);
            return registros;
        }
        catch(NoItemFoundException nifEx)
        {
            eneEx = new EstudianteNoEncontradoException("No se encuentra el estudiante especificado en la base de datos.", nifEx);
            throw eneEx;
        }
    }
}
