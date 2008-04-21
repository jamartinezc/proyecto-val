/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataaccesslayer;

import CRUD.Crud;
import VO.Analista;
import VO.Estudiante;
import VO.ExamenSolicitado;
import VO.Materia;
import VO.Registro;
import VO.SecretariaAcademica;
import VO.Taller;
import VO.Tutor;
import VO.Usuario;
import java.util.Date;
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
        
        //consultar todos los usuarios
        /*
        List<Usuario> lista = query.listaUsuarios();
        for(int i = 0; i<lista.size();i++)
        {
            System.out.println(lista.get(i).getNombres());
        }
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
        Registro nuevo = query.crearRegistro(309, 19);
        System.out.println(nuevo.getIdEstudiante().getIdUsuario().getNombres());
        System.out.println(nuevo.getIdMateria().getNombre());
        System.out.println(nuevo.getIdRegistro());
        */
        
        //consultar registroEstudianteMateria
        /*
        Registro registro=query.consultarRegistroEstudianteMateria(292, 26);
        if(registro!=null)
        {
            System.out.println(registro.getIdEstudiante().getIdUsuario().getNombres());
            System.out.println(registro.getIdRegistro());
        }
        */
        
        //consultar registros activos/inactivos de un estudiante
        /*
        List<Registro> lista=query.consultarRegistrosActivosInactivos(303, true);
        for(int i = 0; i<lista.size();i++)
        {
            System.out.println(lista.get(i).getIdMateria().getNombre());
        }
        */
        
        //mostrar todos los estudiantes de un taller especificado
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
        
        //crear un usuario
        /*
        Usuario usuario = query.crearUsuario("cosa", "amarilla", "tati", "uyuyuy");
        if(usuario!=null){
            System.out.println(usuario.getIdUsuario());
        }
        else{
            System.out.println("El login ya existe");
        }
        */
        
        
        //eliminar un usuario
        /*
        if(query.eliminarUsuario(84))
        {
            System.out.println("bien");
        }
        else{
            System.out.println("mal");
        }
        */
        
        //actualizar usuario
        /*
        Usuario user = query.actualizarUsuario(100, "davor", "tañito", "damontic", "gelosa");
        if(user!=null){
            System.out.println(user.getIdUsuario()+" "+user.getNombres());
        }
        else{
            System.out.println("El id no existe");
        }
        */
        
        //analista de cierta materia
        /*
        Analista analista = query.analistaDeMateria(18);
        System.out.println(analista.getIdUsuario().getNombres());
        */
        
        //crear un examen solicitado
        /*
        Date fecha = new Date();
        ExamenSolicitado examenSol = query.crearExamenSolicitado(fecha, 290, 1, 4, 13);
        if(examenSol!=null){
            System.out.println(examenSol.getFecha()+" "+examenSol.getIdEstudiante().getIdUsuario().getNombres() );
        }
        else{
            System.out.println("El examen ya se solicitó");
        }
        */
        
    }

}
