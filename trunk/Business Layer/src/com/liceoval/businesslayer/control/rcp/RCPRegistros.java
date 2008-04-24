/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.rcp;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import com.liceoval.businesslayer.entities.Analista;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.Examen;
import com.liceoval.businesslayer.entities.Materia;
import com.liceoval.businesslayer.entities.Taller;
import com.liceoval.businesslayer.entities.Tutor;

import CRUD.Crud;

/**
 *
 * @author Senpai
 */
public class RCPRegistros
{
    public static List<Taller> getTalleres()
    {
        LinkedList<Estudiante> estudiantes;
        LinkedList<Taller> talleres;
        List<VO.Taller> workshops;
        Estudiante estudiante;
        VO.Taller workshop;
        Iterator iterator;
        Taller taller;
        Crud crud;
                
        crud = new Crud();
        workshops = crud.listaTalleres();
        iterator = workshops.iterator();
        talleres = new LinkedList<Taller>();
        
        while(iterator.hasNext())
        {
            workshop = (VO.Taller)iterator.next();
            
                        
            taller = new Taller();
            
        }
        
        return null;
    }
    
    /** Recupera una lista de materias
     * 
     *  @return Una lista de Materias
     */
    
    public static List<Materia> getMaterias()
    {
        LinkedList<Materia> materias;
        Crud crud=new Crud();
        List<VO.Materia> mats;
        Materia mat2;
        Iterator iterator;
        Iterator exIterator;
        VO.Materia mat1;
        VO.Examen jpaexamen;
        Examen examen;
        List<Examen> examenes;
        Tutor tutor;
        
        mats = crud.consultarMaterias();
        iterator = mats.iterator();
        Analista analista;

        materias=new LinkedList<Materia>();
        while(iterator.hasNext())
        {
            mat1=(VO.Materia)iterator.next();
            mat2=new Materia();
            
            analista = new Analista();
            analista.setApellidos(mat1.getIdAnalista().getIdUsuario().getApellidos());
            analista.setIdAnalista(mat1.getIdAnalista().getIdAnalista().intValue());
            analista.setIdUsuario(mat1.getIdAnalista().getIdUsuario().getIdUsuario());
            analista.setLogin(mat1.getIdAnalista().getIdUsuario().getLogin());
            analista.setNombres(mat1.getIdAnalista().getIdUsuario().getNombres());
            analista.setPassword(mat1.getIdAnalista().getIdUsuario().getClave().toCharArray());
            
            mat2.setAnalista(analista);
            mat2.setCodigo(mat1.getIdMateria().intValue());
        
            exIterator = mat1.getExamenCollection().iterator();
            examenes = new LinkedList<Examen>();

            while(exIterator.hasNext())
            {
                examen = new Examen();
                jpaexamen = (VO.Examen)exIterator.next();
                
                examen.setCodigo(jpaexamen.getIdExamen().intValue());
                examen.setTema(jpaexamen.getTema());
                examenes.add(examen);
            }
            
            mat2.setNombre(mat1.getNombre());
            
            tutor = new Tutor();
            tutor.setApellidos(mat1.getIdTutor().getIdUsuario().getApellidos());
            tutor.setIdTutor(mat1.getIdTutor().getIdTutor());
            tutor.setIdUsuario(mat1.getIdTutor().getIdUsuario().getIdUsuario().intValue());
            tutor.setLogin(mat1.getIdTutor().getIdUsuario().getLogin());
            tutor.setNombres(mat1.getIdTutor().getIdUsuario().getNombres());
            tutor.setPassword(mat1.getIdTutor().getIdUsuario().getClave().toCharArray());
            
            mat2.setTutor(tutor);
            materias.add(mat2);
        }

        return materias;
    }
}
