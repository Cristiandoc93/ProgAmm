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
        post1.setContent("Son contento. Mi trovai un giorno in casa un medico molto stimato in Venezia, dove alcuni per loro studio, ed altri per curiosità, convenivano tal volta a veder qualche taglio di notomia per mano di uno veramente non men dotto che diligente e pratico notomista. Ed accadde quel giorno, che si andava ricercando l'origine e nascimento de i nervi, sopra di che è famosa controversia tra i medici galenisti ed i peripatetici; e mostrando il notomista come, partendosi dal cervello e passando per la nuca, il grandissimo ceppo de i nervi si andava poi distendendo per la spinale e diramandosi per tutto il corpo, e che solo un filo sottilissimo come il refe arrivava al cuore, voltosi ad un gentil uomo ch'egli conosceva per filosofo peripatetico, e per la presenza del quale egli aveva con estraordinaria diligenza scoperto e mostrato il tutto, gli domandò s'ei restava ben pago e sicuro, l'origine de i nervi venir dal cervello e non dal cuore; al quale il filosofo, doppo essere stato alquanto sopra di sé, rispose: \"Voi mi avete fatto veder questa cosa talmente aperta e sensata, che quando il testo d'Aristotile non fusse in contrario, che apertamente dice, i nervi nascer dal cuore, bisognerebbe per forza confessarla per vera");
        post1.setId(0);
        post1.setUser(utenteFactory.getUtenteById(0));

        Post post2 = new Post();
        post2.setContent("Aristotile non si è acquistata sí grande autorità se non per la forza delle sue dimostrazioni e della profondità de i suoi discorsi: ma bisogna intenderlo, e non solamente intenderlo, ma aver tanta gran pratica ne' suoi libri, che se ne sia formata un'idea perfettissima, in modo che ogni suo detto vi sia sempre innanzi alla mente; perché e' non ha scritto per il volgo, né si è obligato a infilzare i suoi silogismi col metodo triviale ordinato, anzi, servendosi del perturbato, ha messo talvolta la prova di una proposizione fra testi che par che trattino di ogni altra cosa: e però bisogna aver tutta quella grande idea, e saper combinar questo passo con quello, accozzar questo testo con un altro remotissimo; ch'e' non è dubbio che chi averà questa pratica, saprà cavar da' suoi libri le dimostrazioni di ogni scibile, perché in essi è ogni cosa");
        post2.setId(1);
        post2.setUser(utenteFactory.getUtenteById(0));
        

        Post post3 = new Post();
        post3.setContent("Né sarà mai al sicuro, come si abbiano di simili contradittori; ma questo che voi dite non diminuisce punto la stravaganza della risposta del Peripatetico, il quale contro a cosí sensata esperienza non produsse altre esperienze o ragioni d'Aristotile, ma la sola autorità ed il puro ipse dixit.");
        post3.setId(2);
        post3.setUser(utenteFactory.getUtenteById(3));
        
        Post post4 = new Post();
        post4.setContent("Aristotile non si è acquistata sí grande autorità se non per la forza delle sue dimostrazioni e della profondità de i suoi discorsi: ma bisogna intenderlo, e non solamente intenderlo, ma aver tanta gran pratica ne' suoi libri, che se ne sia formata un'idea perfettissima, in modo che ogni suo detto vi sia sempre innanzi alla mente; perché e' non ha scritto per il volgo, né si è obligato a infilzare i suoi silogismi col metodo triviale ordinato, anzi, servendosi del perturbato, ha messo talvolta la prova di una proposizione fra testi che par che trattino di ogni altra cosa: e però bisogna aver tutta quella grande idea, e saper combinar questo passo con quello, accozzar questo testo con un altro remotissimo; ch'e' non è dubbio che chi averà questa pratica, saprà cavar da' suoi libri le dimostrazioni di ogni scibile, perché in essi è ogni cosa");
        post4.setId(3);
        post4.setUser(utenteFactory.getUtenteById(1));
        
        Post post5 = new Post();
        post5.setContent("Aristotile non si è acquistata sí grande autorità se non per la forza delle sue dimostrazioni e della profondità de i suoi discorsi: ma bisogna intenderlo, e non solamente intenderlo, ma aver tanta gran pratica ne' suoi libri, che se ne sia formata un'idea perfettissima, in modo che ogni suo detto vi sia sempre innanzi alla mente; perché e' non ha scritto per il volgo, né si è obligato a infilzare i suoi silogismi col metodo triviale ordinato, anzi, servendosi del perturbato, ha messo talvolta la prova di una proposizione fra testi che par che trattino di ogni altra cosa: e però bisogna aver tutta quella grande idea, e saper combinar questo passo con quello, accozzar questo testo con un altro remotissimo; ch'e' non è dubbio che chi averà questa pratica, saprà cavar da' suoi libri le dimostrazioni di ogni scibile, perché in essi è ogni cosa");
        post5.setId(4);
        post5.setUser(utenteFactory.getUtenteById(3));
      

       

        listaPost.add(post1);
        listaPost.add(post2);
        listaPost.add(post3);
        listaPost.add(post4);
        listaPost.add(post5);
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

