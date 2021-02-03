package com.example.methodspages;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label = editText.getText().toString();
                switch (label) {
                    case "paper":
                        Intent intent0 = new Intent(getApplicationContext(), PaperActivity.class);
                        startActivity(intent0);
                        break;
                    case "metal":
                        Intent intent1 = new Intent(getApplicationContext(), MetalActivity.class);
                        startActivity(intent1);
                        break;
                    case "glass":
                        Intent intent2 = new Intent(getApplicationContext(), GlassActivity.class);
                        startActivity(intent2);
                        break;
                    case "plastic":
                        Intent intent3 = new Intent(getApplicationContext(), PlasticActivity.class);
                        startActivity(intent3);
                        break;
                    case "waste":
                        Intent intent4 = new Intent(getApplicationContext(), WasteActivity.class);
                        startActivity(intent4);
                        break;
                    case "eyeglasses":
                        Intent intent5 = new Intent(getApplicationContext(), EyeglassesActivity.class);
                        startActivity(intent5);
                        break;
                    case "pringles":
                        Intent intent6 = new Intent(getApplicationContext(), PringlesActivity.class);
                        startActivity(intent6);
                        break;
                    case "scissors":
                        Intent intent7 = new Intent(getApplicationContext(), ScissorsActivity.class);
                        startActivity(intent7);
                        break;
                    case "fruit packaging":
                        Intent intent8 = new Intent(getApplicationContext(), FruitPackagingActivity.class);
                        startActivity(intent8);
                        break;
                    case "cool pack":
                        Intent intent9 = new Intent(getApplicationContext(), CoolPackActivity.class);
                        startActivity(intent9);
                        break;
                    case "broken bottle":
                        Intent intent10 = new Intent(getApplicationContext(), BrokenBottleActivity.class);
                        startActivity(intent10);
                        break;
                    case "spring note":
                        Intent intent11 = new Intent(getApplicationContext(), NoteActivity.class);
                        startActivity(intent11);
                        break;
                    case "mat":
                        Intent intent12 = new Intent(getApplicationContext(), MatActivity.class);
                        startActivity(intent12);
                        break;
                    case "wine glass":
                        Intent intent13 = new Intent(getApplicationContext(), WineglassActivity.class);
                        startActivity(intent13);
                        break;
                    case "icepack":
                        Intent intent14 = new Intent(getApplicationContext(), IcepackActivity.class);
                        startActivity(intent14);
                        break;
                }
            }
        });

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