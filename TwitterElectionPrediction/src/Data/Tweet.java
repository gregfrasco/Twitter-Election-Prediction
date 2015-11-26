/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import twitter4j.Status;

/**
 *
 * @author frascog
 */
public class Tweet {
    
    private String message;
    private String author;
    private Sentiment sentiment;
    private Party party;
    private Party classification;

    public Tweet(String tweet) {
      this.message = tweet;
    }

    public Tweet(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public Tweet(String message, Sentiment group) {
        this.message = message;
        this.sentiment = group;
    }

    public Tweet(String author, String message, Sentiment group) {
        this.author = author;
        this.message = message;
        this.sentiment = group;
    } 

    public Tweet(String message, Party party) {
        this.message = message;
        this.party = party;
    }

    public Tweet(String author, String message, Party party) {
        this.author = author;
        this.message = message;
        this.party = party;
    }

    public Tweet(String author, String message, Sentiment group, Party party) {
        this.author = author;
        this.message = message;
        this.sentiment = group;
        this.party = party;
    }
    
    public Tweet(Status tweet) {
        this.author = tweet.getUser().getName();
        this.message = tweet.getText();
    }

    public boolean isPositive() {
        return sentiment.equals(Sentiment.positive);
    }
    
    public boolean isNegitive() {
        return sentiment.equals(Sentiment.negitive);
    }
    
    public boolean isNeutral() {
        return sentiment.equals(Sentiment.neutral);
    }

    public String getMessage() {
        return message;
    }

    public Sentiment getSentiment() {
        return sentiment;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSentiment(Sentiment sentiment) {
        this.sentiment = sentiment;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public boolean isDemocrat() {
        return this.party == Party.democrat;
    }
    
    public boolean isRepbulicain() {
        return this.party == Party.republican;
    }
    
    public Party getParty(){
        return this.party;
    }

    public String getAuthor() {
        return author;
    }

    public Party getClassification() {
        this.checkClassification();
        return classification;
    }
    
    private Party checkClassification(){
        if(this.isDemocrat() && this.isPositive()){
            return this.classification = Party.democrat;
        } else if(this.isDemocrat() && this.isNegitive()){
            return this.classification = Party.republican;
        } else if(this.isRepbulicain() && this.isPositive()){
            return this.classification = Party.republican;
        } else if(this.isRepbulicain() && this.isNegitive()){
            return this.classification = Party.democrat;
        }
        return party;
    }

}
