/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.control.login;

import com.liceoval.businesslayer.entities.Usuario;
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
public class ControladorDeLogInTest {

    public ControladorDeLogInTest() {
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
     * Test of doLogin method, of class ControladorDeLogIn.
     */
    @Test
    public void doLogin() {
        System.out.println("doLogin");
        String userName = "Test";
        String password = "Testing";
        int role = 1;
        ControladorDeLogIn instance = new ControladorDeLogIn();
        Usuario expResult = null;
        Usuario result = instance.doLogin(userName, password, role);
        assertEquals(expResult, result);
        
    }

}