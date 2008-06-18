package com.liceoval.businesslayer.control;

import com.liceoval.businesslayer.control.exceptions.CodigoDeEstudianteYaExisteException;
import com.liceoval.businesslayer.control.exceptions.DatosDeEstudianteInvalidosException;
import com.liceoval.businesslayer.control.exceptions.LoginYaExisteException;
import com.liceoval.businesslayer.control.exceptions.NoSeEncuentraElUsuarioException;
import com.liceoval.businesslayer.control.exceptions.PadreYaExisteException;
import com.liceoval.businesslayer.control.exceptions.PeticionEjecutadaParcialmenteException;
import com.liceoval.businesslayer.control.exceptions.RegistroDuplicadoInesperadoException;

import com.liceoval.businesslayer.entities.entitytranslator.EntityTranslator;
import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.Padre;
import com.liceoval.businesslayer.entities.Usuario;
import com.liceoval.businesslayer.entities.wrappers.UsuarioWrapper;

import CRUD.Crud;
import Errores.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jorge
 */

public class AdministradoraDeUsuarios {
    
    /**
     * 
     * @return una lista List&lt;Usuario&gt; conteniendo los usuarios del
     * sistema registrados en al base de datos.
     */
    public static List<Usuario> solicitarListaDeUsuarios(){
        Crud driverDB;
        driverDB = new Crud();
        
        List<VO.Usuario> listaTemp = driverDB.listaUsuarios();
        List<Usuario> listaUsuarios = new LinkedList();
        
        for(int i=0;i<listaTemp.size();i++){
            VO.Usuario usuario = listaTemp.get(i);
            Usuario nuevoUsuario = new Usuario();
            
            nuevoUsuario.setIdUsuario( usuario.getIdUsuario() );
            nuevoUsuario.setNombres( usuario.getNombres() );
            nuevoUsuario.setApellidos( usuario.getApellidos() );
            nuevoUsuario.setLogin( usuario.getLogin() );
            nuevoUsuario.setPassword(  (usuario.getClave()).toCharArray()  );
            
            listaUsuarios.add(nuevoUsuario);
        }
        
        return listaUsuarios;
    }
    
    /** Crea un usuario.
     * 
     *  @param usuarioWrapper Un UsuarioWrapper que contiene la información del usuario
     *      y la información de los roles que este desempeña en la aplicación.
     *  @throws java.lang.NullPointerException Si alguno de los campos llega en
     *      <code>Null</code>
     *  @throws com.liceoval.businesslayer.control.exceptions.LoginYaExisteException
     *      Si el Login del usuario ya existe en la base de datos.
     *  @throws com.liceoval.businesslayer.control.exceptions.PeticionEjecutadaParcialmenteException
     *      Si la ejecución de la petición se detiene a la mitad debido a que no
     *      se puede crear un registro para en una de las tablas Analista, Tutor
     *      Estudiante o SecretariaAcademica.
     *  @throws com.liceoval.businesslayer.control.exceptions.PadreYaExisteException
     *      Si se intenta registrar un padre que ya existe en la base de datos.
     *  @throws com.liceoval.businesslayer.control.exceptions.CodigoDeEstudianteYaExisteException
     *      Si se intenta registrar un estudiante con un código que ya existe.
     */
    
