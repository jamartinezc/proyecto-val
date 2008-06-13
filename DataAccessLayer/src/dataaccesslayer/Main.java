/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataaccesslayer;

import DAO.DaoAnalista;
import DAO.DaoEstados;
import DAO.DaoEstudiante;
import DAO.DaoExamen;
import DAO.DaoExamenPlaneado;
import DAO.DaoExamenSolicitado;
import DAO.DaoGrado;
import DAO.DaoMateria;
import DAO.DaoPadre;
import DAO.DaoPlaneacionAnual;
import DAO.DaoRegistro;
import DAO.DaoSecretariaAcademica;
import DAO.DaoTaller;
import DAO.DaoTutor;
import DAO.DaoUsuario;
import Errores.MateriaDeOtroGradoException;
import Errores.NoItemFoundException;
import Errores.PosibleDuplicationException;
import VO.Analista;
import VO.Estados;
import VO.Estudiante;
import VO.Examen;
import VO.ExamenPlaneado;
import VO.ExamenSolicitado;
import VO.Grado;
import VO.Materia;
import VO.Padre;
import VO.PlaneacionAnual;
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
 * @author David
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //ProbarDaoAnalista(); //F
        //ProbarDaoEstados(); //F
        //ProbarDaoEstudiante(); //F
        //ProbarDaoExamen(); //F
        //ProbarDaoExamenMes(); //Falta Empezar
        //ProbarDaoExamenPlaneado(); //F
        //ProbarDaoExamenSolicitado(); //Entre fechas no es segura
        //ProbarDaoExcelenciaTaller(); //Falta Empezar
        //ProbarDaoGrado(); //F
        //ProbarDaoMateria(); //F
        //ProbarDaoMateriaPlaneada(); //Falta Empezar
        //ProbarDaoPadre(); //F
        //ProbarDaoPlaneacionAnual(); //F
        //ProbarDaoPlaneacionSemanal(); //Falta Empezar
        //ProbarDaoRegistro(); //F
        //ProbarDaoSecretariaAcademica(); //F
        //ProbarDaoTaller(); //F
        //ProbarDaoTutor(); //F
        //ProbarDaoUsuario(); //F
        //ProbarDaoVariablesGlobales(); //Falta Empezar
    }
    
      /*
    * Pruebas DaoExamenSolicitado()
    */
    private static void ProbarDaoExamenSolicitado(){
        //ProbarDaoExamenSolicitadoConsultarTodos();
        
        //ProbarDaoExamenSolicitadoConsultarUno();
        
        //ProbarDaoExamenSolicitadoConsultarExamenSolicitadoEspecifico();
        
        //ProbarDaoExamenSolicitadoEliminar();
        
        //ProbarDaoExamenSolicitadoCrear();
        
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
        
        //ProbarDaoExamenSolicitadoActualizarEstadoDeExamenSolicitado();
        //ProbarDaoExamenSolicitadoactualizarNotaDeExamenSolicitado();
        //ProbarDaoExamenSolicitadoExamenesSolicitadosPorEstudiantesDeTutor();
        //ProbarDaoExamenSolicitadoExamenesSolicitadosPorTema();
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

    private static void ProbarDaoExamenSolicitadoConsultarExamenSolicitadoEspecifico() {
        try{
            ExamenSolicitado uy = DaoExamenSolicitado.consultarExamenSolicitadoEspecifico(1, 4, 13);
            System.out.println(uy.getIdExamenSolicitado());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    private static void ProbarDaoExamenSolicitadoCrear() {
        Date fecha = new Date();
        try{
            ExamenSolicitado mio=DaoExamenSolicitado.crear(fecha, 4, 8, 79);
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
            DaoExamenSolicitado.eliminar(10);
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
    
    private static void ProbarDaoExamenSolicitadoActualizarEstadoDeExamenSolicitado() {
        try{
            ExamenSolicitado exam = DaoExamenSolicitado.actualizarEstadoDeExamenSolicitado(9, 4);
            System.out.println(exam.getIdExamenSolicitado());
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
    }

    private static void ProbarDaoExamenSolicitadoactualizarNotaDeExamenSolicitado() {
        try{
            ExamenSolicitado exam = DaoExamenSolicitado.actualizarNotaDeExamenSolicitado(4, 3);
            System.out.println(exam.getIdExamenSolicitado());
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
    }
    
    private static void ProbarDaoExamenSolicitadoExamenesSolicitadosPorEstudiantesDeTutor() {
        try{
            List<ExamenSolicitado> exam = DaoExamenSolicitado.examenesSolicitadosPorEstudiantesDeTutor(2);
            for(int i=0;i<exam.size();i++){
                System.out.println(exam.get(i).getIdExamenSolicitado());
            }
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
        }
    }
    
    private static void ProbarDaoExamenSolicitadoExamenesSolicitadosPorTema() {
        try{
            List<ExamenSolicitado> exam = DaoExamenSolicitado.examenesSolicitadosPorTema(325, 114);
            for(int i=0;i<exam.size();i++){
                System.out.println(exam.get(i).getIdExamenSolicitado());
            }            
        }
        catch(NoItemFoundException mal){
            System.out.println(mal.Mensaje());
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
        //ProbarDaoUsuarioBuscarUsuario();
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
    
    private static void ProbarDaoUsuarioBuscarUsuario() {
        try{
            List<Usuario> mansito = DaoUsuario.buscarUsuario("da");
            for(int i = 0; i<mansito.size();i++)
            {
                System.out.println(mansito.get(i).getIdUsuario());
            }
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
        ProbarDaoTallerConsultarTallerDeTutor();
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
            Taller mansito = DaoTaller.consultarUno(3);
            System.out.println(mansito.getIdTaller());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
     
    private static void ProbarDaoTallerConsultarTallerDeTutor() {
        try{
            List<Taller> mansito = DaoTaller.consultarTallerDeTutor(1);
            for(int i = 0; i<mansito.size();i++)
        {
            System.out.println(mansito.get(i).getIdTaller());
        }
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
            SecretariaAcademica mansito = DaoSecretariaAcademica.consultarUno(7);
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
    * Pruebas DaoRegistro
    */
    
    private static void ProbarDaoRegistro() {
        //ProbarDaoRegistroConsultarTodos();
        //ProbarDaoRegistroConsultarUno();
        //ProbarDaoRegistroCrear();   
        //ProbarDaoRegistroEliminar();
        //ProbarDaoRegistroDesactivarRegistro();
        //ProbarDaoRegistroConsultarRegistroActivosInactivos();
        ProbarDaoRegistroConsultarRegistroEstudianteMateria();
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
    
    private static void ProbarDaoRegistroDesactivarRegistro() {
        try{
            Registro mansito = DaoRegistro.desactivarRegistro(4);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    private static void ProbarDaoRegistroConsultarRegistroActivosInactivos() {
        try{
            List<Registro> manes = DaoRegistro.consultarRegistrosActivosInactivos(290, false);
            for(int i = 0; i<manes.size();i++)
            {
                System.out.println(manes.get(i).getIdRegistro());
            }
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
    }
    
    private static void ProbarDaoRegistroConsultarRegistroEstudianteMateria(){
        try{
            Registro mansito = DaoRegistro.consultarRegistroEstudianteMateria(290, 26);
            System.out.println(mansito.getIdRegistro());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoPlaneacionAnual
    */
    
    private static void ProbarDaoPlaneacionAnual(){
        //ProbarDaoPlaneacionAnualConsultarTodos();
        //ProbarDaoPlaneacionAnualConsultarUno();
        //ProbarDaoPlaneacionAnualCrear();
        //ProbarDaoPlaneacionAnualEliminar();
    }
    
    private static void ProbarDaoPlaneacionAnualConsultarTodos() {
        List<PlaneacionAnual> manes = DaoPlaneacionAnual.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdPlaneacionAnual());
        }
    }   
    
    private static void ProbarDaoPlaneacionAnualConsultarUno() {
        try{
            PlaneacionAnual mansito = DaoPlaneacionAnual.consultarUno(2);
            System.out.println(mansito.getIdEstudiante());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    private static void ProbarDaoPlaneacionAnualCrear() {
        try{
            PlaneacionAnual mio=DaoPlaneacionAnual.crear(291);
            System.out.println(mio.getIdEstudiante());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    private static void ProbarDaoPlaneacionAnualEliminar() {
        try{
            DaoPlaneacionAnual.eliminar(2);
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoPadre()
    */
    private static void ProbarDaoPadre(){
        //ProbarDaoPadreConsultarTodos();
        //ProbarDaoPadreConsultarUno();
        //ProbarDaoPadreCrear();
        //ProbarDaoPadreEliminar();
    }
    
    private static void ProbarDaoPadreConsultarTodos() {
        List<Padre> manes = DaoPadre.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getApellidos());
        }
    }   
    
    private static void ProbarDaoPadreConsultarUno() {
        try{
            Padre mansito = DaoPadre.consultarUno(5);
            System.out.println(mansito.getApellidos());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    private static void ProbarDaoPadreCrear() {
        try{
            Padre mio=DaoPadre.crear("Felipe", "Perdomo", "cosocoso@unal.edu.co", 291);
            System.out.println(mio.getApellidos());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
        catch(PosibleDuplicationException error){
            System.out.println(error.Mensaje());
        }
    }
    
    private static void ProbarDaoPadreEliminar() {
        try{
            DaoPadre.eliminar(6);
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
        //ProbarDaoMateriaMateriasDeAnalista();
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
     
    private static void ProbarDaoMateriaMateriasDeAnalista() {
        try{
            List<Materia> mansito = DaoMateria.materiasDeAnalista(1);
            for(int i=0;i<mansito.size();i++)
            {
                System.out.println(mansito.get(i).getNombre());
            }
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
        ProbarDaoGradoConsultarUno();
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
            Grado mansito = DaoGrado.consultarUno(11);
            System.out.println(mansito.getNombre());
        }
        catch(NoItemFoundException error){
            System.out.println(error.Mensaje());
        }
    }
    
    /*
    * Pruebas DaoExamenPlaneado
    */
    private static void ProbarDaoExamenPlaneado() {
        //ProbarDaoExamenPlaneadoConsultarTodos();
        //ProbarDaoExamenPlaneadoConsultarUno();
    }
    
    private static void ProbarDaoExamenPlaneadoConsultarTodos() {
        List<ExamenPlaneado> manes = DaoExamenPlaneado.consultarTodos();
        for(int i = 0; i<manes.size();i++)
        {
            System.out.println(manes.get(i).getIdExamenPlaneado());
        }
    }
    
    private static void ProbarDaoExamenPlaneadoConsultarUno() {
        try{
            ExamenPlaneado mansito = DaoExamenPlaneado.consultarUno(1);
            System.out.println(mansito.getFecha());
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
            Examen mansito = DaoExamen.consultarUno(13);
            System.out.println(mansito.getTema());
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
        //ProbarDaoEstudianteConsultarEstudiantesConExamenParaAnalista();
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
            Estudiante mansito = DaoEstudiante.consultarUno(325);
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
            Estudiante mio=DaoEstudiante.crear(326, 10, 1, hoy, 45);
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
    * Pruebas DaoEstados
    */
    private static void ProbarDaoEstados() {
        //ProbarDaoEstadosConsultarTodos();
        ProbarDaoEstadosConsultarUno();
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
            Estados mansito = DaoEstados.consultarUno(8);
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
        //ProbarDaoAnalistaanalistaDeMateria();
        //ProbarDaoAnalistaConsultarAnalista();
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
            Analista mansito = DaoAnalista.consultarUno(1);
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
    
    private static void ProbarDaoAnalistaanalistaDeMateria() {
        try{
            Analista uy= DaoAnalista.analistaDeMateria(22);
            System.out.println(uy.getIdAnalista());
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
    }
    
    private static void ProbarDaoAnalistaConsultarAnalista() {
        try{
            Analista uy= DaoAnalista.consultarAnalista(30);
            System.out.println(uy.getIdAnalista());
        }
        catch(NoItemFoundException uy){
            System.out.println(uy.Mensaje());
        }
    }
    
}
