package com.lianluo.chatrebot;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by wangyaoguo on 2018/3/2.
 */

public class ChatService {

    String scene_id;    //
    String session_id;  //
    String query;       //

    /**
     * 授权服务，在请求聊天前，调用授权接口，获取access_token,有效期30天，需定期更换
     * 返回数据结构参考：AuthBean
     */

    public void authService() {

        String auth_api = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials";
        String client_id = "5o14pfO8QBrI8dnGvKkjVo6l";
        String client_secret = "YllGkqITOcSsFCZTpEzXPB7gV0F5AhCy";
        String auth_url = auth_api + "&client_id=" + client_id + "&client_secret=" + client_secret;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request
                .Builder()
                .url(auth_url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("authservice", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                AuthBean authBean = gson.fromJson(response.body().string(), AuthBean.class);
                Log.d("authservice", authBean.access_token);
            }
        });
    }

    /**
     * @param query         聊天内容
     * @throws JSONException
     */

    public void unitService(String query) throws JSONException {

        String access_token = "24.f6e78b670aca67e6dcbdcc2f489f7e79.2592000.1522400295.282335-10863169";
        String unit_url = "https://aip.baidubce.com/rpc/2.0/solution/v1/unit_utterance?access_token=" + access_token;
        String scene_id = "100";
        String session_id = "";

        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json, charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scene_id",scene_id);
        jsonObject.put("query", query);
        jsonObject.put("session_id", session_id);
        String json = jsonObject.toString();

        RequestBody requestBody = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(unit_url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("unitservice",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                ChatBean bean = gson.fromJson(response.body().string(), ChatBean.class);
                Log.d("three", "three");
            }
        });

        Log.d("one","one");
    }
}