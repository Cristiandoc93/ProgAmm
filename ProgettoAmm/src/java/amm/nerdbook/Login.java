/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.Classi.AmiciziaFactory;
import amm.nerdbook.Classi.GruppiRegFactory;
import amm.nerdbook.Classi.PartecipaFactory;
import amm.nerdbook.Classi.PostFactory;
import amm.nerdbook.Classi.PostGruppoFactory;
import amm.nerdbook.Classi.UtentiReg;
import amm.nerdbook.Classi.UtentiRegFactory;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cristian
 */

@WebServlet (loadOnStartup = 0)

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
    
    
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";
    
    @Override
    public void init(){
       String dbConnection = "jdbc:derby://localhost:1527/ammdb";
        //String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
       try {
           Class.forName(JDBC_DRIVER);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       }
       UtentiRegFactory.getInstance().setConnectionString(dbConnection);
       PostFactory.getInstance().setConnectionString(dbConnection);
       GruppiRegFactory.getInstance().setConnectionString(dbConnection);
       PartecipaFactory.getInstance().setConnectionString(dbConnection);
       PostGruppoFactory.getInstance().setConnectionString(dbConnection);
       AmiciziaFactory.getInstance().setConnectionString(dbConnection);
    }


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
            
            UtentiReg u = UtentiRegFactory.getInstance().getUtente(username, password);
           
            //ricerca dati dalle Factory
            
            if(u != null)
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
                           return;
                        }   
                      else{ //caso attributi mancanti
                            
                            session.setAttribute("bacError", true);
                            request.getRequestDispatcher("profilo.html").forward(request, response);
                            return;
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
