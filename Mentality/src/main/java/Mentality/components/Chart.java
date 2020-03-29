package Mentality.components;
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

    public Chart(){
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

    private static XYDataset createDataset() {

        DefaultXYDataset ds = new DefaultXYDataset();
        //dummy data, will need to get User's daily happiness scores for the 7 day interval
        double[][] data = { {1, 2, 3, 4, 5, 6, 7,}, {8, 6, 7, 5, 3, 2, 4} };
        ds.addSeries("Happiness Progression", data);
        return ds;
    }

}
