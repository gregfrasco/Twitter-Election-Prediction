/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Data.Data;
import Data.Sentiment;
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
        NaiveBayes naiveBayes = new NaiveBayes(data.getSentimentTweets());
        int positive = 0;
        int negitive = 0;
        int neural = 0;
        double score = 0;
        for (int i = 0; i < data.getSentimentTweets().size(); i++) {
            Tweet tweet = data.getSentimentTweets().get(i);
            if(naiveBayes.classify(tweet).equals(tweet.getSentiment())){
                score += 1;
                if (tweet.getSentiment().equals(Sentiment.positive)) {
                    positive += 1;
                } else if (tweet.getSentiment().equals(Sentiment.neutral)) {
                    neural += 1;
                } else if (tweet.getSentiment().equals(Sentiment.negitive)) {
                    negitive += 1;
                }
            }
            System.out.println((i)/((1.0)*data.getSentimentTweets().size()));
        }
        score = score/data.getSentimentTweets().size();
        System.out.println(score);
        System.out.println("Positive: \t" + positive);
        System.out.println("Negitive: \t" + negitive);
        System.out.println("Neural: \t" + neural);
    }
    
}
