package com.example.ka.myvocabulary.models;

import com.example.ka.myvocabulary.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by KA on 11/8/2017.
 */

public class TestQuestion {
    public static final int mAnswer_None = -1;
    public static final int mAnswer_A = 0;
    public static final int mAnswer_B = 1;
    public static final int mAnswer_C = 2;
    public static final int mAnswer_D = 3;
    public static final int mAnswer_Count = 4;

    private int mCurrentAnswer_Index;

    private Vocabulary mQuestion;
    private ArrayList<Vocabulary> mListAnswer;

    public TestQuestion(){
        if(mListAnswer == null){
            mListAnswer = new ArrayList<>();
        }
    }

    public void setData(int currentIndex, ArrayList<Vocabulary> list){
        mListAnswer.clear();
        ArrayList<Vocabulary> list1 = new ArrayList<>();
        list1.addAll(list);
        mQuestion = list1.get(currentIndex);
        mListAnswer.add(list1.get(currentIndex));
        list1.remove(currentIndex);
        Random random = new Random();
        for (int i = 0; i < 3; i++){
            int n = random.nextInt(list1.size());
            mListAnswer.add(list1.get(n));
            list1.remove(n);
        }
        mListAnswer = Utils.getListVocaRandom(mListAnswer);
    }

    public Vocabulary getQuestion(){
        return mQuestion;
    }

    public Vocabulary getAnswerA(){
        return mListAnswer.get(mAnswer_A);
    }
    public Vocabulary getAnswerB(){
        return mListAnswer.get(mAnswer_B);
    }
    public Vocabulary getAnswerC(){
        return mListAnswer.get(mAnswer_C);
    }
    public Vocabulary getAnswerD(){
        return mListAnswer.get(mAnswer_D);
    }

    public void setChooseAnswer(int index){
        mCurrentAnswer_Index = index;
    }
    public int getAnswerStatus(){
        return mCurrentAnswer_Index;
    }
    public boolean isCorrect(){
        return mQuestion.equals(mListAnswer.get(mCurrentAnswer_Index));
    }
}
