/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.rcp;

import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.Grado;
import com.liceoval.businesslayer.entities.Materia;
import com.liceoval.businesslayer.entities.Taller;
import com.liceoval.businesslayer.entities.wrappers.TallerWrapper;
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
public class RCPRegistrosTest {

    public RCPRegistrosTest() {
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
     * Test of getGrados method, of class RCPRegistros.
     */
    @Test
    public void getGrados() {
        System.out.println("getGrados");
        int expResult = 2;
        List<Grado> result = RCPRegistros.getGrados();
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getTalleres method, of class RCPRegistros.
     */
    @Test
    public void getTalleres() {
        System.out.println("getTalleres");
        int expResult = 3;
        List<Taller> result = RCPRegistros.getTalleres();
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getMaterias method, of class RCPRegistros.
     */
    @Test
    public void getMaterias() throws Exception {
        System.out.println("getMaterias");
        int idGrado = 11;
        int expResult = 11;
        List<Materia> result = RCPRegistros.getMaterias(idGrado);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getMateriasDeRegistrsoActivos method, of class RCPRegistros.
     */
    @Test
    public void getMateriasDeRegistrsoActivos() throws Exception {
        System.out.println("getMateriasDeRegistrsoActivos");
        int idEstudiante = 290;
        int expResult = 11;
        List<Materia> result = RCPRegistros.getMateriasDeRegistrosActivos(idEstudiante);
        assertEquals(expResult, result.size());
        
    }

    /**
     * Test of getEstudiante method, of class RCPRegistros.
     */
    @Test
    public void getEstudiante() throws Exception {
        System.out.println("getEstudiante");
        int idEstudiante = 290;
        int expResult = 11;
        Estudiante result = RCPRegistros.getEstudiante(idEstudiante);
        assertEquals(expResult, result.getGrado().getIdGrado());
        
    }

    /**
     * Test of getTalleresYEstudiantes method, of class RCPRegistros.
     */
    @Test
    public void getTalleresYEstudiantes() throws Exception {
        System.out.println("getTalleresYEstudiantes");
        int idTutor = 2;
        int expResult = 14;
        List<TallerWrapper> result = RCPRegistros.getTalleresYEstudiantes(idTutor);
        assertEquals(expResult, result.get(0).getEstudiantes().size());
        
    }

}