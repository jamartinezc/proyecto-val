/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataaccesslayer;

import CRUD.Crud;
import VO.Analista;
import VO.Estudiante;
import VO.Materia;
import VO.SecretariaAcademica;
import VO.Taller;
import VO.Tutor;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Crud query = new Crud();
        
        //crear estudiantes, analistas, tutores o secretarias académicas
        
        /*
        Estudiante estudiante = new Estudiante();
        estudiante = query.consultarUsuarioEstudiante("mlinaresv", "ssh22");
        System.out.println(estudiante.getIdUsuario().getNombres());  
        
        Analista analista = new Analista();
        analista = query.consultarUsuarioAnalista("mlinaresv", "ssh22");
        System.out.println(analista.getIdUsuario().getNombres());
        
        Tutor tutor = new Tutor();
        tutor = query.consultarUsuarioTutor("mlinaresv", "ssh22");
        System.out.println(tutor.getIdUsuario().getNombres());
        
        SecretariaAcademica secretaria = new SecretariaAcademica();
        secretaria = query.consultarUsuarioSecretariaAcadémica("mlinaresv", "ssh22");
        System.out.println(secretaria.getIdUsuario().getNombres());
        */
        
        //consultar todas las materias
        /*
        List<Materia> materias = query.consultarMaterias();
        for(int i = 0; i<materias.size();i++)
        {
            System.out.println(materias.get(i).getNombre());
        }
        */
        
        //crear registro
        /*
        if(query.crearRegistro(290, 1))
        {
            System.out.println("bien");
        }
        */
        
        //mostrar todos los estuiantes de un taller especificado
        /*
        Taller taller;
        taller=query.consultarTaller(1);
        Estudiante estudiante;
        Iterator iterador;
        iterador=taller.getEstudianteCollection().iterator();
        while(iterador.hasNext())
        {
            estudiante=(Estudiante) iterador.next();
            System.out.println(estudiante.getIdUsuario().getNombres());
        }
        */
        
        
    }

}
