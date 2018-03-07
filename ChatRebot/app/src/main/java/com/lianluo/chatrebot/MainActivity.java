package com.lianluo.chatrebot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity {

    EditText editText;
    ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        editText = findViewById(R.id.edit_text);
        chatService = new ChatService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void speechResult(String say) {
        Log.d("saysome:",say);
        chatService.unitService(say);

    }

    public void beganRecord(View view) {
        //TTSService.getInstance().speak("科大讯飞TTS");
        ASRService.getInstance().record(MainActivity.this);
    }



}



