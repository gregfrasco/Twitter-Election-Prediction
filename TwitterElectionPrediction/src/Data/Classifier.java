/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import PoliticalParty.NaiveBayesPolicitcal;
import SentimentAnalysis.NaiveBayesSentiment;

/**
 *
 * @author frascog
 */
public class Classifier {
    
    private static Classifier classifier = new Classifier();
    private Politicians politicains = new Politicians();
    private static NaiveBayesPolicitcal bayesPolicitcal;
    private static NaiveBayesSentiment bayesSentiment;
    private static TestingData testingData;
    
    private Classifier() {
        Data data = new Data();
        bayesPolicitcal = new NaiveBayesPolicitcal(data.getPartyTweets());
        bayesSentiment = new NaiveBayesSentiment(data.getSentimentTweets());
        testingData = new TestingData();
        //testingData.start();
    }
    
    public Tweet classify(String message){
        Tweet tweet = new Tweet(message);
        tweet.setParty(Classifier.bayesPolicitcal.classify(tweet));
        tweet.setSentiment(Classifier.bayesSentiment.classify(tweet));
        return tweet;
    }
    
    public static Classifier getInstance(){
        if(classifier == null){
            classifier = new Classifier();
        }
        return classifier;
    }

    public Politicians getPoliticains() {
        return politicains;
    }
    
    public void classify(){
        this.politicains.loadData();
    }

    public NaiveBayesPolicitcal getBayesPolicitcal() {
        return bayesPolicitcal;
    }

    public NaiveBayesSentiment getBayesSentiment() {
        return bayesSentiment;
    }
    
}
