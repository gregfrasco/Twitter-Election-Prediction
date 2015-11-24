/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplictionStarter;

import Controller.DataController;
import Controller.TweetSetController;
import Model.Party;
import Model.Tweet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 *
 * @author frascog
 */
public class ClassifyDemReb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        DataController dataController = new DataController();
        dataController.dataSets.put("myData", new File("/Users/frascog/Desktop/TwitterData.csv"));
        TweetSetController tsc = dataController.getData("myData");

        HashMap<String, Integer> demWords = new HashMap<String, Integer>();
        HashMap<String, Integer> repWords = new HashMap<String, Integer>();

        tsc.removeTweets();
        
        
        //create counts
        for (int i = 0; i < tsc.size(); i++) {
            Tweet tweet = tsc.get(i);
            String[] words = tweet.getMessage().split(" ");
            for (String word : words) {
                if (tweet.getParty() != Party.remove) {
                    if (tweet.getParty() == Party.democrat) {
                        if (demWords.containsKey(word)) {
                            demWords.put(word, (demWords.get(word) + 1));
                        } else {
                            demWords.put(word, 1);
                        }
                    } else {
                        if (repWords.containsKey(word)) {
                            repWords.put(word, (repWords.get(word) + 1));
                        } else {
                            repWords.put(word, 1);
                        }
                    }
                }
            }
        }

        //get values {
        for (int i = 0; i < tsc.size(); i++) {
            Tweet tweet = tsc.get(i);
            String[] words = tweet.getMessage().split(" ");
            int demScore = tweet.getDemocrat();
            int repScore = tweet.getRebpublican();
            
            for (String word : words) {
                int dem = 0;
                int reb = 0;
                
                if(demWords.containsKey(word)){
                    dem = demWords.get(word);
                }
                if(repWords.containsKey(word)){
                    reb = repWords.get(word);
                }
                if(dem > reb){
                    demScore += 1;
                } else if(dem < reb){
                    repScore += 1;
                }
            }
            tweet.setDemocrat(demScore);
            tweet.setRebpublican(repScore);
        }
        tsc.save(new File("/Users/frascog/Desktop/demVSrepSet.csv"));
    }

}
