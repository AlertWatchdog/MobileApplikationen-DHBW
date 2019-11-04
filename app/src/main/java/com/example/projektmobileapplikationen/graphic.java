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
        ylabelmax = (int)(ylabelmax*1.1);
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
        renderer.setAnnotationsColor(Color.BLACK);

       //ab hier den Graphen Änderungen vornehmen
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);

        //mRenderer.setChartTitleTextSize(100);
        mRenderer.setShowLegend(false);
        //mRenderer.setChartTitle("Reisekosten");
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
        mRenderer.setShowAxes(false);
        mRenderer.setLabelsTextSize(0);
       // mRenderer.setXLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setMarginsColor(Color.LTGRAY);
        mRenderer.setPanEnabled(false);
        mRenderer.setBarSpacing(0.1);
        mRenderer.setMargins(new int[] {0, 65, 10, 65});
        //mRenderer.setZoomButtonsVisible(true);
        //mRenderer.setPanLimits(new double[]{-10, 20, -10, 40});
        //mRenderer.setZoomLimits(new double[]{-10, 20, -10, 40});


        GraphicalView chartview = ChartFactory.getBarChartView(getApplicationContext(), dataset, mRenderer, BarChart.Type.STACKED);
        setContentView(chartview);

    }
}
