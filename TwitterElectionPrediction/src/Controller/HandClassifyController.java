/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Party;
import Model.Tweet;
import Views.HandClassifyView;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author frascog
 */
public class HandClassifyController {

    private int index = 0;
    private TweetSetController dataSet;
    private HandClassifyView view;

    public HandClassifyController(TweetSetController dataSet) {
        this.dataSet = dataSet;
    }
    
    public int getSize() {
        return this.dataSet.size();
    }

    public Tweet getNextTweet() {
        while(this.dataSet.get(index).getParty() != Party.none){
            this.index = this.index+1;
        }
        return this.dataSet.get(index);
    }

    public JPanel getView() {
        if(this.view == null){
            this.view = new HandClassifyView(this);
        }
        return view;
    }

    public Tweet getLastTweet() {
        this.index = index-1;
        return this.dataSet.get(index);
    }

    public int getIndex() {
        return this.index;
    }

    public void save() {
        String filePath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath().concat("/twitterData.csv");
        this.dataSet.save(new File(filePath));
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Tweet get(int index) {
        return this.dataSet.get(index);
    }

    public void remove(int index) {
        this.dataSet.remove(this.get(index));
    }
    
}
