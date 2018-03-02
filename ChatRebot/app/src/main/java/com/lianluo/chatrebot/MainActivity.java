package com.lianluo.chatrebot;

import android.os.Environment;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

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

public class MainActivity extends AppCompatActivity {

    private RecognizerDialog mIatDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        SpeechUtility.createUtility(MainActivity.this, SpeechConstant.APPID + "=5a976cf6");
        mIatDialog = new RecognizerDialog(MainActivity.this, mInitListener);
        mIatDialog.setListener(mRecognizerDialogListener);
        setParam();

        if (mIatDialog == null) {
            Log.d("initDialog", "failed");
            return;
        } else  {
            Log.d("initDialog", "success");
        }
        */

        try {
            chatBBBService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void beganRecord(View view) {
        mIatDialog.show();
    }

    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int i) {
            if (i != ErrorCode.SUCCESS) {
                Log.d("initlistener", "failed");
            } else  {
                Log.d("initlistener", "success");
            }
        }
    };

    private  RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            Log.d("result","success");
        }

        @Override
        public void onError(SpeechError speechError) {
            Log.d("result", "error");

        }
    };

    private void setParam() {

        //清空参数
        mIatDialog.setParameter(SpeechConstant.PARAMS, null);

        //设置听写引擎
        mIatDialog.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);

        //设置返回结果格式
        mIatDialog.setParameter(SpeechConstant.RESULT_TYPE, "json");

        //设置语言
        mIatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");

        //设置语言区域
        mIatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");

        //设置语音前端点：静音超过时间，用户多长时间不说话做出超时处理
        mIatDialog.setParameter(SpeechConstant.VAD_BOS, "4000");

        //设置后端点：后端检测静音检测时间，用户停止说话多长时间默认不再输入，自动停止录音
        mIatDialog.setParameter(SpeechConstant.VAD_EOS, "2000");

        //设置标点符号，设置0表示返回结果无标点，设置1表示返回结果有标点
        mIatDialog.setParameter(SpeechConstant.ASR_PTT, "1");

        //设置保存格式，保存格式支持pcm，wav，
        mIatDialog.setParameter(SpeechConstant.AUDIO_FORMAT, "Wav");

        //设置音频保存路径，设置路径为sd卡，注意设置权限
        mIatDialog.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()  + "/msc/iat.wav");
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

    public void chatBBBService() throws JSONException {

        String access_token = "24.f6e78b670aca67e6dcbdcc2f489f7e79.2592000.1522400295.282335-10863169";

        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json, charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scene_id","100");
        jsonObject.put("query","你好");
        String json = jsonObject.toString();

        RequestBody requestBody = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rpc/2.0/solution/v1/unit_utterance?access_token=" + access_token)
                .post(requestBody)
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


