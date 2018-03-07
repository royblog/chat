package com.lianluo.chatrebot;

import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Created by wangyaoguo on 2018/3/7.
 * 科大讯飞语音合成服务
 */

public class TTSService {

    private boolean isInitSuccess = false;
    private SpeechSynthesizer mTts;

    //构造私有方法，外界不能直接访问
    private TTSService() {

    }

    //构造单例
    private volatile static TTSService instance = null;
    public static TTSService getInstance() {
        if (instance == null) {
            synchronized (TTSService.class) {
                if (instance == null) { //二次检测
                    instance = new TTSService();
                }
            }
        }
        return instance;
    }

    //初始化信息
    public void init() {
        mTts = SpeechSynthesizer.createSynthesizer(ContextApplication.getAppContext(), mInitListener);
        setTTSParas();
    }

    //播放语音
    public void speak(String msg) {
        if (isInitSuccess == false) {
            init();
            isInitSuccess = true;
        }
        if (mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }
        mTts.startSpeaking(msg, mSynthesizerListener);
    }

    //初始化监听器
    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int i) {
            if (i != ErrorCode.SUCCESS) {
                Log.d("", "failed");
            } else {
                Log.d("", "success");
            }
        }
    };

    //合成监听器
    private SynthesizerListener mSynthesizerListener = new SynthesizerListener() {
        @Override
        public void onSpeakBegin() {

        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }

        @Override
        public void onCompleted(SpeechError speechError) {

        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };

    //设置参数
    private void setTTSParas() {
        mTts.setParameter(SpeechConstant.PARAMS,null);
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        mTts.setParameter(SpeechConstant.VOICE_NAME,"xiaoyan");
        mTts.setParameter(SpeechConstant.SPEED,"50");
        mTts.setParameter(SpeechConstant.PITCH,"50");
        mTts.setParameter(SpeechConstant.VOLUME,"100");
        mTts.setParameter(SpeechConstant.STREAM_TYPE,"3");
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS,"true");
    }
}
