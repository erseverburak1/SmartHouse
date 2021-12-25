import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import java.io.IOException;

public class SummerGraphics extends ApplicationFrame {
    Operations ops = new Operations();
    FileIO f = new FileIO();
    double[] houseHourlyConsumption;
    Appliance[] appliances;

    public SummerGraphics(final String title) throws IOException {

        super(title);
        final XYSeries series1 = new XYSeries("Load");

        appliances = f.appliancesReader("summerDay.csv");
        houseHourlyConsumption = ops.houseHourlyConsumptionFinder(appliances);

        for(int i =0;i<24;i++){
            series1.add(i, houseHourlyConsumption[i]);
        }
        series1.add(24, houseHourlyConsumption[0]);

        final XYSeriesCollection data = new XYSeriesCollection(series1);

        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Summer Daily Base Loading of the Smart House",
                "Hours",
                "kw",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        NumberAxis xAxis = new NumberAxis();
        xAxis.setTickUnit(new NumberTickUnit(1));
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(650, 350));
        setContentPane(chartPanel);
    }
}