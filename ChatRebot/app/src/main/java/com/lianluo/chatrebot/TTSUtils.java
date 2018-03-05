package com.lianluo.chatrebot;

import android.os.Bundle;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Created by wangyaoguo on 2018/3/5.
 */

public class TTSUtils implements InitListener, SynthesizerListener {

    private boolean isInitSuccess = false;
    private SpeechSynthesizer mTts;
    private volatile static TTSUtils instance = null;

    //构造私有方法，外界不能直接访问
    /*
    private TTSUtils() {

    }
    */

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

    public void init() {
        mTts = SpeechSynthesizer.createSynthesizer(ContextApplication.getAppContext(), this);
        mTts.setParameter(SpeechConstant.PARAMS,null);
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        mTts.setParameter(SpeechConstant.VOICE_NAME,"xiaoyan");
        mTts.setParameter(SpeechConstant.SPEED,"50");
        mTts.setParameter(SpeechConstant.PITCH,"50");
        mTts.setParameter(SpeechConstant.VOLUME,"100");
        mTts.setParameter(SpeechConstant.STREAM_TYPE,"3");
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS,"true");
    }

    public void speak(String msg) {
        if (isInitSuccess) {
            if (mTts.isSpeaking()) {
                stop();
            }

            mTts.startSpeaking(msg, this);
        } else  {
            init();
        }
    }

    public void stop() {
        mTts.stopSpeaking();
    }


    @Override
    public void onInit(int i) {
        if (i== ErrorCode.SUCCESS) {
            isInitSuccess = true;
        }

    }

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
}
