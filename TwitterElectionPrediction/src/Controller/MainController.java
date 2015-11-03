/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DataSets;
import Views.MainView;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author frascog
 */
public class MainController {

    private MainView mainView;
    private DataSets currentDataSet;
    private DataController dataController;
    private HandClassifyController handClassifyController;

    public MainController() {
        this.dataController = new DataController();
    }
    
    public MainView getMainView() {
        if(this.mainView == null){
            this.mainView = new MainView(this);
        }
        return this.mainView;
    }

    public DataController getDataController() {
        return dataController;
    }

    public void openHandClassifier() {
        if(handClassifyController == null){
            try {
                this.handClassifyController = new HandClassifyController(this.dataController.getData(currentDataSet));
            } catch (FileNotFoundException ex) {
                System.out.println("NO DATA FOUND");
            }
        }
        JFrame frame = new JFrame("Hand Classifer");
        frame.setLayout(new BorderLayout());
        frame.add(this.handClassifyController.getView());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public DataSets getCurrentDataSet() {
        return currentDataSet;
    }

    public void setCurrentDataSet(DataSets currentDataSet) {
        this.currentDataSet = currentDataSet;
    }

    public void openFile(File selectedFile) {
        
    }

}
