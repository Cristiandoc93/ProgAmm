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

   

    private PostFactory() {}
    

   public Post getPostById(int id){
   UtentiRegFactory utentireg = UtentiRegFactory.getInstance();
       try 
        {
          
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            String query = "select * from post "
            + "where id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(query);
           
            stmt.setInt(1, id);
          
            ResultSet res = stmt.executeQuery();
           
         
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
         
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "select * from post "
                      + "where utente_post = ?";
            
       
            PreparedStatement stmt = conn.prepareStatement(query);
            
        
            stmt.setInt(1, utt.getId());
            
 
            ResultSet res = stmt.executeQuery();

       
            while (res.next()) {
                
                Post current = new Post();
        
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
          
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "insert into post (id, utente_post, autore_post, content) "
                    + "values (default, ? , ? , ?)";
            
         
            PreparedStatement stmt = conn.prepareStatement(query);
            
          
            

            
            stmt.setInt(1, post.getUser().getId());
            stmt.setString(3, post.getContent());
            stmt.setInt(2, post.getAutore().getId());
            
         
            stmt.executeUpdate();
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    
     public void deletePosts(Post post, UtentiReg utente) throws SQLException{
         Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
         
         
        
         
        
         
             conn.setAutoCommit(false);
            
            String query = 
                      "delete from post "
                    + "where utente_post = ?";
            
            String query2 = 
                      "delete from utenti "
                    + "where id = ?";
            
         
            PreparedStatement stmt = conn.prepareStatement(query);
            PreparedStatement stmt2 = conn.prepareStatement(query2);
               try{   
            stmt.setInt(1, post.getUser().getId());
            
            stmt.executeUpdate();
            
            stmt2.setInt(1, utente.getId());
            
  
            stmt2.executeUpdate();
            
           
          
         }
         
         catch (SQLException e ) {
                    PostFactory.printSQLException(e);
                    if (conn != null) {
                    try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                    } catch(SQLException excep) {
                    PostFactory.printSQLException(excep);
                    }
                    }
        
            
          
        }
          finally {
             
                        if (stmt != null) {
                        stmt.close();
                        }
                        if (stmt2 != null) {
                        stmt2.close();
                        }
                        conn.setAutoCommit(true);
                  }
    }

     
     
     public static void printSQLException(SQLException ex) { 
        for (Throwable e : ex) { 
        if (e instanceof SQLException) { 
       
        e.printStackTrace(System.err); 
        System.err.println("SQLState: " + ((SQLException)e).getSQLState()); 
        System.err.println("Error Code: " + ((SQLException)e).getErrorCode()); 
        System.err.println("Message: " + e.getMessage()); 
        Throwable t = ex.getCause(); 
        while (t != null) { 
        System.out.println("Cause: " + t); 
        t = t.getCause(); 
                } 
            } 
        } 
     }
}

    
