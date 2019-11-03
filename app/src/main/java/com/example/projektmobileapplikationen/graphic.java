package com.example.projektmobileapplikationen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

public class graphic extends AppCompatActivity {

    private DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//////////////////////////////////////////////////////////////////////////////////////////
        db = new DBHandler(this);

        List <Reise> resultate = db.getAllTrips();


        //Höhe des Graphen setzen
        int ylabelmax = 0;
        for (int i = 0; i< resultate.size();i++){
            if(Integer.parseInt(resultate.get(i).getAuszahlung())>ylabelmax){
                ylabelmax=Integer.parseInt(resultate.get(i).getAuszahlung());
                }
        }
        ylabelmax = (int)(ylabelmax*1.2);
        //Breite des Graphen setzen
        int xlabelmax = resultate.size()+1;
//////////////////////////////////////////////////////////////////////////////////////////


        CategorySeries series = new CategorySeries("Demo Bar Chart Graph...");
        for (int i = 0; i < resultate.size(); i++) {
            series.add(Double.parseDouble(resultate.get(i).getAuszahlung()));
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series.toXYSeries());

        //hier Balken Änderungen vornehmen.
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing((float) 0.5);
        renderer.setColor(Color.BLACK);
        renderer.setChartValuesTextSize(50);

       //ab hier den Graphen Änderungen vornehmen
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);

        mRenderer.setChartTitleTextSize(100);
        mRenderer.setShowLegend(false);
        mRenderer.setChartTitle("Reisekosten");
        mRenderer.setXTitle("Reise");
        mRenderer.setAxisTitleTextSize(50);
        mRenderer.setYTitle("Betrag in €");
        //mRenderer.setShowAxes(false);
        mRenderer.setXAxisMin(0);
        mRenderer.setXAxisMax((double) xlabelmax);
        mRenderer.setYAxisMin(0);
        mRenderer.setYAxisMax((double) ylabelmax);
        mRenderer.setAxesColor(Color.BLACK);
        //mRenderer.setYAxisColor(Color.BLACK);
        mRenderer.setLabelsColor(Color.BLACK);
        mRenderer.setXLabels(5);
        mRenderer.setYLabels(10);
        mRenderer.setLabelsTextSize(30);
       // mRenderer.setXLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setMarginsColor(Color.WHITE);
        mRenderer.setPanEnabled(false);
        //mRenderer.setMargins(new int[] {20, 30, 15, 0});
        //mRenderer.setZoomButtonsVisible(true);
        //mRenderer.setPanLimits(new double[]{-10, 20, -10, 40});
        //mRenderer.setZoomLimits(new double[]{-10, 20, -10, 40});


        GraphicalView chartview = ChartFactory.getBarChartView(getApplicationContext(), dataset, mRenderer, BarChart.Type.STACKED);
        setContentView(chartview);

    }
}
