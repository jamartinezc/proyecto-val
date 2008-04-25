/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.rcp;

import java.util.List;

import com.liceoval.businesslayer.entities.Materia;
import com.liceoval.businesslayer.entities.Taller;
import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;

import CRUD.Crud;

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
