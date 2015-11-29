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
public class PoliticianGraph {
    
    private JFreeChart barChart;

    public PoliticianGraph() {
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
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Classifier classifier = Classifier.getInstance();
        Politicians politicians = classifier.getPoliticains();
        int count = 0; 
        for (Politicain politicain : politicians.getPoliticains()) {
            double value = politicain.getNegitiveDemocrat().size() + politicain.getNegitiveRepublicain().size() + politicain.getPositiveDemocrat().size() + politicain.getPositiveRepublicain().size();
            dataset.addValue(value,count + "",politicain.getName());
        }
        return dataset;
    }
    
}
