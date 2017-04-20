
package amm.nerdbook.Classi;

/**
 *
 * @author Cristian
 */
public class Post {

    
    protected int id;
    protected UtentiReg user;
    private String content;
    

    public Post() {
        id = 0;
        user = null;
        content = "";
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the user
     */
    public UtentiReg getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UtentiReg user) {
        this.user = user;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
}


