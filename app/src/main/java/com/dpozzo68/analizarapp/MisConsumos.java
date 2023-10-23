package com.dpozzo68.analizarapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.database.Cursor;
import android.content.ContentValues;

import androidx.appcompat.app.AppCompatActivity;

import com.dpozzo68.analizarapp.helpers.ConsumosSQLiteHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        int i = 0;
        while (cursor1.moveToNext()) {
            int idConsumo = cursor1.getInt(cursor1.getColumnIndexOrThrow("id_consumo"));
            float consumo = cursor1.getInt(cursor1.getColumnIndexOrThrow("consumo"));
            entries1.add(new BarEntry(idConsumo, consumo));
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
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7); // Resta 8 días para obtener los últimos 8
        Date endDate = new Date(); // Fecha actual

        while (calendar.getTime().before(endDate)) {
            String fecha = dateFormat.format(calendar.getTime());

            // Realiza la consulta para obtener la suma de consumos por fecha
            Cursor cursor2 = database.rawQuery("SELECT fecha_medicion, SUM(consumo) FROM Consumos WHERE fecha_medicion = ? GROUP BY fecha_medicion", new String[]{fecha});

            if (cursor2.moveToFirst()) {
                String fechaConsulta = cursor2.getString(0);
                float sumaConsumo = cursor2.getFloat(1);
                entries2.add(new BarEntry(entries2.size(), sumaConsumo));
            } else {
                entries2.add(new BarEntry(entries2.size(), 0f));
            }

            cursor2.close();
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Avanza un día

        }

        BarDataSet dataSet2 = new BarDataSet(entries2, "Consumo");
        dataSet2.setColors(getColors(entries2,190,210));
        BarData barData2 = new BarData(dataSet2);
        barChart2.setData(barData2);


        //Consumos mensuales - barchart3
        BarChart barChart3 = findViewById(R.id.barChart3);
        setupBarChart(barChart3, "");

        ArrayList<BarEntry> entries3 = new ArrayList<>();

        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar3 = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -30); // Resta 8 días para obtener los últimos 8
        Date endDate3 = new Date(); // Fecha actual

        while (calendar.getTime().before(endDate3)) {
            String fecha3 = dateFormat3.format(calendar.getTime());

            // Realiza la consulta para obtener la suma de consumos por fecha
            Cursor cursor3 = database.rawQuery("SELECT fecha_medicion, SUM(consumo) FROM Consumos WHERE fecha_medicion = ? GROUP BY fecha_medicion", new String[]{fecha3});

            if (cursor3.moveToFirst()) {
                String fechaConsulta = cursor3.getString(0);
                float sumaConsumo = cursor3.getFloat(1);
                entries3.add(new BarEntry(entries3.size(), sumaConsumo));
            } else {
                entries3.add(new BarEntry(entries3.size(), 0f));
            }

            cursor3.close();
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Avanza un día

        }

        BarDataSet dataSet3 = new BarDataSet(entries3, "Consumo");
        dataSet3.setColors(getColors(entries3,190,210));
        BarData barData3 = new BarData(dataSet3);
        barChart3.setData(barData3);
        database.close();

    }
        private void CargarConsumosdePrueba(){
            //limpiamos la tabla Consumos

            String tabla = "Consumos";

            ConsumosSQLiteHelper us = new ConsumosSQLiteHelper(this, "DBconsumos", null, 1);
            SQLiteDatabase dbu = us.getWritableDatabase();

            dbu.execSQL("DELETE FROM " + tabla);

            dbu.close();


            //CARGAR DATOS EN CONSUMOS PARA PRUEBA

            ConsumosSQLiteHelper udb = new ConsumosSQLiteHelper(this, "DBconsumos", null, 1);
            SQLiteDatabase db = udb.getWritableDatabase();
            // Define la fecha inicial
            String fecha = "12/08/2023";

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date;

            try {
                date = dateFormat.parse(fecha);
                Random random = new Random();
                for (int i = 1; i <= 840; i++) {


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
            db.close();
        }
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
        barChart.getDescription().setText(chartLabel);
        barChart.getDescription().setTextSize(12f);
        barChart.setBackgroundColor(Color.WHITE);
        barChart.setDrawGridBackground(false);
        barChart.setFitBars(true);

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

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de AnalizArApp?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
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
