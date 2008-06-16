/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import com.liceoval.businesslayer.entities.Estudiante;
import com.liceoval.businesslayer.entities.Examen;
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
public class AdministradoraSolicitudesExamenTest {

    public AdministradoraSolicitudesExamenTest() {
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
     * Test of SolicitarExamen method, of class AdministradoraSolicitudesExamen.
     */
    @Test
    public void SolicitarExamen() throws Exception {
        System.out.println("SolicitarExamen");
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo(290);
        com.liceoval.businesslayer.entities.Grado g = new com.liceoval.businesslayer.entities.Grado();
        g.setIdGrado(11);
        g.setNombre("Once");
        SimpleDateFormat a=new SimpleDateFormat();
        String q="2008-05-01";
        a.applyPattern("dd-MM-yyyy");
        estudiante.setFechaInicioGrado(a.parse(q));
        estudiante.setGrado(g);
        int codExamen = 2;
        int codigoMateria = 2;
        AdministradoraSolicitudesExamen.SolicitarExamen(estudiante, codExamen, codigoMateria);
        
    }

    /**
     * Test of getSiguienteExamen method, of class AdministradoraSolicitudesExamen.
     */
    @Test
    public void getSiguienteExamen() throws Exception {
        System.out.println("getSiguienteExamen");
        int idEstudiante = 290;
        int codigoMateria = 2;
        Examen expResult = null;
        Examen result = AdministradoraSolicitudesExamen.getSiguienteExamen(idEstudiante, codigoMateria);
        assertEquals(expResult, result);
        
    }

}