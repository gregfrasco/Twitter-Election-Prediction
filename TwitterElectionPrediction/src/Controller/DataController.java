/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DataSets;
import Model.Tweet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frascog
 */
public class DataController {
    
    private HashMap<DataSets,File> dataSets;

    public DataController() {
        this.dataSets = new HashMap<DataSets,File>();
        File file = new File("src/rawData/presdential2008.csv"); 
        this.dataSets.put(DataSets.presidential2008, file);
    }
    
    public TweetSetController getData(DataSets dataSet) throws FileNotFoundException{
        File file = this.dataSets.get(dataSet);
        BufferedReader br = new BufferedReader(new FileReader(file));
        TweetSetController tweets = new TweetSetController(dataSet);
        try {
            String line = br.readLine();
            String[] headers = line.split(",");
            while ((line = br.readLine()) != null) {
                String author = null, message = null;
                String[]lines = line.split(",");
                for (int i = 0; i < headers.length; i++) {
                    String header = headers[i].toLowerCase();
                    switch(header){
                        case "author":
                            author = lines[i];
                            break;
                        case "message":
                            message = lines[i];
                    }
                }
                Tweet tweet = new Tweet(author, message);
                tweets.add(tweet);
            }
        } catch (IOException ex) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tweets;
    }
}
