/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Data.Data;
import SentimentAnalysis.NaiveBayes;

/**
 *
 * @author frascog
 */
public class NaiveBayesTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Data data = new Data();
        data.loadData();
        NaiveBayes naiveBayes = new NaiveBayes(data.getTweets());
    }
    
}
