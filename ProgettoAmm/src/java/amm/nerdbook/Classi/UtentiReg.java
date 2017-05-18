
package amm.nerdbook.Classi;

import java.util.ArrayList;

/**
 *
 * @author Cristian
 */
public class UtentiReg{
    private ArrayList<Post> posts = new ArrayList<Post>();
    
    
    protected int id;
    protected String username;
    protected String nome;
    protected String cognome;
    protected String urlfotoprofilo;
    protected String presentazione;
    protected String datanascita;
    protected String password;
    
    public UtentiReg(){
        
        id = 0;
        nome = "";
        cognome = "";
        urlfotoprofilo = "";
        presentazione = "";
        datanascita = "";
        password = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return the urlfotoprofilo
     */
    public String getUrlfotoprofilo() {
        return urlfotoprofilo;
    }

    /**
     * @param urlfotoprofilo the urlfotoprofilo to set
     */
    public void setUrlfotoprofilo(String urlfotoprofilo) {
        this.urlfotoprofilo = urlfotoprofilo;
    }

    /**
     * @return the presentazione
     */
    public String getPresentazione() {
        return presentazione;
    }

    /**
     * @param presentazione the presentazione to set
     */
    public void setPresentazione(String presentazione) {
        this.presentazione = presentazione;
    }

    /**
     * @return the datanascita
     */
    public String getDatanascita() {
        return datanascita;
    }

    /**
     * @param datanascita the dataNascita to set
     */
    public void setDatanascita(String datanascita) {
        this.datanascita = datanascita;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public ArrayList<Post> getPostUtente() {
        return posts;
    }
    
    public void setPostUtente(ArrayList<Post> posts) {
        this.posts = posts;
    }
}

