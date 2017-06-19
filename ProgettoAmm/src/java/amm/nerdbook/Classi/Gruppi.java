
package amm.nerdbook.Classi;

/**
 *
 * @author Cristian
 */
public class Gruppi {
    
    
    
    protected int gruppo_id;
    protected String nome_gruppo;
    protected UtentiReg amm_gruppo;
    protected String data_creazione;
    
    public Gruppi(){
        gruppo_id = 0;
        nome_gruppo = "";
        amm_gruppo = null;
        data_creazione = "";
        
        
    }
    
    public int getGruppo_id() {
        return gruppo_id;
    }

    public void setGruppo_id(int gruppo_id) {
        this.gruppo_id = gruppo_id;
    }
  

    public String getNome_gruppo() {
        return nome_gruppo;
    }

    public void setNome_gruppo(String nome_gruppo) {
        this.nome_gruppo = nome_gruppo;
    }

    public UtentiReg getAmm_gruppo() {
        return amm_gruppo;
    }

    public void setAmm_gruppo(UtentiReg amm_gruppo) {
        this.amm_gruppo = amm_gruppo;
    }

    public String getData_creazione() {
        return data_creazione;
    }

    public void setData_creazione(String data_creazione) {
        this.data_creazione = data_creazione;
    }
 
  
}   

