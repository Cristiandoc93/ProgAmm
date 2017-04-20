/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook.Classi;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristian
 */
public class PostFactory {

    //Pattern Design Singleton
    private static PostFactory singleton;

    public static PostFactory getInstance() {
        if (singleton == null) {
            singleton = new PostFactory();
        }
        return singleton;
    }

    private ArrayList<Post> listaPost = new ArrayList<Post>();

    private PostFactory() {
        
        UtentiRegFactory utenteFactory = UtentiRegFactory.getInstance();

        //Creazione Post
        Post post1 = new Post();
        post1.setContent("Ciao, miei schiavi. Datemi cibo! Adesso! Miaomiaomiaomiaomiao!");
        post1.setId(0);
        post1.setUser(utenteFactory.getUtenteById(0));

        Post post2 = new Post();
        post2.setContent("img/djanni1.jpg");
        post2.setId(1);
        post2.setUser(utenteFactory.getUtenteById(0));
        

        Post post3 = new Post();
        post3.setContent("img/djanni2.jpg");
        post3.setId(2);
        post3.setUser(utenteFactory.getUtenteById(0));
      

       

        listaPost.add(post1);
        listaPost.add(post2);
        listaPost.add(post3);
        
    }

    public Post getPostById(int id) {
        for (Post post : this.listaPost) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public List getPostList(UtentiReg utt) {

        List<Post> listaPost = new ArrayList<Post>();

        for (Post post : this.listaPost) {
            if (post.getUser().equals(utt)) {
                listaPost.add(post);
            }
        }
        return listaPost;
    }
}

