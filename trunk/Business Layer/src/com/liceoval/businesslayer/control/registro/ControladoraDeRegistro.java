package com.liceoval.businesslayer.control.registro;

import com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoEncontradoException;
import com.liceoval.businesslayer.control.registro.exceptions.EstudianteNoPuedeRegistrarMasExamenesException;
import com.liceoval.businesslayer.control.registro.exceptions.InsersionDeExamenException;
import com.liceoval.businesslayer.control.registro.exceptions.NoExisteAnalistaParaMateriaException;
import com.liceoval.businesslayer.control.registro.exceptions.RegistroNoEncontradoException;
import com.liceoval.businesslayer.control.registro.exceptions.RegistroNoExisteYNoPuedeSerCreadoException;

import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.entities.Estado;
import com.liceoval.businesslayer.entities.ExamenSolicitado;
import com.liceoval.businesslayer.entities.Registro;
import com.liceoval.businesslayer.exceptions.InvalidProcedureCallOrArgumentException;

import CRUD.Crud;

import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;

import com.liceoval.businesslayer.control.registro.exceptions.NotaNoCalculableException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
     *  @throws com.liceoval.businesslayer.control.registro.exceptions.InsersionDeExamenException Si falla la insersión del examen una vez que se han hecho todas las verificaciones en la capa de negocio. En este caso la excepción se presenta en la Capa de Acceso a Datos.
     */
    
    public static void agregarExamen(int idExamen, int idEstudiante, int idMateria) throws InvalidProcedureCallOrArgumentException, RegistroNoExisteYNoPuedeSerCreadoException, NoExisteAnalistaParaMateriaException, EstudianteNoPuedeRegistrarMasExamenesException, InsersionDeExamenException
    {
        EstudianteNoPuedeRegistrarMasExamenesException estEx;
        RegistroNoExisteYNoPuedeSerCreadoException regEx;
        InvalidProcedureCallOrArgumentException ipcEx;
        ExamenSolicitado examenSolicitadoTraducido;
        NoExisteAnalistaParaMateriaException anEx;
        VO.ExamenSolicitado examenSolicitado;
        InsersionDeExamenException examEx;       
        List<VO.Registro> registros;
        VO.Registro registro;
        VO.Analista analista;
        Crud crud;
                
        Iterator iterator;

        // Variables para el manejo de la fecha
        Date fechaExamen;
        
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
            registro = DAO.DaoRegistro.consultarRegistroEstudianteMateria(idEstudiante, idMateria);
        }
        catch(NoItemFoundException nifEx)
        {
            try
            {
                // Verificar cuantos registros activos tiene el estudiante
                registros = DAO.DaoRegistro.consultarRegistrosActivosInactivos(idEstudiante, true);
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
        // asociados a este tenga estado NotaPendiente, NotaExamen, este Pendiente
        // por Aprobar, Pendiente por Presentar o Pendiente por Calificar.
        iterator = registro.getExamenSolicitadoCollection().iterator();
        while(iterator.hasNext())
        {
            examenSolicitado = (VO.ExamenSolicitado)iterator.next();
            examenSolicitadoTraducido = EntityTranslator.translateExamenSolicitado(examenSolicitado);
            if(examenSolicitadoTraducido.getEstado().equals(Estado.PENDIENTE_APROBAR))
            {
                estEx = new EstudianteNoPuedeRegistrarMasExamenesException(
                    "No se puede completar la solicitud por que el estudiante tiene un examen pendiente por aprobar");
                throw estEx;
            }
            if(examenSolicitadoTraducido.getEstado().equals(Estado.PENDIENTE_PRESENTAR))
            {
                estEx = new EstudianteNoPuedeRegistrarMasExamenesException(
                    "No se puede completar la solicitud por que el estudiante tiene un examen pendiente por presentar");
                throw estEx;
            }
            if(examenSolicitadoTraducido.getEstado().equals(Estado.NOTA_EXAMEN))
            {
                estEx = new EstudianteNoPuedeRegistrarMasExamenesException(
                    "No se puede completar la solicitud por que el estudiante tiene un examen con Nota Examen");
                throw estEx;
            }
            if(examenSolicitadoTraducido.getEstado().equals(Estado.NOTA_PENDIENTE))
            {
                estEx = new EstudianteNoPuedeRegistrarMasExamenesException(
                    "No se puede completar la solicitud por que el estudiante tiene un examen con Nota Pendiente");
                throw estEx;
            }
            if(examenSolicitadoTraducido.getEstado().equals(Estado.PENDIENTE_CALIFICAR))
            {
                estEx = new EstudianteNoPuedeRegistrarMasExamenesException(
                    "No se puede completar la solicitud por que el estudiante tiene un examen pendiente por calificar");
                throw estEx;
            }
            if(examenSolicitadoTraducido.getEstado().equals(Estado.GANADO))
            {
                estEx = new EstudianteNoPuedeRegistrarMasExamenesException(
                    "No se puede completar la solicitud por que el estudiante ya ha solicitado y aprobado este examen.");
            }
        }
        
        try
        {
            // Recuperar el analista que corresponde a la materia
            analista = DAO.DaoAnalista.analistaDeMateria(idMateria);
        }
        catch(NoItemFoundException infEx)
        {
            anEx = new NoExisteAnalistaParaMateriaException(
                "No existe un analista asociado a la materia especificada", infEx);
            throw anEx;
        }

        // Programar el examen.
        fechaExamen = programarExamen();
                
        try
        {
            // Crear el nuevo examen solicitado
            DAO.DaoExamenSolicitado.crear(fechaExamen, analista.getIdAnalista().intValue(), registro.getIdRegistro().intValue(), idExamen);
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
    
    /** Aprueba o rechaza los examanes especificados.
     * 
     *  @param aprobeList La lista de IDs de los examenes a Aprobar.
     *  @param rejectList La lista de IDs de los examenes a rechazar
     *  @return Un valor booleano. Verdadero si todas las operaciones se completaron
     *      exitosamente, Falso de lo contrario.
     */
    
    public static boolean aprobarRechazarExamenes(List<Integer> aprobeList, List<Integer> rejectList)
    {
        Date fechaExamen;
        boolean success;
        VO.Registro record;
        int idExamenSolicitado;
        Iterator aprobeIterator;
        Iterator rejectIterator;
        VO.ExamenSolicitado requestedExam;
        
        aprobeIterator = aprobeList.iterator();
        rejectIterator = rejectList.iterator();
        success = true;
                
        // Aprobar los exámeenes de la lista
        while(aprobeIterator.hasNext())
        {
            idExamenSolicitado = ((Integer)aprobeIterator.next()).intValue();
            
            try
            {
                // Intentar recuperar el examenSolicitado
                requestedExam = DAO.DaoExamenSolicitado.consultarUno(idExamenSolicitado);
                fechaExamen = requestedExam.getFecha();
                
                // Verificar la fecha del exámen
                if(fechaExamen.compareTo(new Date()) <= 0)
                {
                    // El exámen está vencido, reprogramarlo
                    fechaExamen = programarExamen();
                }
                
                // Actualizar la fecha y el estado
                DAO.DaoExamenSolicitado.actualizarFecha(idExamenSolicitado, fechaExamen);
                DAO.DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(idExamenSolicitado, Estado.PENDIENTE_PRESENTAR.getIdEstado());
            }
            catch(NoItemFoundException nifEx) { success = false; }
        }
        
        while(rejectIterator.hasNext())
        {
            idExamenSolicitado = ((Integer)rejectIterator.next()).intValue();
            try
            {
                requestedExam = DAO.DaoExamenSolicitado.consultarUno(idExamenSolicitado);
                record = requestedExam.getIdRegistro();
                if(record.getExamenSolicitadoCollection().size() == 1)
                {
                    DAO.DaoExamenSolicitado.eliminar(idExamenSolicitado);
                    if(record.getVecesDevuelta() == 0)
                    {
                        DAO.DaoRegistro.eliminar(record.getIdRegistro().intValue());
                    }
                }
                else
                {
                    DAO.DaoExamenSolicitado.eliminar(idExamenSolicitado);
                }
            }
            catch(NoItemFoundException nifEx) { success = false; }
        }
        
        return success;
    }
    
    /** Devuelve la siguiente fecha disponible para programar un examen.
     * 
     *  @return La siguiente fecha disponible para programar un examen.
     */
    
    public static Date programarExamen()
    {
        Calendar cal;
        Date fechaExamen;
        boolean fechaLista;
        
        cal = Calendar.getInstance();
        cal.setTime(new Date());
        fechaLista = false;
        int dayOfWeek;
        
        while(fechaLista == false)
        {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            
            // TODO Agregar el código que verifica que la fecha sea válida.
            // Excluir aquí festivos y vacaciones. Por ahora solo verificamos
            // que no se trate de un fin de semana.
            dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if(!(dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY))
            {
                fechaLista = true;
            }
        }
        
        fechaExamen = cal.getTime();
        return fechaExamen;
    }

    /**
     * Calcula la nota final para un registro inactivo.
     * @param idRegistro id del registro a calcularle la nota.
     * @return  la nota final de ese registro.
     * @throws com.liceoval.businesslayer.control.registro.exceptions.NotaNoCalculableException si el registro es activo.
     * @throws com.liceoval.businesslayer.control.registro.exceptions.RegistroNoEncontradoException si el registro con idRegistro no existe.
     */
    public static float calcularNotaDefinitiva(int idRegistro) throws NotaNoCalculableException, RegistroNoEncontradoException
    {
        float NotaDefinitiva=0.0f;
        VO.Registro registro;
        try {
            registro = DAO.DaoRegistro.consultarUno(idRegistro);
        } catch (NoItemFoundException ex) {
            throw new RegistroNoEncontradoException();
        }
        if (!registro.getActivo()) {
            Collection<VO.ExamenSolicitado> examenes = registro.getExamenSolicitadoCollection();
            Iterator<VO.ExamenSolicitado> itExamenes = examenes.iterator();
            VO.ExamenSolicitado examen;
            int cantidadExamenes = examenes.size();
            NotaDefinitiva = 0.0f;
            while (itExamenes.hasNext()) {
                examen = itExamenes.next();
                NotaDefinitiva += examen.getNota() / cantidadExamenes;
            }
        } else {
            throw new NotaNoCalculableException();
        }
        return NotaDefinitiva;
    }
}
