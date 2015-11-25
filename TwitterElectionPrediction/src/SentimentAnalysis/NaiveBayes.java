/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SentimentAnalysis;

import Data.Group;
import Data.Tweet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author frascog
 */
public class NaiveBayes {

    private int postitve = 0;
    private int negitve = 0;
    private int size = 0;
    private List<Tweet> tweets;

    private double pOfPositive;
    private double pOfNegitve;

    private List<Tweet> positiveTweets;
    private List<Tweet> negitveTweets;
    
    private HashMap<String,Integer> positiveWords;
    private HashMap<String,Integer> negitiveWords;

    public NaiveBayes(List<Tweet> tweets) {
        this.tweets = tweets;
        
        this.positiveTweets = new ArrayList<Tweet>();
        this.negitveTweets = new ArrayList<Tweet>();
        
        this.positiveWords = new HashMap<String,Integer>();
        this.negitiveWords = new HashMap<String,Integer>();
        this.count();
    }

    private void count() {
        for (Tweet tweet : tweets) {
            if (tweet.isPositive()) {
                this.postitve += 1;
                this.positiveTweets.add(tweet);
            } else if (tweet.isNegitive()) {
                this.negitve += 1;
                this.negitveTweets.add(tweet);
            }
        }
        this.size = this.tweets.size();
        this.pOfPositive = this.postitve / ((1.0) * this.size);
        this.pOfNegitve = this.negitve / ((1.0) * this.size);
        
        for (Tweet tweet : positiveTweets) {
            String[] words = tweet.getMessage().split(" ");
            for (String word : words) {
                if (positiveWords.containsKey(word)) {
                    positiveWords.put(word, 1+positiveWords.get(word));
                } else {
                    positiveWords.put(word, 1);
                }
            }
        }
        for (Tweet tweet : negitveTweets) {
            String[] words = tweet.getMessage().split(" ");
            for (String word : words) {
                if (negitiveWords.containsKey(word)) {
                    negitiveWords.put(word, 1+negitiveWords.get(word));
                } else {
                    negitiveWords.put(word, 1);
                }
            }
        }
    }

    private double pWordGiven(String word, Group group) {
        double value = 0;
        switch (group) {
            case positive:
                if(positiveWords.containsKey(word)){
                    return positiveWords.get(word) / (1.0)*postitve;
                } else {
                    return 0;
                }
            case negitive:
                if(negitiveWords.containsKey(word)){
                    return negitiveWords.get(word) / (1.0)*negitve;
                } else {
                    return 0;
                }
        }
        return 0;
    }

    public double pTweetGiven(Tweet tweet, Group group) {
        double value = 0;
        String[] words;
        switch (group) {
            case positive:
                words = tweet.getMessage().split(" ");
                for (String word : words) {
                    value += Math.log(pWordGiven(word, Group.positive));
                }
                return value;
            case negitive:
                words = tweet.getMessage().split(" ");
                for (String word : words) {
                    value += Math.log(pWordGiven(word, Group.negitive));
                }
                return value;
        }
        return 0;
    }

    public double groupGivenText(Group group,Tweet tweet){
        switch(group){
            case positive:
                return pTweetGiven(tweet, group) + Math.log(pOfPositive);
            case negitive:
                return pTweetGiven(tweet, group) + Math.log(pOfNegitve);
        }
        return 0;
    }
    
    public Group classify(Tweet tweet){
        Group classify = Group.neutral;
        double pOfPostitive = groupGivenText(Group.positive, tweet);
        double pOfNegitive = groupGivenText(Group.negitive, tweet);
        //double support = (pOfPostitive * this.pOfPositive) + (pOfNegitive * this.pOfNegitve);
        double support = 1;
        pOfPostitive /= support;
        pOfNegitive /= support;
        if(pOfPostitive > pOfNegitive){
            classify = Group.positive;
        } else {
            classify = Group.negitive;
        }
        return classify;
    }
}
