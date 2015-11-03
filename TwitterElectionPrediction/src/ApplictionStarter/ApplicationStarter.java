/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplictionStarter;

import Controller.MainController;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author frascog
 */
public class ApplicationStarter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame topwindow = new JFrame("Twitter Election Analysis");
        topwindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });        
        MainController controller = new MainController();
        topwindow.setLayout(new BorderLayout());
        topwindow.add(controller.getMainView());
        topwindow.pack();
        topwindow.setExtendedState( topwindow.getExtendedState()|JFrame.MAXIMIZED_BOTH ); // maxiumizes window
        topwindow.setVisible(true);
    }
    
}
