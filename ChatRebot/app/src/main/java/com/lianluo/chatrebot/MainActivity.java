package com.lianluo.chatrebot;

import android.content.SharedPreferences;
import android.os.Environment;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

public class MainActivity extends AppCompatActivity {

    private RecognizerDialog mIatDialog;
    private SpeechSynthesizer mTts;
    private ChatService chatService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIatDialog = new RecognizerDialog(MainActivity.this, mInitListener);
        mTts = new SpeechSynthesizer.createSynthesizer(this,mInitListener);

        mIatDialog.setListener(mRecognizerDialogListener);
        setParam();

        if (mIatDialog == null) {
            Log.d("initDialog", "failed");
            return;
        } else {
            Log.d("initDialog", "success");
        }

        chatService = new ChatService();
    }

    public void textToSpeech() {

    }

    public void beganRecord(View view) {
        mIatDialog.show();
    }

    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int i) {
            if (i != ErrorCode.SUCCESS) {
                Log.d("initlistener", "failed");
            } else {
                Log.d("initlistener", "success");
            }
        }
    };

    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            Log.d("result", "success");
            chatService.unitService(recognizerResult.getResultString());
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
        mIatDialog.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");
    }
}



