/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author frascog
 */
public class Graph {
    
    private Politicain politicain;
    private JFreeChart barChart;

    public Graph(Politicain politicain) {
        this.politicain = politicain;
        this.barChart = ChartFactory.createBarChart("CAR USAGE STATIStICS", "Category", "Score", dataset,PlotOrientation.VERTICAL, true, true, false);
    }
}
