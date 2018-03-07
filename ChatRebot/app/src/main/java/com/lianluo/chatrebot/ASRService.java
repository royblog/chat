package com.lianluo.chatrebot;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

/**
 * Created by wangyaoguo on 2018/3/7.
 * 科大讯飞语音听写服务，科大讯飞语音听写有两套API，一套有UI，一套没有UI，暂时使用有UI的API
 */

public class ASRService {

    private boolean isInitSuccess = false;
    private RecognizerDialog mIatDialog;

    //构造私有方法，外界不能直接访问
    private ASRService() {

    }

    //构造单例
    private volatile static ASRService instance = null;
    public static ASRService getInstance() {
        if (instance == null) {
            synchronized (ASRService.class) {
                if (instance == null) { //二次检测
                    instance = new ASRService();
                }
            }
        }
        return instance;
    }

    //初始化
    public void init(Context ctx) {
        mIatDialog = new RecognizerDialog(ctx, mInitListener);
        mIatDialog.setListener(mRecognizerDialogListener);
        setParas();
    }

    //开始语音听写
    public void record(Context context) {
        if (isInitSuccess == false) {
            init(context);
            isInitSuccess = true;
        }
        mIatDialog.show();
    }

    //初始化监听器
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

    //听写监听器
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            Gson gson = new Gson();
            StringBuffer speechResult = new StringBuffer();
            SpeechBean speechBean = gson.fromJson(recognizerResult.getResultString(), SpeechBean.class);
            if (speechBean.sn == 1) {
                for (int i = 0; i < speechBean.ws.size(); i++) {
                    speechResult.append(speechBean.ws.get(i).cw.get(0).w);
                }
                ChatService chat =  new ChatService();
                chat.unitService(speechResult.toString());
            }
        }


        @Override
        public void onError(SpeechError speechError) {
            Log.d("result", "error");
        }
    };

    //设置参数
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
}
