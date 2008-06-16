/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control;

import com.liceoval.businesslayer.entities.wrappers.ExamenSolicitadoWrapper;
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
public class AdministradoraListaDeExamenesAsignadosTest {

    public AdministradoraListaDeExamenesAsignadosTest() {
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
     * Test of generarListaDeExamenesACalificar method, of class AdministradoraListaDeExamenesAsignados.
     */
    @Test
    public void generarListaDeExamenesACalificar() throws Exception {
        System.out.println("generarListaDeExamenesACalificar");
        Integer idAnalista = 2;
        List<ExamenSolicitadoWrapper> expResult = null;
        List<ExamenSolicitadoWrapper> result = AdministradoraListaDeExamenesAsignados.generarListaDeExamenesACalificar(idAnalista);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of guardarNota method, of class AdministradoraListaDeExamenesAsignados.
     */
    @Test
    public void guardarNota() throws Exception {
        System.out.println("guardarNota");
        int idExamenSolicitado = 5;
        float nota = 4.9F;
        AdministradoraListaDeExamenesAsignados.guardarNota(idExamenSolicitado, nota);
        
        
    }

    /**
     * Test of asignarNoPresentado method, of class AdministradoraListaDeExamenesAsignados.
     */
    @Test
    public void asignarNoPresentado() throws Exception {
        System.out.println("asignarNoPresentado");
        int idExamenSolicitado = 9;
        AdministradoraListaDeExamenesAsignados.asignarNoPresentado(idExamenSolicitado);
       
    }

}