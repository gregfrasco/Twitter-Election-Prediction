/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.UserList;

/**
 *
 * @author frascog
 */
public class GetPoliciticalTweets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        ResponseList<UserList> lists = twitter.getUserLists("tweetcongress");
        for (UserList list : lists) {
            if(list.getName().equals("Democrats")){
                ResponseList<Status> tweets = twitter.getUserListStatuses(list.getId(),new Paging(1,200));
                for (Status tweet : tweets) {
                    String line = tweet.getText();
                    line = line.replaceAll(",", "");
                    line = line.replaceAll("\n", "");
                    System.out.println("Democrats,"+line);
                }
            } else if(list.getName().equals("Republican")){
                ResponseList<Status> tweets = twitter.getUserListStatuses(list.getId(),new Paging(1,200));
                for (Status tweet : tweets) {
                    String line = tweet.getText();
                    line = line.replaceAll(",", "");
                    line = line.replaceAll("\n", "");
                    System.out.println("Republican,"+ line);
                }
            }
            
        }
    }
    
}
