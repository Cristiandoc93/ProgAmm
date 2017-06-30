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
public class Amm extends UtentiReg {

    public UtentiReg getUtente() {
        return utente;
    }

    /* Attributi */
    public void setUtente(UtentiReg utente) {
        this.utente = utente;
    }

    protected UtentiReg utente;
    
    
    /* Costruttore */
    public Amm()
    {
        super();
    }
    
}