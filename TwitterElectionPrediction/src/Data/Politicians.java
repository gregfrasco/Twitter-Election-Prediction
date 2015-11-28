/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frascog
 */
public class Politicians {
    
    private List<Politicain> politicains;

    public Politicians() {
        this.politicains = new ArrayList<Politicain>();
        Politicain hillery = new Politicain("Hillary Clinton", "src/Images/clinton.jpg", Party.democrat,"clinton");
        Politicain sanders = new Politicain("Bernie Sanders", "src/Images/snaders.jpg", Party.democrat,"sanders");
        Politicain trump = new Politicain("Donald Trump", "src/Images/trump.jpg", Party.republican,"trump");
        Politicain carson = new Politicain("Ben Carson", "src/Images/carson.jpg", Party.republican,"carson");
        Politicain rubio = new Politicain("Marco Rubio", "src/Images/rubio.jpg", Party.republican,"rubio");
        
        politicains.add(hillery);
        politicains.add(sanders);
        politicains.add(trump);
        politicains.add(carson);
        politicains.add(rubio);
    }

    public List<Politicain> getPoliticains() {
        return politicains;
    }
    
    public void loadData(){
        try {
            Classifier classifier = Classifier.getInstance();
            BufferedReader br = new BufferedReader(new FileReader("src/rawdata/testing.csv"));
            String line = "";
            while((line = br.readLine()) != null){
                String[] words = line.split(",");
                if(words[0].equals("Hillary Clinton")){
                    this.politicains.get(0).addTweet(classifier.classify(words[1]));
                } else if(words[0].equals("Bernie Sanders")){
                    this.politicains.get(1).addTweet(classifier.classify(words[1]));
                } else if(words[0].equals("Donald Trump")){
                    this.politicains.get(2).addTweet(classifier.classify(words[1]));
                } else if(words[0].equals("Ben Carson")){
                    this.politicains.get(3).addTweet(classifier.classify(words[1]));
                } else if(words[0].equals("Marco Rubio")){
                    this.politicains.get(4).addTweet(classifier.classify(words[1]));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Politicians.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Politicians.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
