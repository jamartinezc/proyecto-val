/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import com.liceoval.businesslayer.entities.Usuario;
import com.liceoval.businesslayer.entities.wrappers.UsuarioWrapper;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrador
 */
public class AdministradoraDeUsuariosTest1 {

    public AdministradoraDeUsuariosTest1() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of solicitarListaDeUsuarios method, of class AdministradoraDeUsuarios.
     */
    @Test
    public void solicitarListaDeUsuarios() {
        System.out.println("solicitarListaDeUsuarios");
        List<Usuario> expResult = null;
        List<Usuario> result = AdministradoraDeUsuarios.solicitarListaDeUsuarios();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of crearUsuario method, of class AdministradoraDeUsuarios.
     */
    @Test
    public void crearUsuario() throws Exception {
        System.out.println("crearUsuario");
        String nombres = "Senpai";
        String apellidos = "Prueba";
        String login = "sp";
        String clave = "123";
        boolean expResult = true;
        boolean result = AdministradoraDeUsuarios.crearUsuario(nombres, apellidos, login, clave);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of modificarUsuario method, of class AdministradoraDeUsuarios.
     */
    @Test
    public void modificarUsuario() throws Exception {
        System.out.println("modificarUsuario");
        String q="PassTest";
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2);
        usuario.setNombres("Tester");
        usuario.setApellidos("Test");
        usuario.setLogin("Testing");
        usuario.setPassword(q.toCharArray());
        boolean expResult = true;
        boolean result = AdministradoraDeUsuarios.modificarUsuario(usuario);
        assertEquals(expResult, result);
        
    }


    /**
     * Test of cargarUsuario method, of class AdministradoraDeUsuarios.
     */
    @Test
    public void cargarUsuario() throws Exception {
        System.out.println("cargarUsuario");
        int idUsuario = 26;
        String expResult="Mario";
        UsuarioWrapper result = AdministradoraDeUsuarios.cargarUsuario(idUsuario);
        assertEquals(expResult, result.getUsuario().getNombres());
        
    }

    /**
     * Test of confirmarEstudianteTallerTutor method, of class AdministradoraDeUsuarios.
     */
    @Test
    public void confirmarEstudianteTallerTutor() {
        System.out.println("confirmarEstudianteTallerTutor");
        int idEstudiante = 290;
        int idTutor = 2;
        boolean expResult = true;
        boolean result = AdministradoraDeUsuarios.confirmarEstudianteTallerTutor(idEstudiante, idTutor);
        assertEquals(expResult, result);
        
    }

}