    public static void crearUsuario(UsuarioWrapper usuarioWrapper) throws NullPointerException, LoginYaExisteException, PeticionEjecutadaParcialmenteException, PadreYaExisteException, CodigoDeEstudianteYaExisteException
    {
        List<VO.Usuario> usuarios;
        VO.Usuario nuevoUsuario;
        Estudiante estudiante;
        Usuario usuario;
        
        String apellidos;
        String nombres;
        String login;
        String clave;
        int userId;
        
        int grado;
        int codigo;
        int taller;
        int ccPadre;
        String correo;
        String nombresPadre;
        String apellidosPadre;
        
        Padre padre;
        List<Padre> padres;
        Date fechaInicioGrado;
        Calendar fechaInicioGradoCal;
        
        if(usuarioWrapper == null || usuarioWrapper.getUsuario() == null)
            throw new NullPointerException();
        
        usuario = usuarioWrapper.getUsuario();
        
        clave = new String(usuario.getPassword());
        
        apellidos = usuario.getApellidos();
        nombres = usuario.getNombres();
        login = usuario.getLogin();
        
        if(apellidos == null || nombres == null || clave == null || login == null)
            throw new NullPointerException();
        
        try
        {
            nuevoUsuario = DAO.DaoUsuario.crear(nombres, apellidos, login, clave);
            if(nuevoUsuario != null)
            {
                try
                {
                    // El usuario ha sido creado, momento de recuperar el Id del usuario.
                    usuarios = DAO.DaoUsuario.consultarUsuarioPorLogin(login);
                    nuevoUsuario = usuarios.get(0);
                    
                    // Recuperar la ID del usuario.
                    userId = nuevoUsuario.getIdUsuario();
                    
                    // Crear los privilegios de acceso para el usuario.
                    if(usuarioWrapper.isAnalista())
                        DAO.DaoAnalista.crear(userId);
                    
                    if(usuarioWrapper.isSecretariaAcademica())
                        DAO.DaoSecretariaAcademica.crear(userId);
                    
                    if(usuarioWrapper.isTutor())
                        DAO.DaoTutor.crear(userId);
                    
                    if(usuarioWrapper.isEstudiante())
                    {
                        estudiante = (Estudiante)usuario;
                        
                        padres = estudiante.getPadres();
                        if(padres == null)
                            throw new NullPointerException();
                        
                        if(padres.size() < 1)
                            throw new DatosDeEstudianteInvalidosException();
                        
                        padre = padres.get(0);
                        if(padre == null) throw new NullPointerException();
                        
                        grado = estudiante.getGrado().getIdGrado();
                        codigo = estudiante.getCodigo();
                        taller = estudiante.getTaller().getIdTaller();
                        ccPadre = padre.getIdPadre();
                        
                        if(grado < 1 || codigo < 1 || taller < 1 || ccPadre < 1)
                            throw new DatosDeEstudianteInvalidosException();
                        
                        correo = padre.getCorreo();
                        nombresPadre = padre.getNombres();
                        apellidosPadre = padre.getApellidos();
                        
                        if(correo == null || nombresPadre == null || apellidosPadre == null)
                            throw new NullPointerException();
                        
                        fechaInicioGrado = estudiante.getFechaInicioGrado();
                        if(fechaInicioGrado == null)
                            throw new NullPointerException();
                        
                        fechaInicioGradoCal = Calendar.getInstance();
                        fechaInicioGradoCal.setTime(fechaInicioGrado);
                        
                        try
                        {
                            // Crear el estudiante.
                            DAO.DaoEstudiante.crear(codigo, grado, taller, fechaInicioGradoCal, nuevoUsuario.getIdUsuario());
                            
                            try
                            {
                                // Crear el Padre del Estudiante
                                DAO.DaoPadre.crear(nombresPadre, apellidosPadre, correo, codigo);
                            }
                            catch(PosibleDuplicationException pdEx)
                            {
                                throw new PadreYaExisteException("Un padre con ese número " +
                                    "de identificación ya se encuentra registrado en la " +
                                    "base de datos", pdEx);
                            }
                        }
                        catch(PosibleDuplicationException pdEx)
                        {
                            throw new CodigoDeEstudianteYaExisteException("Ya existe un " +
                                "estudiante registrado con ese código en la base de datos");
                        }
                    }
                    
                }
                catch(NoItemFoundException nifEx)
                {
                    throw new PeticionEjecutadaParcialmenteException("El usuario fue creado, sin" +
                        " embargo, no se pudieron establecer todos los permisos de acceso para" +
                        " el mismo.", nifEx);
                }
                catch(NullPointerException npEx)
                {
                    throw new PeticionEjecutadaParcialmenteException("El usuario fue creado, sin" +
                        " embargo, no se pudieron establecer todos los permisos de acceso para" +
                        " el mismo.", npEx);
                }
                catch(DatosDeEstudianteInvalidosException deiEx)
                {
                    throw new PeticionEjecutadaParcialmenteException("El usuario fue creado, sin" +
                        " embargo, no se pudieron establecer todos los permisos de acceso para" +
                        " el mismo.", deiEx);
                }
            }
        }
        catch(PosibleDuplicationException pdEx)
        {
            throw new LoginYaExisteException("El login ya está en uso", pdEx);
        }
    }
        
