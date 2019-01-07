package com.example.ka.myvocabulary.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by KA on 10/29/2017.
 */

public class TextSpeech {
    private TextToSpeech mTextToSpeech;
    private static Context mContext;
    public static  TextSpeech sInstance;

    private TextSpeech(){
       if(mContext == null){
           System.out.println("must call init ");
           return;
       }
       mTextToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
           @Override
           public void onInit(int status) {
               if(status != TextToSpeech.ERROR){
                   mTextToSpeech.setLanguage(Locale.UK);
               }
           }
       });

    }

    public static void init(Context context){
        mContext = context;
    }
    public static TextSpeech getsInstance(){
        if(sInstance == null){
            sInstance = new TextSpeech();
        }
        return sInstance;
    }
    @SuppressWarnings("deprecation")
    private  void ttUnder20(String text){
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"messageId");
        mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private  void ttsGreater21(String text){
        String utteran = this.hashCode()+ "";
        mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteran);
    }
    public void speak(String str){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ttsGreater21(str);
        }
        else{
            ttUnder20(str);
        }
    }

}
