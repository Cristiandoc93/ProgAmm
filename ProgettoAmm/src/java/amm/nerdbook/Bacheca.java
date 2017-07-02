package amm.nerdbook;

import amm.nerdbook.Classi.AmiciziaFactory;
import amm.nerdbook.Classi.Amm;
import amm.nerdbook.Classi.AmmFactory;
import amm.nerdbook.Classi.Gruppi;
import amm.nerdbook.Classi.GruppiRegFactory;
import amm.nerdbook.Classi.PartecipaFactory;
import amm.nerdbook.Classi.UtentiReg;
import amm.nerdbook.Classi.UtentiRegFactory;
import amm.nerdbook.Classi.Post;
import amm.nerdbook.Classi.PostFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Bacheca extends HttpServlet {

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
         if(session.getAttribute("bacError") != "true"){
        
         if(session!=null && 
           session.getAttribute("loggedOn")!=null &&
           session.getAttribute("loggedOn").equals(true)){
           
            String user = request.getParameter("user");
            
        
            
            
            int userid = (int)session.getAttribute("id");
            
            int userID = (int)session.getAttribute("id");
            
             
            if(user != null){
                
                userID = Integer.parseInt(user);
                
            } else {
                
                Integer id = (Integer)session.getAttribute("id");
                
                userID = id;
                
                }
            
            //caso stessa bacheca
           if(userid == userID){
               request.setAttribute("same", true);
           }else{
               request.setAttribute("same", false);
           }
       
          UtentiReg utentep = UtentiRegFactory.getInstance().getUtenteById(userid);
          UtentiReg utente = UtentiRegFactory.getInstance().getUtenteById(userID);
         
          Amm amm = AmmFactory.getInstance().getUtenteById(userid);
             session.setAttribute("amm", amm);
            
        
      
        if(utente != null && session.getAttribute("bacError") != "true") {
            request.setAttribute("sendPostok", null);
            request.setAttribute("utente", utente);
            request.setAttribute("utentep", utentep);
            
                  
            ArrayList<Post> posts = PostFactory.getInstance().getPostList(utente);
            request.setAttribute("posts", posts); 
         
            
              
              
            ArrayList<UtentiReg> utenti = UtentiRegFactory.getInstance().getUtentiList();
            request.setAttribute("utenti", utenti);
        
            
            ArrayList<Gruppi> gruppi = GruppiRegFactory.getInstance().getGruppiList();
            request.setAttribute("gruppi", gruppi);
            
            
            
            //confronto amicizia
            int friend = AmiciziaFactory.getInstance().getIdUtenteAndSeguace((int)session.getAttribute("id"), userID);
            if( friend != -1){
                request.setAttribute("amiciziaok", true);
            }else{
                request.setAttribute("amiciziaok", false);
            }

            
            //caso aggiungi amico
            
            if(request.getParameter("addfr") != null)
            {
            
            int seguace = userID;
            
            AmiciziaFactory.getInstance().addamico(userid, seguace);
            request.setAttribute("addfr",null);
            request.setAttribute("amiciziaok", true);
            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            return;
            
            }
      
            //caso cancellazione post admin
              if(request.getParameter("cancposts") != null){
              String ute = request.getParameter("ute");
              String contentx = request.getParameter("contentx");
              
              int i = PostFactory.getInstance().getidPostByutenteandContent(ute,contentx );
              PostFactory.getInstance().cancposts(i);
              ute = null;
              request.setAttribute("postcance",contentx);
              contentx = null;
              
              request.setAttribute("cancposts",null);
              
              
              request.setAttribute("cancfinito",true);
              
              request.getRequestDispatcher("bacheca.jsp").forward(request, response);
              
              return;
            }  
              
            String content = request.getParameter("newpost");
            String url = request.getParameter("urlP");
            
            //caso nuovo post
                if(request.getParameter("inviapost") != null){
          
                request.setAttribute("content", content);
                request.setAttribute("url", url);
                session.setAttribute("wrPost", request.getParameter("newpost"));
                session.setAttribute("urlPost", request.getParameter("urlP"));
                request.setAttribute("invioPost", "okk");
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);}
            
          
          
            //caso conferma nuovo post
                if(request.getParameter("sendpost") != null){
                    Post post = new Post();
                    post.setContent(content + "<br>"
                          + url);
                    
                    post.setUser(UtentiRegFactory.getInstance().getUtenteById(userID));
                    post.setAutore(UtentiRegFactory.getInstance().getUtenteById((int)session.getAttribute("id")));
                    PostFactory.getInstance().addNewPost(post);
                    
                request.setAttribute("sendPostok", "okk");
                request.setAttribute("invioPost", null);
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);}
                
             //caso creazione gruppo
             if(request.getParameter("makegroup") != null){
                 request.setAttribute("makegroupok", true);
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);}
             
            // caso conferma gruppo
               if(request.getParameter("Conf") != null){
                   String nome_gruppo = request.getParameter("nome_gruppo");
                   String data_creazione = request.getParameter("data_creazione");
                   Gruppi group = new Gruppi();
                   group.setNome_gruppo(nome_gruppo);
                   group.setData_creazione(data_creazione);
                   group.setAmm_gruppo(utentep);
                   GruppiRegFactory.getInstance().createGroup(group);
                   int idgr = GruppiRegFactory.getInstance().getidByGroup(nome_gruppo);
                   PartecipaFactory.getInstance().joinGroup(userid,idgr );
                   request.setAttribute("confgr", true);
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);
               }
                
            session.setAttribute("loggedOn", true);            
            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            
            
    
            }
        
           
            
        request.getRequestDispatcher("bacheca.jsp").forward(request, response);
        
         
        
        }}
        // caso utente non loggato//
         if(request.getAttribute("bacError")!=null){
            request.getRequestDispatcher("profiloPage.jsp").forward(request, response);
        }
        else {
            
            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
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