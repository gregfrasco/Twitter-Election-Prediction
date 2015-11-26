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
public class Tweet{
    
    private String author;
    private String message;
    private Group group;
    private Party party;

    public Tweet(String tweet) {
      this.message = tweet;
    }

    public Tweet(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public Tweet(String message, Group group) {
        this.message = message;
        this.group = group;
    }

    public Tweet(String author, String message, Group group) {
        this.author = author;
        this.message = message;
        this.group = group;
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

    public Tweet(String author, String message, Group group, Party party) {
        this.author = author;
        this.message = message;
        this.group = group;
        this.party = party;
    }
    
    public Tweet(Status tweet) {
        this.author = tweet.getUser().getName();
        this.message = tweet.getText();
    }

    public boolean isPositive() {
        return group.equals(Group.positive);
    }
    
    public boolean isNegitive() {
        return group.equals(Group.negitive);
    }
    
    public boolean isNeutral() {
        return group.equals(Group.neutral);
    }

    public String getMessage() {
        return message;
    }

    public Group getGroup() {
        return group;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setGroup(Group group) {
        this.group = group;
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
    
}
