/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.presentationlayer.control.timetriggered.threads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author Jorge
 */
public class TriggerSemanalThread extends Thread{
        public void run() {
                        
        System.out.println(new Date());
        try {
            Thread.sleep(10000);
            System.out.println(new Date());
            //comprovar si el mes ya se pasó
            
        } catch (InterruptedException ex) {
            System.out.println("se totió");
        }
    }
        
    private void crearInformeSemanal(){
        
    }
}
