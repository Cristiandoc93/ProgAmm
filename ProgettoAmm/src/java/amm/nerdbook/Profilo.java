/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;


       
import amm.nerdbook.Classi.AmiciziaFactory;
import amm.nerdbook.Classi.Amm;
import amm.nerdbook.Classi.AmmFactory;
import amm.nerdbook.Classi.Gruppi;
import amm.nerdbook.Classi.GruppiRegFactory;
import amm.nerdbook.Classi.PartecipaFactory;
import amm.nerdbook.Classi.Post;
import amm.nerdbook.Classi.PostFactory;
import amm.nerdbook.Classi.PostGruppoFactory;
import amm.nerdbook.Classi.UtentiReg;
import amm.nerdbook.Classi.UtentiRegFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * @throws java.sql.SQLException
     */ 
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        
        
          if(session!=null && 
           session.getAttribute("loggedOn")!=null &&
           session.getAttribute("loggedOn").equals(true)){
             
           
        
            Integer id = (Integer)session.getAttribute("id");
            
            
            
            int user = (int)session.getAttribute("id");
            Amm amm = AmmFactory.getInstance().getUtenteById(user);
            request.setAttribute("amm", amm);
      
        
        //inserimento dati profilo
        
       if(request.getParameter("Conf") != null){
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String img = request.getParameter("img");
        String present = request.getParameter("present");
        String bday = request.getParameter("bday");
         String psw = request.getParameter("psw");
        
        
      
            request.setAttribute("nome", nome);
            session.setAttribute("cognome", cognome);
            session.setAttribute("img", img);
            session.setAttribute("present", present);
            session.setAttribute("bday",bday);
            session.setAttribute("psw", psw);
         
            UtentiReg modute = new UtentiReg();
            
            
            if(!nome.isEmpty() ){
            modute.setNome(nome);
            UtentiRegFactory.getInstance().updateNome(nome,user);
            request.setAttribute("modifica" , "ok");
            }
            if(!cognome.isEmpty()){
            modute.setCognome(cognome);
            UtentiRegFactory.getInstance().updateCognome(cognome,user);
            request.setAttribute("modifica" , "ok");
            }
            if(!img.isEmpty()){
            modute.setUrlfotoprofilo(img);
            UtentiRegFactory.getInstance().updateImg(img,user);
            request.setAttribute("modifica" , "ok");
            }
             
            if(!present.isEmpty()){
            modute.setPresentazione(present);
            UtentiRegFactory.getInstance().updatePresentazione(present,user);
            request.setAttribute("modifica" , "ok");
            }
            
            if(!bday.isEmpty()){
            modute.setDatanascita(bday);
            UtentiRegFactory.getInstance().updateBday(bday,user);
            request.setAttribute("modifica" , "ok");
            }
            
            if(!psw.isEmpty()){
            modute.setPassword(psw);
            UtentiRegFactory.getInstance().updatePass(psw,user);
            request.setAttribute("modifica" , "ok");
            }
            
             request.getRequestDispatcher("profiloPage.jsp").forward(request, response);
            }
        //caso cancellazione utente
        if(request.getParameter("delete") != null){
            
          //  UtentiReg utente = UtentiRegFactory.getInstance().getUtenteById(user);
          //  request.setAttribute("utente", utente);
            
            request.setAttribute("cancella" , "ok");
       
            AmiciziaFactory.getInstance().cancellaAmicizia(user);
            //AmiciziaFactory.getInstance().cancellaseguace(user);
            int i = GruppiRegFactory.getInstance().getammById(user);
            if(i != 0){
                    int u = GruppiRegFactory.getInstance().getidByamm(user);
                
                PostGruppoFactory.getInstance().cancellaPostGruppo(u);
                PartecipaFactory.getInstance().cancellaRelazione(u);
                GruppiRegFactory.getInstance().cancellaGruppiByAmm(user);
            }
       
            PostGruppoFactory.getInstance().cancellaPostByutente(user);
            PartecipaFactory.getInstance().cancellaPartecipante(user);
            PostFactory.getInstance().CancPostandUtente(user,user,user);
           
            request.setAttribute("complete" , "ok");
            session.invalidate();
            
      
            request.getRequestDispatcher("login.html").forward(request, response);
          }
        UtentiReg utentep = UtentiRegFactory.getInstance().getUtenteById(id);
            request.setAttribute("utentep", utentep);
             UtentiReg utente = UtentiRegFactory.getInstance().getUtenteById(id);
            request.setAttribute("utente", utente);
 
            ArrayList<UtentiReg> utenti = UtentiRegFactory.getInstance().getUtentiList();
            request.setAttribute("utenti", utenti);
        
            
            ArrayList<Gruppi> gruppi = GruppiRegFactory.getInstance().getGruppiList();
            request.setAttribute("gruppi", gruppi);
        
        request.getRequestDispatcher("profiloPage.jsp").forward(request, response);
  
    }else //caso errore login
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Profilo.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(Profilo.class.getName()).log(Level.SEVERE, null, ex);
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
