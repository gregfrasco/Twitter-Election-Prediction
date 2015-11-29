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
public class PoliticalGraph {
    
    private JFreeChart barChart;

    public PoliticalGraph() {
        this.barChart = this.makeGraph();
    }
    
    public ChartPanel getGraph() {
        this.barChart = makeGraph();
        ChartPanel chartPanel = new ChartPanel(this.barChart);
        return chartPanel;
    }

    private JFreeChart makeGraph() {
        return ChartFactory.createBarChart("Tweets", "Partisan", "Percent", createDataset(), PlotOrientation.VERTICAL,false, false, false);
        
    }
    
    private CategoryDataset createDataset() {
        final String pos = "Democrat";
        final String neg = "Republicain";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int republicain = 0;
        int democrat = 0;
        Classifier classifier = Classifier.getInstance();
        Politicians politicians = classifier.getPoliticains();
        for (Politicain politicain : politicians.getPoliticains()) {
            democrat += politicain.getNegitiveDemocrat().size();
            republicain += politicain.getNegitiveRepublicain().size();
            democrat += politicain.getPositiveDemocrat().size();
            republicain += politicain.getPositiveRepublicain().size();
        }
        double total = republicain + democrat;
        dataset.addValue(republicain/total, "0", neg);
        dataset.addValue(democrat/total, "1", pos);
        return dataset;
    }
    
}
