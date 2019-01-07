package com.example.ka.myvocabulary.models;

import java.io.Serializable;

/**
 * Created by KA on 10/22/2017.
 */

public class Vocabulary implements Serializable{
    public static final String VOCABULARY = "vocabulary";
    public static final String WORD = "word";
    public static final String PRONUN = "pronun";
    public static final String MEAN = "mean";
    public static final String TOPICID = "topicId";
    private String mWord;
    private String mPronunciation;
    private String mMean;
    private int mTopicId;

    public Vocabulary(String mWord, String mPronunciation, String mMean, int mTopicId) {
        this.mWord = mWord;
        this.mPronunciation = mPronunciation;
        this.mMean = mMean;
        this.mTopicId = mTopicId;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String mWord) {
        this.mWord = mWord;
    }

    public String getPronunciation() {
        return mPronunciation;
    }

    public void setPronunciation(String mPronunciation) {
        this.mPronunciation = mPronunciation;
    }

    public String getMean() {
        return mMean;
    }

    public void setMean(String mMean) {
        this.mMean = mMean;
    }

    public int getTopicId() {
        return mTopicId;
    }

    public void setTopicId(int mTopicId) {
        this.mTopicId = mTopicId;
    }

    @Override
    public boolean equals(Object obj) {
        Vocabulary vocabulary = (Vocabulary)obj;
        return (mWord.equals(vocabulary.getWord()) && mPronunciation.equals(vocabulary.getPronunciation()) && mMean.equals(vocabulary.getMean()));
    }



    @Override
    public String toString() {
        return "Vocabulary{" +
                "mWord='" + mWord + '\'' +
                ", mPronunciation='" + mPronunciation + '\'' +
                ", mMean='" + mMean + '\'' +
                ", mTopicId=" + mTopicId +
                '}';
    }
}
