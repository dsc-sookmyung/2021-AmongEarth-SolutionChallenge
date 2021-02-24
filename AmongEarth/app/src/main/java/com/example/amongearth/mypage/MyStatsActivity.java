package com.example.amongearth.mypage;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.amongearth.MainActivity;
import com.example.amongearth.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class MyStatsActivity extends AppCompatActivity {

    private LineChart lineChart;
    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};


        final TextView textView = findViewById(R.id.textView);
        final Spinner monthSpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, month);
        monthSpinner.setAdapter(adapter);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ///// linechart /////
                lineChart = findViewById(R.id.linechart);

                ArrayList<Entry> lineChartValues = new ArrayList<>();

                for (int x = 0; x < 4; x++) {
                    float val = (float) (Math.random() * 10);
                    lineChartValues.add(new Entry(x, val));
                }

                LineDataSet lineDataSet;
                lineDataSet = new LineDataSet(lineChartValues, "Total Waste");
                ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
                lineDataSets.add(lineDataSet); // add the data sets
                // create a data object with the data sets
                LineData lineData = new LineData(lineDataSets);
                // lines and points
                lineDataSet.setColor(Color.BLACK);
                lineDataSet.setCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.Green));
                lineDataSet.setCircleHoleColor(ContextCompat.getColor(getApplicationContext(), R.color.Green));
                lineDataSet.setValueFormatter(new MyValueFormatter());

                final XAxis xAxis = lineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelCount(4,true);
                final ArrayList<String> xAxisLabel = new ArrayList<>();
                xAxisLabel.add("Week 1");
                xAxisLabel.add("Week 2");
                xAxisLabel.add("Week 3");
                xAxisLabel.add("Week 4");
                xAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return xAxisLabel.get((int)value);
                    }
                });
                //y축의 오른쪽면 활성화를 제거함
                YAxis yAxisRight = lineChart.getAxisRight();
                yAxisRight.setDrawLabels(false);
                yAxisRight.setDrawAxisLine(false);
                yAxisRight.setDrawGridLines(false);

                lineChart.getLegend().setEnabled(false);
                lineChart.getDescription().setEnabled(false);

                // set data
                lineChart.setData(lineData);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        ///// barchart /////
        barChart = findViewById(R.id.barchart);

        int[] colorArray = new int[] {Color.rgb(143, 146, 191), Color.rgb(85, 166, 217), Color.rgb(234, 132, 104), Color.rgb(242, 183, 5),  Color.rgb(142, 191, 69)};

        ArrayList<BarEntry> barChartValues = new ArrayList<>();
        for (int x = 0; x < 7; x++) {
            float[] val = new float[5];
            for (int i=0; i<5; i++) {
                val[i] = (float) (Math.random() * 10);
            }
            barChartValues.add(new BarEntry(x, val));
        }
        BarDataSet barDataSet = new BarDataSet(barChartValues, "");
        barDataSet.setStackLabels(new String[] {"Paper", "Metal", "Glass", "Plastic", "General Waste"});
        barDataSet.setColors(colorArray);
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        barDataSet.setValueFormatter(new MyValueFormatter());

        final XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");
        xAxisLabel.add("Sun");
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xAxisLabel.get((int)value);
            }
        });

        //y축의 오른쪽면 활성화를 제거함
        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0);

        BarData bardata = new BarData(barDataSet);
        barChart.getDescription().setEnabled(false);
        barChart.setData(bardata);
        barChart.animateXY(1000,1000);
        barChart.setDrawValueAboveBar(false);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
            case R.id.BtnHome: { // 오른쪽 상단 버튼 눌렀을 때
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

class MyValueFormatter extends ValueFormatter {

    @Override
    public String getFormattedValue(float value) {
        if (value > 0)
            return "" + ((int) value);
        else
            return "";
    }


}