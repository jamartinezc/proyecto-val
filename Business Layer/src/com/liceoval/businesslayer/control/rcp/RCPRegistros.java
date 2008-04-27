/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.rcp;

import com.liceoval.businesslayer.control.rcp.exceptions.GradoNoEncontradoException;
import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.entities.Grado;
import com.liceoval.businesslayer.entities.Materia;
import com.liceoval.businesslayer.entities.Taller;

import CRUD.Crud;

import Errores.NoItemFoundException;

import java.util.List;
import java.util.Collection;

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
}
