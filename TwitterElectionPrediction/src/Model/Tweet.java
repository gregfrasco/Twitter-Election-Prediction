/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.List;
import java.sql.Time;

/**
 *
 * @author frascog
 */
public class Tweet {

    private Time timestamp;
    private final String author;
    private final String message;
    private List hashtags;
    private boolean verfied = false;
    private int retweets = 0;
    private int favorites = 0;
    private Party party = Party.none;

    public Tweet(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public Tweet(Time timestamp, String author, String message) {
        this.timestamp = timestamp;
        this.author = author;
        this.message = message;
    }

    public Tweet(Time timestamp, String author, String message, boolean verfied) {
        this.timestamp = timestamp;
        this.author = author;
        this.message = message;
        this.verfied = verfied;
    }

    public Tweet(Time timestamp, String author, String message, boolean verfied, Party party) {
        this.timestamp = timestamp;
        this.author = author;
        this.message = message;
        this.verfied = verfied;
        this.party = party;
    }

    public Time getTimestamp() {
        return timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public List getHashtags() {
        return hashtags;
    }

    public boolean isVerfied() {
        return verfied;
    }

    public Party getParty() {
        return party;
    }

    public void setTimestamp(Time timestamp) {
        this.timestamp = timestamp;
    }

    public void setHashtags(List hashtags) {
        this.hashtags = hashtags;
    }

    public void setVerfied(boolean verfied) {
        this.verfied = verfied;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public int getRetweets() {
        return retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public static String getHeader() {
        return "author,message,verfied,retweets,favorites,party";
    }

    @Override
    public String toString() {
        return this.author+","+this.message+","+this.verfied+","+this.retweets+","+this.favorites+","+this.party;
    }
    
    
}
