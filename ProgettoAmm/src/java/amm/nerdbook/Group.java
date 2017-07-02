package amm.nerdbook;

import amm.nerdbook.Classi.Amm;
import amm.nerdbook.Classi.AmmFactory;
import amm.nerdbook.Classi.Gruppi;
import amm.nerdbook.Classi.GruppiRegFactory;
import amm.nerdbook.Classi.Partecipa;
import amm.nerdbook.Classi.PartecipaFactory;
import amm.nerdbook.Classi.Post;
import amm.nerdbook.Classi.PostFactory;
import amm.nerdbook.Classi.PostGruppo;
import amm.nerdbook.Classi.PostGruppoFactory;
import amm.nerdbook.Classi.UtentiReg;
import amm.nerdbook.Classi.UtentiRegFactory;
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

/**
 *
 * @author Cristian
 */
public class Group extends HttpServlet {

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
           
            
            Integer utente_id = (Integer)session.getAttribute("id");
            
            int userID = (int)session.getAttribute("id");
           String group = request.getParameter("group");
           int groupID ;
           groupID = Integer.parseInt(group);
          
            
            Partecipa partecipa = PartecipaFactory.getInstance().getPartecipanteById(userID);
            Gruppi gruppo = GruppiRegFactory.getInstance().getGruppoById(groupID);
            UtentiReg utente = UtentiRegFactory.getInstance().getUtenteById(userID);
             UtentiReg utentep = UtentiRegFactory.getInstance().getUtenteById(utente_id);
            request.setAttribute("utentep", utentep);
              Amm amm = AmmFactory.getInstance().getUtenteById(utente_id);
             session.setAttribute("amm", amm);
            
            if(utente != null && session.getAttribute("bacError") != "true") {
            request.setAttribute("partecipa", partecipa);
            request.setAttribute("utente", utente);
            request.setAttribute("gruppo", gruppo);
            String content = request.getParameter("newpost");
            String url = request.getParameter("urlP");
            
            ArrayList<UtentiReg> utenti = UtentiRegFactory.getInstance().getUtentiList();
            request.setAttribute("utenti", utenti);
        
            
            List<PostGruppo> postsg = PostGruppoFactory.getInstance().getPostGList(gruppo);
            request.setAttribute("postsg", postsg); 
            
            ArrayList<Gruppi> gruppi = GruppiRegFactory.getInstance().getGruppiList();
            request.setAttribute("gruppi", gruppi);
            
            int valido = PartecipaFactory.getInstance().getIdUtenteAndGroup(userID, groupID);
            if( valido != -1){
                request.setAttribute("okgroup", true);
            }else{
                request.setAttribute("okgroup", false);
            }
            //caso cancellazione post admin
              if(request.getParameter("cancposts") != null){
              String gid = request.getParameter("gid");
              String contentg = request.getParameter("contentg");
              
              int i = PostGruppoFactory.getInstance().getidPostByutenteandContent(gid,contentg );
              PostGruppoFactory.getInstance().cancpostsgruppo(i);
              gid = null;
              request.setAttribute("postcance",contentg);
              contentg = null;
              
              request.setAttribute("cancposts",null);
              
              
              request.setAttribute("cancfinito",true);
              
              request.getRequestDispatcher("bachecag.jsp").forward(request, response);
              
              return;
            }  
           
            
            //caso iscrizione
            
            if(request.getParameter("Iscrizione") != null)
            {
            int utente_iscr = userID;
            int gruppo_iscr = groupID;
            request.setAttribute("okgroup", true);
            PartecipaFactory.getInstance().joinGroup(utente_iscr, gruppo_iscr);
            request.setAttribute("Iscrizione",null);
            request.getRequestDispatcher("bachecag.jsp").forward(request, response);
            
            }
            
            //caso cancellazione gruppo
            
             if(request.getParameter("Cancella") != null)
            {
           
            
            request.setAttribute("okgroup", true);
            PostGruppoFactory.getInstance().cancellaPostGruppo(groupID);
            PartecipaFactory.getInstance().cancellaRelazione(groupID);
            GruppiRegFactory.getInstance().cancellaGruppo(groupID);
            
      
            request.getRequestDispatcher("bacheca.html").forward(request, response);
            
            }
            
            //richiamo la lista partecipanti
            ArrayList<Partecipa> partecipanti = PartecipaFactory.getInstance().getListaPartecipanti(groupID);
            //request.setAttribute("partecipanti", partecipanti); 
             
            //caso nuovo post
                if(request.getParameter("inviapost") != null){
                request.setAttribute("content", content);
                request.setAttribute("url", url);
                session.setAttribute("wrPost", request.getParameter("newpost"));
                session.setAttribute("urlPost", request.getParameter("urlP"));
                request.setAttribute("invioPost", "okk");
                request.getRequestDispatcher("bachecag.jsp").forward(request, response);}
            
          
          
            //caso conferma nuovo post
                if(request.getParameter("sendpost") != null){
                    PostGruppo postg = new PostGruppo();
                    postg.setContent(content + "<br>"
                          + url);
                    
                   postg.setAutore_post_gruppo(UtentiRegFactory.getInstance().getUtenteById((int)session.getAttribute("id")));
                    postg.setGruppo_id(GruppiRegFactory.getInstance().getGruppoById(groupID));
                    PostGruppoFactory.getInstance().addNewPostGruppo(postg);
                    
                
                
               for (int i = 0; i < partecipanti.size(); i++) {
                   UtentiReg prova = partecipanti.get(i).getUtente_id();
                   Post post = new Post(); 
                   post.setContent(content + "<br>"
                          + url);
                    
                    post.setUser(prova);
                    post.setAutore(UtentiRegFactory.getInstance().getUtenteById((int)session.getAttribute("id")));
                    PostFactory.getInstance().addNewPost(post);
                   
               }
               
               request.setAttribute("sendPostok", "okk");
                request.getRequestDispatcher("bachecag.jsp").forward(request, response);}
                
            session.setAttribute("loggedOn", true);            
            request.getRequestDispatcher("bachecag.jsp").forward(request, response);
            
            
    
            }
        
           
            
        request.getRequestDispatcher("bachecag.jsp").forward(request, response);
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
