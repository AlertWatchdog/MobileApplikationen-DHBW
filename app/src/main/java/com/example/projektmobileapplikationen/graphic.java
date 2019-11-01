package com.example.projektmobileapplikationen;

import androidx.appcompat.app.AppCompatActivity;

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

public class graphic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int xlabelmax = 10;
        int ylabelmax = 10;

        int[] y = {1, 33, 53, 45};

        CategorySeries series = new CategorySeries("Demo Bar Chart Graph...");
        for(int i =0; i<y.length;i++){
            series.add("Bar " + (i+1), y[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series.toXYSeries());

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);

        setChartSettings(mRenderer, "Reisekosten", "Reise: ", "Betrag: ", 0, (double)xlabelmax, 0, (double)ylabelmax, Color.LTGRAY, Color.LTGRAY);

        mRenderer.setXLabels(5);
        mRenderer.setYLabels(10);
        mRenderer.setLabelsTextSize(50);
        mRenderer.setXLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setMarginsColor(Color.WHITE);
        mRenderer.setPanEnabled(false);
        //mRenderer.setZoomButtonsVisible(true);
        mRenderer.setPanLimits(new double[] { -10, 20, -10, 40 });
        mRenderer.setZoomLimits(new double[] { -10, 20, -10, 40 });


        GraphicalView chartview = ChartFactory.getBarChartView(getApplicationContext(), dataset, mRenderer, BarChart.Type.DEFAULT);
        setContentView(chartview);

    }
    private void setChartSettings(XYMultipleSeriesRenderer mRenderer,
                                  String title, String xTitle, String yTitle, double xMin,
                                  double xMax, double yMin, double yMax, int axesColor,
                                  int labelsColor) {
        mRenderer.setChartTitle(title);
        mRenderer.setXTitle(xTitle);
        mRenderer.setYTitle(yTitle);
        mRenderer.setXAxisMin(xMin);
        mRenderer.setXAxisMax(xMax);
        mRenderer.setYAxisMin(yMin);
        mRenderer.setYAxisMax(yMax);
        mRenderer.setAxesColor(axesColor);
        mRenderer.setLabelsColor(labelsColor);
    }





}
