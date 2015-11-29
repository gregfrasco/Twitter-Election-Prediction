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
public class SentimentGraph {
    
    private JFreeChart barChart;

    public SentimentGraph() {
        this.barChart = this.makeGraph();
    }
    
    public ChartPanel getGraph() {
        this.barChart = makeGraph();
        ChartPanel chartPanel = new ChartPanel(this.barChart);
        return chartPanel;
    }

    private JFreeChart makeGraph() {
        return ChartFactory.createBarChart("Tweets", "Sentiment", "Percent", createDataset(), PlotOrientation.VERTICAL,false, false, false);
        
    }
    
    private CategoryDataset createDataset() {
        final String pos = "Positive";
        final String neg = "Negative";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int negative = 0;
        int positive = 0;
        Classifier classifier = Classifier.getInstance();
        Politicians politicians = classifier.getPoliticains();
        for (Politicain politicain : politicians.getPoliticains()) {
            negative += politicain.getNegitiveDemocrat().size();
            negative += politicain.getNegitiveRepublicain().size();
            positive += politicain.getPositiveDemocrat().size();
            positive += politicain.getPositiveRepublicain().size();
        }
        double total = positive + negative;
        dataset.addValue(positive/total, "0", pos);
        dataset.addValue(negative/total, "0", neg);
        return dataset;
    }
    
}
