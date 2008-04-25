/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.rcp;

import com.liceoval.businesslayer.control.rcp.exceptions.GradoNoEncontradoException;
import com.liceoval.businesslayer.entities.Materia;
import com.liceoval.businesslayer.entities.Taller;
import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;

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
