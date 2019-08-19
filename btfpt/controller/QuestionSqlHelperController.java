package com.example.ka.btfpt.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ka.btfpt.model.Question;
import com.example.ka.btfpt.sqliteHelper.SQLiteHelper;

import java.util.ArrayList;

public class QuestionSqlHelperController {

    private static QuestionSqlHelperController sInstance = null;
    private ArrayList<Question> mList;
    private SQLiteHelper mSqLiteHelper;
    private SQLiteDatabase mSqLiteDatabase;

    private final String[]COLUMNS = {
            Question.FIELD_ID,
            Question.FIELD_QUESTION,
            Question.FIELD_ANSWER_A,
            Question.FIELD_ANSWER_B,
            Question.FIELD_ANSWER_C,
            Question.FIELD_ANSWER_D,
            Question.FIELD_ANSWER_E,
            Question.FIELD_ANSWER
    };

    public static QuestionSqlHelperController getsInstance(){
        if(sInstance == null){
            sInstance = new QuestionSqlHelperController();
        }
        return sInstance;
    }

    private QuestionSqlHelperController(){
        mList = new ArrayList<>();
        mSqLiteHelper = SQLiteHelper.getsInstance();
    }

    public void load(){
//        faleData();
//        delete();
//        faleData();
        mSqLiteDatabase = mSqLiteHelper.getReadableDatabase();
        Cursor cursor = mSqLiteDatabase.query(SQLiteHelper.TABLE_TOPIC, COLUMNS, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(Question.FIELD_ID));
                String question = cursor.getString(cursor.getColumnIndex(Question.FIELD_QUESTION));
                String a = cursor.getString(cursor.getColumnIndex(Question.FIELD_ANSWER_A));
                String b = cursor.getString(cursor.getColumnIndex(Question.FIELD_ANSWER_B));
                String c = cursor.getString(cursor.getColumnIndex(Question.FIELD_ANSWER_C));
                String d = cursor.getString(cursor.getColumnIndex(Question.FIELD_ANSWER_D));
                String e = cursor.getString(cursor.getColumnIndex(Question.FIELD_ANSWER_E));
                String answer = cursor.getString(cursor.getColumnIndex(Question.FIELD_ANSWER));
                Question model = new Question(id, question, a, b, c, d, e, answer);
                mList.add(model);
                cursor.moveToNext();
            }
//            delete();
        }
    }

    public ArrayList<Question> getmList() {
        if(mList.isEmpty()){
            load();
        }
        return mList;
    }

    public void add(Question topic){
        mSqLiteDatabase = mSqLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Question.FIELD_QUESTION, topic.getmQuestion());
        values.put(Question.FIELD_ANSWER_A, topic.getmAnswerA());
        values.put(Question.FIELD_ANSWER_B, topic.getmAnswerB());
        values.put(Question.FIELD_ANSWER_C, topic.getmAnswerC());
        values.put(Question.FIELD_ANSWER_D, topic.getmAnswerD());
        values.put(Question.FIELD_ANSWER_E, topic.getmAnswerE());
        values.put(Question.FIELD_ANSWER, topic.getmAnswer());
        int id = (int)mSqLiteDatabase.insert(SQLiteHelper.TABLE_TOPIC, null, values);
        mSqLiteDatabase.close();
        mSqLiteHelper.close();
        topic.setmId(id);
    }

    private void faleData(){
        add(new Question( "1+1", "1","2","3","4","","2,"));
        add(new Question( "2+3", "5","2","","","","5,"));
        add(new Question( "3+3", "4","6","3","2+4","7","6,2+4,"));
        add(new Question( "4+6", "10","8","3","4","","10,"));
        add(new Question( "5+1", "8","6","3","4","","6,"));
        add(new Question( "6+1", "10","7","","","","7,"));
        add(new Question( "7+1", "16/2","11","8","4","90","8,16/2,"));
    }

    public void delete(){
        mSqLiteDatabase = mSqLiteHelper.getWritableDatabase();
        mSqLiteDatabase.delete(SQLiteHelper.TABLE_TOPIC,
                Question.FIELD_ID + " != ?", new String[]{String.valueOf(1000)});
        mSqLiteDatabase.close();
        mSqLiteHelper.close();
    }

    public ArrayList<Integer> getListID(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++){
            list.add(mList.get(i).getmId());
        }
        return list;
    }
}
