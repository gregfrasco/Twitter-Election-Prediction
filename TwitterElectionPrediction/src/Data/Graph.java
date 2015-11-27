/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import org.jfree.chart.ChartFactory;
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
        this.barChart = ChartFactory.createBarChart("CAR USAGE STATIStICS", "Category", "Score", createDataset(),PlotOrientation.VERTICAL, true, true, false);
    }
    
    private CategoryDataset createDataset(){
      final String demPos = "D+";        
      final String demNeg = "D-";        
      final String repPos = "R+";        
      final String repNeg = "R-";              
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

//      dataset.addValue( 1.0 , fiat , speed );        
//      dataset.addValue( 3.0 , fiat , userrating );        
//      dataset.addValue( 5.0 , fiat , millage ); 
//      dataset.addValue( 5.0 , fiat , safety );           
//
//      dataset.addValue( 5.0 , audi , speed );        
//      dataset.addValue( 6.0 , audi , userrating );       
//      dataset.addValue( 10.0 , audi , millage );        
//      dataset.addValue( 4.0 , audi , safety );
//
//      dataset.addValue( 4.0 , ford , speed );        
//      dataset.addValue( 2.0 , ford , userrating );        
//      dataset.addValue( 3.0 , ford , millage );        
//      dataset.addValue( 6.0 , ford , safety );               

      return dataset; 
   }
}
