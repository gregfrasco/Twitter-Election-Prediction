/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author frascog
 */
public class Graph {

    private Politicain politicain;
    private JFreeChart barChart;

    public Graph(Politicain politicain) {
        this.politicain = politicain;
        this.barChart = this.makeGraph();
    }

    private CategoryDataset createDataset() {
        final String demPos = "D+";
        final String demNeg = "D-";
        final String repPos = "R+";
        final String repNeg = "R-";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(this.politicain.getPositiveRepublicain().size(), "1", repPos);
        dataset.addValue(this.politicain.getNegitiveRepublicain().size(), "1", repNeg);
        dataset.addValue(this.politicain.getPositiveDemocrat().size(), "0", demPos);
        dataset.addValue(this.politicain.getNegitiveDemocrat().size(), "0", demNeg);
        return dataset;
    }

    public ChartPanel getGraph() {
        this.barChart = makeGraph();
        ChartPanel chartPanel = new ChartPanel(this.barChart);
        return chartPanel;
    }

    private JFreeChart makeGraph() {
        return ChartFactory.createBarChart("Political View", "Party", "Tweets", createDataset(), PlotOrientation.VERTICAL,false, false, false);
    }

    public void update() {
        this.barChart = makeGraph();
    }
}
