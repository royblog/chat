package com.lianluo.speechchat;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

public class MainActivity extends AppCompatActivity {

    private RecognizerDialog mIatDialog;
    private String mEngineType = SpeechConstant.TYPE_CLOUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIatDialog = new RecognizerDialog(MainActivity.this, mInitListener);
        mIatDialog.setListener(mRecognizerDialogListener);
    }

    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                Log.d("initlistener", "failed");
            } else  {
                Log.d("initlistener", "success");
            }
        }
    };

    public void listenSpeech(View view) {
        setParam();
        mIatDialog.show();
    }

    public void setParam() {
        mIatDialog.setParameter(SpeechConstant.PARAMS, null);
        mIatDialog.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        mIatDialog.setParameter(SpeechConstant.RESULT_TYPE, "json");
        mIatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        mIatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIatDialog.setParameter(SpeechConstant.VAD_BOS, "4000");

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIatDialog.setParameter(SpeechConstant.VAD_EOS, "1000");

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIatDialog.setParameter(SpeechConstant.ASR_PTT, "1");

        //// 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        mIatDialog.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mIatDialog.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");

    }

    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            Log.d("result", "success :" + recognizerResult.toString());
        }

        @Override
        public void onError(SpeechError speechError) {
            Log.d("result", "error" + speechError.toString());
        }
    };

    protected  void onDestroy() {
        super.onDestroy();

        if (null != mIatDialog) {
            mIatDialog.cancel();
            mIatDialog.destroy();
        }
    }
}
