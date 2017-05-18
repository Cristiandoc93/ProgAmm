
package amm.nerdbook.Classi;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Cristian
 */
public class UtentiRegFactory {
    
    private static UtentiRegFactory singleton;
   
    public static UtentiRegFactory getInstance() {
        if (singleton == null) {
            singleton = new UtentiRegFactory();
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
    //Fine gestione DB

    
    
    private ArrayList<UtentiReg> listaUtenti = new ArrayList<UtentiReg>();
    
    
    private UtentiRegFactory(){}
    
     public UtentiReg getUtente(String username, String password)
    {
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            // sql command
            String query = "select * from utenti where "
                    + "password = ? and username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            // dati
            
            stmt.setString(1, password);
            stmt.setString(2, username);
            //
            ResultSet set = stmt.executeQuery();

            
            if(set.next())
            {
                UtentiReg utente = new UtentiReg();
        
                utente.id = set.getInt("id");
                utente.username = set.getString("username");
                utente.nome = set.getString("nome");
                utente.cognome = set.getString("cognome");
                utente.urlfotoprofilo = set.getString("urlfotoprofilo");
                utente.datanascita = set.getString("datanascita");
                utente.presentazione = set.getString("presentazione");
                utente.password = set.getString("password");
                
                stmt.close();
                conn.close();
                return utente;
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
           // e.printStackTrace();
        }
            
        return null;
            
    }

    public UtentiReg getUtenteById(int id){
   
       try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            String query = "select * from utenti "
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
                UtentiReg current = new UtentiReg();
                current.setId(res.getInt("id"));
                current.setUsername(res.getString("username"));
                current.setNome(res.getString("nome"));
                current.setCognome(res.getString("cognome"));
                current.setUrlfotoprofilo(res.getString("urlfotoprofilo"));
                current.setPresentazione(res.getString("presentazione"));
                current.setDatanascita(res.getString("datanascita"));
                current.setPassword(res.getString("password"));
                
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
   
    
   
    
    public ArrayList<UtentiReg> getUtentiList()
    {
        ArrayList<UtentiReg> listaUtenti = new ArrayList<UtentiReg>();
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            Statement stmt = conn.createStatement();
            String query = "select * from utenti";
            ResultSet set = stmt.executeQuery(query);
            
             // ciclo sulle righe restituite
            while(set.next()) 
            {
                UtentiReg current = new UtentiReg();
                current.setId(set.getInt("id"));
                current.setUsername(set.getString("username"));
                current.setNome(set.getString("nome"));
                current.setCognome(set.getString("cognome"));
                current.setUrlfotoprofilo(set.getString("urlfotoprofilo"));
                current.setPresentazione(set.getString("presentazione"));
                current.setDatanascita(set.getString("datanascita"));
                current.setPassword(set.getString("password"));
                listaUtenti.add(current);
            }     
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return listaUtenti;
    }
    
    public int getIdUserAndPassword(String user, String password){
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "cri", "123");
            
            String query = 
                      "select id from utenti "
                    + "where name = ? and password = ?";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Si associano i valori
            stmt.setString(1, user);
            stmt.setString(2, password);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();

            // ciclo sulle righe restituite
            if (res.next()) {
                int id = res.getInt("id");

                stmt.close();
                conn.close();
                return id;
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
        
    }
    
    
   
    
    
    
   
    
}
