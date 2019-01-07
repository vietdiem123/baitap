package com.example.ka.myvocabulary.controller;

import android.content.Context;
import android.os.AsyncTask;

import com.example.ka.myvocabulary.MainActivity;
import com.example.ka.myvocabulary.models.Topic;
import com.example.ka.myvocabulary.models.Vocabulary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by KA on 10/22/2017.
 */

public class TopicManager {
    private  static TopicManager sInstance = null;

    private ArrayList<Topic> mTopicList;
    private ArrayList<Vocabulary> mVocabularyList;

    private  TopicManager(){
        mTopicList = new ArrayList<>();
        mVocabularyList = new ArrayList<>();
    }
    public static TopicManager getInstance (){
        if(sInstance == null){
            sInstance = new TopicManager();
        }
        return sInstance;
    }
    public void load(String s){
        mTopicList.clear();
        mVocabularyList.clear();
        String content = s;
        try {
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray(Topic.TOPIC);
            JSONArray jsonArrayVoca = jsonObject.getJSONArray(Vocabulary.VOCABULARY);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt(Topic.TOPICID);
                String name = object.getString(Topic.TOPICNAME);
                Topic topic = new Topic(id, name);
                mTopicList.add(topic);
            }
            for (int i = 0; i < jsonArrayVoca.length(); i++) {
                JSONObject object = jsonArrayVoca.getJSONObject(i);
                String word = object.getString(Vocabulary.WORD);
                String pronun = object.getString(Vocabulary.PRONUN);
                String mean = object.getString(Vocabulary.MEAN);
                int id = object.getInt(Vocabulary.TOPICID);
                Vocabulary vocabulary = new Vocabulary(word, pronun, mean, id);
                mVocabularyList.add(vocabulary);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    private void parse(String content){
        try {
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray(Topic.TOPIC);
            JSONArray jsonArrayVoca = jsonObject.getJSONArray(Vocabulary.VOCABULARY);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt(Topic.TOPICID);
                String name = object.getString(Topic.TOPICNAME);
                Topic topic = new Topic(id, name);
                mTopicList.add(topic);
            }
            for (int i = 0; i < jsonArrayVoca.length(); i++) {
                JSONObject object = jsonArrayVoca.getJSONObject(i);
                String word = object.getString(Vocabulary.WORD);
                String pronun = object.getString(Vocabulary.PRONUN);
                String mean = object.getString(Vocabulary.MEAN);
                int id = object.getInt(Vocabulary.TOPICID);
                Vocabulary vocabulary = new Vocabulary(word, pronun, mean, id);
                mVocabularyList.add(vocabulary);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

        //faleData();

    public ArrayList<Topic> getTopicList(){
         return mTopicList;
    }
    public ArrayList<Vocabulary> getVocabularyList(){
        return mVocabularyList;
    }

    public String[] getTopicsName(){
        String []name = new String[mTopicList.size()];
        for (int i = 0; i < mTopicList.size(); i++){
           name[i] = mTopicList.get(i).getName();
        }
        return name;
    }
    public ArrayList<Vocabulary> getVocabularies(Topic topic){
        ArrayList<Vocabulary> array = new ArrayList<>();
        for (int i =0 ; i < mVocabularyList.size(); i++){
            if(topic.getId() == mVocabularyList.get(i).getTopicId()){
                array.add(mVocabularyList.get(i));
            }
        }
        return array;
    }
    private String readAssetsFile(String path, Context contextt){
        String content = "";
        try {
            InputStream inputStream = contextt.getAssets().open(path);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line!= null){
                content += line + "\n";
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return content;
    }
    private void faleData(){
        mTopicList.add(new Topic(1, "topic1"));
        mTopicList.add(new Topic(2, "topic2"));
        mTopicList.add(new Topic(3, "topic3"));
        mTopicList.add(new Topic(4, "topic4"));
        mTopicList.add(new Topic(5, "topic5"));

        mVocabularyList.add(new Vocabulary("word11", "pronun11", "mean11" ,1));
        mVocabularyList.add(new Vocabulary("word12", "pronun12", "mean12" ,1));
        mVocabularyList.add(new Vocabulary("word13", "pronun13", "mean13" ,1));
        mVocabularyList.add(new Vocabulary("word14", "pronun14", "mean14" ,1));
        mVocabularyList.add(new Vocabulary("word15", "pronun15", "mean15" ,1));

        mVocabularyList.add(new Vocabulary("word21", "pronun21", "mean21" ,2));
        mVocabularyList.add(new Vocabulary("word22", "pronun22", "mean22" ,2));
        mVocabularyList.add(new Vocabulary("word23", "pronun23", "mean23" ,2));
        mVocabularyList.add(new Vocabulary("word24", "pronun24", "mean24" ,2));
        mVocabularyList.add(new Vocabulary("word25", "pronun25", "mean25" ,2));

        mVocabularyList.add(new Vocabulary("word31", "pronun31", "mean31" ,3));
        mVocabularyList.add(new Vocabulary("word32", "pronun32", "mean32" ,3));
        mVocabularyList.add(new Vocabulary("word33", "pronun33", "mean33" ,3));
        mVocabularyList.add(new Vocabulary("word34", "pronun34", "mean34" ,3));
        mVocabularyList.add(new Vocabulary("word35", "pronun35", "mean35" ,3));

        mVocabularyList.add(new Vocabulary("word41", "pronun41", "mean41" ,4));
        mVocabularyList.add(new Vocabulary("word42", "pronun42", "mean42" ,4));
        mVocabularyList.add(new Vocabulary("word43", "pronun43", "mean43" ,4));
        mVocabularyList.add(new Vocabulary("word44", "pronun44", "mean44" ,4));
        mVocabularyList.add(new Vocabulary("word45", "pronun45", "mean45" ,4));

        mVocabularyList.add(new Vocabulary("word51", "pronun51", "mean51" ,5));
        mVocabularyList.add(new Vocabulary("word52", "pronun52", "mean52" ,5));
        mVocabularyList.add(new Vocabulary("word53", "pronun53", "mean53" ,5));
        mVocabularyList.add(new Vocabulary("word54", "pronun54", "mean54" ,5));
        mVocabularyList.add(new Vocabulary("word55", "pronun55", "mean55" ,5));

    }
}
