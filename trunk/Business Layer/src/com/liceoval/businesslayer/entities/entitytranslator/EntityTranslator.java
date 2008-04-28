package com.liceoval.businesslayer.entities.entitytranslator;

/** Traduce entre las entidades devueltas por JPA a las entidades de la capa
 *  de negocios.
 *
 *  @author Sergio
 */

import com.liceoval.businesslayer.entities.Analista;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.Examen;
import com.liceoval.businesslayer.entities.ExamenPlaneado;
import com.liceoval.businesslayer.entities.ExamenSolicitado;
import com.liceoval.businesslayer.entities.Grado;
import com.liceoval.businesslayer.entities.Materia;
import com.liceoval.businesslayer.entities.MateriaPlaneada;
import com.liceoval.businesslayer.entities.Padre;
import com.liceoval.businesslayer.entities.PlaneacionAnual;
import com.liceoval.businesslayer.entities.PlaneacionSemanal;
import com.liceoval.businesslayer.entities.Registro;
import com.liceoval.businesslayer.entities.Taller;
import com.liceoval.businesslayer.entities.Tutor;
import com.liceoval.businesslayer.entities.Usuario;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EntityTranslator
{
    /** Traduce un VO.Analista en un Analista.
     * 
     *  @param analist El VO.Analista a traducir
     *  @return El Analista.
     */
    
    public static Analista translateAnalista(VO.Analista analist)
    {
        Analista analista;

        // Si el valor de entrada es null, el de salida también.
        if(analist == null) return null;
        
        // Crear el analista
        analista = new Analista();
        
        // Traducir loa atributos
        analista.setApellidos(analist.getIdUsuario().getApellidos());
        analista.setIdAnalista(analist.getIdAnalista().intValue());
        analista.setIdUsuario(analist.getIdUsuario().getIdUsuario().intValue());
        analista.setLogin(analist.getIdUsuario().getLogin());
        analista.setNombres(analist.getIdUsuario().getNombres());
        analista.setPassword(analist.getIdUsuario().getClave().toCharArray());
        
        // Devolver el analista
        return analista;
    }

    /** Traduce un objeto de tipo VO.Estudiante en un objeto de clase Estudiante
     *  equivalente.
     * 
     *  @param student Objeto de clase VO.Estudiante.
     *  @return Objeto de clase Estudiante equivalente.
     */
    
    public static Estudiante translateEstudiante(VO.Estudiante student)
    {
        Estudiante estudiante;
        
        // Si el valor de entrada es null, el de salida también.
        if(student == null) return null;
        
        // Crrar el estudiante
        estudiante = new Estudiante();
        
        // Traducir cada uno de los atributos.
        estudiante.setApellidos(student.getIdUsuario().getApellidos());
        estudiante.setCodigo(student.getIdEstudiante().intValue());
        estudiante.setFechaInicioGrado(student.getFechaInicioGrado());
        estudiante.setGrado(translateGrado(student.getIdGrado()));
        estudiante.setIdUsuario(student.getIdUsuario().getIdUsuario().intValue());
        estudiante.setLogin(student.getIdUsuario().getLogin());
        estudiante.setNombres(student.getIdUsuario().getNombres());
        estudiante.setPadres(translatePadres(student.getPadreCollection()));
        estudiante.setPassword(student.getIdUsuario().getClave().toCharArray());
        estudiante.setPlaneacionAnual(translatePlaneacionAnual(student.getPlaneacionAnual()));
        estudiante.setPlaneacionesSemanales(translatePlaneacionesSemanales(student.getPlaneacionSemanalCollection()));
        estudiante.setRegistros(translateRegistros(student.getRegistroCollection()));
        estudiante.setTaller(translateTaller(student.getIdTaller()));
        
        // Devolver el estudiante
        return estudiante;
    }
    
    /** Traduce una colección de objetos de clase VO.Grado en una lista
     *  de objetos de clase Grado equivalente.
     * 
     *  @param grades La colección de objetos de clase VO.Grado
     *  @return La lista de objetos de clase Grado equivalente.
     */
    
    public static List<Grado> translateGrados(Collection<VO.Grado> grades)
    {
        LinkedList<Grado> grados;
        Iterator gradesIterator;
        Grado grado;
        
        // Si el valor de entrada es null, el de salida también
        if(grades == null) return null;
        
        // Crear la lista de grados
        grados = new LinkedList<Grado>();
        gradesIterator = grades.iterator();
        
        // Recorrer la lista de grados y traducir cada uno de sus elementos
        while(gradesIterator.hasNext())
        {
            grado = translateGrado((VO.Grado)gradesIterator.next());
            grados.add(grado);
        }
        
        // Devolver la lista
        return grados;
    }
    
    /** Traduce un VO.Grado en un objeto de clase Grado equivalente.
     * 
     *  @param grade El VO.Grado a ser traducido.
     *  @return El objeto de clase Grado equivalente.
     */
    
    public static Grado translateGrado(VO.Grado grade)
    {
        Grado grado;
                
        // Si el valor de entrada es null, el de salida también.
        if(grade == null) return null;
        
        // Crear el grado
        grado = new Grado();
        
        // Traducir cada uno de los atributos
        grado.setIdGrado(grade.getIdGrado().intValue());
        grado.setMaterias(translateMaterias(grade.getMateriaCollection()));
        grado.setNombre(grade.getNombre());
        
        // Devolver el grado.
        return grado;
    }
    
    /** Traduce una colección de objetos VO.Materia en una Lista de objetos
     *  Materia
     * 
     *  @param asignments La colección de VO.Materias a traducir.
     *  @return La lista de objetos Materia equivalente.
     */
    
    public static List<Materia> translateMaterias(Collection<VO.Materia> asignments)
    {
        LinkedList<Materia> materias;
        Iterator asignmentsIterator;
        Materia materia;
        
        // Si el valor de entrada es null, el de salida también.
        if(asignments == null) return null;
        
        // Crear la lista de materias
        materias = new LinkedList<Materia>();
        asignmentsIterator = asignments.iterator();
        
        // Traducir cada uno de los elementos de la lista
        while(asignmentsIterator.hasNext())
        {
            materia = translateMateria((VO.Materia)asignmentsIterator.next());
            materias.add(materia);
        }
        
        // Devolver la lista de materias
        return materias;
    }
    
    public static Materia translateMateria(VO.Materia asignment)
    {
        Materia materia;
        
        // Si el valor de entrada es null, el de salida también.
        if(asignment == null) return null;
        
        // Crear la nueva materia
        materia = new Materia();
        
        // Traducir cada uno de los atributos
        materia.setAnalista(translateAnalista(asignment.getIdAnalista()));
        materia.setCodigo(asignment.getIdMateria());
        materia.setExamenes(translateExamenes(asignment.getExamenCollection()));
        materia.setNombre(asignment.getNombre());
        materia.setTutor(translateTutor(asignment.getIdTutor()));
        
        // Devolver la materia
        return materia;
    }
    
    /** Traduce una colección de VO.Examen en una lista de objetos de tipo Examen.
     * 
     *  @param exams La colección de VO.Examen a ser traducida.
     *  @return La lista de objetos Examen
     */
    
    public static List<Examen> translateExamenes(Collection<VO.Examen> exams)
    {
        LinkedList<Examen> examenes;
        Iterator examsIterator;
        Examen examen;
        
        // Si el valor de entrada es null, el de salida también.
        if(exams == null) return null;
        
        // Crear la lista de exámenes
        examenes = new LinkedList<Examen>();
        examsIterator = exams.iterator();
        
        // Traducir cada examen en la colección
        while(examsIterator.hasNext())
        {
            examen = translateExamen((VO.Examen)examsIterator.next());
            examenes.add(examen);
        }
        
        // Devolver la lista de exámenes
        return examenes;
    }
    
    /** Traduce en VO.Examen a un Examen
     * 
     *  @param exam El VO.Examen a traducir.
     *  @return El Examen
     */
    
    public static Examen translateExamen(VO.Examen exam)
    {
        Examen examen;
        
        // Si el valor de entrada es null, el de salida también.
        if(exam == null) return null;
        
        // Crear el nuevo examen
        examen = new Examen();
        
        // Traducir cada atributo
        examen.setCodigo(exam.getIdExamen());
        examen.setTema(exam.getTema());
        
        // Devolver el examen
        return examen;
    }
    
    /** Traduce un VO.Tutor en un objeto de clase Tutor.
     * 
     *  @param tut El VO.Tutor a traducir.
     *  @return El objeto Tutor equivalente.
     */
    
    public static Tutor translateTutor(VO.Tutor tut)
    {
        Tutor tutor;
        
        // Si el valor de entrada es null, el de salida también.
        if(tut == null) return null;
        
        // Crear el nuevo tutor
        tutor = new Tutor();
        
        // Traducir cada uno de los atributos
        tutor.setApellidos(tut.getIdUsuario().getApellidos());
        tutor.setIdTutor(tut.getIdTutor());
        tutor.setIdUsuario(tut.getIdUsuario().getIdUsuario().intValue());
        tutor.setLogin(tut.getIdUsuario().getLogin());
        tutor.setNombres(tut.getIdUsuario().getNombres());
        tutor.setPassword(tut.getIdUsuario().getClave().toCharArray());
        
        // Devolver el Tutor
        return tutor;
    }
    
    /** Convierte una collection de VO.Padre en una lista de objetos de clase
     *  Padre equivalente.
     * 
     *  @param parents La colección de objetos VO.Padre.
     *  @return La lista de objetos Padre equivalente.
     */
    
    public static List<Padre> translatePadres(Collection<VO.Padre> parents)
    {
        LinkedList<Padre> padres;
        Iterator parentsIterator;
        Padre padre;
        
        // Si el valor de entrada es null, el de salida también.
        if(parents == null) return null;
        
        // Crear la lista de Padres
        padres = new LinkedList<Padre>();
        parentsIterator = parents.iterator();
        
        // Traducir cada uno de los elementos de la colección
        while(parentsIterator.hasNext())
        {
            padre = translatePadre((VO.Padre)parentsIterator.next());
            padres.add(padre);
        }
        
        // Devolver la lista de padres
        return padres;
    }
    
    /** Traduce un objeto VO.Padre a un objeto de clase Padre equivalente.
     * 
     *  @param parent El VO.Padre a traducir.
     *  @return El objeto de clase Padre equivalente
     */
        
    public static Padre translatePadre(VO.Padre parent)
    {
        Padre padre;
        
        // Si el valor de entrada es null, el de salida también.
        if(parent == null) return null;
        
        // Crear el nuevo padre
        padre = new Padre();
        
        // Traducir cada uno de los atributos
        padre.setApellidos(parent.getApellidos());
        padre.setCorreo(parent.getCorreo());
        padre.setIdPadre(parent.getIdPadre().intValue());
        padre.setNombres(parent.getNombres());
        
        // Devolver el padre
        return padre;
    }
    
    /** Traduce un objeto de clase VO.PlaneacionAnual en un objeto de clase
     *  PlaneacionAnual equivalente.
     * 
     *  @param anualPlanning El objeto de clase VO.PlaneacionAnual.
     *  @return El objeto de clase PlaneacionAnual equivalente.
     */
    
    public static PlaneacionAnual translatePlaneacionAnual(VO.PlaneacionAnual anualPlanning)
    {
        PlaneacionAnual planeacionAnual;
        
        // Si el valor de entrada es null, el de salida también.
        if(anualPlanning == null) return null;
        
        // Crear la nueva Planeación Anual
        planeacionAnual = new PlaneacionAnual();
        
        // Traducir cada uno de los atributos
        planeacionAnual.setMateriasPlaneadas(translateMateriasPlaneadas(anualPlanning.getMateriaPlaneadaCollection()));
        
        // Devolver la Planeación Anual
        return planeacionAnual;
    }
    
    /** Traduce una colección de VO.MateriaPlaneada en una lista de objetos de
     *  clase MateriaPlaneada equivalente.
     * 
     *  @param plannedAsignments La colección de VO.MateriaPlaneada.
     *  @return La lista de Materias Planeadas equivalente.
     */
    
    public static List<MateriaPlaneada> translateMateriasPlaneadas(Collection<VO.MateriaPlaneada> plannedAsignments)
    {
        LinkedList<MateriaPlaneada> materiasPlaneadas;
        Iterator plannedAsignmentsIterator;
        MateriaPlaneada materiaPlaneada;
        
        // Si el valor de entrada es null, el de salida también.
        if(plannedAsignments == null) return null;
        
        // Crear la lista de Materias Planeadas
        materiasPlaneadas = new LinkedList<MateriaPlaneada>();
        plannedAsignmentsIterator = plannedAsignments.iterator();
        
        // Traducir cada uno de los elementos de la lista
        while(plannedAsignmentsIterator.hasNext())
        {
            materiaPlaneada = translateMateriaPlaneada((VO.MateriaPlaneada)plannedAsignmentsIterator.next());
            materiasPlaneadas.add(materiaPlaneada);
        }
        
        // Devolver la lista de Materia Planeadas
        return materiasPlaneadas;
    }
    
    /** Traduce una objeto de clase VO.MateriaPlaneada a un objeto de clase
     *  MateriaPlaneada equivalente.
     * 
     *  @param plannedAsignment El VO.MateriaPlaneada a traducir.
     *  @return El objeto de clase MateriaPlaneada equivalente.
     */
    
    public static MateriaPlaneada translateMateriaPlaneada(VO.MateriaPlaneada plannedAsignment)
    {
        MateriaPlaneada materiaPlaneada;
        
        // Si el valor de entrada es null, el de salida también.
        if(plannedAsignment == null) return null;
        
        // Crear la nueva Materia Planeada
        materiaPlaneada = new MateriaPlaneada();
        
        // Traducir cada uno de los atributos
        materiaPlaneada.setMateria(translateMateria(plannedAsignment.getIdMateria()));
        materiaPlaneada.setMesFin(plannedAsignment.getMesFin());
        materiaPlaneada.setMesInicio(plannedAsignment.getMesInicio());
        
        // Devolver la materia planeada
        return materiaPlaneada;
        
    }
    
    /** Traduce una colección de objetos de clase VO.PlaneacionSemanal en una
     *  lista de objetos PlaneacionSemanal equivalente.
     * 
     *  @param weekPlannings La colección de objetos VO.PlaneacionSemanal a
     *      traducir.
     *  @return Una lista de objetos PlaneacionSemanal equivalente.
     */
    
    public static List<PlaneacionSemanal> translatePlaneacionesSemanales(Collection<VO.PlaneacionSemanal> weekPlannings)
    {
        LinkedList<PlaneacionSemanal> planeacionesSemanales;
        Iterator weekPlanningsIterator;
        PlaneacionSemanal planeacionSemanal;
        
        // Si el valor de entrada es null, el de salida también.
        if(weekPlannings == null) return null;
        
        // Crea la lista de Planeaciones Semanales
        planeacionesSemanales = new LinkedList<PlaneacionSemanal>();
        weekPlanningsIterator = weekPlannings.iterator();
        
        // Traducir cada uno de los elementos de la colección
        while(weekPlanningsIterator.hasNext())
        {
            planeacionSemanal = translatePlaneacionSemanal((VO.PlaneacionSemanal)weekPlanningsIterator.next());
            planeacionesSemanales.add(planeacionSemanal);
        }
        
        // Devolver al lista de Planeaciones Semanales
        return planeacionesSemanales;
    }
    
    /** Traduce un objeto de tipo VO.PlaneacionSemanal en un objeto de tipo
     *  PlaneacionSemanal equivalente.
     * 
     *  @param weekPlanning El objeto de clase VO.PlaneacionSemanal a traducir.
     *  @return El objeto de clase PlaneacionSemanal equivalente.
     */
    
    public static PlaneacionSemanal translatePlaneacionSemanal(VO.PlaneacionSemanal weekPlanning)
    {
        PlaneacionSemanal planeacionSemanal;
        
        // Si el valor de entrada es null, el de salida también.
        if(weekPlanning == null) return null;
        
        // Crea la Planeacion Semanal
        planeacionSemanal = new PlaneacionSemanal();
        
        // Traducir cada uno de los atributos
        planeacionSemanal.setExamenesPlaneados(translateExamenesPlaneados(weekPlanning.getExamenPlaneadoCollection()));
        
        // Devolver la Planeación Semanal
        return planeacionSemanal;
    }
    
    /** Traduce una colección de objetos VO.ExamenPlaneado a una lista de
     *  objetos ExamenPlaneado equivalente.
     * 
     *  @param plannedExams La colección de objetos VO.ExamenPlaneado.
     *  @rturn La lista de objetos ExamenPlaneado equivalente.
     */
    
    public static List<ExamenPlaneado> translateExamenesPlaneados(Collection<VO.ExamenPlaneado> plannedExams)
    {
        LinkedList<ExamenPlaneado> examenesPlaneados;
        Iterator plannedExamsIterator;
        ExamenPlaneado examenPlaneado;
        
        // Si el valor de entrada es null, el de salida también.
        if(plannedExams == null) return null;
        
        // Crear la lista de Exámenes Planeados
        examenesPlaneados = new LinkedList<ExamenPlaneado>();
        plannedExamsIterator = plannedExams.iterator();
        
        // Traducir cada uno de los elementos de la lista
        while(plannedExamsIterator.hasNext())
        {
            examenPlaneado = translateExamenPlaneado((VO.ExamenPlaneado)plannedExamsIterator.next());
            examenesPlaneados.add(examenPlaneado);
        }
        
        // Devolver la lista de Exámenes Planeados.
        return examenesPlaneados;
    }
    
    /** Traduce un objeto VO.ExamenPlaneado en un objeto ExamenPlaneado
     *  equivalente.
     * 
     *  @param plannedExam El objeto VO.ExamenPlaneado a traducir.
     *  @return El objeto ExamenPlaneado equivalente.
     */
    
    public static ExamenPlaneado translateExamenPlaneado(VO.ExamenPlaneado plannedExam)
    {
        ExamenPlaneado examenPlaneado;
        
        // Si el valor de entrada es null, el de salida también.
        if(plannedExam == null) return null;
        
        // Crear el Examen Planeado
        examenPlaneado = new ExamenPlaneado();
                        
        // Traducir cada atributo
        examenPlaneado.setEstado(plannedExam.getIdEstado().getIdEstado().intValue());
        examenPlaneado.setExamen(translateExamen(plannedExam.getIdExamen()));
        
        // Devolver el Examen Planeado
        return examenPlaneado;
    }
    
    /** Traduce una colección de objetos VO.Registro en una lista de objetos
     *  de clase Registro equivalente.
     * 
     *  @param records La colección de objetos VO.Registro a traducir.
     *  @return La lista de objetos de clase Registro equivalente.
     */
    
    public static List<Registro> translateRegistros(Collection<VO.Registro> records)
    {
        LinkedList<Registro> registros;
        Iterator recordsIterator;
        Registro registro;
        
        // Si el valor de entrada es null, el de salida también.
        if(records == null) return null;
        
        // Crear la lista de registros
        registros = new LinkedList<Registro>();
        recordsIterator = records.iterator();
        
        // Traducir cada uno de los elementos de la lista
        while(recordsIterator.hasNext())
        {
            registro = translateRegistro((VO.Registro)recordsIterator.next());
            registros.add(registro);
        }
        
        // Devolver al lista de registros
        return registros;
    }
    
    /** Traduce un objeto de tipo VO.Registro en un objeto de clase Registro
     *  equivalente.
     * 
     *  @param record El objeto de clase VO.Registro a traducir.
     *  @return El objeto de clase Registro equivalente.
     */
    
    public static Registro translateRegistro(VO.Registro record)
    {
        Registro registro;
        
        // Si el valor de entrada es null, el de salida también.
        if(record == null) return null;
        
        // Crear el nuevo registro
        registro = new Registro();
        
        // Traducir cada uno de los atributos
        registro.setActivo(record.getActivo());
        registro.setExamenesSolicitados(translateExamenesSolicitados(record.getExamenSolicitadoCollection()));
        registro.setMateria(translateMateria(record.getIdMateria()));
        registro.setVecesDevuelta(record.getVecesDevuelta());
        
        // Devolver el registro.
        return registro;
    }
    
    /** Convierte una colección de objetos de clase VO.ExamenSolicitado en una
     *  lista de objetos de clase ExamenSolicitado equivalente.
     * 
     *  @param requestedExams La colección de objetos de clase VO.ExamenSolicitado.
     *  @return La lista de objetos de clase ExamenSolicitado equivalente.
     */
    
    public static List<ExamenSolicitado> translateExamenesSolicitados(Collection<VO.ExamenSolicitado> requestedExams)
    {
        LinkedList<ExamenSolicitado> examenesSolicitados;
        Iterator requestedExamsIterator;
        ExamenSolicitado examenSolicitado;
        
        // Si el valor de entrada es null, el de salida también.
        if(requestedExams == null) return null;
        
        // Crear la lista de Exámenes Solicitados
        examenesSolicitados = new LinkedList<ExamenSolicitado>();
        requestedExamsIterator = requestedExams.iterator();
        
        // Traducir cada uno de los elementos de la lista
        while(requestedExamsIterator.hasNext())
        {
            examenSolicitado = translateExamenSolicitado((VO.ExamenSolicitado)requestedExamsIterator.next());
            examenesSolicitados.add(examenSolicitado);
        }
        
        // Devolver la lista de Examenes Solicitados.
        return examenesSolicitados;
    }
    
    /** Traduce un objeto de clase VO.ExamenSolicitado en un objeto equivalente
     *  de clase ExamenSolicitado
     * 
     *  @param requestedExam El objeto de clase VO.ExamenSolicitado a ser
     *      traducido.
     *  @return El objeto de clase ExamenSolicitado equivalente.
     */
    
    public static ExamenSolicitado translateExamenSolicitado(VO.ExamenSolicitado requestedExam)
    {
        ExamenSolicitado examenSolicitado;
        
        // Si el valor de entrada es null, el de salida también.
        if(requestedExam == null) return null;
        
        // Crear el nuevo Examen Solicitado
        examenSolicitado = new ExamenSolicitado();
        
        // Traducir cada uno de los atributos
        examenSolicitado.setAnalista(translateAnalista(requestedExam.getIdAnalista()));
        examenSolicitado.setEstado(requestedExam.getIdEstado().getIdEstado().intValue());
        examenSolicitado.setExamen(translateExamen(requestedExam.getIdExamen()));
        examenSolicitado.setFecha(requestedExam.getFecha());
        
        // Devolver el Examen Solicitado
        return examenSolicitado;
    }

    /** Traduce una colección de objetos VO.Taller en una lista de objetos
     *  de clase Taller equivalente.
     * 
     *  @param workshops Colección de objetos de clase VO.Taller a ser traducida.
     *  @return Lasta de objetos de clase Taller equivalente.
     */
    
    public static List<Taller> translateTalleres(Collection<VO.Taller> workshops)
    {
        LinkedList<Taller> talleres;
        Iterator workshopsIterator;
        Taller taller;
        
        // Si el valor de entrada es null, el de salida también.
        if(workshops == null) return null;
        
        // Crear la lista de talleress
        talleres = new LinkedList<Taller>();
        workshopsIterator = workshops.iterator();
        
        // Traducir cada uno de los elementos de la colección
        while(workshopsIterator.hasNext())
        {
            taller = translateTaller((VO.Taller)workshopsIterator.next());
            talleres.add(taller);
        }
        
        // Devolver la nueva lista
        return talleres;
    }
    
    /** Traduce un objeto de clase VO.Taller en un objeto de clase Taller
     *  equivalente.
     * 
     *  @param workshop El objeto de clase VO.Taller a traducir.
     *  @return El objeto de clase Taller equivalente.
     */
    
    public static Taller translateTaller(VO.Taller workshop)
    {
        Taller taller;
                
        // Si el valor de entrada es null, el de salida también.
        if(workshop == null) return null;
        
        // Crear el nuevo Taller
        taller = new Taller();
        
        // Traducir cada uno de los atributos.
        taller.setIdTaller(workshop.getIdTaller().intValue());
        taller.setTutor(translateTutor(workshop.getIdTutor()));
        
        // Devolver el taller
        return taller;
    }
    
    public static Usuario translateUsuario(VO.Usuario user)
    {
        Usuario usuario;
        
        // Si el valor de entrada es null, el de salida también.
        if(user == null) return null;
        
        // Crear el nuevo usuario
        usuario = new Usuario();
        
        // Traducir cada uno de los atributos
        usuario.setApellidos(user.getApellidos());
        usuario.setIdUsuario(user.getIdUsuario().intValue());
        usuario.setLogin(user.getLogin());
        usuario.setNombres(user.getNombres());
        usuario.setPassword(user.getClave().toCharArray());
        
        // Devolver el usuario
        return usuario;
    }
}