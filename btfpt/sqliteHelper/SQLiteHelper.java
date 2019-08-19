package com.example.ka.btfpt.sqliteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ka.btfpt.model.Question;

/**
 * Created by KA on 12/6/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "question_management";

    public static final String TABLE_TOPIC = "question2";

    private static Context mContext;
    private static SQLiteHelper sInstance = null;

    public static void init(Context context){
        mContext = context;
    }

    public static SQLiteHelper getsInstance(){
        if(sInstance == null){
            sInstance = new SQLiteHelper();
        }
        return sInstance;
    }

    private SQLiteHelper() {
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTopic = " create table " + TABLE_TOPIC
                + " ( "
                + Question.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Question.FIELD_QUESTION + " TEXT, "
                + Question.FIELD_ANSWER_A + " TEXT, "
                + Question.FIELD_ANSWER_B + " TEXT, "
                + Question.FIELD_ANSWER_C + " TEXT, "
                + Question.FIELD_ANSWER_D + " TEXT, "
                + Question.FIELD_ANSWER_E + " TEXT, "
                + Question.FIELD_ANSWER + " TEXT "
                + ")";

        sqLiteDatabase.execSQL(sqlTopic);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPIC);
        onCreate(sqLiteDatabase);
    }
}
