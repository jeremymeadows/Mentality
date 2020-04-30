package Mentality.components;
import Mentality.utils.CronScheduler;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import java.awt.*;

public class Chart {

    ChartPanel chartPanel;

    double [] hapinesslevels = new double [7];

    // static variable single_instance of type Singleton
    private static Chart single_instance = null;

    // static method to create instance of Singleton class
    public static Chart getInstance()
    {
        if (single_instance == null)
            single_instance = new Chart();

        return single_instance;
    }

    public void setHappiness (double [] data){
        this.hapinesslevels = data;
        for (double i : hapinesslevels)
            System.out.print("HL:" +  i);
    }

    public Chart() {

        double data[] = new double[7];
        for (int i = 0; i < 7; i++)
            data[i] = 0;
    }
    public void run(){

        XYDataset ds = createDataset();
        JFreeChart chart = ChartFactory.createXYLineChart("User's Mood",
                "Days", "Happiness", ds, PlotOrientation.VERTICAL, true, true,
                false);

        chartPanel = new ChartPanel(chart);
        //adjust x axis interval to increment by one
        final XYPlot plot = chart.getXYPlot();
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(1));

        //set color
        chart.getPlot().setBackgroundPaint( Color.BLUE );
        chart.getPlot().setBackgroundAlpha( .2f);

    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    private XYDataset createDataset() {
        DefaultXYDataset ds = new DefaultXYDataset();
        double[][] data = { {1, 2, 3, 4, 5, 6, 7,}, hapinesslevels};
        ds.addSeries("Happiness Progression", data);
        return ds;
    }

}
