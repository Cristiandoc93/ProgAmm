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
public class AmmFactory {
    
    private static AmmFactory singleton;
   
    public static AmmFactory getInstance() {
        if (singleton == null) {
            singleton = new AmmFactory();
        }
        return singleton;
    }
     //Gestione DB
    private String connectionString;
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    public String getConnectionString(){
            return this.connectionString;
    }
    
    
    
    private AmmFactory(){}
    
    
     public Amm getUtenteById(int id){
         
          UtentiRegFactory utentireg = UtentiRegFactory.getInstance();
   
       try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            String query = "select * from amm "
            + "where utente = ?";
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, id);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
           
             // ciclo sulle righe restituite
            if(res.next()) 
            {
                Amm current = new Amm();
                UtentiReg utente = utentireg.getUtenteById(res.getInt("utente"));
                current.setUtente(utente);
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
    
}
