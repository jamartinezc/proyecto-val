/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataaccesslayer;

import CRUD.Crud;
import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Analista;
import VO.Estados;
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
        try{
            estudiante = query.consultarUsuarioEstudiante("asdsd", "ssasdasdh22");
            System.out.println(estudiante.getIdUsuario().getNombres());  
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
   
        Analista analista = new Analista();
        try{
            analista = query.consultarUsuarioAnalista("mlinaresv", "ssh22");
            System.out.println(analista.getIdUsuario().getNombres());
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
          
        Tutor tutor = new Tutor();
        try{
            tutor = query.consultarUsuarioTutor("mlinaresv", "ssh22");
            System.out.println(tutor.getIdUsuario().getNombres());
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
         
        SecretariaAcademica secretaria = new SecretariaAcademica();
        try{
            secretaria = query.consultarUsuarioSecretariaAcadémica("mlinaresv", "ssh22");
            System.out.println(secretaria.getIdUsuario().getNombres());
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
        */
        
        //consultar todos los usuarios ordenados por nombre
        /*
        List<Usuario> lista = query.listaUsuarios();
        for(int i = 0; i<lista.size();i++)
        {
            System.out.println(lista.get(i).getApellidos());
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
        
        //consultar todos los talleres
        /*
        List<Taller> talleres = query.listaTalleres();
        for(int i = 0; i<talleres.size();i++)
        {
            System.out.println(talleres.get(i).getIdTaller());
        }
        */
        
        //crear registro
        /*
        try{
            Registro nuevo = query.crearRegistro(290, 13);
            System.out.println(nuevo.getIdEstudiante().getIdUsuario().getNombres());
            System.out.println(nuevo.getIdMateria().getNombre());
            System.out.println(nuevo.getIdRegistro());
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }
        */
        
        //consultar registroEstudianteMateria
        /*
        try{
            Registro registro=query.consultarRegistroEstudianteMateria(292, 26);
            System.out.println(registro.getIdEstudiante().getIdUsuario().getNombres());
            System.out.println(registro.getIdRegistro());
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
        */
        
        //mostrar todos los estudiantes de un taller especificado
        /*
        Taller taller;
        try{
            taller=query.consultarTaller(100);
            Estudiante estudiante;
            Iterator iterador;
            iterador=taller.getEstudianteCollection().iterator();
            while(iterador.hasNext())
            {
                estudiante=(Estudiante) iterador.next();
                System.out.println(estudiante.getIdUsuario().getNombres());
            }
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        */
        
        //consultar registros activos/inactivos de un estudiante
        /*
        try{
            List<Registro> lista=query.consultarRegistrosActivosInactivos(1000, true);
            for(int i = 0; i<lista.size();i++)
            {
                System.out.println(lista.get(i).getIdMateria().getNombre());
            }
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
        */
        
        //crear un usuario
        /*
        try{
            Usuario usuario = query.crearUsuario("df", "amarsdfsdfilla", "damontic", "gelosa");
        }
        catch(PosibleDuplicationException duplicado){
            System.out.println(duplicado.Mensaje());
        }
        */
        
        //eliminar un usuario
        /*
        try{
            query.eliminarUsuario(84);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        */
        
        //actualizar usuario
        /*
        try{
            Usuario user = query.actualizarUsuario(100, "davor", "tañito", "damontic", "gelosa");
            System.out.println(user.getIdUsuario()+" "+user.getNombres());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        */
        
        //analista de cierta materia
        /*
        try{
            Analista analista = query.analistaDeMateria(18);
            System.out.println(analista.getIdUsuario().getNombres());
        }
        catch(NoItemFoundException error){
             System.out.println(error.Mensaje());
        }   
        */
        
        //crear un examen solicitado
        /*
        Date fecha = new Date();
        try{
            ExamenSolicitado examenSol = query.crearExamenSolicitado(fecha, 309, 4, 7, 10);
            System.out.println(examenSol.getFecha()+" "+examenSol.getIdEstudiante().getIdUsuario().getNombres() );
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
          */
        
        //desactivar registro
        /*
        try{
            Registro registro = query.desactivarRegistro(4);
            System.out.println(registro.getIdRegistro());
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
        */
        
        //cambiar estado de examen solicitado
        /*
        try{
            ExamenSolicitado exam = query.actualizarEstadoDeExamenSolicitado(450, 5);
            System.out.println(exam.getIdExamenSolicitado()+" "+exam.getIdEstudiante().getIdUsuario().getNombres());
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
        */
        
        //consultar estados
        /*
        List<Estados> lista = query.consultarEstados();
        for(int i = 0; i<lista.size();i++)
        {
            System.out.println(lista.get(i).getNombre());
        }
        */
        
        //consultar estudiante
        /*
        try{
            Estudiante estudiante = query.consultarEstudiante(303);
            System.out.println(estudiante.getIdTaller().getIdTutor().getIdUsuario().getNombres());
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
        */
        
        //consultar analista
        /*
        try{
            Analista analist = query.consultarAnalista(25);
            System.out.println(analist.getIdAnalista()+" "+analist.getIdUsuario().getNombres());
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
        */
        
        //consultarExamenSolicitadoEspecífico
        List<ExamenSolicitado> examen;
        /*
        try{
            examen = query.consultarExamenSolicitadoEspecífico(290, 1, 4, 13);
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
        */
        
        //retorna el siguiente examen a presentar de cierta materia para cierto usuario
        /*
        try{
            query.getSiguienteExamenDeMateria(1, 290);
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
        */
        
    }

}
