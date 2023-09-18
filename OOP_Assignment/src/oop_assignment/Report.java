package oop_assignment;

import java.util.ArrayList;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class Report extends JFrame {
    ArrayList<Sales> salesList;
    private static final long serialVersionUID = 1L;

    public Report(ArrayList<Sales> salesData, String applicationTitle, String chartTitle) {
        super(applicationTitle);
        salesList = salesData;
        // This will create the dataset
        PieDataset dataset = createDataset();
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);

    }
    
    // Statistics report
    public void statistics() {
        // Variables for statistical report
        Sales[] bestSelling = new Sales[5];
        int highestQuantity;
        int totalItemsSold = 0;
        int[] highestIndex = {0, 0, 0, 0, 0};
        int tempIndex;
        boolean skip = false;
        
        // Calculate the total quantity sold for every item and find top 5 highest
        for (int i = 0; i < 5; ++i) {
            highestQuantity = -1;
            tempIndex = 0;
            for (Sales sale : salesList) {
                if (i == 0) {
                    totalItemsSold += sale.getSalesAmount();
                }
                else {
                    for (int j = 0; j < i; ++j) {
                        if (tempIndex == highestIndex[j]) {
                        skip = true;
                        }
                    }
                }
                if (skip) {
                    skip = false;
                    tempIndex++;
                    continue;
                }
                if (sale.getSalesAmount() > highestQuantity) {
                    highestQuantity = sale.getSalesAmount();
                    highestIndex[i] = tempIndex;
                }
                tempIndex++;
            }
            bestSelling[i] = salesList.get(highestIndex[i]);
        }
        
        //Printing the results
        System.out.println("Statistical Report on Best-Selling Items:");
        System.out.println("Total Number of Items Sold: " + totalItemsSold);
        System.out.println("The 5 best selling items are:");
        System.out.printf("\n|---------------|--------------------|-------------------|");
        System.out.printf("\n|(1) %11s| Quantity sold: %4d| Percentage: %5.2f%c|", bestSelling[0].getGroceryName(), bestSelling[0].getSalesAmount(), (100.0*bestSelling[0].getSalesAmount()/totalItemsSold), '%');
        System.out.printf("\n|---------------|--------------------|-------------------|");
        System.out.printf("\n|(2) %11s| Quantity sold: %4d| Percentage: %5.2f%c|", bestSelling[1].getGroceryName(), bestSelling[1].getSalesAmount(), (100.0*bestSelling[1].getSalesAmount()/totalItemsSold), '%');
        System.out.printf("\n|---------------|--------------------|-------------------|");
        System.out.printf("\n|(3) %11s| Quantity sold: %4d| Percentage: %5.2f%c|", bestSelling[2].getGroceryName(), bestSelling[2].getSalesAmount(), (100.0*bestSelling[2].getSalesAmount()/totalItemsSold), '%');
        System.out.printf("\n|---------------|--------------------|-------------------|");
        System.out.printf("\n|(4) %11s| Quantity sold: %4d| Percentage: %5.2f%c|", bestSelling[3].getGroceryName(), bestSelling[3].getSalesAmount(), (100.0*bestSelling[3].getSalesAmount()/totalItemsSold), '%');
        System.out.printf("\n|---------------|--------------------|-------------------|");
        System.out.printf("\n|(5) %11s| Quantity sold: %4d| Percentage: %5.2f%c|", bestSelling[4].getGroceryName(), bestSelling[4].getSalesAmount(), (100.0*bestSelling[4].getSalesAmount()/totalItemsSold), '%');
        System.out.printf("\n|---------------|--------------------|-------------------|\n");
    }
    
    //Creates a sample data set
    private PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        for (Sales sale : salesList){
            result.setValue(sale.getGroceryName(), sale.getSalesAmount());
        }
        return result;

    }
    
    //Creates a chart
    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart(
            title,                  // chart title
            dataset,                // data
            true,             // include legend
            true,
            false
        );

        return chart;

    }
}