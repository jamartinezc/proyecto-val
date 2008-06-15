/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.rcp;

import VO.Registro;
import com.liceoval.businesslayer.control.rcp.exceptions.EstudianteNoEncontradoException;
import com.liceoval.businesslayer.control.rcp.exceptions.GradoNoEncontradoException;
import com.liceoval.businesslayer.control.rcp.exceptions.TutorNoEncontradoException;
import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.Materia;
import com.liceoval.businesslayer.entities.Grado;
import com.liceoval.businesslayer.entities.Taller;
import com.liceoval.businesslayer.entities.wrappers.TallerWrapper;

import CRUD.Crud;

import DAO.DaoEstudiante;

import Errores.NoItemFoundException;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Recupera registros desde la base de datos para las listas desplegables en
 *  los formularios HTML, etc.
 *
 *  @author Senpai
 */

public class RCPRegistros
{
    /** Devuelve una lista de todos los grados disponibles en el sistema.
     * 
     *  @return Una lista de objetos de clase Grado que representa la lista
     *  de grados disponibles en el sistema.
     */
    
    public static List<Grado> getGrados()
    {
        List<VO.Grado> grades;
        
        Crud crud;
        
        crud = new Crud();
        grades = crud.consultarGrados();
        return EntityTranslator.translateGrados(grades);
    }
    
    /** Devuelve la lista de todos los talleres almacenados en la base de
     *  datos.
     * 
     *  @return Una lista de objetos de clase Taller.
     */
    
    public static List<Taller> getTalleres()
    {
        List<Taller> talleres;
        List<VO.Taller> workshops;
       
        Crud crud;
        
        crud = new Crud();
        workshops = crud.listaTalleres();
        talleres = EntityTranslator.translateTalleres(workshops);
        
        return talleres;
    }
    
    /** Devuelve la lista de materias del grado especificado.
     * 
     *  @param idGrado La ID del grado del cual se desea recuperar la lista de materias.
     *  @return La lista de materias del grado espeicificado.
     *  @throws com.liceoval.businesslayer.control.rcp.exceptions.GradoNoEncontradoException Si
     *      la capa de acceso a datos reporta que no encontró registros con la ID de grado
     *      especificada.
     */
    
    public static List<Materia> getMaterias(int idGrado) throws GradoNoEncontradoException
    {
        GradoNoEncontradoException gneEx;
        Collection<VO.Materia> mats;
        List<Materia> materias;
        Crud crud;
        
        crud = new Crud();
        
        try
        {
            mats = crud.materiasDeGrado(idGrado);
        }
        catch(NoItemFoundException ex)
        {
            gneEx = new GradoNoEncontradoException(
                "No se encuentra el grado especificado", ex);
            throw gneEx;
        }
        
        materias = EntityTranslator.translateMaterias(mats);
        return materias;
    }
    
    public static List<Materia> getMateriasDeRegistrsoActivos(int idEstudiante) throws EstudianteNoEncontradoException, GradoNoEncontradoException{
        
        VO.Estudiante estudiante = null;
        Collection<VO.Materia> listaMateriasVO;
        List<VO.Registro> listaRegistrosConExamenesARemover;
        try {
            estudiante = DAO.DaoEstudiante.consultarUno(idEstudiante);
        } catch (NoItemFoundException e) {
            throw new EstudianteNoEncontradoException("No se encuentra el estudiante especificado",e);

        }
        try {
            listaMateriasVO=DAO.DaoMateria.materiasDeGrado(estudiante.getIdGrado().getIdGrado());
        } catch (NoItemFoundException ex) {
            throw new GradoNoEncontradoException("No se encuentra el grado especificado", ex);
        }
        try {
            listaRegistrosConExamenesARemover = DAO.DaoRegistro.consultarRegistrosActivosInactivos(idEstudiante, false);
        } catch (NoItemFoundException ex) {
            //si no hay registros inactivos no se deve remover nada, lista a remover vacia.
            listaRegistrosConExamenesARemover=new LinkedList<VO.Registro>();
        }

        Iterator<Registro> itListaRegistrosConExamenesARemover = listaRegistrosConExamenesARemover.iterator();
        while(itListaRegistrosConExamenesARemover.hasNext()){
            VO.Registro registro = itListaRegistrosConExamenesARemover.next();
            listaMateriasVO.remove(registro.getIdMateria());
        }

        List<Materia> materiasActivas = EntityTranslator.translateMaterias(listaMateriasVO);
        return materiasActivas;
    }
    
