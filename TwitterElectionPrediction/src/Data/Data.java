/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frascog
 */
public class Data {
    
    private String filePathSentiment = "src/rawData/training.csv";
    private List<Tweet> sentimentTweets;
    private String filePathParty = "src/rawData/PartyClassification.csv";
    private List<Tweet> partyTweets;

    public Data() {
        this.partyTweets = new ArrayList<Tweet>();
        this.sentimentTweets = new ArrayList<Tweet>();
        this.loadDataParty(filePathParty);
        this.loadDataSentiment(filePathSentiment);
    }
    
    private void loadDataSentiment(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = "";
            while((line = br.readLine()) != null){
                line = line.replaceAll("^[^a-zA-Z0-9\\s]+|[^a-zA-Z0-9\\s]+$", "");
                line = line.replaceAll("\"", "");
                String[] data = line.split(",");
                Sentiment group = null;
                if(data[0].equals("4")){
                    group = Sentiment.positive;
                } else if(data[0].equals("2")){
                    group = Sentiment.neutral;
                }else if(data[0].equals("0")){
                    group = Sentiment.negitive;
                }
                data = shortenExtraText(data);
                sentimentTweets.add(new Tweet(data[5], group));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String[] shortenExtraText(String[] data){
        if(data.length > 6){
            data[5] = data[5]+data[6];
            String[] newData = new String[data.length-1];
            for (int i = 0; i < data.length-1; i++) {
                newData[i] = data[i];
            }
            data = newData;
            data = shortenExtraText(data);
        }
        return data;
    }

    private void loadDataParty(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = "";
            while((line = br.readLine()) != null){
                line = line.replaceAll("^[^a-zA-Z0-9\\s]+|[^a-zA-Z0-9\\s]+$", "");
                line = line.replaceAll("\"", "");
                String[] data = line.split(",");
                Party group = null;
                if(data[0].equals("Democrats")){
                    group = Party.democrat;
                } else if(data[0].equals("Republican")){
                    group = Party.republican;
                }
                if(group != null){
                    this.partyTweets.add(new Tweet(data[1], group));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ArrayIndexOutOfBoundsException ex){
            
        }
    }
    
    public List<Tweet> getSentimentTweets() {
        return this.sentimentTweets;
    }
    
    public List<Tweet> getPartyTweets() {
        return this.partyTweets;
    }
}
