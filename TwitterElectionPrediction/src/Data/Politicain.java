/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Views.PoliticianView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author frascog
 */
public class Politicain {
    
    private final String name;
    private String picture;
    private List<Tweet> positiveDemocrat;
    private List<Tweet> positiveRepublicain;
    private List<Tweet> negitiveDemocrat;
    private List<Tweet> negitiveRepublicain;
    private final Party party;
    private PoliticianView view;
    private List<PoliticainListener> listeners;
    private String query;

    public Politicain(String name, String picture, Party party,String query) {
        this.name = name;
        this.picture = picture;
        this.party = party;
        this.listeners = new ArrayList<PoliticainListener>();
        this.positiveDemocrat = new ArrayList<Tweet>();
        this.negitiveDemocrat = new ArrayList<Tweet>();
        this.positiveRepublicain = new ArrayList<Tweet>();
        this.negitiveRepublicain = new ArrayList<Tweet>();
        this.query = query;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    protected List<Tweet> getPositiveDemocrat() {
        return positiveDemocrat;
    }

    protected List<Tweet> getPositiveRepublicain() {
        return positiveRepublicain;
    }

    protected List<Tweet> getNegitiveDemocrat() {
        return negitiveDemocrat;
    }

    protected List<Tweet> getNegitiveRepublicain() {
        return negitiveRepublicain;
    }

    public void addTweet(Sentiment sentiment,Party party,Tweet tweet){
        if(this.party.equals(Party.democrat)){
            if(party.equals(Party.democrat)){
                if(sentiment.equals(Sentiment.positive)){
                    this.negitiveDemocrat.add(tweet);
                } else {
                    this.positiveDemocrat.add(tweet);
                }
            } else {
                if(sentiment.equals(Sentiment.positive)){
                    this.positiveRepublicain.add(tweet);
                } else {
                    this.negitiveRepublicain.add(tweet);
                }
            }
        } else {
            if(party.equals(Party.democrat)){
                if(sentiment.equals(Sentiment.positive)){
                    this.positiveDemocrat.add(tweet);
                } else {
                    this.negitiveDemocrat.add(tweet);
                }
            } else {
                if(sentiment.equals(Sentiment.positive)){
                    this.negitiveRepublicain.add(tweet);
                } else {
                    this.positiveRepublicain.add(tweet);
                }
            }
        }
        this.fireUpdate();
    }
    
    public Party getParty() {
        return party;
    }

    public void setPicture(String picture) {
        this.picture = picture;
        fireUpdate();
    }
    
    public PoliticianView getView(){
        if(this.view == null){
            this.view = new PoliticianView(this);
        }
        regitiserListeners();
        return this.view;
    }
    
    private void fireUpdate(){
        for (PoliticainListener listner : this.listeners) {
            listner.politicainChangeResponce();
        }
        unregisterListeners();
    }
    
    private void regitiserListeners(){
        if(this.view != null){
            if(!this.listeners.contains(this.view)){
                this.listeners.add(this.view);
            }
        }
    }
    
    private void unregisterListeners(){
        if(this.view == null){
            if(this.listeners.contains(this.view)){
                this.listeners.remove(this.view);
            }
        }
    }

    public void addTweet(Tweet tweet) {
        this.addTweet(tweet.getSentiment(), tweet.getParty(), tweet);
    }

    public String getQuery() {
        return query;
    }
   
}
