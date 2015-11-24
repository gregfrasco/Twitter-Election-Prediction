/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Party;
import Model.Tweet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frascog
 */
public class TweetSetController {
    
    private List<Tweet> tweets;
    private final String dataset;
    private HashMap<String,Integer> bagOfWords;
    private HashMap<String,Party> wordParty;

    public TweetSetController(String dataSet) {
        this.dataset = dataSet;
        this.tweets = new ArrayList<Tweet>();
    }
    
    public boolean add(Tweet tweet){
        return this.tweets.add(tweet);
    }
    
    public boolean remove(Tweet tweet){
        return this.tweets.remove(tweet);
    }
    
    public boolean contains(Tweet tweet){
        return this.tweets.contains(tweet);
    }
    
    public Tweet get(int index){
        return this.tweets.get(index);
    }
    
    public int size(){
        return this.tweets.size();
    }

    public String getDataset() {
        return dataset;
    }

    public void save(File file) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println(Tweet.getHeader());
            for (Tweet tweet : tweets) {
                writer.println(tweet);
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TweetSetController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }

    public HashMap<String,Integer> getBagOfWords() {
        if(bagOfWords == null){
            this.bagOfWords = new HashMap<String,Integer>();
            this.wordParty = new HashMap<String,Party>();
            this.createBagOfWords(bagOfWords);
        }
        return bagOfWords;
    }
    
    public void demRebClassify() {
        for (Tweet tweet : tweets) {
            String[] words = tweet.getMessage().split(" ");
            for (String word : words) {
                int value = this.getBagOfWords().get(word);
                if(this.wordParty.get(word) != Party.remove){
                    if(this.wordParty.get(word) == Party.democrat){
                        tweet.setDemocrat(tweet.getDemocrat()+value);
                    } else {
                        tweet.setRebpublican(tweet.getRebpublican()+value);
                    }
                }
            }
        }
    }

    private void createBagOfWords(HashMap<String,Integer> bagOfWords) {
        for (Tweet tweet : tweets) {
            String[] words = tweet.getMessage().split(" ");
            for (String word : words) {
                if(bagOfWords.containsKey(word)) {
                    int count = bagOfWords.get(word);
                    bagOfWords.put(word,count+1);
                    wordParty.put(word, tweet.getParty());
                } else {
                    bagOfWords.put(word, 1);
                    wordParty.put(word, tweet.getParty());
                }
            }
        }
    }

    public void removeTweets() {
        List<Tweet> ts = new ArrayList<Tweet>();
        for (Tweet tweet : tweets) {
            if(tweet.getParty() != Party.remove){
                ts.add(tweet);
            }
        }
        tweets = ts;
    }
}
