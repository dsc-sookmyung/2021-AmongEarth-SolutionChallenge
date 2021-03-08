package com.example.amongearth.mypage;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        final TextView textView = findViewById(R.id.textView);


        Intent intent = getIntent();
        ArrayList<WasteRecord> wasteRecords = new ArrayList<>(7);
        wasteRecords = (ArrayList<WasteRecord>) intent.getSerializableExtra("wasteRecords");

        ///// linechart /////
        lineChart = findViewById(R.id.linechart);

        ArrayList<Entry> lineChartValues = new ArrayList<>();

        for (int x = 0; x < 7; x++) {
            float var = (float) wasteRecords.get(x).total;
            lineChartValues.add(new Entry(x, var));
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
        xAxis.setLabelCount(7,true);
        final ArrayList<String> xAxisLabel = new ArrayList<>();
        for (int x=0; x<7; x++) {
            String str = wasteRecords.get(x).date;
            str = str.substring(4,6) + "/" + str.substring(6);
            xAxisLabel.add(str);
        }
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

        ///// barchart /////
        barChart = findViewById(R.id.barchart);

        int[] colorArray = new int[] {Color.rgb(143, 146, 191), Color.rgb(85, 166, 217), Color.rgb(234, 132, 104), Color.rgb(242, 183, 5),  Color.rgb(142, 191, 69), Color.rgb(150,150,150)};

        ArrayList<BarEntry> barChartValues = new ArrayList<>();
        for (int x = 0; x < 7; x++) {
            float[] val = new float[6];
            val[0] = wasteRecords.get(x).paper;
            val[1] = wasteRecords.get(x).metal;
            val[2] = wasteRecords.get(x).glass;
            val[3] = wasteRecords.get(x).plastic;
            val[4] = wasteRecords.get(x).waste;
            val[5] = wasteRecords.get(x).none;

            barChartValues.add(new BarEntry(x, val));
        }
        BarDataSet barDataSet = new BarDataSet(barChartValues, "");
        barDataSet.setStackLabels(new String[] {"Paper", "Metal", "Glass", "Plastic", "General Waste", "None"});
        barDataSet.setColors(colorArray);
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        barDataSet.setValueFormatter(new MyValueFormatter());

        final XAxis bar_xAxis = barChart.getXAxis();
        bar_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final ArrayList<String> bar_xAxisLabel = new ArrayList<>();
        for (int x=0; x<7; x++) {
            String str = wasteRecords.get(x).date;
            str = str.substring(4,6) + "/" + str.substring(6);
            bar_xAxisLabel.add(str);
        }
        bar_xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return bar_xAxisLabel.get((int)value);
            }
        });

        //y축의 오른쪽면 활성화를 제거함
        YAxis bar_yAxisRight = barChart.getAxisRight();
        bar_yAxisRight.setDrawLabels(false);
        bar_yAxisRight.setDrawAxisLine(false);
        bar_yAxisRight.setDrawGridLines(false);

        YAxis bar_yAxisLeft = barChart.getAxisLeft();
        bar_yAxisLeft.setAxisMinimum(0);

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


