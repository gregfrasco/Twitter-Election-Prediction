/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Data.Data;
import Data.Tweet;
import PoliticalParty.NaiveBayesPolicitcal;
import SentimentAnalysis.NaiveBayes;
import java.util.List;
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
public class PoliciticalTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Data data = new Data();
        NaiveBayesPolicitcal bayes = new NaiveBayesPolicitcal(data.getPartyTweets());
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Query query = new Query("Trump");
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println(bayes.classify(new Tweet(tweet)) + "\t" +tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
    
}
