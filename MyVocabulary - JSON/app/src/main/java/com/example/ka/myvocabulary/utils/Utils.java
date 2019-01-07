package com.example.ka.myvocabulary.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.example.ka.myvocabulary.models.Vocabulary;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Created by KA on 10/22/2017.
 */

public class Utils {
    private static String TAG = "English Vocabulary";
    private static boolean sShowLog = true;

    public static void LOG(String str){
        if(sShowLog){
            Log.d(TAG, str);
        }
    }

    public static ArrayList<Vocabulary> getListVocaRandom (ArrayList<Vocabulary> list){
        ArrayList<Vocabulary> listVoca = new ArrayList<>();
        listVoca.addAll(list);
        Random random = new Random();
        for(int i =0; i <listVoca.size(); i++){
            int rand = random.nextInt(listVoca.size());
            Vocabulary tmp = listVoca.get(i);
            listVoca.set(i, listVoca.get(rand));
            listVoca.set(rand, tmp);
        }
        return listVoca;
    }
    @SuppressWarnings("deprecation")
    public static void setLanguage(Context context, String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
    }

}
