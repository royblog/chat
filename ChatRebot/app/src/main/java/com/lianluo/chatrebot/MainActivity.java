package com.lianluo.chatrebot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //authService();
        chatBBBService();
    }

    public void authService() {

        String client_id = "5o14pfO8QBrI8dnGvKkjVo6l";
        String client_secret = "YllGkqITOcSsFCZTpEzXPB7gV0F5AhCy";
        String auth_url = "https://aip.baidubce.com/oauth/2.0/token?";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request
                .Builder()
                .url(auth_url + "grant_type=client_credentials" + "&client_id=" + client_id + "&client_secret=" + client_secret)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.print(response.body().string());
            }
        });
    }

    public void chatBBBService() {

        String access_token = "24.f6e78b670aca67e6dcbdcc2f489f7e79.2592000.1522400295.282335-10863169";

        OkHttpClient client = new OkHttpClient();

        FormBody formBody = new FormBody
                .Builder()
                .add("scene_id","100")
                .add("query", "你好")
                .build();

        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rpc/2.0/solution/v1/unit_utterance?access_token=" + access_token)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("errorlog",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("chatlog",response.body().string());
            }
        });
    }
}


