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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cristian
 */
public class Profilo extends HttpServlet {

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
        
         List<UtentiReg> utenti = UtentiRegFactory.getInstance().getUtentiList();
         request.setAttribute("utenti", utenti);
        
        //inserimento dati profilo
        
        if(request.getParameter("Conf") != null){
            session.setAttribute("name", request.getParameter("nome"));
            session.setAttribute("sirname", request.getParameter("cognome"));
            session.setAttribute("image", request.getParameter("img"));
            session.setAttribute("presentazione", request.getParameter("present"));
            session.setAttribute("birthday", request.getParameter("bday"));
            session.setAttribute("password", request.getParameter("psw"));
            session.setAttribute("confpsw", request.getParameter("cpsw"));
            
            request.setAttribute("modifica" , "ok");
            request.getRequestDispatcher("profiloPage.jsp").forward(request, response);
            
            
        }
        
        
        // caso utente loggato
        
        if (session.getAttribute("loggedOn") != null &&
            session.getAttribute("loggedOn").equals(true)) {
            
            
            request.getRequestDispatcher("profiloPage.jsp").forward(request, response);
            
            
            
        }
        else //caso errore login
        {
        
            request.getRequestDispatcher("profiloPage.jsp").forward(request, response);
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
