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
public class GruppiRegFactory {
    
    
    private static GruppiRegFactory singleton;
    
    public static GruppiRegFactory getInstance() {
        if (singleton == null) {
            singleton = new GruppiRegFactory();
        }
        return singleton;
    }
    
    private String connectionString;
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    public String getConnectionString(){
            return this.connectionString;
    }
}
