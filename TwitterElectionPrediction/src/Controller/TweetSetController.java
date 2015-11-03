/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DataSets;
import Model.Tweet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
}
