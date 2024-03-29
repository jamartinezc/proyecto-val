/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.presentationlayer.control.timetriggered;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liceoval.presentationlayer.control.timetriggered.threads.*;
import Errores.NoItemFoundException;

/**
 *
 * @author Jorge
 */
public class CronoTrigger extends HttpServlet {
   
    private TriggerMensualThread triggerMensual;
    private TriggerSemanalThread triggerSemanal;
    
    public CronoTrigger(){
        
        super();
        
        triggerMensual = new TriggerMensualThread();
        triggerMensual.start();
        
        triggerSemanal = new TriggerSemanalThread();
        triggerSemanal.start();
        
    }
    
    @Override
    public void destroy(){
        triggerMensual.interrupt();
        triggerMensual.setEjecutar(false);
        triggerMensual.interrupt();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CronoTrigger</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CronoTrigger at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         // Simplemente llamar a doPost y pasarle el request y el response.
            doPost(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public void service(ServletRequest req, ServletResponse res){
        
        
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
