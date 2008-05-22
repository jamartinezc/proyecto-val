/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataaccesslayer;

import DAO.DaoAnalista;
import DAO.DaoEstados;
import DAO.DaoEstudiante;
import DAO.DaoExamen;
import DAO.DaoGrado;
import DAO.DaoMateria;
import DAO.DaoSecretariaAcademica;
import DAO.DaoTaller;
import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Analista;
import VO.Estados;
import VO.Estudiante;
import VO.Examen;
import VO.Grado;
import VO.Materia;
import VO.SecretariaAcademica;
import VO.Taller;
import java.util.Calendar;
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
        //Crud query = new Crud();
        /*
        //buscar usuario por id
        Usuario usuario = new Usuario();
        try{
            usuario = query.consultarUsuario(1);
            System.out.println(usuario.getApellidos());
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
        */
        
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
            Registro nuevo = query.crearRegistro(303, 16);
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
            Analista analista = query.analistaDeMateria(87);
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
            ExamenSolicitado examenSol = query.crearExamenSolicitado(fecha, 290, 1, 4, 14);
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
        /*
        List<ExamenSolicitado> examen;
        try{
            examen = query.consultarExamenSolicitadoEspecífico(308, 4, 7, 10);
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
        */
        
        //buscar usuario por nombre
        /*
        try{
            List<Usuario> lista = query.buscarUsuario("v");
            for(int i = 0; i<lista.size();i++)
            {
                System.out.println(lista.get(i).getApellidos()+lista.get(i).getNombres());
            }
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
        */   
        
        
        //retorna el siguiente examen a presentar de cierta materia para cierto usuario
        /*
        Examen next;
        try{
            next = query.getSiguienteExamenDeMateria(2, 290);
            System.out.println(next.getTema());
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
        catch(UltimoTemaException au){
            System.out.println(au.Mensaje());
        }
        catch(NoPresentableException jeje){
            System.out.println(jeje.Mensaje());
        }
        */
        
        //consultar grados
        /*
        List<Grado> lista = query.consultarGrados();
        for(int i = 0; i<lista.size();i++)
        {
            System.out.println(lista.get(i).getNombre());
        }
        */
        
        //buscar examen solicitado por tema
        /*
        try{
            List<ExamenSolicitado> lista = query.examenesSolicitadosPorTema(290, 13);
            for(int i = 0; i<lista.size();i++)
            {
                System.out.println(lista.get(i).getIdExamenSolicitado());
            }
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
        */
        
        //materias de cierto analista
        /*
        try{
            List<Materia> lista = query.materiasDeAnalista(1);
            for(int i = 0; i<lista.size();i++)
            {
                System.out.println(lista.get(i).getNombre());
            }
        }
        catch(NoItemFoundException error){
             System.out.println(error.Mensaje());
        }   
        */
        
        //consultar examen solicitado correspondiente a cierto analista (En proceso XD)
        /*
        try{
            List<ExamenSolicitado> lista = query.consultarListaExamenesPorCalificarDeAnalista(1);
            for(int i = 0; i<lista.size();i++)
            {
                System.out.println(lista.get(i).getIdExamenSolicitado());
            }
        }
        catch(NoItemFoundException error){
             System.out.println(error.Mensaje());
        }   
        */
        
        //nueva manera de hacer las cosas
        
        //DaoAnalista
        /*List<Analista> manes = DaoAnalista.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdAnalista());
        }
        try{
            Analista mansito = DaoAnalista.consultarUno(1);
            System.out.println(mansito.getIdUsuario().getIdUsuario());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }/*
        try{
            DaoAnalista.eliminar(6);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        try{
            Analista mio=DaoAnalista.crear(17);
            System.out.println(mio.getIdAnalista());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }*/
         
        
        //DaoEstados
        /*
        List<Estados> manes = DaoEstados.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getNombre());
        }
        try{
            Estados mansito = DaoEstados.consultarUno(4);
            System.out.println(mansito.getNombre());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        */
        
        //DaoEstudiante
        
        /*List<Estudiante> manes = DaoEstudiante.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdUsuario().getNombres());
        }
        try{
            Estudiante mansito = DaoEstudiante.consultarUno(290);
            System.out.println(mansito.getIdUsuario().getNombres());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        try{
            DaoEstudiante.eliminar(310);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        
        Calendar hoy = Calendar.getInstance();
        hoy.set(2008, 5, 24);
        try{
            Estudiante mio=DaoEstudiante.crear(313, 10, 3, hoy, 14);
            System.out.println(mio.getIdUsuario().getNombres());
            System.out.println(mio.getIdEstudiante());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }*/
        
        //DaoExamen
        /*
        List<Examen> manes = DaoExamen.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getTema());
        }
        try{
            Examen mansito = DaoExamen.consultarUno(4);
            System.out.println(mansito.getTema());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        */
        
        //DaoGrado
        /*
        List<Grado> manes = DaoGrado.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getNombre());
        }
        try{
            Grado mansito = DaoGrado.consultarUno(11);
            System.out.println(mansito.getNombre());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        */
        
        //DaoMateria
        /*
        List<Materia> manes = DaoMateria.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getNombre());
        }
        try{
            Materia mansito = DaoMateria.consultarUno(11);
            System.out.println(mansito.getNombre());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        */
        
        //DaoSecretariaAcademica
        /*
        List<SecretariaAcademica> manes = DaoSecretariaAcademica.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdUsuario().getNombres());
        }
        
        try{
            SecretariaAcademica mansito = DaoSecretariaAcademica.consultarUno(1);
            System.out.println(mansito.getIdUsuario().getIdUsuario());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        
        try{
            DaoSecretariaAcademica.eliminar(5);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        
        try{
            SecretariaAcademica mio=DaoSecretariaAcademica.crear(2);
            System.out.println(mio.getIdUsuario().getNombres());
            System.out.println(mio.getIdSecretariaAcademica());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }*/
        
        
        //DaoTaller
        /*
        List<Taller> manes = DaoTaller.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdTaller());
        }
        
        try{
            Taller mansito = DaoTaller.consultarUno(1);
            System.out.println(mansito.getIdTaller());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        */
    }

}
