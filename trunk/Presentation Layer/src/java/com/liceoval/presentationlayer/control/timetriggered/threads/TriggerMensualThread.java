/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.presentationlayer.control.timetriggered.threads;

import Errores.NoItemFoundException;
import com.liceoval.businesslayer.control.GeneradoraInformeExcelencia;
import com.liceoval.businesslayer.control.GeneradoraInformeMensual;
import com.liceoval.businesslayer.control.exceptions.ErrorEnviandoInformeException;
import java.util.Calendar;

/**
 *
 * @author Jorge
 */
public class TriggerMensualThread extends Thread{
    
    private boolean ejecutar;
    
    @Override
    public void run() throws ErrorEnviandoInformeException{
        //ciclo que envia informes mensuales por siempre (cada mes)
        ejecutar=true;
        while(ejecutar){
            try {
                //System.out.println("mensual: "+new Date());
                //comprovar si el mes ya se pasó 
                String mesDeEnvioString = DAO.DaoVariablesGlobales.consultarUno("MesSiguienteInforme").getValor();
                int mesDeDisparo = Integer.parseInt(mesDeEnvioString);
                Calendar fechaActual = Calendar.getInstance();
                if(fechaActual.get(Calendar.MONTH)>=mesDeDisparo){
                    crearInformeMensual(mesDeDisparo);
                    //crearInformeDeExcelenciaPorTaller();
                    //programar el envio de informes para el mes siguiente
                    fechaActual.add(Calendar.MONTH, 1);
                    DAO.DaoVariablesGlobales.actualizar("MesSiguienteInforme", String.valueOf(fechaActual.get(Calendar.MONTH)));
                    System.out.println("actualiza la variable de mes");
                }
                
                //esperar un dia
                Thread.sleep(24*60*60*1000);
                //Thread.sleep(30000);//esperar 1/2 minuto, para pruebas

            } catch (NoItemFoundException ex) {
                ex.printStackTrace();
                System.out.println("no se encontro alguna variable");
                throw new ErrorEnviandoInformeException("Error enviando correos del informe mensual", ex);
            } catch (InterruptedException ex) {
                System.out.println("se ha interrumpido el thread mensual");
            }
        }
    }
    
    private void crearInformeMensual(int mesDeDisparo) throws ErrorEnviandoInformeException{
        System.out.println("trata de crear informes");
        GeneradoraInformeMensual.generarInformesMensuales();
        System.out.println("termina de crear informes");
    }
    
    private void crearInformeDeExcelenciaPorTaller(){
        
        System.out.println("trata de crear informe Excelencia");
        GeneradoraInformeExcelencia.generarInforme();
        System.out.println("termina de crear informe Excelencia");
    }

    public boolean isEjecutar() {
        return ejecutar;
    }

    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }
}
