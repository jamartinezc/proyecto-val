/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.registro;

import com.liceoval.businesslayer.entities.Registro;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Administrador
 */
public class ControladoraDeRegistroTest {

    public ControladoraDeRegistroTest() {
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
     * Test of agregarExamen method, of class ControladoraDeRegistro.
     */
    @Test
    public void agregarExamen() throws Exception {
        System.out.println("agregarExamen");
        int idExamen = 3;
        int idEstudiante = 290;
        int idMateria = 2;
        ControladoraDeRegistro.agregarExamen(idExamen, idEstudiante, idMateria);
        
    }

    /**
     * Test of getRegistro method, of class ControladoraDeRegistro.
     */
    @Test
    public void getRegistro() throws Exception {
        System.out.println("getRegistro");
        int idEstudiante = 290;
        int idMateria = 2;
        int expResult = 4;
        Registro result = ControladoraDeRegistro.getRegistro(idEstudiante, idMateria);
        assertEquals(expResult, result.getIdRegistro());
        
    }

    /**
     * Test of getRegistros method, of class ControladoraDeRegistro.
     */
    @Test
    public void getRegistros() throws Exception {
        System.out.println("getRegistros");
        int idEstudiante = 290;
        boolean activo = true;
        int expResult = 3;
        List<Registro> result = ControladoraDeRegistro.getRegistros(idEstudiante, activo);
        assertEquals(expResult, result.size());
        
    }


    /**
     * Test of programarExamen method, of class ControladoraDeRegistro.
     */
    @Test
    public void programarExamen() {
        try {
            System.out.println("programarExamen");
            SimpleDateFormat a = new SimpleDateFormat();
            a.applyPattern("dd-MM-aaaa");
            Date expResult = a.parse("16-06-2008");
            Date result = ControladoraDeRegistro.programarExamen();
            assertEquals(expResult, result);
        } catch (ParseException ex) {
            Logger.getLogger(ControladoraDeRegistroTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

        

}