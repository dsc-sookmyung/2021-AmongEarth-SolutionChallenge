package com.example.amongearth_hackaton.mypage;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.amongearth_hackaton.MainActivity;
import com.example.amongearth_hackaton.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    private LineChart lineChart;
    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        String[] month = {"1월", "2월", "3월"};

        TextView textView = findViewById(R.id.textView);
        Spinner monthSpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, month);
        monthSpinner.setAdapter(adapter);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText(monthSpinner.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ///// linechart /////
        lineChart = findViewById(R.id.linechart);

        ArrayList<Entry> valuesMonth = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            float val = (float) (Math.random() * 10);
            valuesMonth.add(new Entry(i, val));
        }

        LineDataSet lineDataSet;
        lineDataSet = new LineDataSet(valuesMonth, "DataSet Month");
        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet); // add the data sets
        // create a data object with the data sets
        LineData lineData = new LineData(lineDataSets);
        // lines and points
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.Green));
        lineDataSet.setCircleHoleColor(ContextCompat.getColor(getApplicationContext(), R.color.Green));
        // set data
        lineChart.setData(lineData);


        ///// barchart /////
        barChart = findViewById(R.id.barchart);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
