/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CRUD;

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
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jaguar
 */
public class Crud {

    //retorna un usuario por su id
    public Usuario consultarUsuario(int idUsuario) throws NoItemFoundException {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();         
            
            Usuario usuario = em.find(Usuario.class, idUsuario);
            if(usuario!=null){
                return usuario;
            }
            else{
                throw new NoItemFoundException();
            }
            
    }
    
    //retorna una lista de los usuarios con cierto login y contraseña
    public List<Usuario> consultarUsuario(String usuario, String password) throws NoItemFoundException {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Usuario p where p.login = :log AND p.clave = :pass");
                q.setParameter("log", usuario);
                q.setParameter("pass", password);
                List<Usuario> lista = q.getResultList();
                tx.commit();
                if(lista.size()>0){
                    return lista;
                }
                else{
                    throw new NoItemFoundException();
                }
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
            
    }
    
    //retorna un estudiante
    public Estudiante consultarUsuarioEstudiante(String usuario, String password) throws NoItemFoundException{
        
            List lista = this.consultarUsuario(usuario, password);
            
            if(lista.size()>0)
            {           
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                EntityManager pm = emf.createEntityManager();            
                Usuario usu;
                usu = (Usuario) lista.get(0);
                try
                {
                    tx.begin();

                    Query q = pm.createQuery("select p from Estudiante p where p.idUsuario = :usuario");
                    q.setParameter("usuario", usu);
                    List<Estudiante> result = q.getResultList();
                    tx.commit();
                    try
                    {
                        return result.get(0);
                    }
                    catch(ArrayIndexOutOfBoundsException err)
                    {
                        return null;
                    }
                }
                finally
                {
                    if (tx.isActive())
                    {
                        tx.rollback();
                    }

                    em.close();
                }
            }
            else
            {
                throw new NoItemFoundException();
            }
    }

    //retorna un Analista
    public Analista consultarUsuarioAnalista(String usuario, String password)throws NoItemFoundException {
        
            List lista = this.consultarUsuario(usuario, password);
            
            if(lista.size()>0)
            {           
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                EntityManager pm = emf.createEntityManager();            
                Usuario usu;
                usu = (Usuario) lista.get(0);
                try
                {
                    tx.begin();

                    Query q = pm.createQuery("select p from Analista p where p.idUsuario = :usuario");
                    q.setParameter("usuario", usu);
                    List<Analista> result = q.getResultList();

                    tx.commit();
                    try
                    {
                        return result.get(0);
                    }
                    catch(ArrayIndexOutOfBoundsException err)
                    {
                        return null;
                    }
                }
                finally
                {
                    if (tx.isActive())
                    {
                        tx.rollback();
                    }

                    em.close();
                }
            }
            else
            {
                throw new NoItemFoundException();
            }
    }
    
    //retorna un Tutor
    public Tutor consultarUsuarioTutor(String usuario, String password) throws NoItemFoundException{
        
            List lista = this.consultarUsuario(usuario, password);
            
            if(lista.size()>0)
            {           
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                EntityManager pm = emf.createEntityManager();            
                Usuario usu;
                usu = (Usuario) lista.get(0);
                try
                {
                    tx.begin();

                    Query q = pm.createQuery("select p from Tutor p where p.idUsuario = :usuario");
                    q.setParameter("usuario", usu);
                    List<Tutor> result = q.getResultList();

                    tx.commit();
                    try
                    {
                        return result.get(0);
                    }
                    catch(ArrayIndexOutOfBoundsException err)
                    {
                        return null;
                    }
                }
                finally
                {
                    if (tx.isActive())
                    {
                        tx.rollback();
                    }

                    em.close();
                }
            }
            else
            {
                throw new NoItemFoundException();
            }
    }

