package com.dpozzo68.analizarapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.database.Cursor;
import android.content.ContentValues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.dpozzo68.analizarapp.R;
import com.dpozzo68.analizarapp.helpers.ConsumosSQLiteHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MisConsumos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misconsumos);

        CargarConsumosdePrueba(); //Cargamos consumos para probar

        //CONSUMOS DIARIOS - barchart1
        BarChart barChart1 = findViewById(R.id.barChart1);
        ConsumosSQLiteHelper cons = new ConsumosSQLiteHelper(this, "DBconsumos", null, 1);
        SQLiteDatabase database = cons.getReadableDatabase();

        setupBarChart(barChart1, "");

        // Recupera los últimos 12 registros de la base de datos
        ArrayList<BarEntry> entries1 = new ArrayList<>();
        Cursor cursor1 = database.rawQuery("SELECT id_consumo, consumo FROM Consumos ORDER BY id_consumo DESC LIMIT 12", null);
        int i = 1;
        while (cursor1.moveToNext()) {
            int idConsumo = cursor1.getInt(cursor1.getColumnIndexOrThrow("id_consumo"));
            float consumo = cursor1.getInt(cursor1.getColumnIndexOrThrow("consumo"));
            entries1.add(new BarEntry(i, consumo));
            i++;
        }

        BarDataSet dataSet1 = new BarDataSet(entries1, "Consumo");
        dataSet1.setColors(getColors(entries1, 20, 25));
        BarData barData1 = new BarData(dataSet1);
        barChart1.setData(barData1);
        cursor1.close();


        //CONSUMOS SEMANALES barchart2 - consumos agrupados por dia o sea por fecha, sin contar la fecha actual


        BarChart barChart2 = findViewById(R.id.barChart2);
        setupBarChart(barChart2, "");

        ArrayList<BarEntry> entries2 = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendars = Calendar.getInstance();
        calendars.add(Calendar.DAY_OF_YEAR, -7); // Resta 7 días para obtener los últimos 7
        Date endDate = new Date(); // Fecha actual
        int k = 1;
        while (calendars.getTime().before(endDate)) {
            String fechas = dateFormat.format(calendars.getTime());

            // Realiza la consulta para obtener la suma de consumos por fecha
            Cursor cursor2 = database.rawQuery("SELECT fecha_medicion, SUM(consumo) FROM Consumos WHERE fecha_medicion = ? GROUP BY fecha_medicion", new String[]{fechas});

            if (cursor2.moveToFirst()) {
                String fechaConsulta = cursor2.getString(0);
                int sumaConsumo = cursor2.getInt(1);
                entries2.add(new BarEntry(k, sumaConsumo));

            } else {
                entries2.add(new BarEntry(k, 0f));

            }
            k++;

            cursor2.close();
            calendars.add(Calendar.DAY_OF_YEAR, 1); // Avanza un día

        }

        BarDataSet dataSet2 = new BarDataSet(entries2, "Consumo");
        dataSet2.setColors(getColors(entries2,190,210));
        dataSet2.setDrawValues(true);
        BarData barData2 = new BarData(dataSet2);
        barChart2.setData(barData2);



        //CONSUMOS MENSUALES - LINECHART

        LineChart lineChart3 = findViewById(R.id.chart);
        ArrayList<Entry> entries3 = new ArrayList<>();

        // Configura el gráfico
        lineChart3.getDescription().setEnabled(false);
        lineChart3.setTouchEnabled(true);
        lineChart3.setDragEnabled(true);
        lineChart3.setScaleEnabled(true);
        lineChart3.setPinchZoom(true);

        // Configura los ejes X e Y
        XAxis xAxis = lineChart3.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        YAxis leftAxis = lineChart3.getAxisLeft();
        YAxis rightAxis = lineChart3.getAxisRight();
        rightAxis.setEnabled(false);
        // Recupera los registros correspondientes a los últimos 30 días (excluyendo hoy)

        Calendar calendarm = Calendar.getInstance();
        calendarm.add(Calendar.DAY_OF_YEAR, -30); // Resta 30 días para obtener los últimos 30
        Date endDate3 = new Date(); // Fecha actual

        while (calendarm.getTime().before(endDate3)) {
            String fecham = dateFormat.format(calendarm.getTime());

            // Realiza la consulta para obtener la suma de consumos por fecha
            Cursor cursor3 = database.rawQuery("SELECT fecha_medicion , SUM(consumo) FROM Consumos WHERE fecha_medicion = ? GROUP BY fecha_medicion", new String[]{fecham});

            if (cursor3.moveToFirst()) {
                String fechaConsulta = cursor3.getString(0);
                int sumaConsumom = cursor3.getInt(1);
                entries3.add(new Entry(entries3.size(), sumaConsumom));
            } else {
                entries3.add(new Entry(entries3.size(), 0f));
            }

            cursor3.close();
            calendarm.add(Calendar.DAY_OF_YEAR, 1); // Avanza un día

        }
        LineDataSet dataSet3 = new LineDataSet(entries3, "Consumo");
        dataSet3.setColors(ColorTemplate.MATERIAL_COLORS);
        LineData lineData3 = new LineData(dataSet3);
        lineChart3.setData(lineData3);


        lineChart3.invalidate();

        database.close();
    }


    private void CargarConsumosdePrueba() {
        //limpiamos la tabla Consumos

        ConsumosSQLiteHelper us = new ConsumosSQLiteHelper(this, "DBconsumos", null, 1);
        SQLiteDatabase dbu = us.getWritableDatabase();

        dbu.execSQL("DELETE FROM Consumos" );

        dbu.close();


        //CARGAR DATOS EN CONSUMOS PARA PRUEBA

        ConsumosSQLiteHelper udb = new ConsumosSQLiteHelper(this, "DBconsumos", null, 1);
        SQLiteDatabase db = udb.getWritableDatabase();
        // Define la fecha inicial
        String fecha = "27/09/2023";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date;

        try {
            date = dateFormat.parse(fecha);
            Random random = new Random();
            for (int i = 1; i <= 360; i++) {


                ContentValues values = new ContentValues();
                values.put("id_consumo", i);
                values.put("consumo", getRandomConsumoValue());
                values.put("fecha_medicion", dateFormat.format(date));

                // Inserta los datos en la base de datos
                long newRowId = db.insert("Consumos", null, values);

                // Si i es divisible por 12, suma un día a la fecha
                if (i % 12 == 0) {
                    date.setTime(date.getTime() + 24 * 60 * 60 * 1000); // Suma 1 día
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();        }
    // Función para obtener un valor de consumo aleatorio
    private double getRandomConsumoValue() {
        Random random = new Random();
        double min = 0;
        double max = 30;
        return min + (max - min) * random.nextDouble();
    }


    private List<Integer> getColors(List<BarEntry> entries, int max1, int max2) {
        int colorNaranja = Color.parseColor("#FBC05D");
        int colorRojo = Color.parseColor("#E83845");
        int colorVerde = Color.parseColor("#2E8B57");
        List<Integer> colors = new ArrayList<>();
        for (BarEntry entry : entries) {
            if (entry.getY() > max1) {
                colors.add(colorRojo);
            } else {
                if (entry.getY() > max2) {
                    colors.add(colorNaranja);
                } else {
                    colors.add(colorVerde);
                }
            }

        }
        return colors;
    }



    private void setupBarChart(BarChart barChart, String chartLabel) {
        barChart.getDescription().setEnabled(false);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setPinchZoom(true);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);
        xAxis.setDrawLabels(true);
        xAxis.setLabelCount(7);
        YAxis leftAxis = barChart.getAxisLeft();
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setEnabled(true);

        barChart.getAxisLeft().setAxisMinimum(0f);
        barChart.getAxisRight().setAxisMinimum(0f);

        barChart.invalidate();
    }


    public void irConsumos(View view) {
        ImageView imagen = findViewById(R.id.imagen_home);
        imagen.setClickable(true);
        Intent intent = new Intent(this, MisConsumos.class);
        startActivity(intent);
    }

    public void irAlarmas(View view) {
        ImageView imagen = findViewById(R.id.imagen_alerta);
        imagen.setClickable(true);
        Intent intent = new Intent(this, MisAlarmas1.class);
        startActivity(intent);
    }

    public void irPerfil(View view) {
        ImageView imagen = findViewById(R.id.imagen_perfil);
        imagen.setClickable(true);
        Intent intent = new Intent(this, Mi_Cuenta.class);
        startActivity(intent);
    }

    public void irContacto(View view) {
        ImageView imagen = findViewById(R.id.imagen_contacto);
        imagen.setClickable(true);
        Intent intent = new Intent(this, ContactoActivity.class);
        startActivity(intent);
    }
}
