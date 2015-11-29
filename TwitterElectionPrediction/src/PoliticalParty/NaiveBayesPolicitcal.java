/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PoliticalParty;

import Data.Party;
import Data.Tweet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author frascog
 */
public class NaiveBayesPolicitcal implements Runnable{

    private int democrat = 0;
    private int republican = 0;
    private int size = 0;
    private List<Tweet> tweets;

    private double pOfDemocrat;
    private double pOfRepublican;

    private List<Tweet> democratTweets;
    private List<Tweet> republicanTweets;

    private HashMap<String, Integer> democratWords;
    private HashMap<String, Integer> republicanWords;

    private double resubstitutionError = 0;
    private double tenCrossFold = 0;
    
    public NaiveBayesPolicitcal(List<Tweet> tweets) {
        this.tweets = tweets;

        this.democratTweets = new ArrayList<Tweet>();
        this.republicanTweets = new ArrayList<Tweet>();

        this.democratWords = new HashMap<String, Integer>();
        this.republicanWords = new HashMap<String, Integer>();
        this.count();
    }

    private void count() {
        
        for (Tweet tweet : tweets) {
            if (tweet.isDemocrat()) {
                this.democrat += 1;
                this.democratTweets.add(tweet);
            } else if (tweet.isRepbulicain()) {
                this.republican += 1;
                this.republicanTweets.add(tweet);
            }
        }
        
        this.size = this.tweets.size();
        this.pOfDemocrat = this.democrat / ((1.0) * this.size);
        this.pOfRepublican = this.republican / ((1.0) * this.size);

        for (Tweet tweet : democratTweets) {
            String[] words = tweet.getMessage().split(" ");
            for (String word : words) {
                if (democratWords.containsKey(word)) {
                    democratWords.put(word, 1 + democratWords.get(word));
                } else {
                    democratWords.put(word, 1);
                }
            }
        }
        
        for (Tweet tweet : republicanTweets) {
            String[] words = null;
            try {
                words = tweet.getMessage().split(" ");
            } catch (StackOverflowError ex){
                System.out.println();
            }
            
            for (String word : words) {
                if (republicanWords.containsKey(word)) {
                    republicanWords.put(word, 1 + republicanWords.get(word));
                } else {
                    republicanWords.put(word, 1);
                }
            }
        }
    }

    private double pWordGiven(String word, Party group) {
        switch (group) {
            case democrat:
                if (democratWords.containsKey(word)) {
                    return democratWords.get(word) / (1.0) * democrat;
                } else {
                    return 0;
                }
            case republican:
                if (republicanWords.containsKey(word)) {
                    return republicanWords.get(word) / (1.0) * republican;
                } else {
                    return 0;
                }
        }
        return 0;
    }

    public double pTweetGiven(Tweet tweet, Party group) {
        double value = 0;
        String[] words;
        switch (group) {
            case democrat:
                try{
                words = tweet.getMessage().split(" ");
                for (String word : words) {
                    if(pWordGiven(word, Party.democrat) > 0){
                        value += Math.log(pWordGiven(word, Party.democrat));
                    }
                }
                }catch (StackOverflowError ex){
                    
                }
                return value;
            case republican:
                try{
                words = tweet.getMessage().split(" ");
                for (String word : words) {
                    if(pWordGiven(word, Party.republican) > 0){
                        value += Math.log(pWordGiven(word, Party.republican));
                    }
                }
                }catch (StackOverflowError ex){
                    
                }
                return value;
        }
        return 0;
    }

    public double groupGivenText(Party group, Tweet tweet) {
        switch (group) {
            case democrat:
                return pTweetGiven(tweet, group) + Math.log(pOfDemocrat);
            case republican:
                return pTweetGiven(tweet, group) + Math.log(pOfRepublican);
        }
        return 0;
    }

    public Party classify(Tweet tweet) {
        Party classify = Party.neutral;
        double pOfDemocrats = groupGivenText(Party.democrat, tweet);
        double pOfRepublicans = groupGivenText(Party.republican, tweet);
        //double support = (pOfDemocrats * this.pOfDemocrat) + (pOfRepublicans * this.pOfRepublican);
        double support = 1;
        pOfDemocrats /= support;
        pOfRepublicans /= support;
        if (pOfDemocrats > pOfRepublicans) {
            classify = Party.democrat;
        } else {
            classify = Party.republican;
        }
        return classify;
    }

    private void error() {
        int count = 0;
        for (int i = 0; i < tweets.size(); i++) {
            if(tweets.get(i).getParty().equals(this.classify(tweets.get(i)))){
                count += 1;
            }
        }
        this.resubstitutionError = 1 - (count / ((1.0)*this.tweets.size()));
        
        int range = this.tweets.size()/10;
        int lowerbound = 0;
        int upperbound = range;
        List<Tweet> testing = new ArrayList<Tweet>();
        List<Tweet> training = new ArrayList<Tweet>();
        NaiveBayesPolicitcal bayesPolicitcal = null;
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
            bayesPolicitcal = new NaiveBayesPolicitcal(training);
            double cross = 0;
            for (int i = 0; i < testing.size(); i++) {
                if(bayesPolicitcal.classify(testing.get(i)) == testing.get(i).getParty()){
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