    public static void modificarUsuario(UsuarioWrapper usuarioWrapper) throws NullPointerException, NoSeEncuentraElUsuarioException, RegistroDuplicadoInesperadoException, PeticionEjecutadaParcialmenteException, PadreYaExisteException, CodigoDeEstudianteYaExisteException
    {
        VO.Usuario usuarioAActualizar;
        Estudiante estudiante;
        Usuario usuario;
        
        String apellidos;
        String nombres;
        String login;
        String clave;
        int userId;
        
        int grado;
        int codigo;
        int taller;
        int ccPadre;
        String correo;
        String nombresPadre;
        String apellidosPadre;
        
        Padre padre;
        List<Padre> padres;
        Date fechaInicioGrado;
        Calendar fechaInicioGradoCal;
        
        Iterator<VO.Padre> padresIterator;
        Iterator<VO.Tutor> tutoresIterator;
        Iterator<VO.Analista> analistasIterator;
        Iterator<VO.SecretariaAcademica> secretariasAcademicasIterator;
        VO.SecretariaAcademica secretariaAcademica;
        VO.Estudiante estudianteAActualizar;
        VO.Estudiante estudianteAEliminar;
        VO.Padre padreAActualizar;
        VO.Analista analista;
        VO.Tutor tutor;
        
        if(usuarioWrapper == null || usuarioWrapper.getUsuario() == null)
            throw new NullPointerException();
        
        usuario = usuarioWrapper.getUsuario();
        
        clave = new String(usuario.getPassword());
        apellidos = usuario.getApellidos();
        nombres = usuario.getNombres();
        login = usuario.getLogin();
        userId = usuario.getIdUsuario();
        
        if(apellidos == null || nombres == null || clave == null || login == null)
            throw new NullPointerException();
        
        try
        {
            DAO.DaoUsuario.actualizar(userId, nombres, apellidos, login, clave);
            usuarioAActualizar = DAO.DaoUsuario.consultarUno(userId);
            
            if(usuarioAActualizar != null)
            {
                try
                {
                    // Crear los privilegios de acceso para el usuario.
                    if(usuarioWrapper.isAnalista())
                    {
                        if(usuarioAActualizar.getAnalistaCollection() == null || usuarioAActualizar.getAnalistaCollection().size() == 0)
                            DAO.DaoAnalista.crear(userId);
                    }
                    else
                    {
                        if(usuarioAActualizar.getAnalistaCollection() != null && usuarioAActualizar.getAnalistaCollection().size() > 0)
                        {
                            analistasIterator = usuarioAActualizar.getAnalistaCollection().iterator();
                            while(analistasIterator.hasNext())
                            {
                                analista = analistasIterator.next();
                                DAO.DaoAnalista.eliminar(analista.getIdAnalista().intValue());
                                break;
                            }
                        }
                    }
                    
                    if(usuarioWrapper.isSecretariaAcademica())
                    {
                        if(usuarioAActualizar.getSecretariaAcademicaCollection() == null || usuarioAActualizar.getSecretariaAcademicaCollection().size() == 0)
                            DAO.DaoSecretariaAcademica.crear(userId);                        
                    }
                    else
                    {
                        if(usuarioAActualizar.getSecretariaAcademicaCollection() != null && usuarioAActualizar.getSecretariaAcademicaCollection().size() > 0)
                        {
                            secretariasAcademicasIterator = usuarioAActualizar.getSecretariaAcademicaCollection().iterator();
                            while(secretariasAcademicasIterator.hasNext())
                            {
                                secretariaAcademica = secretariasAcademicasIterator.next();
                                DAO.DaoSecretariaAcademica.eliminar(secretariaAcademica.getIdSecretariaAcademica().intValue());
                                break;
                            }
                        }
                    }
                    
                    if(usuarioWrapper.isTutor())
                    {
                        if(usuarioAActualizar.getTutorCollection() == null || usuarioAActualizar.getTutorCollection().size() == 0)
                            DAO.DaoTutor.crear(userId);
                    }
                    else
                    {
                        if(usuarioAActualizar.getTutorCollection() != null && usuarioAActualizar.getTutorCollection().size() > 0)
                        {
                            tutoresIterator = usuarioAActualizar.getTutorCollection().iterator();
                            while(tutoresIterator.hasNext())
                            {
                                tutor = tutoresIterator.next();
                                DAO.DaoTutor.eliminar(tutor.getIdTutor().intValue());
                                break;
                            }
                        }
                    }
                                        
                    if(usuarioWrapper.isEstudiante())
                    {
                        estudiante = (Estudiante)usuario;
                        
                        padres = estudiante.getPadres();
                        if(padres == null)
                            throw new NullPointerException();
                        
                        if(padres.size() < 1)
                            throw new DatosDeEstudianteInvalidosException();
                        
                        padre = padres.get(0);
                        if(padre == null) throw new NullPointerException();
                        
                        grado = estudiante.getGrado().getIdGrado();
                        codigo = estudiante.getCodigo();
                        taller = estudiante.getTaller().getIdTaller();
                        ccPadre = padre.getIdPadre();
                        
                        if(grado < 1 || codigo < 1 || taller < 1 || ccPadre < 1)
                            throw new DatosDeEstudianteInvalidosException();
                        
                        correo = padre.getCorreo();
                        nombresPadre = padre.getNombres();
                        apellidosPadre = padre.getApellidos();
                        
                        if(correo == null || nombresPadre == null || apellidosPadre == null)
                            throw new NullPointerException();
                        
                        fechaInicioGrado = estudiante.getFechaInicioGrado();
                        if(fechaInicioGrado == null)
                            throw new NullPointerException();
                        
                        fechaInicioGradoCal = Calendar.getInstance();
                        fechaInicioGradoCal.setTime(fechaInicioGrado);
                        
                        try
                        {
                            estudianteAActualizar = usuarioAActualizar.getEstudiante();
                            if(estudianteAActualizar == null)
                            {
                                System.out.println("Codigo:" + codigo);
                                System.out.println("Taller:" + taller);
                                System.out.println("Usuario:" + userId);
                                
                                // Crear el estudiante.
                                DAO.DaoEstudiante.crear(codigo, grado, taller, fechaInicioGradoCal, userId);
                                
                                // Recuperar el estudiante recien creado
                                estudianteAActualizar = DAO.DaoEstudiante.consultarUno(codigo);
                            }
                            else
                            {
                                // Actualiza el estudiante
                                // TODO: Agregar el código para actualizar el estudiante.
                            }
                            
                            try
                            {
                                if(estudianteAActualizar.getPadreCollection() == null || estudianteAActualizar.getPadreCollection().size() == 0)
                                {
                                    // Crear el Padre del Estudiante
                                    DAO.DaoPadre.crear(nombresPadre, apellidosPadre, correo, codigo);
                                }    
                                else
                                {
                                    padresIterator = estudianteAActualizar.getPadreCollection().iterator();
                                    padreAActualizar = null;
                                    
                                    while(padresIterator.hasNext())
                                    {
                                        padreAActualizar = padresIterator.next();
                                        break;
                                    }
                                    
                                    if(padreAActualizar != null)
                                    {
                                        // Actualizar el padre del estudiante
                                        // TODO: Agregar el código para actualizar el padre.
                                    }
                                }
                            }
                            catch(PosibleDuplicationException pdEx)
                            {
                                throw new PadreYaExisteException("Un padre con ese número " +
                                    "de identificación ya se encuentra registrado en la " +
                                    "base de datos", pdEx);
                            }
                        }
                        catch(PosibleDuplicationException pdEx)
                        {
                            throw new CodigoDeEstudianteYaExisteException("Ya existe un " +
                                "estudiante registrado con ese código en la base de datos");
                        }
                    }
                    else
                    {
                        estudianteAEliminar = usuarioAActualizar.getEstudiante();
                        if(estudianteAEliminar != null)
                        {
                            DAO.DaoEstudiante.eliminar(estudianteAEliminar.getIdEstudiante().intValue());
                        }
                    }
                    
                }
                catch(NoItemFoundException nifEx)
                {
                    throw new PeticionEjecutadaParcialmenteException("El usuario fue actualizado, sin" +
                        " embargo, no se pudieron establecer todos los permisos de acceso para" +
                        " el mismo.", nifEx);
                }
                catch(NullPointerException npEx)
                {
                    throw new PeticionEjecutadaParcialmenteException("El usuario fue actualizado, sin" +
                        " embargo, no se pudieron establecer todos los permisos de acceso para" +
                        " el mismo.", npEx);
                }
                catch(DatosDeEstudianteInvalidosException deiEx)
                {
                    throw new PeticionEjecutadaParcialmenteException("El usuario fue actualizado, sin" +
                        " embargo, no se pudieron establecer todos los permisos de acceso para" +
                        " el mismo.", deiEx);
                }
            }
        }
        catch(NoItemFoundException nifEx)
        {
            throw new NoSeEncuentraElUsuarioException("No se puede localizar el registro del usuario en la base de datos.", nifEx);
        }
        catch(PosibleDuplicationException pdEx)
        {
            throw new RegistroDuplicadoInesperadoException();
        }
    }
    
