package com.example.wastecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultsGraph extends AppCompatActivity {

    private TextView returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_graph);

        String[] CO2entry = ResultsLog.resultslist.getCO2entry();
        String[] clean;

        /* Set graph values */
        ArrayList<BarEntry> entries = new ArrayList<>();
        int i;
        for (i = 0; i < CO2entry.length; i++) {
            clean = CO2entry[i].split("k");
            entries.add(new BarEntry(i, Float.parseFloat(clean[0])));
        }

        BarChart barChart = findViewById(R.id.barChart);
        BarDataSet barDataSet = new BarDataSet(entries, "CO2 kg/year");
        barDataSet.setValueTextSize(8f);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("CO2 emissions over time");

        returnButton = findViewById(R.id.textViewReturnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultsGraph.this, ResultsLog.class));
            }
        });
    }
}