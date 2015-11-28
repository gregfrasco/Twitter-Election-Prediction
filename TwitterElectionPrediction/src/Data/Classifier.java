/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import PoliticalParty.NaiveBayesPolicitcal;
import SentimentAnalysis.NaiveBayesSentiment;
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
public class Classifier implements Runnable{
    
    private Politicians politicains;
    private NaiveBayesPolicitcal bayesPolicitcal;
    private NaiveBayesSentiment bayesSentiment;
    private Thread thread;
    private String filePath = "src/rawData/testing.csv";

    public Classifier(Politicians politicains) {
        this.politicains = politicains;
        Data data = new Data();
        bayesPolicitcal = new NaiveBayesPolicitcal(data.getPartyTweets());
        bayesSentiment = new NaiveBayesSentiment(data.getSentimentTweets());
    }
    
    private void getTweets(Politicain politicain){
    Twitter twitter = new TwitterFactory().getInstance();
        try {
            Query query = new Query(politicain.getQuery());
            QueryResult result;
            int count = 0;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath,true));
                    String message = tweet.getText();
                    message = message.replaceAll(",", "").replaceAll("\n", "");
                    bw.append(politicain.getName()+","+message);
                    bw.newLine();
                    bw.flush();
                    count +=1;
                }
            } while (count <= 100);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addTweet(Politicain politicain,Sentiment sentiment, Party party, Tweet tweet) {
        tweet.setParty(party);
        tweet.setSentiment(sentiment);
        politicain.addTweet(tweet);
    }

    @Override
    public void run() {
        for (Politicain politicain : this.politicains.getPoliticains()) {
            this.getTweets(politicain);
        }
    }
    
    public Tweet classify(String message){
        
    }
    
    public void start(){
        this.thread = new Thread(this, "Classifier");
        this.thread.run();
    }
}
