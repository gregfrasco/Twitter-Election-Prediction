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
    
    private String filePath = "src/rawData/training.csv";
    private List<Tweet> tweets;

    public Data() {
        this.tweets = new ArrayList<Tweet>();
    }
    
    public void loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = "";
            while((line = br.readLine()) != null){
                line = line.replaceAll("^[^a-zA-Z0-9\\s]+|[^a-zA-Z0-9\\s]+$", "");
                line = line.replaceAll("\"", "");
                String[] data = line.split(",");
                Group group;
                if(data[0].equals("4")){
                    group = Group.positive;
                } else if(data[0].equals("2")){
                    group = Group.neutral;
                }else if(data[0].equals("0")){
                    group = Group.negitive;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
