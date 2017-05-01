
package amm.nerdbook.Classi;

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
    
    private ArrayList<UtentiReg> listaUtenti = new ArrayList<UtentiReg>();
    
    
    private UtentiRegFactory(){
        
        UtentiReg utente1 = new UtentiReg();
        
        utente1.setId(0);
        utente1.setUsername("cri");
        utente1.setNome("Cristian");
        utente1.setCognome("Rossi");
        utente1.setUrlfotoprofilo("img/ginopino.jpg");
        utente1.setPresentazione("Ciao Ciao");
        utente1.setDataNascita("30/12/1990");
        utente1.setPassword("12345678");
        
        
        UtentiReg utente2 = new UtentiReg();
        
        utente2.setId(1);
        utente2.setUsername("gianino");
        utente2.setNome("Gianni");
        utente2.setCognome("Bianchi");
        utente2.setUrlfotoprofilo("img/marioloco.jpg");
        utente2.setPresentazione("Bye Bye");
        utente2.setDataNascita("12/09/1956");
        utente2.setPassword("2");
        
        UtentiReg utente3 = new UtentiReg();
        
        utente3.setId(2);
        utente3.setUsername("pino");
        utente3.setNome(null);
        utente3.setCognome(null);
        utente3.setUrlfotoprofilo("img/tinotoni.jpg");
        utente3.setPresentazione("ti amo ti amo");
        utente3.setDataNascita("10/09/1986");
        utente3.setPassword("000000");
        
       
        
        listaUtenti.add(utente1);
        listaUtenti.add(utente2);
        listaUtenti.add(utente3);
        
    }
   
    
    public ArrayList<UtentiReg> getUtenteList()
    {
        return listaUtenti;
    }
    public UtentiReg getUtenteById(int id)
    {
        for(UtentiReg u : listaUtenti)
        {
            if(u.id == id)
                return u;
        }
        return null;
    }
    
    public int getIdByUserAndPassword(String user, String password){
        for(UtentiReg utente : this.listaUtenti){
            if(utente.getNome().equals(user) && utente.getPassword().equals(password)){
                return utente.getId();
            }
        }
        return -1;
    }
    
}
