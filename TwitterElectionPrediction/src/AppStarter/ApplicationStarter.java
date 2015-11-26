/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppStarter;

import Views.MainView;
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
        JFrame jFrame = new JFrame("Political Twitter");
        jFrame.setLayout(new BorderLayout());
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        jFrame.add(new MainView(),BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setExtendedState( jFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH ); // maxiumizes window
        jFrame.setVisible(true);
    }
    
}
