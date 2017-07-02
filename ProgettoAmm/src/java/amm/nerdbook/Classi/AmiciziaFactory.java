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

/**
 *
 * @author Cristian
 */
public class AmiciziaFactory {
    
    private static AmiciziaFactory singleton;
    
    public static AmiciziaFactory getInstance() {
        if (singleton == null) {
            singleton = new AmiciziaFactory();
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
    
    
    
    private AmiciziaFactory(){}
    
    
    public int getIdUtenteAndSeguace(int utente, int seguace){
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "select utente from amicizia "
                    + "where utente = ? and seguace = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setInt(1, utente);
            stmt.setInt(2, seguace);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();

            // ciclo sulle righe restituite
            if (res.next()) {
                int friend = res.getInt("utente");

                stmt.close();
                conn.close();
                return friend;
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
        
    }
    
    public void addamico(int utente, int seguace){
        try {
          
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "insert into amicizia (utente,seguace) "
                    + "values (?,?)";
            
         
            PreparedStatement stmt = conn.prepareStatement(query);
            
          
            

            
            stmt.setInt(1, utente);
            stmt.setInt(2, seguace);
            
         
            stmt.executeUpdate();
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void cancellaAmicizia(int user){
         try{
         Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "delete from amicizia "
                    + "where utente = ?";
            
            String query2 = 
                      "delete from amicizia "
                    + "where seguace = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            
            stmt.setInt(1, user);
            stmt.executeUpdate();
            
            stmt2.setInt(1, user);
            stmt2.executeUpdate();
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
   /* public void cancellaseguace(int seguace){
         try{
         Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "delete from amicizia "
                    + "where seguace = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            
            stmt.setInt(1, seguace);
            stmt.executeUpdate();
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    */
    
    
}
