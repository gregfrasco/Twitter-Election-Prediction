/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Party;
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
    
    public HashMap<String,File> dataSets;

    public DataController() {
        this.dataSets = new HashMap<String,File>();
        File file = new File("src/rawData/presdential2008.csv"); 
        this.dataSets.put("presidential2008", file);
    }
    
    public TweetSetController getData(String dataSet) throws FileNotFoundException{
        File file = this.dataSets.get(dataSet);
        BufferedReader br = new BufferedReader(new FileReader(file));
        TweetSetController tweets = new TweetSetController(dataSet);
        try {
            String line = br.readLine();
            String[] headers = line.split(",");
            while ((line = br.readLine()) != null) {
                String author = null, message = null,party=null;
                String[]lines = line.split(",");
                for (int i = 0; i < headers.length; i++) {
                    String header = headers[i].toLowerCase();
                    switch(header){
                        case "author":
                            author = lines[i];
                            break;
                        case "message":
                            message = lines[i];
                            break;
                        case "party":
                            party = lines[i];
                    }
                }
                Tweet tweet = new Tweet(author, message);
                tweet.setParty(Party.valueOf(party));
                tweets.add(tweet);
            }
        } catch (IOException ex) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tweets;
    }

    public void openData(String dataName,File selectedFile) {
        this.dataSets.put(dataName, selectedFile);
    }

    public File getFile(String currentDataSet) {
        return this.dataSets.get(currentDataSet);
    }
}
