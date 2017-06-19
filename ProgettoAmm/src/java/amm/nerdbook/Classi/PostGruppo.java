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
public class PostGruppo {

   
    
    protected int post_id;
    protected Gruppi gruppo_id;
    protected UtentiReg autore_post_gruppo;
    private String content;
    
    public PostGruppo(){
    
        
        post_id = 0;
        gruppo_id = null;
        autore_post_gruppo = null;
        content = "";
   }
    
    
     public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public Gruppi getGruppo_id() {
        return gruppo_id;
    }

    public void setGruppo_id(Gruppi gruppo_id) {
        this.gruppo_id = gruppo_id;
    }

    public UtentiReg getAutore_post_gruppo() {
        return autore_post_gruppo;
    }

    public void setAutore_post_gruppo(UtentiReg autore_post_gruppo) {
        this.autore_post_gruppo = autore_post_gruppo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
