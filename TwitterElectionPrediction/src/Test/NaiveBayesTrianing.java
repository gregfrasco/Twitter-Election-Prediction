/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Data.Data;
import Data.Group;
import Data.Tweet;
import SentimentAnalysis.NaiveBayes;

/**
 *
 * @author frascog
 */
public class NaiveBayesTrianing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Data data = new Data();
        data.loadData();
        NaiveBayes naiveBayes = new NaiveBayes(data.getTweets());
        int positive = 0;
        int negitive = 0;
        int neural = 0;
        double score = 0;
        for (int i = 0; i < data.getTweets().size(); i++) {
            Tweet tweet = data.getTweets().get(i);
            if(naiveBayes.classify(tweet).equals(tweet.getGroup())){
                score += 1;
                if (tweet.getGroup().equals(Group.positive)) {
                    positive += 1;
                } else if (tweet.getGroup().equals(Group.neutral)) {
                    neural += 1;
                } else if (tweet.getGroup().equals(Group.negitive)) {
                    negitive += 1;
                }
            }
            System.out.println((i)/((1.0)*data.getTweets().size()));
        }
        score = score/data.getTweets().size();
        System.out.println(score);
        System.out.println("Positive: \t" + positive);
        System.out.println("Negitive: \t" + negitive);
        System.out.println("Neural: \t" + neural);
    }
    
}
