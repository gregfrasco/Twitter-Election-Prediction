/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SentimentAnalysis;

import Data.Sentiment;
import Data.Tweet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author frascog
 */
public class NaiveBayesSentiment implements Runnable{

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
    
    private double resubstitutionError;
    private double tenCrossFold;

    public NaiveBayesSentiment(List<Tweet> tweets) {
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

    private double pWordGiven(String word, Sentiment group) {
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

    public double pTweetGiven(Tweet tweet, Sentiment group) {
        double value = 0;
        String[] words;
        switch (group) {
            case positive:
                words = tweet.getMessage().split(" ");
                for (String word : words) {
                    value += Math.log(pWordGiven(word, Sentiment.positive));
                }
                return value;
            case negitive:
                words = tweet.getMessage().split(" ");
                for (String word : words) {
                    value += Math.log(pWordGiven(word, Sentiment.negitive));
                }
                return value;
        }
        return 0;
    }

    public double groupGivenText(Sentiment group,Tweet tweet){
        switch(group){
            case positive:
                return pTweetGiven(tweet, group) + Math.log(pOfPositive);
            case negitive:
                return pTweetGiven(tweet, group) + Math.log(pOfNegitve);
        }
        return 0;
    }
    
    public Sentiment classify(Tweet tweet){
        Sentiment classify = Sentiment.neutral;
        double pOfPostitive = groupGivenText(Sentiment.positive, tweet);
        double pOfNegitive = groupGivenText(Sentiment.negitive, tweet);
        //double support = (pOfPostitive * this.pOfPositive) + (pOfNegitive * this.pOfNegitve);
        double support = 1;
        pOfPostitive /= support;
        pOfNegitive /= support;
        if(pOfPostitive > pOfNegitive){
            classify = Sentiment.positive;
        } else {
            classify = Sentiment.negitive;
        }
        return classify;
    }
    
    private void error() {
        int count = 0;
        for (int i = 0; i < tweets.size(); i++) {
            if(tweets.get(i).getSentiment().equals(this.classify(tweets.get(i)))){
                count += 1;
            }
        }
        this.resubstitutionError = 1 - (count / ((1.0)*this.tweets.size()));
        
        int range = this.tweets.size()/10;
        int lowerbound = 0;
        int upperbound = range;
        List<Tweet> testing = new ArrayList<Tweet>();
        List<Tweet> training = new ArrayList<Tweet>();
        NaiveBayesSentiment naiveBayesSentiment = null;
        for (int j = 0; j < 10; j++) {
            lowerbound = j*range;
            upperbound = lowerbound + range;
            for (int i = lowerbound; i < upperbound; i++) {
                testing.add(this.tweets.get(i));
            }
            for (int i = 0; i < lowerbound; i++) {
                training.add(this.tweets.get(i));
            }
            for (int i = upperbound; i < this.tweets.size(); i++) {
                training.add(this.tweets.get(i));
            }
            naiveBayesSentiment = new NaiveBayesSentiment(training);
            double cross = 0;
            for (int i = 0; i < testing.size(); i++) {
                if(naiveBayesSentiment.classify(testing.get(i)) == testing.get(i).getSentiment()){
                    cross += 1;
                }
            }
            cross = cross / ((1.0)*testing.size());
            this.tenCrossFold += cross;
        }
        this.tenCrossFold /= 10;
    }

    public double getResubstitutionError() {
        return resubstitutionError;
    }

    public double getTenCrossFold() {
        return tenCrossFold;
    }

    @Override
    public void run() {
        this.error();
    }

}
