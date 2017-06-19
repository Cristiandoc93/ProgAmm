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
public class PartecipaFactory {
    
    
    private static PartecipaFactory singleton;
    
    public static PartecipaFactory getInstance() {
        if (singleton == null) {
            singleton = new PartecipaFactory();
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
    
    private PartecipaFactory(){}
    
    public Partecipa getPartecipanteById(int utente_id){
        
        UtentiRegFactory utentireg = UtentiRegFactory.getInstance();
        GruppiRegFactory gruppi = GruppiRegFactory.getInstance();
        
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            String query = "select * from partecipa "
            + "where utente_id = ?";
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, utente_id);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
            if(res.next()) 
            {
                Partecipa current = new Partecipa();
                Gruppi gruppo = gruppi.getGruppoById(res.getInt("gruppo_id"));
                current.setGruppo_id(gruppo);
                UtentiReg utente = utentireg.getUtenteById(res.getInt("utente_id"));
                current.setUtente_id(utente);
               
                
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
    
    
    
    
     public int getIdUtenteAndGroup(int utente_id, int gruppo_id){
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "select utente_id from partecipa "
                    + "where utente_id = ? and gruppo_id = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setInt(1, utente_id);
            stmt.setInt(2, gruppo_id);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();

            // ciclo sulle righe restituite
            if (res.next()) {
                int valido = res.getInt("utente_id");

                stmt.close();
                conn.close();
                return valido;
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
        
    }
     
    public void joinGroup(int utente_id, int gruppo_id){
        try {
          
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "insert into partecipa (utente_id,gruppo_id) "
                    + "values (?,?)";
            
         
            PreparedStatement stmt = conn.prepareStatement(query);
            
          
            

            
            stmt.setInt(1, utente_id);
            stmt.setInt(2, gruppo_id);
            
         
            stmt.executeUpdate();
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void cancellaRelazione(int gruppo){
         try{
         Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "delete from partecipa "
                    + "where gruppo_id = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            
            stmt.setInt(1, gruppo);
            stmt.executeUpdate();
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
           
    
    
}
