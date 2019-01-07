package com.example.ka.myvocabulary.models;

import java.io.Serializable;

/**
 * Created by KA on 10/22/2017.
 */

public class Topic implements Serializable{
    public static final String TOPICID = "id";
    public static final String TOPICNAME = "name";
    public static final String TOPIC = "topic";

    private int mId;
    private String mName;

    public Topic(int mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                '}';
    }
}
