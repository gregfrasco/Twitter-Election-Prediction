/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Data.Data;
import Data.Tweet;
import PoliticalParty.NaiveBayesPolicitcal;

/**
 *
 * @author frascog
 */
public class PoliticalTraining {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Data data = new Data();
        NaiveBayesPolicitcal bayes = new NaiveBayesPolicitcal(data.getPartyTweets());
        int count = 0;
        for (Tweet tweet : data.getPartyTweets()) {
            if(bayes.classify(tweet).equals(tweet.getParty())){
                count += 1;
            }
        }
        System.out.println(count / ((1.0) * data.getPartyTweets().size()));
    }
    
}
