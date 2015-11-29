/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author frascog
 */
public class TestingData implements Runnable{
    
    private String filePath = "src/rawData/testing.csv";

    private void getTweets(Politicain politicain) {
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Query query = new Query(politicain.getQuery());
            QueryResult result;
            int count = 0;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath, true));
                    String message = tweet.getText();
                    message = message.replaceAll(",", "").replaceAll("\n", "");
                    bw.append(politicain.getName() + "," + message);
                    bw.newLine();
                    bw.flush();
                    count += 1;
                }
            } while (count <= 100);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Politicians politicians = new Politicians();
        for (Politicain politicain : politicians.getPoliticains()) {
            this.getTweets(politicain);
        }
    }

    public void start() {
        this.run();
    }
}
