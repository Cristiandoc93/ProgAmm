/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.Classi.UtentiReg;
import amm.nerdbook.Classi.UtentiRegFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cristian
 */

public class Login extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        
     
       
        //caso utente loggato
        
        if(session.getAttribute("loggedOn")!=null &&
           session.getAttribute("loggedOn").equals(true)) {
            
        request.getRequestDispatcher("bacheca.html").forward(request, response);
        
        }
        
        // caso inserimento dati loggin
        if(request.getParameter("Submit") != null)
        { //inserimento dati
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            ArrayList<UtentiReg> listaUtenti = UtentiRegFactory.getInstance().getUtenteList();
           
            //ricerca dati dalle Factory
            for(UtentiReg u : listaUtenti)
            {
                if(u.getUsername().equals(username) && u.getPassword().equals(password))
                    {   
                      session.setAttribute("loggedOn", true);
                      session.setAttribute("id", u.getId());
                      session.setAttribute("nome", u.getNome());
                            request.setAttribute("utente", u);
                      //caso attributi presenti
                      if (u.getNome() != null && u.getCognome() != null && u.getUrlfotoprofilo() != null
                            && u.getPresentazione() != null)
                        {
                            
                            request.getRequestDispatcher("bacheca.html").forward(request, response);
                           
                        }   
                      else{ //caso attributi mancanti
                            
                            request.setAttribute("bacError", true);
                            request.getRequestDispatcher("profilo.html").forward(request, response);
                            
                      }
                    }
            }
            //caso utente non registrato 
            request.setAttribute("logError", true);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            
        }else{  
        //pagina di default
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
