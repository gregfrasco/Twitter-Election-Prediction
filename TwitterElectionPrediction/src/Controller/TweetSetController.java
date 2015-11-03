/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DataSets;
import Model.Tweet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author frascog
 */
public class TweetSetController {
    
    private List<Tweet> tweets;
    private final DataSets dataset;

    public TweetSetController(DataSets dataSet) {
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

    public DataSets getDataset() {
        return dataset;
    }
    
}