    /** Recupera una lista de materias
     * 
     *  @return Una lista de Materias
     */
    
    public static List<Materia> getMaterias()
    {
        List<Materia> materias;
        List<VO.Materia> mats;
        Crud crud;
        
        crud = new Crud();
        
        mats = crud.consultarMaterias();
        materias = EntityTranslator.translateMaterias(mats);
        
        return materias;
    }
    
    /** Devuelve el estudiante que corresponde al id de estudiante especificado.
     * 
     *  @param idEstudiante El id del estudiante especificado.
     *  @return Un objeto de clase Estudiante, la representación del estudiante especificado.
     *  @throws com.liceoval.businesslayer.control.rcp.exceptions.EstudianteNoEncontradoException
     *      Si la base de datos no puede localizar un estudiante cuyo id sea el id especificado.
     */
    
    public static Estudiante getEstudiante(int idEstudiante) throws EstudianteNoEncontradoException
    {
        EstudianteNoEncontradoException eneEx;
                
        try
        {
            return EntityTranslator.translateEstudiante(DaoEstudiante.consultarUno(idEstudiante));
        }
        catch(NoItemFoundException nifEx)
        {
            eneEx = new EstudianteNoEncontradoException("No se encuentra el estudiante especificado. Id: " +
                String.valueOf(idEstudiante), nifEx);
            throw eneEx;
        }
    }
    
    /** Recupera la lista de talleres del Tutor especificado.
     * 
     *  @param idTutor El ID del tutor del cual se desea recuperar los talleres.
     *  @return Una lista de Talleres para el tutor.
     *  @throws com.liceoval.businesslayer.control.rcp.exceptions.TutorNoEncontradoException
     *      Si la base de datos no encuentra al tutor especificado.
     */
    
    public static List<Taller> getTalleres(int idTutor) throws TutorNoEncontradoException
    {
        Crud crud;
        List<Taller> talleres;
        List<VO.Taller> workshops;
        TutorNoEncontradoException tneEx;
        
        crud = new Crud();
        
        try
        {
            // Recuperar los talleres que le pertenencen al tutor
            workshops = crud.consultarTallerDeTutor(idTutor);
            talleres = EntityTranslator.translateTalleres(workshops);
            return talleres;
        }
        catch(NoItemFoundException nifEx)
        {
            tneEx = new TutorNoEncontradoException("No se encuentra el tutor especificado. Id de tutor:" +
                String.valueOf(idTutor), nifEx);
            throw tneEx;
        }
    }
    
    /** Devuelve una lista de TallerWrapper que contiene cada uno de los talleres
     *  de los cuales es tutor el tutor especificado.
     * 
     *  @param idTutor El ID del tutor.
     *  @return Una lista de TallerWrapper con los talleres de los cuales es tutor el Tutor.
     *  @throws com.liceoval.businesslayer.control.rcp.exceptions.TutorNoEncontradoException
     *      Si la base de datos no puede localizar talleres para el tutor especificado.
     */
    
    public static List<TallerWrapper> getTalleresYEstudiantes(int idTutor) throws TutorNoEncontradoException
    {
        Crud crud;
        VO.Taller workshop;
        TallerWrapper taller;
        List<VO.Taller> workshops;
        Iterator workshopsIterator;
        List<Estudiante> estudiantes;
        List<TallerWrapper> talleres;
        TutorNoEncontradoException tneEx;
                
        crud = new Crud();
        
        try
        {
            workshops = crud.consultarTallerDeTutor(idTutor);
            workshopsIterator = workshops.iterator();
            talleres = new LinkedList<TallerWrapper>();
            
            while(workshopsIterator.hasNext())
            {
                workshop = (VO.Taller)workshopsIterator.next();
                taller = new TallerWrapper(
                    EntityTranslator.translateTaller(workshop),
                    EntityTranslator.translateEstudiantes(workshop.getEstudianteCollection()));                

                talleres.add(taller);
            }
            
            return talleres;
        }
        catch(NoItemFoundException nifEx)
        {
            tneEx = new TutorNoEncontradoException("No se encuentra el tutor especificado. Id de tutor:" +
                String.valueOf(idTutor), nifEx);
            throw tneEx;
        }
    }
}