    //retorna una secretaria académica
    public SecretariaAcademica consultarUsuarioSecretariaAcadémica(String usuario, String password) throws NoItemFoundException{
        
            List lista = this.consultarUsuario(usuario, password);
            
            if(lista.size()>0)
            {           
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                EntityManager pm = emf.createEntityManager();            
                Usuario usu;
                usu = (Usuario) lista.get(0);
                try
                {
                    tx.begin();

                    Query q = pm.createQuery("select p from SecretariaAcademica p where p.idUsuario = :usuario");
                    q.setParameter("usuario", usu);
                    List<SecretariaAcademica> result = q.getResultList();

                    tx.commit();
                    try
                    {
                        return result.get(0);
                    }
                    catch(ArrayIndexOutOfBoundsException err)
                    {
                        return null;
                    }
                }
                finally
                {
                    if (tx.isActive())
                    {
                        tx.rollback();
                    }

                    em.close();
                }
            }
            else
            {
                throw new NoItemFoundException();
            }
    }
    
    //consultar estudiante
    public Estudiante consultarEstudiante(int idEstudiante) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        
        Estudiante buscado = em.find(Estudiante.class, idEstudiante);
        if(buscado!=null){
            return buscado;
        }
        else{
            throw new NoItemFoundException();
        }
    }
    
    //consultar analista por idUsuario
    public Analista consultarAnalista(int idUsuario) throws NoItemFoundException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        EntityManager pm = emf.createEntityManager();
        
        Usuario usuario = em.find(Usuario.class, idUsuario);
        try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Analista p where p.idUsuario = :id");
                q.setParameter("id", usuario);
                List<Analista> lista = q.getResultList();
                tx.commit();
                if(lista.size()>0){
                    return lista.get(0);
                }
                else{
                    throw new NoItemFoundException();
                }
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
    }
    
    //retorna una lista de materias
    public List<Materia> consultarMaterias(){
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Materia p");
                List<Materia> lista = q.getResultList();
                tx.commit();
                return lista;
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
        
    } 
    
    //consulta si un estudiante tiene cierta materia registrada
    public Registro consultarRegistroEstudianteMateria(int idEstudiante, int codigoMateria) throws NoItemFoundException{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();  
            
            Estudiante estudiante;
            Materia materia;
            estudiante = em.find(Estudiante.class, idEstudiante);
            materia = em.find(Materia.class, codigoMateria);
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Registro p where p.idEstudiante = :estudiante AND p.idMateria = :materia");
                q.setParameter("estudiante", estudiante);
                q.setParameter("materia", materia);
                List<Registro> lista = q.getResultList();
                tx.commit();
                if(lista.size()==1)
                {
                    return lista.get(0);
                }
                else{
                    throw new NoItemFoundException();
                }
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
    }
    
    public List<Registro> consultarRegistrosActivosInactivos(int idEstudiante, boolean activo) throws NoItemFoundException{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();  
            
            Estudiante estudiante;
            estudiante = em.find(Estudiante.class, idEstudiante);
            
            if(estudiante!=null){
                try
                {
                    tx.begin();

                    Query q = pm.createQuery("select p from Registro p where p.idEstudiante = :estudiante AND p.activo = :estado");
                    q.setParameter("estudiante", estudiante);
                    q.setParameter("estado", activo);
                    List<Registro> lista = q.getResultList();
                    tx.commit();

                    return lista;
                }
                finally
                {
                    if (tx.isActive())
                    {
                        tx.rollback();
                    }

                    em.close();
                }
            }
            else{
                throw new NoItemFoundException();
            }
    }
    
    //crea un registro
    public Registro crearRegistro(int idEstudiante, int codigoMateria) throws PosibleDuplicationException, NoItemFoundException{
        try{
            Registro repetido = this.consultarRegistroEstudianteMateria(idEstudiante, codigoMateria);
            throw new PosibleDuplicationException();
        }
        catch(NoItemFoundException uy){
               
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            try{
                Estudiante estudiante;
                Materia materia;
                estudiante = em.find(Estudiante.class, idEstudiante);
                materia = em.find(Materia.class, codigoMateria);
                Registro registro = new Registro();

                if(estudiante!=null || materia!=null )
                {
                    registro.setActivo(true);
                    registro.setIdEstudiante(estudiante);  
                    registro.setIdMateria(materia);
                    registro.setVecesDevuelta(0);

                }

                tx.begin();
                em.persist(registro);
                tx.commit();

                Registro nuevo = this.consultarRegistroEstudianteMateria(idEstudiante, codigoMateria);
                return nuevo;
            }
            catch(NoItemFoundException ey){
                throw new NoItemFoundException();   
            }
            finally{
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
        } 
        
    }
    
    //retorna lista de todos los talleres
    public List<Taller> listaTalleres(){
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Taller p");
                List<Taller> lista = q.getResultList();
                tx.commit();
                return lista;
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
        
    } 
    
    
    //Consultar talleres
    public Taller consultarTaller(int idTaller) throws NoItemFoundException
    {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();  
            
            try
            {
                Taller taller;
                tx.begin();
                Query q = pm.createQuery("select p from Taller p where p.idTaller = :taller");
                q.setParameter("taller", idTaller);
                List<Taller> lista = q.getResultList();
                tx.commit();
                if(lista.size()==1){
                    taller = lista.get(0);
                    return taller;
                }
                else{
                    throw new NoItemFoundException();
                }
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
    }
    
    //Retorna todos los usuarios
    public List<Usuario> listaUsuarios() {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Usuario p ORDER BY p.apellidos");
                List<Usuario> lista = q.getResultList();
                tx.commit();
                return lista;
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
            
    }
    
    //verificar login existente
    public boolean loginExiste(String login)
    {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();  
            
            try
            {
                tx.begin();
                Query q = pm.createQuery("select p from Usuario p where p.login = :log");
                q.setParameter("log", login);
                List<Usuario> usuario = q.getResultList();
                tx.commit();
                if(usuario.size()==1){
                    return false;
                }
                else{
                    return true;
                }
                
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
    }
    
    //Crear un usuario
    public Usuario crearUsuario(String nombre, String apellido, String login, String clave)throws PosibleDuplicationException, NoItemFoundException{
                       
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            
            if(this.loginExiste(login)){
                try
                {
                    tx.begin();

                    Usuario usuario = new Usuario();
                    usuario.setNombres(nombre);
                    usuario.setApellidos(apellido);
                    usuario.setLogin(login);
                    usuario.setClave(clave);

                    em.persist(usuario);

                    tx.commit();

                    List<Usuario> nuevo = this.consultarUsuario(usuario.getLogin(), usuario.getClave());

                    return nuevo.get(0);
                }
                finally
                {
                    if (tx.isActive())
                    {
                        tx.rollback();
                    }

                    em.close();
                }
            }
            else{
                throw new PosibleDuplicationException();
            }
            
        }
    
    //Eliminar un usuario
    public void eliminarUsuario(int id)throws NoItemFoundException{
                       
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
         
            try
            {
                Usuario usuario = em.find(Usuario.class, id);
                
                if(usuario!=null){
                    tx.begin();
                    em.remove(usuario);
                    tx.commit();
                }
                else{
                    throw new NoItemFoundException();
                }

                
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
        }
    
     //Actualizar un usuario
    public Usuario actualizarUsuario(int idUsuario, String nombre, String apellido, String login, String clave) throws NoItemFoundException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();          
            try
            {
                Usuario usuario = em.find(Usuario.class, idUsuario);
                if(usuario!=null){
                    tx.begin();
                
                    usuario.setNombres(nombre);
                    usuario.setApellidos(apellido);
                    usuario.setLogin(login);
                    usuario.setClave(clave);

                    tx.commit();

                    return usuario;
                }
                else{
                    throw new NoItemFoundException();
                }
                
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
        }
    
    //retorna analista de cierta materia
    public Analista analistaDeMateria(int idMateria) throws NoItemFoundException{
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();       
        
        Materia materia = em.find(Materia.class, idMateria);

        if(materia!=null){
            return materia.getIdAnalista();
        }
        else{
            throw new NoItemFoundException();
        }
   
       
        
    }
    
    //retorna una lista de exámenes solicitados
    public List<ExamenSolicitado> consultarExamenSolicitadoEspecífico(int idEstudiante, int idAnalista, int idRegistro, int idExamen) throws NoItemFoundException {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
            Analista analista = em.find(Analista.class, idAnalista);
            Registro registro = em.find(Registro.class, idRegistro);
            Examen examen = em.find(Examen.class, idExamen);
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from ExamenSolicitado p where p.idEstudiante = :estu AND p.idAnalista = :analis AND p.idRegistro = :reg AND p.idExamen = :exam");

                q.setParameter("estu", estudiante);
                q.setParameter("analis", analista);
                q.setParameter("reg", registro);
                q.setParameter("exam", examen);
                List<ExamenSolicitado> lista = q.getResultList();
                tx.commit();
                if(lista.size()>0){
                    return lista;
                }
                else{
                    throw new NoItemFoundException();
                }
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
            
    }
    
     //Crear un examen solicitado
    public ExamenSolicitado crearExamenSolicitado(Date fecha, int idEstudiante, int idAnalista, int idRegistro, int idExamen) throws NoItemFoundException, PosibleDuplicationException{
                       
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            
            Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
            Analista analista = em.find(Analista.class, idAnalista);
            Registro registro = em.find(Registro.class, idRegistro);
            Examen examen = em.find(Examen.class, idExamen);
            Estados estado = em.find(Estados.class, 3);
            
            System.out.println(estudiante.getIdEstudiante());
            
            try{
                List<ExamenSolicitado> repetido = this.consultarExamenSolicitadoEspecífico(idEstudiante, idAnalista, idRegistro, idExamen);
                throw new PosibleDuplicationException();
            }
            catch(NoItemFoundException ey){
                try
                {
                    ExamenSolicitado examenSolicitado = new ExamenSolicitado();
                    examenSolicitado.setFecha(fecha);
                    examenSolicitado.setIdAnalista(analista);
                    examenSolicitado.setIdEstado(estado);
                    examenSolicitado.setIdEstudiante(estudiante);
                    examenSolicitado.setIdExamen(examen);
                    examenSolicitado.setIdRegistro(registro);
                    examenSolicitado.setNota(0);

                    tx.begin();
                    em.persist(examenSolicitado);
                    tx.commit();

                    List<ExamenSolicitado> nuevo = this.consultarExamenSolicitadoEspecífico(examenSolicitado.getIdEstudiante().getIdEstudiante(), examenSolicitado.getIdAnalista().getIdAnalista(), examenSolicitado.getIdRegistro().getIdRegistro(), examenSolicitado.getIdExamen().getIdExamen());

                    if(nuevo.size()==1){
                        return nuevo.get(0);
                    }
                    else{
                        return null;
                    }

                }
                finally
                {
                    if (tx.isActive())
                    {
                        tx.rollback();
                    }

                    em.close();
                }
            }
        }
    
    //consultar estados
    public List<Estados> consultarEstados(){
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Estados p");
                List<Estados> lista = q.getResultList();
                tx.commit();
                return lista;
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
        
    } 
   
    //Actualizar estado de registro
    public Registro desactivarRegistro(int idRegistro) throws NoItemFoundException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();           
            try
            {
                Registro registro = em.find(Registro.class, idRegistro);
                if(registro!=null){
                    tx.begin();
                
                    registro.setActivo(false);

                    tx.commit();

                    return registro;
                }
                else{
                    throw new NoItemFoundException();
                }
                
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
        }
    
    //cambiar estado de examen solicitado
    public ExamenSolicitado actualizarEstadoDeExamenSolicitado(int idExamenSolicitado, int idEstado) throws NoItemFoundException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();          
            try
            {
                ExamenSolicitado examen = em.find(ExamenSolicitado.class, idExamenSolicitado);
                Estados estado = em.find(Estados.class, idEstado);
                if(examen!=null){
                    tx.begin();
                
                    examen.setIdEstado(estado);

                    tx.commit();

                    return examen;
                }
                else{
                    throw new NoItemFoundException();
                }
                
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
        }
    
    public List<Usuario> buscarUsuario(String comodin) throws NoItemFoundException{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();  
            
                try
                {
                    tx.begin();

                    Query q = pm.createQuery("select p from Usuario p where UPPER(p.nombres) LIKE :nombre OR UPPER(p.apellidos) LIKE :nombre ORDER BY p.nombres");
                    q.setParameter("nombre", "%"+comodin+"%");
                    List<Usuario> lista = q.getResultList();
                    tx.commit();
                    if(lista.size()>0){
                        return lista;
                    }     
                    else{
                        throw new NoItemFoundException();
                    }
                }
                finally
                {
                    if (tx.isActive())
                    {
                        tx.rollback();
                    }

                    em.close();
                }
    }
    
    //retorna las materias de cierto grado
    public Collection materiasDeGrado(int idGrado)throws NoItemFoundException{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            
            Grado grado = em.find(Grado.class, idGrado);
            
            return grado.getMateriaCollection();
        
    }
    
    //retorna el siguiente examen a presentar de cierta materia para cierto usuario
    public Examen getSiguienteExamenDeMateria(int codigoMateria, int idEstudiante) throws NoItemFoundException, UltimoTemaException, NoPresentableException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();   
            
            Registro registro = this.consultarRegistroEstudianteMateria(idEstudiante, codigoMateria);
            List<ExamenSolicitado> examenes = (List<ExamenSolicitado>) registro.getExamenSolicitadoCollection();
            
            int tamaño = examenes.size()-1;
           
            if(tamaño<0) throw new NoItemFoundException();
            
            int etat = examenes.get(tamaño).getIdEstado().getIdEstado();
            
            Examen actual = examenes.get(tamaño).getIdExamen();
            int temaActual = actual.getIdExamen();
            
            if(etat==1)
            {
                if(ultimoTema(codigoMateria, temaActual)){
                    throw new UltimoTemaException();
                }
                else{
                    int nuevo = temaActual + 1;
                    Examen siguienteTema = em.find(Examen.class, nuevo);
                    return siguienteTema;
                }
            }
            else{
                switch(etat)
                {
                    case 2:
                        return actual;
                    default:
                        throw new NoPresentableException();
                }
            }
         
        }

    //retorna si es el ultimo tema de una materia
    public boolean ultimoTema(int codigoMateria, int temaActual){
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();   
            
            int next = temaActual + 1;
            Examen siguiente = em.find(Examen.class, next);
            
            if(siguiente.getIdMateria().getIdMateria()==codigoMateria)
            {
                return false;
            }
            else{
                return true;
            }
         
        }
    
    //retorna una lista de grados
    public List<Grado> consultarGrados(){
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from Grado p");
                List<Grado> lista = q.getResultList();
                tx.commit();
                return lista;
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }
        
    }
    
    //retorna una lista de grados
    public List<ExamenSolicitado> examenesSolicitadosPorTema (int idEstudiante, int idExamen)throws NoItemFoundException{
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            EntityManager pm = emf.createEntityManager();            
            
            Estudiante etudiant = em.find(Estudiante.class, idEstudiante);
            Examen control = em.find(Examen.class, idExamen);
            
            try
            {
                tx.begin();

                Query q = pm.createQuery("select p from ExamenSolicitado p where p.idExamen = :exam AND p.idEstudiante =:student");
                q.setParameter("exam", control);
                q.setParameter("student", etudiant);
                List<ExamenSolicitado> lista = q.getResultList();
                tx.commit();
                
                if(lista.size()>0){
                    return lista;
                }
                else{
                    throw new NoItemFoundException();
                }
                
            }
            finally
            {
                if (tx.isActive())
                {
                    tx.rollback();
                }

                em.close();
            }   
        
    }
    
    //retorna las materias de un analista
    public List<Materia> materiasDeAnalista(int idAnalista) throws NoItemFoundException{
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();         
        
        Analista analista = em.find(Analista.class, idAnalista);
        

        if(analista!=null){
            return (List<Materia>) analista.getMateriaCollection();
        }
        else{
            throw new NoItemFoundException();
        }
            
    }
    
    //retorna una lista de exámenes solicitados
    /*
    public List<ExamenSolicitado> consultarListaExamenesPorCalificarDeAnalista(int idAnalista) throws NoItemFoundException {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("DataAccessLayerPU");
            EntityManager em = emf.createEntityManager();        
            
            Analista analista = em.find(Analista.class, idAnalista);
            
            if(analista==null) throw new NoItemFoundException();
            
            List<ExamenSolicitado> examenes = (List<ExamenSolicitado>) analista.getExamenSolicitadoCollection();
            
            for(int i=0;i<examenes.size();i++){
                if(examenes.get(i).getIdEstado().getIdEstado()!=4){
                    examenes.remove(i);
                }
            }
            
            if(examenes==null) throw new NoItemFoundException();
            
            return examenes;
            
    }
    */
    
}
