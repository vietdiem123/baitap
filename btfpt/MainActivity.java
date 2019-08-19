package com.example.ka.btfpt;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.ka.btfpt.controller.QuestionSqlHelperController;
import com.example.ka.btfpt.fragment.FragmentDetail;
import com.example.ka.btfpt.fragment.FragmentList;
import com.example.ka.btfpt.fragment.FragmentResult;
import com.example.ka.btfpt.interface_fragment.InterfaceFragment;
import com.example.ka.btfpt.model.Question;
import com.example.ka.btfpt.sqliteHelper.SQLiteHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements InterfaceFragment {

    private static final String TAG = MainActivity.class.getSimpleName();

    FragmentTransaction transaction;
    QuestionSqlHelperController contactOperation;
    FragmentList mFragmentList;
    FragmentDetail mFragmentDetail;
    ArrayList<Integer> mListPosition;
    ArrayList<Question> mListAnswer;
    Question q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        SQLiteHelper.init(this);

        if(savedInstanceState!=null){
            q = savedInstanceState.getParcelable("key_question");
            mListPosition = savedInstanceState.getIntegerArrayList("key_list_position");
            mListAnswer = savedInstanceState.getParcelableArrayList("key_list_answer");
        } else {
            mListPosition = new ArrayList<>();
            mListAnswer = new ArrayList<>();
        }
        init(q, mListPosition);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("key_question", q);
        outState.putIntegerArrayList("key_list_position", mListPosition);
        outState.putParcelableArrayList("key_list_answer", mListAnswer);
        Log.d(TAG, "onSaveInstanceState: ");
    }


    @Override
    public void onClickRecycler(Question q) {
        this.q = q;

        FragmentDetail fragmentDetail = new FragmentDetail();
        FragmentList fragmentList = new FragmentList();
        Bundle bundle = new Bundle();

        bundle.putParcelable("key", q);
        bundle.putIntegerArrayList("key_position", mListPosition);
        bundle.putParcelableArrayList("key_answer", mListAnswer);

        fragmentList.setArguments(bundle);
        fragmentDetail.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        if(frameLayout != null){
            transaction.replace(R.id.frame_layout, fragmentDetail);
            transaction.addToBackStack(null);
        } else {
            transaction.replace(R.id.frame_list_land, fragmentList);
            transaction.replace(R.id.frame_question_land, fragmentDetail);
        }

        transaction.commit();
    }

    @Override
    public void onClickNext(int i, Question q, Question questionAnswer) {
        this.q = q;
        int vtri = -1;
        for (int j = 0; j < mListAnswer.size(); j++){
            if(mListAnswer.get(j).getmId() == questionAnswer.getmId()){
                vtri = j;
                break;
            }
        }
        if(vtri != -1){  // trung
            mListAnswer.remove(vtri);
            mListAnswer.add(vtri, questionAnswer);
        } else { // k trung
            mListAnswer.add(questionAnswer);
        }

        Bundle bundle  = new Bundle();
        FragmentList fragmentList = new FragmentList();
        mListPosition.add(i);
        bundle.putIntegerArrayList("key_position", mListPosition);
        fragmentList.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        if(frameLayout != null){

        } else {
            transaction.replace(R.id.frame_list_land, fragmentList);
            transaction.commit();
        }
    }

    @Override
    public void onClickSubmit() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        FragmentResult fragmentResult = new FragmentResult();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("key_submit", mListAnswer);

        fragmentResult.setArguments(bundle);
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        if(frameLayout != null){
            transaction.replace(R.id.frame_layout, fragmentResult);
            transaction.addToBackStack(null);
        } else {
            transaction.replace(R.id.frame_question_land, fragmentResult);
        }
        transaction.commit();
    }

    private void beginTransition( Question question, ArrayList<Integer> list){

        Bundle bundle = new Bundle();
        if(question == null){
            question = contactOperation.getmList().get(0);
            bundle.putParcelable("key", question);
        }
        bundle.putParcelable("key", question);
        bundle.putIntegerArrayList("key_position", list);

        mFragmentList.setArguments(bundle);
        mFragmentDetail.setArguments(bundle);
        transaction.replace(R.id.frame_list_land, mFragmentList);
        transaction.replace(R.id.frame_question_land, mFragmentDetail);
    }
    private void init(Question question, ArrayList<Integer> list){
        contactOperation = QuestionSqlHelperController.getsInstance();
        transaction = getFragmentManager().beginTransaction();
        mFragmentList = new FragmentList();
        mFragmentDetail = new FragmentDetail();
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("key_position", list);
        mFragmentList.setArguments(bundle);

        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frame_layout);

        if(frameLayout != null){
            transaction.replace(R.id.frame_layout, mFragmentList);
        } else {
            beginTransition(question, list);
        }

        transaction.commit();
    }

}
