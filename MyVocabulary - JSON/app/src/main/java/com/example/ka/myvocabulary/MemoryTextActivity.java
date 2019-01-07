package com.example.ka.myvocabulary;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ka.myvocabulary.models.TestQuestion;
import com.example.ka.myvocabulary.models.Vocabulary;
import com.example.ka.myvocabulary.utils.Global;
import com.example.ka.myvocabulary.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

public class MemoryTextActivity extends AppCompatActivity {

    private TextView mTvThutu, mTvShow, mTvCongratulation;
    private RadioGroup mRadioGroup;
    private Button mBtNext, mBtWriting, mBtMemory, mBtAgain;
    private RadioButton mRadioBt1, mRadioBt2, mRadioBt3, mRadioBt4;
    private ArrayList<Vocabulary> mListVoca;
    private TestQuestion mTestQuestions ;
    private int mQuestionIndex;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_text);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();
    }

    private void init(){
        mActivity = this;
        mQuestionIndex = 0;
        Intent intent = getIntent();
        mListVoca = (ArrayList<Vocabulary>) intent.getSerializableExtra(Global.mKey_Main_Memory_Voca);
        mTestQuestions = new TestQuestion();
    }
    private void getWidgets(){
        mBtNext = (Button)findViewById(R.id.bt_memory_next);
        mTvThutu = (TextView)findViewById(R.id.tv_menory_tuthu);
        mTvShow = (TextView)findViewById(R.id.tv_menory_show);
        mRadioBt1 = (RadioButton)findViewById(R.id.radio_memory_1);
        mRadioBt2 = (RadioButton)findViewById(R.id.radio_memory_2);
        mRadioBt3 = (RadioButton)findViewById(R.id.radio_memory_3);
        mRadioBt4 = (RadioButton)findViewById(R.id.radio_memory_4);
        mRadioGroup = (RadioGroup)findViewById(R.id.radioGroup_memory);
    }
    private void setWidgets(){
        showQuestion();
    }

    private void showQuestion(){
        mTestQuestions.setData(mQuestionIndex, mListVoca);
        mTestQuestions.setChooseAnswer(TestQuestion.mAnswer_None);
        Random random = new Random();
        boolean isWord = random.nextBoolean();
        if(isWord){
            mTvShow.setText(mTestQuestions.getQuestion().getWord());
            mRadioBt1.setText(mTestQuestions.getAnswerA().getMean());
            mRadioBt2.setText(mTestQuestions.getAnswerB().getMean());
            mRadioBt3.setText(mTestQuestions.getAnswerC().getMean());
            mRadioBt4.setText(mTestQuestions.getAnswerD().getMean());
            mTvThutu.setText((mQuestionIndex + 1) + "/" + mListVoca.size());
            mRadioGroup.clearCheck();
        }
        else{
            mTvShow.setText(mTestQuestions.getQuestion().getMean());
            mRadioBt1.setText(mTestQuestions.getAnswerA().getWord());
            mRadioBt2.setText(mTestQuestions.getAnswerB().getWord());
            mRadioBt3.setText(mTestQuestions.getAnswerC().getMean());
            mRadioBt4.setText(mTestQuestions.getAnswerD().getMean());
            mTvThutu.setText((mQuestionIndex + 1) + "/" + mListVoca.size());
            mRadioGroup.clearCheck();
        }

    }

    private void addWidgetsListener(){
        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTestQuestions.getAnswerStatus() == TestQuestion.mAnswer_None){
                    Toast.makeText(getApplication(), "Chua chon", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(mQuestionIndex == mListVoca.size()-1 && mTestQuestions.isCorrect()){
                        showCongratulation();
                    }
                    if(mTestQuestions.isCorrect() && (mQuestionIndex < mListVoca.size()-1)) {
                        mQuestionIndex++;
                        showQuestion();
                    }
                }
            }
        });
    }

    private void showCongratulation(){
        LayoutInflater inflater = getLayoutInflater();
        View layoutHeader = inflater.inflate(R.layout.dialog_custum_header_memorytest, null);
        mTvCongratulation = (TextView)layoutHeader.findViewById(R.id.tvCongratulation);
        mTvCongratulation.setText(R.string.congratu);
        View layout = inflater.inflate(R.layout.dialog_body_memory_test, null);
        mBtAgain = (Button)layout.findViewById(R.id.bt_test_again);
        mBtMemory = (Button)layout.findViewById(R.id.bt_memory_test);
        mBtWriting = (Button)layout.findViewById(R.id.bt_writing_test);
        mBtAgain.setText(R.string.testAgain);
        mBtMemory.setText(R.string.listeningTest);
        mBtWriting.setText(R.string.writingTest);
        AlertDialog.Builder builder = new AlertDialog.Builder(MemoryTextActivity.this);
        builder.setCustomTitle(layoutHeader);
        builder.setView(layout);
        AlertDialog dialog = builder.create();
        dialog.show();


        mBtWriting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
                Intent intent = new Intent(MemoryTextActivity.this, WritingActivity.class);
                intent.putExtra(Global.mKey_Main_Memory_Voca, mListVoca);
                startActivity(intent);

            }
        });
        mBtMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
                Intent intent = new Intent(MemoryTextActivity.this, ListeningActivity.class);
                intent.putExtra(Global.mKey_Main_Memory_Voca, mListVoca);
                startActivity(intent);

            }
        });
        mBtAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
                Intent intent = new Intent(MemoryTextActivity.this, MemoryTextActivity.class);
                intent.putExtra(Global.mKey_Main_Memory_Voca, mListVoca);
                startActivity(intent);

            }
        });
    }


    public void checkAnswer(View view){
        switch (view.getId()){
            case R.id.radio_memory_1:
                mTestQuestions.setChooseAnswer(TestQuestion.mAnswer_A);
                break;
            case R.id.radio_memory_2:
                mTestQuestions.setChooseAnswer(TestQuestion.mAnswer_B);
                break;
            case R.id.radio_memory_3:
                mTestQuestions.setChooseAnswer(TestQuestion.mAnswer_C);
                break;
            case R.id.radio_memory_4:
                mTestQuestions.setChooseAnswer(TestQuestion.mAnswer_D);
                break;
        }
    }
}
