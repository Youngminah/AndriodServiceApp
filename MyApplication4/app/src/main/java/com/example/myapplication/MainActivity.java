package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isServiceRunning = false;
    EditText editText;
    int selected=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        editText= findViewById(R.id.editText);
        Button service_on = (Button)findViewById(R.id.button);
        Button service_off = (Button)findViewById(R.id.button2);
        Button count_on = (Button)findViewById(R.id.button4);
        Button count_off = (Button)findViewById(R.id.button3);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i==R.id.radioButton){
                    selected=100;
                }
                else if(i==R.id.radioButton2) {
                    selected=300;
                }
                else if(i==R.id.radioButton3) {
                    selected=500;
                }
                else{
                    selected=1000;
                }
            }

        });

    }

    public void onClickServiceOnClicked(View view){

            Intent intent = new Intent(getApplicationContext(),MyService.class);
            String text = editText.getText().toString();
           // Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
            if(!text.isEmpty()){
                intent.putExtra(MyService.EXTRA_TEXT,text);
            }
            intent.putExtra("selected",selected);
            intent.putExtra("printnumselected",1);
            startService(intent);
            isServiceRunning = true;

    }

    public void onClickServiceOffClicked(View view){

        if(isServiceRunning){
            Intent intent = new Intent(getApplicationContext(),MyService.class);
            stopService(intent);
            isServiceRunning = false;
        }

    }

    public void onClickCountOnClicked(View view){
        if(isServiceRunning) {
            Intent intent = new Intent(getApplicationContext(), MyService.class);
            int countstart = 1;
            intent.putExtra("countstart", countstart);
            intent.putExtra("cnt_selected", selected);
            startService(intent);
        }
    }


    public void onClickCountOffClicked(View view) {
        Intent intent = new Intent(getApplicationContext(),MyService.class);
        int countstart = 0;
        intent.putExtra("countstart",countstart);
        startService(intent);
    }


}