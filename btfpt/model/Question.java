package com.example.ka.btfpt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    public static final String FIELD_ID = "id";
    public static final String FIELD_QUESTION = "question";
    public static final String FIELD_ANSWER_A = "answera";
    public static final String FIELD_ANSWER_B = "answerb";
    public static final String FIELD_ANSWER_C = "answerc";
    public static final String FIELD_ANSWER_D = "answerd";
    public static final String FIELD_ANSWER_E = "answere";
    public static final String FIELD_ANSWER = "answer";

    private int mId;
    private String mQuestion;
    private String mAnswerA;
    private String mAnswerB;
    private String mAnswerC;
    private String mAnswerD;
    private String mAnswerE;
    private String mAnswer;
    private String mAnswerUser;


    public Question( String mQuestion, String mAnswerA, String mAnswerB, String mAnswerC, String mAnswerD, String mAnswerE, String mAnswer) {
        this.mQuestion = mQuestion;
        this.mAnswerA = mAnswerA;
        this.mAnswerB = mAnswerB;
        this.mAnswerC = mAnswerC;
        this.mAnswerD = mAnswerD;
        this.mAnswerE = mAnswerE;
        this.mAnswer = mAnswer;
    }

    public Question(int mId, String mQuestion, String mAnswerA, String mAnswerB, String mAnswerC, String mAnswerD, String mAnswerE, String mAnswer) {
        this.mId = mId;
        this.mQuestion = mQuestion;
        this.mAnswerA = mAnswerA;
        this.mAnswerB = mAnswerB;
        this.mAnswerC = mAnswerC;
        this.mAnswerD = mAnswerD;
        this.mAnswerE = mAnswerE;
        this.mAnswer = mAnswer;
    }


    public Question(String mQuestion, String mAnswerA, String mAnswerB, String mAnswerC, String mAnswerD, String mAnswerE, String mAnswer, String mAnswerUser) {
        this.mQuestion = mQuestion;
        this.mAnswerA = mAnswerA;
        this.mAnswerB = mAnswerB;
        this.mAnswerC = mAnswerC;
        this.mAnswerD = mAnswerD;
        this.mAnswerE = mAnswerE;
        this.mAnswer = mAnswer;
        this.mAnswerUser = mAnswerUser;
    }

    public Question(int mId, String mQuestion, String mAnswerA, String mAnswerB, String mAnswerC, String mAnswerD, String mAnswerE, String mAnswer, String mAnswerUser) {
        this.mId = mId;
        this.mQuestion = mQuestion;
        this.mAnswerA = mAnswerA;
        this.mAnswerB = mAnswerB;
        this.mAnswerC = mAnswerC;
        this.mAnswerD = mAnswerD;
        this.mAnswerE = mAnswerE;
        this.mAnswer = mAnswer;
        this.mAnswerUser = mAnswerUser;
    }

    public String getmAnswerUser() {
        return mAnswerUser;
    }

    public void setmAnswerUser(String mAnswerUser) {
        this.mAnswerUser = mAnswerUser;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmAnswerA() {
        return mAnswerA;
    }

    public void setmAnswerA(String mAnswerA) {
        this.mAnswerA = mAnswerA;
    }

    public String getmAnswerB() {
        return mAnswerB;
    }

    public void setmAnswerB(String mAnswerB) {
        this.mAnswerB = mAnswerB;
    }

    public String getmAnswerC() {
        return mAnswerC;
    }

    public void setmAnswerC(String mAnswerC) {
        this.mAnswerC = mAnswerC;
    }

    public String getmAnswerD() {
        return mAnswerD;
    }

    public void setmAnswerD(String mAnswerD) {
        this.mAnswerD = mAnswerD;
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public String getmAnswerE() {
        return mAnswerE;
    }

    public void setmAnswerE(String mAnswerE) {
        this.mAnswerE = mAnswerE;
    }

    protected Question(Parcel in) {
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
