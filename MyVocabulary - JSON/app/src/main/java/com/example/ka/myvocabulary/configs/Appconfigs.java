package com.example.ka.myvocabulary.configs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.ka.myvocabulary.utils.Utils;


/**
 * Created by KA on 12/3/2017.
 */

public class Appconfigs {

    private static Appconfigs sInstance = null;
    private static SharedPreferences sp;

    private Appconfigs() {
    }

    public static Appconfigs getInstance() {
        if (sInstance == null) {
            sInstance = new Appconfigs();
        }
        return sInstance;
    }

    public static void init(Context context) {
        if(sp == null){
            sp = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public void setLanguage(String language) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key_language", language);
        editor.commit();
    }
    public String getLanguage(){
        return sp.getString("key_language","en");
    }
}


