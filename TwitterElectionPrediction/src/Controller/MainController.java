/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Views.MainView;

/**
 *
 * @author frascog
 */
public class MainController {

    private MainView mainView;
    private DataController dataController;

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

}
