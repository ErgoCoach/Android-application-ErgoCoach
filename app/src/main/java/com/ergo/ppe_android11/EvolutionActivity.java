package com.ergo.ppe_android11;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class EvolutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evolution);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GraphView graph;
        graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 10),
                new DataPoint(10, 50),
                new DataPoint(20, 30),
                new DataPoint(30, 20),
                new DataPoint(40, 60)
        });
        series.setTitle("Posture");
        graph.addSeries(series);

        // second series
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 30),
                new DataPoint(10, 30),
                new DataPoint(20, 60),
                new DataPoint(30, 20),
        });
        series2.setTitle("Correction");
        series2.setDrawBackground(true);
        series2.setColor(Color.argb(255, 255, 60, 60));
        series2.setBackgroundColor(Color.argb(50, 250, 250, 250));
        series2.setDrawDataPoints(true);
        graph.addSeries(series2);
        graph.setTitle("EVOLUTION DU MOIS");

        // legend
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }

}
