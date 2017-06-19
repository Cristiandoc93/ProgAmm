
package amm.nerdbook.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristian
 */
public class GruppiRegFactory {
    
    
    private static GruppiRegFactory singleton;
    
    public static GruppiRegFactory getInstance() {
        if (singleton == null) {
            singleton = new GruppiRegFactory();
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
    
    
    
    private ArrayList<Gruppi> listaGruppi = new ArrayList<Gruppi>();
    
    private GruppiRegFactory(){}
    
    public Gruppi getGruppoById(int gruppo_id){
        
        UtentiRegFactory utentireg = UtentiRegFactory.getInstance();
        
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            String query = "select * from gruppi "
            + "where gruppo_id = ?";
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, gruppo_id);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
           
             // ciclo sulle righe restituite
            if(res.next()) 
            {
                Gruppi current = new Gruppi();
                current.setGruppo_id(res.getInt("gruppo_id"));
                UtentiReg utente = utentireg.getUtenteById(res.getInt("amm_gruppo"));
                current.setAmm_gruppo(utente);
                current.setNome_gruppo(res.getString("nome_gruppo"));
                current.setData_creazione(res.getString("data_creazione"));
               
                
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
    
    
    
    public ArrayList<Gruppi> getGruppiList()
    {
        UtentiRegFactory utentireg = UtentiRegFactory.getInstance();
        ArrayList<Gruppi> listaGruppi = new ArrayList<Gruppi>();
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            Statement stmt = conn.createStatement();
            
            String query = "select * from gruppi";
            
            ResultSet set = stmt.executeQuery(query);
            
             // ciclo sulle righe restituite
            while(set.next()) 
            {
                Gruppi current = new Gruppi();
                current.setGruppo_id(set.getInt("gruppo_id"));
                UtentiReg utente = utentireg.getUtenteById(set.getInt("amm_gruppo"));
                current.setAmm_gruppo(utente);
                current.setNome_gruppo(set.getString("nome_gruppo"));
                current.setData_creazione(set.getString("data_creazione"));
                listaGruppi.add(current);
            }     
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return listaGruppi;
    }
    
    
    public void cancellaGruppo(int gruppo){
         try{
         Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "delete from gruppi "
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
