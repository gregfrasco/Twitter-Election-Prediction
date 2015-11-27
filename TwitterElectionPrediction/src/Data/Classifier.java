/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import PoliticalParty.NaiveBayesPolicitcal;
import SentimentAnalysis.NaiveBayesSentiment;
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
public class Classifier implements Runnable{
    
    private Politicain politicain;
    private String query;
    private NaiveBayesPolicitcal bayesPolicitcal;
    private NaiveBayesSentiment bayesSentiment;

    public Classifier(Politicain politicain,String query) {
        this.politicain = politicain;
        this.query = query;
        Data data = new Data();
        bayesPolicitcal = new NaiveBayesPolicitcal(data.getPartyTweets());
        bayesSentiment = new NaiveBayesSentiment(data.getSentimentTweets());
        //getTweets();
    }
    
    private void getTweets(){
    Twitter twitter = new TwitterFactory().getInstance();
        try {
            Query query = new Query(this.query);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    this.addTweet(this.bayesSentiment.classify(new Tweet(tweet)),this.bayesPolicitcal.classify(new Tweet(tweet)),new Tweet(tweet));
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }

    private void addTweet(Sentiment sentiment, Party party, Tweet tweet) {
        tweet.setParty(party);
        tweet.setSentiment(sentiment);
        this.politicain.addTweet(tweet);
    }

    @Override
    public void run() {
        getTweets();
    }
    
    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
}
