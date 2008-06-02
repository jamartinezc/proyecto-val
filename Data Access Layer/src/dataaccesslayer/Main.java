/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataaccesslayer;

import CRUD.Crud;
import DAO.DaoAnalista;
import DAO.DaoEstados;
import DAO.DaoEstudiante;
import DAO.DaoExamen;
import DAO.DaoExamenSolicitado;
import DAO.DaoGrado;
import DAO.DaoMateria;
import DAO.DaoRegistro;
import DAO.DaoSecretariaAcademica;
import DAO.DaoTaller;
import DAO.DaoTutor;
import DAO.DaoUsuario;
import Errores.MateriaDeOtroGradoException;
import Errores.NoItemFoundException;
import Errores.NoPresentableException;
import Errores.PosibleDuplicationException;
import Errores.UltimoTemaException;
import VO.Analista;
import VO.Estados;
import VO.Estudiante;
import VO.Examen;
import VO.ExamenSolicitado;
import VO.Grado;
import VO.Materia;
import VO.Registro;
import VO.SecretariaAcademica;
import VO.Taller;
import VO.Tutor;
import VO.Usuario;
import java.util.Calendar;
import java.util.Date;
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
        
        //boolean estudianteDelTallerDeTutor(int idEstudiante, int idTutor)
        //permite saber si un estudiante pertenece al taller de cierto tutor
        /*
        if(query.estudianteDelTallerDeTutor(290, 3)==true){
            System.out.println("esta en el taller");
        }
        else{
            System.out.println("no esta en el taller");
        }
        */
        
        //Collection<taller> consultarTallerDeTutor(int idTutor)
        //retorna el taller del tutor
        /*
        try{
            List<Taller> taller = query.consultarTallerDeTutor(4);
            System.out.println(taller.get(0).getIdTaller());
            System.out.println(taller.get(1).getIdTaller());
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
        */
        
        //List<examenessolicitados> examenesSolicitadosPorEstudiantesDeTutor(int idTutor)
        //retorna los examenes solicitados d elos estudiantes de tutor
        /*
        List<ExamenSolicitado> ex = null;
        try{
            ex = query.examenesSolicitadosPorEstudiantesDeTutor(2);
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
        if(ex!=null){
            System.out.println(ex.get(0).getIdExamenSolicitado());
            System.out.println(ex.get(1).getIdExamenSolicitado());
            System.out.println(ex.get(0).getClass());
        }
        */
        
        //nueva manera de hacer las cosas
        
        //ProbarDaoAnalista();
        //ProbarDaoEstados();
        ProbarDaoEstudiante();
        //ProbarDaoExamen();
        
        //ProbarDaoExamenSolicitado(); //falta!!!!
        
        
        //ProbarDaoGrado();
        //ProbarDaoMateria();
        
        
        
        
        //ProbarDaoRegistro();
        //ProbarDaoSecretariaAcademica();
        //ProbarDaoTaller();
        //ProbarDaoTutor();
        //ProbarDaoUsuario();
    }
    
    /*
    * Pruebas DaoExamenSolicitado()
    */
    private static void ProbarDaoExamenSolicitado(){
        //ProbarDaoExamenSolicitadoConsultarTodos();
        
        //ProbarDaoExamenSolicitadoConsultarUno();
        
        //ProbarDaoExamenSolicitadoEliminar();
        
        //Date fecha = new Date();
        //ProbarDaoExamenSolicitadoActualizarFecha(4,fecha);
        
        /*
        Date desde = new Date();
        Date hasta = new Date();
        Calendar myCal = Calendar.getInstance();
        myCal.setTime(new Date());
        myCal.set(Calendar.YEAR, 2008);
        myCal.set(Calendar.MONTH, 4);
        myCal.set(Calendar.DAY_OF_MONTH, 1);
        desde = myCal.getTime();
        System.out.println(desde.getMonth());
        myCal.setTime(new Date());
        myCal.set(Calendar.YEAR, 2008);
        myCal.set(Calendar.MONTH, 5);
        myCal.set(Calendar.DAY_OF_MONTH, 30);
        hasta = myCal.getTime();
        System.out.println(hasta.getMonth());
        ProbarDaoExamenSolicitadoConsultarExamenesSolicitadosEntreFechas(desde, hasta);
        */
        
        //ProbarDaoExamenSolicitadoCrear();
        
        
    }
    
    private static void ProbarDaoExamenSolicitadoConsultarTodos() {
        List<ExamenSolicitado> manes = DaoExamenSolicitado.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdExamenSolicitado());
        }
    }

    private static void ProbarDaoExamenSolicitadoConsultarUno() {
        try{
            ExamenSolicitado mansito = DaoExamenSolicitado.consultarUno(5);
            System.out.println(mansito.getIdExamenSolicitado());
            System.out.println(mansito.getIdRegistro().getIdRegistro());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoExamenSolicitadoCrear() {
        Date fecha = new Date();
        try{
            ExamenSolicitado mio=DaoExamenSolicitado.crear(fecha, 291, 4, 8, 79);
            System.out.println(mio.getIdExamenSolicitado());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoExamenSolicitadoEliminar() {
        try{
            DaoExamenSolicitado.eliminar(456);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    private static void ProbarDaoExamenSolicitadoActualizarFecha(int id,Date fecha){
        try{
            ExamenSolicitado uno = DaoExamenSolicitado.actualizarFecha(id, fecha);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    private static void ProbarDaoExamenSolicitadoConsultarExamenesSolicitadosEntreFechas(Date from, Date to){
        try{
            List<ExamenSolicitado> lista = DaoExamenSolicitado.consultarExamenesSolicitadosEntreFechas(from, to);
            for(int i=0;i<lista.size();i++){
                System.out.println(lista.get(i).getFecha().getMonth()+lista.get(i).getFecha().getDay());
            }
            
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoRegistro
    */
    private static void ProbarDaoRegistro() {
        //ProbarDaoRegistroConsultarTodos();
        //ProbarDaoRegistroConsultarUno();
        //ProbarDaoRegistroCrear();   
        //ProbarDaoRegistroEliminar();
    }
    
    private static void ProbarDaoRegistroConsultarTodos() {
        List<Registro> manes = DaoRegistro.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdRegistro());
        }
    }

    private static void ProbarDaoRegistroConsultarUno() {
        try{
            Registro mansito = DaoRegistro.consultarUno(31);
            System.out.println(mansito.getIdRegistro());
            System.out.println(mansito.getVecesDevuelta());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoRegistroCrear() {
        try{
            Registro mio=DaoRegistro.crear(324, true, 1, 0);
            System.out.println(mio.getIdRegistro());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }
        catch(MateriaDeOtroGradoException otro){
            System.out.println(otro.Mensaje());
        }
    }

    private static void ProbarDaoRegistroEliminar() {
        try{
            DaoRegistro.eliminar(74);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoTutor
    */
    private static void ProbarDaoTutor() {
        //ProbarDaoTutorConsultarTodos();
        //ProbarDaoTutorConsultarUno();
        //ProbarDaoTutorCrear();   
        //ProbarDaoTutorEliminar();
    }
    
    private static void ProbarDaoTutorConsultarTodos() {
        List<Tutor> manes = DaoTutor.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdUsuario().getNombres());
        }
    }

    private static void ProbarDaoTutorConsultarUno() {
        try{
            Tutor mansito = DaoTutor.consultarUno(5);
            System.out.println(mansito.getIdUsuario().getIdUsuario());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoTutorCrear() {
        try{
            Tutor mio=DaoTutor.crear(42);
            System.out.println(mio.getIdUsuario().getNombres());
            System.out.println(mio.getIdTutor());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoTutorEliminar() {
        try{
            DaoTutor.eliminar(10);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    /*
    * Pruebas DaoTaller
    */
    private static void ProbarDaoTaller() {
        //ProbarDaoTallerConsultarTodos();
        //ProbarDaoTallerConsultarUno();
    }
    
    private static void ProbarDaoTallerConsultarTodos() {
        List<Taller> manes = DaoTaller.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdTaller());
        }
    }
    
     private static void ProbarDaoTallerConsultarUno() {
        try{
            Taller mansito = DaoTaller.consultarUno(4);
            System.out.println(mansito.getIdTaller());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoSecretariaAcademica
    */
    private static void ProbarDaoSecretariaAcademica() {
        //ProbarDaoSecretariaAcademicaConsultarTodos();
        //ProbarDaoSecretariaAcademicaConsultarUno();
        //ProbarDaoSecretariaAcademicaCrear();   
        //ProbarDaoSecretariaAcademicaEliminar();
    }
    
    private static void ProbarDaoSecretariaAcademicaConsultarTodos() {
        List<SecretariaAcademica> manes = DaoSecretariaAcademica.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdUsuario().getNombres());
        }
    }

    private static void ProbarDaoSecretariaAcademicaConsultarUno() {
        try{
            SecretariaAcademica mansito = DaoSecretariaAcademica.consultarUno(45);
            System.out.println(mansito.getIdUsuario().getIdUsuario());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoSecretariaAcademicaCrear() {
        try{
            SecretariaAcademica mio=DaoSecretariaAcademica.crear(42);
            System.out.println(mio.getIdUsuario().getNombres());
            System.out.println(mio.getIdSecretariaAcademica());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoSecretariaAcademicaEliminar() {
        try{
            DaoSecretariaAcademica.eliminar(9);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoMateria
    */
    private static void ProbarDaoMateria() {
        //ProbarDaoMateriaConsultarTodos();
        //ProbarDaoMateriaConsultarUno();
    }
    
    private static void ProbarDaoMateriaConsultarTodos() {
        List<Materia> manes = DaoMateria.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getNombre());
        }
    }
    
     private static void ProbarDaoMateriaConsultarUno() {
        try{
            Materia mansito = DaoMateria.consultarUno(11);
            System.out.println(mansito.getNombre());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoGrado
    */
    private static void ProbarDaoGrado() {
        //ProbarDaoGradoConsultarTodos();
        //ProbarDaoGradoConsultarUno();
    }
    
    private static void ProbarDaoGradoConsultarTodos() {
        List<Grado> manes = DaoGrado.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getNombre());
        }
    }
    
     private static void ProbarDaoGradoConsultarUno() {
        try{
            Grado mansito = DaoGrado.consultarUno(12);
            System.out.println(mansito.getNombre());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoExamen
    */
    private static void ProbarDaoExamen() {
        //ProbarDaoExamenConsultarTodos();
        //ProbarDaoExamenConsultarUno();
    }
    
    private static void ProbarDaoExamenConsultarTodos() {
        List<Examen> manes = DaoExamen.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getTema());
        }
    }
    
    private static void ProbarDaoExamenConsultarUno() {
        try{
            Examen mansito = DaoExamen.consultarUno(136);
            System.out.println(mansito.getTema());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoEstados
    */
    private static void ProbarDaoEstados() {
        //ProbarDaoEstadosConsultarTodos();
        //ProbarDaoEstadosConsultarUno();
    }
    
    private static void ProbarDaoEstadosConsultarTodos() {
        List<Estados> manes = DaoEstados.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getNombre());
        }
    }
    
    private static void ProbarDaoEstadosConsultarUno() {
        try{
            Estados mansito = DaoEstados.consultarUno(10);
            System.out.println(mansito.getNombre());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoAnalista
    */
    private static void ProbarDaoAnalista() {
        //ProbarDaoAnalistaConsultarTodos();
        //ProbarDaoAnalistaConsultarUno();
        //ProbarDaoAnalistaCrear();   
        //ProbarDaoAnalistaEliminar();
    }
    
    private static void ProbarDaoAnalistaConsultarTodos() {
        List<Analista> manes = DaoAnalista.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdAnalista());
        }
    }

    private static void ProbarDaoAnalistaConsultarUno() {
        try{
            Analista mansito = DaoAnalista.consultarUno(45);
            System.out.println(mansito.getIdUsuario().getIdUsuario());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoAnalistaCrear() {
        try{
            Analista mio=DaoAnalista.crear(42);
            System.out.println(mio.getIdAnalista());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoAnalistaEliminar() {
        try{
            DaoAnalista.eliminar(11);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    /*
    * Pruebas DaoEstudiante
    */
    
    private static void ProbarDaoEstudiante() {
        //ProbarDaoEstudianteConsultarTodos();
        //ProbarDaoEstudianteConsultarUno();
        //ProbarDaoEstudianteCrear();   
        //ProbarDaoEstudianteEliminar();
        ProbarDaoEstudianteConsultarEstudiantesConExamenParaAnalista();
    }

    private static void ProbarDaoEstudianteConsultarEstudiantesConExamenParaAnalista() {
        List<Estudiante> manes = DaoEstudiante.consultarEstudiantesConExamenParaAnalista(4);
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdEstudiante());
        }
    }
    
    private static void ProbarDaoEstudianteConsultarTodos() {
        List<Estudiante> manes = DaoEstudiante.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdUsuario().getNombres());
        }
    }

    private static void ProbarDaoEstudianteConsultarUno() {
        try{
            Estudiante mansito = DaoEstudiante.consultarUno(456);
            System.out.println(mansito.getIdUsuario().getNombres());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoEstudianteCrear() {
        Calendar hoy = Calendar.getInstance();
        hoy.set(2008, 5, 26);
        try{
            Estudiante mio=DaoEstudiante.crear(324, 10, 1, hoy, 45);
            System.out.println(mio.getIdUsuario().getNombres());
            System.out.println(mio.getIdEstudiante());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoEstudianteEliminar() {
        try{
            DaoEstudiante.eliminar(324);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    /*
    * Pruebas DaoUsuario
    */

    private static void ProbarDaoUsuario() {
        //ProbarDaoUsuarioConsultarTodos();
        //ProbarDaoUsuarioConsultarUno();
        //ProbarDaoUsuarioCrear();   
        //ProbarDaoUsuarioEliminar();
        //ProbarDaoUsuarioActualizar();
        //ProbarDaoUsuarioConsultarUsuario();
    }

    private static void ProbarDaoUsuarioActualizar() {
        try{
            Usuario user = DaoUsuario.actualizar(44, "davor", "tañito", "damontic", "gelosa");
            System.out.println(user.getIdUsuario()+" "+user.getNombres());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    private static void ProbarDaoUsuarioConsultarTodos() {
        List<Usuario> manes = DaoUsuario.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdUsuario());
        }
    }

    private static void ProbarDaoUsuarioConsultarUno() {
        try{
            Usuario mansito = DaoUsuario.consultarUno(42);
            System.out.println(mansito.getIdUsuario());
            System.out.println(mansito.getApellidos()+" "+mansito.getNombres());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoUsuarioConsultarUsuario() {
        try{
            List<Usuario> mansito = DaoUsuario.consultarUsuario("sergio", "sergio");
            System.out.println(mansito.get(0).getNombres());
            System.out.println(mansito.get(0).getIdUsuario());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }

    private static void ProbarDaoUsuarioCrear(){
        try{
            Usuario usuario = DaoUsuario.crear("Pepito", "Perez", "pepito", "pepito");
        }
        catch(PosibleDuplicationException duplicado){
            System.out.println(duplicado.Mensaje());
        }
    }

    private static void ProbarDaoUsuarioEliminar() {
         try{
            Usuario eliminado = DaoUsuario.eliminar(44);
            System.out.println(eliminado.getIdUsuario());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    
}
