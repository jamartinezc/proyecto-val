/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.presentationlayer.control.timetriggered.threads;

import java.util.Date;

/**
 *
 * @author Jorge
 */
public class TriggerSemanalThread extends Thread{
        public void run() {
                        
        try {
            System.out.println("Semanal: "+new Date());
            //comprovar si la semana ya terminó
            
            
            Thread.sleep(30000);
        } catch (InterruptedException ex) {
                System.out.println("se ha interrumpido el thread semanal");
        }
    }
        
    private void crearInformeSemanal(){
        
    }
}
