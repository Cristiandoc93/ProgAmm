/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook.Classi;

/**
 *
 * @author Cristian
 */
public class Partecipa {

    
    
    protected UtentiReg utente_id;
    protected Gruppi gruppo_id;
    
    public Partecipa(){
        utente_id = null;
        gruppo_id = null; 
    }
    
    
    public UtentiReg getUtente_id() {
        return utente_id;
    }

    public void setUtente_id(UtentiReg utente_id) {
        this.utente_id = utente_id;
    }

    public Gruppi getGruppo_id() {
        return gruppo_id;
    }

    public void setGruppo_id(Gruppi gruppo_id) {
        this.gruppo_id = gruppo_id;
    }
    
    
}
