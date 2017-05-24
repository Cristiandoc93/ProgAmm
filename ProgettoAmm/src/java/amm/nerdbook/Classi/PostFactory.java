/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristian
 */
public class PostFactory {

    //Pattern Design Singleton
    private static PostFactory singleton;

    public static PostFactory getInstance() {
        if (singleton == null) {
            singleton = new PostFactory();
        }
        return singleton;
    }
    private String connectionString;
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    public String getConnectionString(){
            return this.connectionString;
    }

   //private ArrayList<Post> listaPost = new ArrayList<Post>();

    private PostFactory() {}
    

   public Post getPostById(int id){
   UtentiRegFactory utentireg = UtentiRegFactory.getInstance();
       try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            String query = "select * from post "
            + "where id = ?";
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, id);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
           
             // ciclo sulle righe restituite
            if(res.next()) 
            {
                Post current = new Post();
                current.setId(res.getInt("id"));
                
                current.setContent(res.getString("content"));
                UtentiReg autore = utentireg.getUtenteById(res.getInt("autore_post"));
                UtentiReg utente = utentireg.getUtenteById(res.getInt("utente_post"));
                current.setUser(utente);
                current.setAutore(autore);
                stmt.close();
                conn.close();
                return current;
            }
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
   
    
    /////////////////////////////////
    
    
    
////////////////////////////////////

   
    public List getPostList(UtentiReg utt) {
        UtentiRegFactory utentepost = UtentiRegFactory.getInstance();
        List<Post> listaPost = new ArrayList<Post>();

        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "select * from post "
                      + "where utente_post = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setInt(1, utt.getId());
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();

            // ciclo sulle righe restituite
            while (res.next()) {
                
                Post current = new Post();
                //imposto id del post
                current.setId(res.getInt("id"));
             
                current.setContent(res.getString("content"));
     
                current.setAutore(utentepost.getUtenteById(res.getInt("autore_post")));
           
                current.setUser(utt);
                
                listaPost.add(current);
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPost;
    }
    public void addNewPost(Post post){
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "insert into post (id, utente_post, autore_post, content) "
                    + "values (default, ? , ? , ?)";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            

            
            stmt.setInt(1, post.getUser().getId());
            stmt.setString(3, post.getContent());
            stmt.setInt(2, post.getAutore().getId());
            
            // Esecuzione query
            stmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    /* public void addNewPost(Post post){
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "insert into post (id, utente_post,autore_post, content) "
                    + "values (default, ? , ? , ?)";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            

            
            stmt.setInt(1, post.getUser().getId());
            stmt.setInt(1, post.getAutore());
            stmt.setString(3, post.getContent());
            
            // Esecuzione query
            stmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }*/
     
     public void deletePosts(Post post){
         
         try{
         Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "delete from post "
                    + "where utente_post = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            
            stmt.setInt(1, post.getUser().getId());
     
            
                
                
                
             
            stmt.executeUpdate();
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        

     }
}