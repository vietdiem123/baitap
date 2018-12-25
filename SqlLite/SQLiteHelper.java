package com.example.ka.myvocabulary.sqliteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ka.myvocabulary.models.Topic;
import com.example.ka.myvocabulary.models.Vocabulary;

/**
 * Created by KA on 12/6/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "my_management";

    public static final String TABLE_TOPIC = "topic";
    public static final String TABLE_VOCABULARY = "vocabulary";

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
                + Topic.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Topic.FIELD_NAME + " TEXT "
                + ")";

        String sqlVocabulary = " create table " + TABLE_VOCABULARY
                + " ( "
                + Vocabulary.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Vocabulary.FIELD_WORD + " TEXT, "
                + Vocabulary.FIELD_PRONUN + " TEXT, "
                + Vocabulary.FIELD_MEAN + " TEXT, "
                + Vocabulary.FIELD_TOPIC_ID + " INTEGER "
                + ")";

        sqLiteDatabase.execSQL(sqlVocabulary);
        sqLiteDatabase.execSQL(sqlTopic);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPIC);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VOCABULARY);
        onCreate(sqLiteDatabase);
    }
}
