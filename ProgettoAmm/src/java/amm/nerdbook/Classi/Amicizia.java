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
public class Amicizia {
    
    
     protected UtentiReg utente;
     protected UtentiReg seguace;

    
    public Amicizia(){
        utente = null;
        seguace = null; 
    }
    
    
    
    
    public UtentiReg getUtente() {
        return utente;
    }

    public void setUtente(UtentiReg utente) {
        this.utente = utente;
    }

    public UtentiReg getSeguace() {
        return seguace;
    }

    public void setSeguace(UtentiReg seguace) {
        this.seguace = seguace;
    }
    
}
