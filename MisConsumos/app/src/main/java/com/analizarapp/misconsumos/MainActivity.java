package com.analizarapp.misconsumos;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.analizarapp.misconsumos.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BarChart barChart1 = findViewById(R.id.barChart1);
        BarChart barChart2 = findViewById(R.id.barChart2);
        BarChart barChart3 = findViewById(R.id.barChart3);

        setupBarChart(barChart1, "", Color.YELLOW);
        setupBarChart(barChart2, "", Color.BLUE);
        setupBarChart(barChart3, "", Color.GREEN);

        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();
        List<BarEntry> entries3 = new ArrayList<>();

        // CARGA DE DATOS
        entries1.add(new BarEntry(1f, 10f));
        entries1.add(new BarEntry(2f, 60f));
        entries1.add(new BarEntry(3f, 98f));
        entries1.add(new BarEntry(4f, 105f));
        entries1.add(new BarEntry(5f, 148f));
        entries1.add(new BarEntry(6f, 105f));
        entries1.add(new BarEntry(7f, 84f));
        entries1.add(new BarEntry(8f, 74f));

        entries2.add(new BarEntry(1f, 148f));
        entries2.add(new BarEntry(2f, 40f));
        entries2.add(new BarEntry(3f, 40f));
        entries2.add(new BarEntry(4f, 79f));
        entries2.add(new BarEntry(5f, 95f));
        entries2.add(new BarEntry(6f, 105f));
        entries2.add(new BarEntry(7f, 84f));

        entries3.add(new BarEntry(1f, 100f));
        entries3.add(new BarEntry(2f, 50f));
        entries3.add(new BarEntry(3f, 78f));
        entries3.add(new BarEntry(4f, 58f));
        entries3.add(new BarEntry(5f, 56f));
        entries3.add(new BarEntry(6f, 55f));
        entries3.add(new BarEntry(7f, 88f));
        entries3.add(new BarEntry(8f, 90f));
        entries3.add(new BarEntry(9f, 394f));
        entries3.add(new BarEntry(10f, 200f));
        entries3.add(new BarEntry(11f, 134f));
        entries3.add(new BarEntry(12f, 30f));
        entries3.add(new BarEntry(13f, 50f));
        entries3.add(new BarEntry(14f, 70f));
        entries3.add(new BarEntry(15f, 149f));
        entries3.add(new BarEntry(16f, 23f));
        entries3.add(new BarEntry(17f, 100f));
        entries3.add(new BarEntry(18f, 50f));
        entries3.add(new BarEntry(19f, 78f));
        entries3.add(new BarEntry(20f, 58f));
        entries3.add(new BarEntry(21f, 56f));
        entries3.add(new BarEntry(22f, 55f));
        entries3.add(new BarEntry(23f, 88f));
        entries3.add(new BarEntry(24f, 90f));
        entries3.add(new BarEntry(25f, 394f));
        entries3.add(new BarEntry(26f, 200f));
        entries3.add(new BarEntry(27f, 134f));
        entries3.add(new BarEntry(28f, 30f));
        entries3.add(new BarEntry(29f, 50f));
        entries3.add(new BarEntry(30f, 70f));



        BarDataSet dataSet1 = new BarDataSet(entries1, "DataSet 1");
        BarDataSet dataSet2 = new BarDataSet(entries2, "DataSet 2");
        BarDataSet dataSet3 = new BarDataSet(entries3, "DataSet 3");

        dataSet1.setColors(getColors(entries1));
        dataSet2.setColors(getColors(entries2));
        dataSet3.setColors(getColors(entries3));


        barChart1.setFitBars(true);
        barChart2.setFitBars(true);
        barChart3.setFitBars(true);


        BarData barData1 = new BarData(dataSet1);
        BarData barData2 = new BarData(dataSet2);
        BarData barData3 = new BarData(dataSet3);

        barChart1.setData(barData1);
        barChart2.setData(barData2);
        barChart3.setData(barData3);


    }


    private List<Integer> getColors(List<BarEntry> entries) {
        int colorNaranja = Color.parseColor("#FBC05D");
        int colorRojo = Color.parseColor("#E83845");
        int colorVerde = Color.parseColor("#2E8B57");
        List<Integer> colors = new ArrayList<>();
        for (BarEntry entry : entries) {
            if (entry.getY() > 90) {
                colors.add(colorRojo);
            } else {
                if (entry.getY() > 80) {
                    colors.add(colorNaranja);
                } else {
                    colors.add(colorVerde);
                }
            }

        }
        return colors;
    }



    private void setupBarChart(BarChart barChart, String chartLabel, int barColor) {
        barChart.getDescription().setText(chartLabel);
        barChart.getDescription().setTextSize(12f);
        barChart.setBackgroundColor(Color.WHITE);
        barChart.setDrawGridBackground(false);

        XAxis xAxis = barChart.getXAxis();


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(false);
        xAxis.setDrawLabels(false);

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

        barChart.getDescription().setEnabled(true);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setPinchZoom(true);


        barChart.getAxisLeft().setAxisMinimum(0f);
        barChart.getAxisRight().setAxisMinimum(0f);

        barChart.invalidate();
    }


}
