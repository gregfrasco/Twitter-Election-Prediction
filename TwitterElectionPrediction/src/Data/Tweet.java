/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author frascog
 */
public class Tweet{
    
    private String author;
    private String message;
    private Group group;
    private Party party;

    public Tweet(String tweet) {
      this.message = tweet;
    }

    public Tweet(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public Tweet(String message, Group group) {
        this.message = message;
        this.group = group;
    }

    public Tweet(String author, String message, Group group) {
        this.author = author;
        this.message = message;
        this.group = group;
    } 

    public boolean isPositive() {
        return group.equals(Group.positive);
    }
    
    public boolean isNegitive() {
        return group.equals(Group.negitive);
    }
    
    public boolean isNeutral() {
        return group.equals(Group.neutral);
    }

    public String getMessage() {
        return message;
    }

    public Group getGroup() {
        return group;
    }
    
}
