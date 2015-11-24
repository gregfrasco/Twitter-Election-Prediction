/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SentimentAnalysis;

import Data.Tweet;
import java.util.List;

/**
 *
 * @author frascog
 */
public class NaiveBayes {
    
    private int postitve = 0;
    private int negitve = 0;
    private int neutral = 0;
    private int size = 0;
    private List<Tweet> tweets;
    
    private double pOfPositive;
    private double pOfNeutral;
    private double pOfNegitve;

    public NaiveBayes(List<Tweet> tweets) {
        this.tweets = tweets;
        this.count();
    }
    
    private void count() {
        for (Tweet tweet : tweets) {
            if(tweet.isPositive()){
                this.postitve += 1;
            } else if(tweet.isNeutral()){
                this.neutral += 1;
            } else if(tweet.isNegitive()){
                this.negitve += 1;
            }
        }
        this.size = this.tweets.size();
        this.pOfPositive = this.postitve / ((1.0)*this.size);
        this.pOfNeutral = this.neutral / ((1.0)*this.size);
        this.pOfNegitve = this.negitve / ((1.0)*this.size);
    }
}
