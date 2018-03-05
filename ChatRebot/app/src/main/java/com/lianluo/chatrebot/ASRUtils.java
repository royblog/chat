package com.lianluo.chatrebot;

import android.os.Environment;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

/**
 * Created by wangyaoguo on 2018/3/5.
 */

public class ASRUtils {
    private boolean isInitSuccess = false;
    private RecognizerDialog mIatDialog;
    private volatile static TTSUtils instance = null;

    //构造私有方法，禁止外界访问
    private ASRUtils() {

    }

    //构造单例
    public static TTSUtils getInstance() {
        if (instance == null) {
            synchronized (TTSUtils.class) {
                if (instance == null) { //二次检测
                    instance = new TTSUtils();
                }
            }
        }
        return instance;
    }

    //开始语音识别
    public void record() {
        if (isInitSuccess) {
            mIatDialog.show();
        } else  {
            init();
            isInitSuccess = true;
        }
    }

    public void init() {

        mIatDialog = new RecognizerDialog(ContextApplication.getAppContext(), mInitListener);
        mIatDialog.setListener(mRecognizerDialogListener);
        if (mIatDialog == null) {
            Log.d("initDialog", "failed");
            return;
        } else {
            Log.d("initDialog", "success");
        }
        setParas();
    }

    public void setParas() {
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
            //chatService.unitService(recognizerResult.getResultString());
        }

        @Override
        public void onError(SpeechError speechError) {
            Log.d("result", "error");
        }
    };

}
