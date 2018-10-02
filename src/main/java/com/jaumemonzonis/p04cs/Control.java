/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaumemonzonis.p04cs;

import com.google.gson.Gson;
import com.jaumemonzonis.p04cs.java.Errores;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jaume monzonis
 */
public class Control extends HttpServlet {
 
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException {
        response.setContentType("application/json");
       
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Double num1 = null;
        Double num2 = null;

        String op = "multiplicar";
        Double res = 0.0;

        int random = (int)Math.round(Math.random() * 10 + 1);
        
          // validar numerico
        try {
           
           num1 = Double.parseDouble(request.getParameter("num1"));
           num2 = Double.parseDouble(request.getParameter("num2"));
       
            } catch (NumberFormatException nfex) {
            response.setStatus(500);
            Errores error = new Errores();
            error.setMensaje("Por favor, ingrese dos numeros en el formulario");
            error.setNum2(request.getParameter("num1"));
            error.setNum1(request.getParameter("num2"));
            response.getWriter().append(gson.toJson(error));

        }  if (!(num1 >= 0 && num1 <= 100) || !(num2 >= 0 && num2 <= 100)) {
            response.setStatus(500);
            Errores error = new Errores();
            error.setMensaje("Numeros entre 1 y 100");
            error.setNum2(request.getParameter("num1"));
            error.setNum1(request.getParameter("num2"));
            response.getWriter().append(gson.toJson(error));
            
        } else {  
         
            try {  
            
            if (op.equals("sumar") ){
                res=num1+num2;
            } else if (op.equals("restar"))  {
                res=num1-num2;
            } else  if (op.equals("multiplicar"))  {
                res=num1*num2;
            } else  if (op.equals("dividir"))  {
                if (num2 == 0) {
                 response.setStatus(500);
            Errores error = new Errores();
            error.setMensaje("El divisor no puede ser negativo");
            error.setNum2(request.getParameter("num1"));
            error.setNum1(request.getParameter("num2"));
            response.getWriter().append(gson.toJson(error));
                        } else {
                            res = num1 / num2;
                         }
            }

                String strJson = gson.toJson(res);
                Thread.sleep((long)(random * 1000));
                //TimeUnit.SECONDS.sleep(random);
                out.print(strJson);
                
         } finally {
            out.close();
        }
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