    /**
     * 
     * @param usuario objeto usuairo con el ID de usuario y con los datos como
     * se quiere que estén despues de la modificacion.
     * @return <p>true si se modificó satisfacroriamente </p>
     * <p>false si hubo un error y no se pudo modificar
     * @throws java.lang.Exception si el usuario no existe.
     
    public static boolean modificarUsuario(Usuario usuario) throws NoItemFoundException{
        
        if(usuario != null){
            int idUsuario = usuario.getIdUsuario();
            String nombres = usuario.getNombres();
            String apellidos = usuario.getApellidos();
            String login = usuario.getLogin();
            String clave = String.valueOf(usuario.getPassword());
            
            VO.Usuario usuarioModificado = DAO.DaoUsuario.actualizar(idUsuario, nombres, apellidos, login, clave);
            
            if(usuarioModificado != null){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
        
    } */
    
    /** Elimina el usuario específicado de la base de datos.
     * 
     *  @param idUsuario El ID del usuario.
     *  @throws com.liceoval.businesslayer.control.exceptions.NoSeEncuentraElUsuarioException Si
     *      no existe un usuario con la ID especificada en la base de datos.
     */
    
    public static void eliminarUsuario(int idUsuario) throws NoSeEncuentraElUsuarioException
    {
        Crud driverDB;
        driverDB = new Crud();
        NoSeEncuentraElUsuarioException nseeuEx;
        
        try
        {
            DAO.DaoUsuario.eliminar(idUsuario);
        }
        catch(NoItemFoundException nifEx)
        {
            nseeuEx = new NoSeEncuentraElUsuarioException(
                "No se encuentra el usuario especificado", nifEx);
            throw nseeuEx;
        }
    }
    
