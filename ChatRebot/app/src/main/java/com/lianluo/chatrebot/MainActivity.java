package com.lianluo.chatrebot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
    }


    public void beganRecord(View view) {
        //TTSService.getInstance().speak("科大讯飞TTS");
        //ASRService.getInstance().record(MainActivity.this);
    }



}