    /** Devuelve un usuario específico de acuerdo con la ID de usuario
     *  especificada. El usuario devuelto está envuelto en una clase
     *  UsuarioWrapper para manejar el problema de los múltiples roles.
     * 
     *  @param idUsuario El ID del usuario que se desea recuperar. Este
     *      objeto puede ser de clase Usuario o puede ser de clase Estudiante.
     *  @return Un objeto de clase UsuarioWrapper con la información del
     *      usuario especificado.
     *  @throws com.liceoval.businesslayer.control.exceptions.NoSeEncuentraElUsuarioException; Si
     *      no se puede encontrar un usuario con el ID especificado en la base
     *      de datos.
     */
    
    public static UsuarioWrapper cargarUsuario(int idUsuario) throws NoSeEncuentraElUsuarioException
    {
        NoSeEncuentraElUsuarioException nseeuEx;
        UsuarioWrapper usuarioWrapper;
        VO.Usuario user;
        Usuario usuario;
        
        
        try
        {
            user = DAO.DaoUsuario.consultarUno(idUsuario);
        }
        catch(NoItemFoundException nifEx)
        {
            nseeuEx = new NoSeEncuentraElUsuarioException(
                "No se encuentra el usuario especificado", nifEx);
            throw nseeuEx;
        }
        
        // Verificar si se trata de un estudiante
        if(user.getEstudiante() != null)
        {
            // Obtener un objeto estudiante a partir del usuario.            
            usuario = EntityTranslator.translateEstudiante(user.getEstudiante());
                        
            usuarioWrapper = new UsuarioWrapper(usuario);
            usuarioWrapper.setRolEstudiante(true);
        }
        else
        {
            // Traducir el usuario y meterlo en el Wrapper.
            usuario = EntityTranslator.translateUsuario(user);
            usuarioWrapper = new UsuarioWrapper(usuario);
        }
        
        // Verificar que roles cumple el usuario
        if(user.getAnalistaCollection() != null)
        {
            if(user.getAnalistaCollection().size() > 0)
                usuarioWrapper.setRolAnalista(true);
        }
        
        if(user.getTutorCollection() != null)
        {
            if(user.getTutorCollection().size() > 0)
                usuarioWrapper.setRolTutor(true);
        }
        
        if(user.getSecretariaAcademicaCollection() != null)
        {
            if(user.getSecretariaAcademicaCollection().size() > 0)
                usuarioWrapper.setRolSecretariaAcademica(true);
        }
        
        // Devolver el Wrapper del usuario.
        return usuarioWrapper;
    }
    
    /** Verifica si el estudiante especificado pertenece al taller del tutor
     *  especificado.
     * 
     *  @param idEstudiante El id del estudiante que se quiere confirmar.
     *  @param idTutor El id del tutor que se quiere confirmar.
     *  @return Verdadero si el estudiante pertenece al taller del tutor, falso si no.
     */
    
    public static boolean confirmarEstudianteTallerTutor(int idEstudiante, int idTutor)
    {
        Crud crud;
        
        crud = new Crud();
        return crud.estudianteDelTallerDeTutor(idEstudiante, idTutor);
    }
    public static void main(String[] args) throws NoItemFoundException {
                System.out.println("modificarUsuario");
        String q="PassTest";
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2);
        usuario.setNombres("Tester");
        usuario.setApellidos("Test");
        usuario.setLogin("Testing");
        usuario.setPassword(q.toCharArray());
        boolean expResult = true;
        // boolean result = AdministradoraDeUsuarios.modificarUsuario(usuario);
    }
}
