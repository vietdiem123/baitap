package com.example.ka.myvocabulary;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ka.myvocabulary.models.TestQuestion;
import com.example.ka.myvocabulary.models.Vocabulary;
import com.example.ka.myvocabulary.utils.Global;

import java.util.ArrayList;

public class WritingActivity extends AppCompatActivity {

    private TextView tvThutu, tvShow, mTvCongratulation;
    private Button btNext, mBtWriting, mBtMemory, mBtAgain;
    private EditText editText;
    private ArrayList<Vocabulary> mListVoca, mListVocaDemo;
    private TestQuestion mTestQuestions ;
    private int mQuestionIndex;
    private Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();
    }
    private void init(){
       // mActivity.this;
        mQuestionIndex = 0;
        Intent intent = getIntent();
        mListVoca = (ArrayList<Vocabulary>) intent.getSerializableExtra(Global.mKey_Main_Memory_Voca);
        mListVocaDemo = mListVoca;
        mTestQuestions = new TestQuestion();
    }
    private void showQuestion(){
        tvThutu.setText((mQuestionIndex+ 1) + "/"  + mListVoca.size());
        tvShow.setText(mListVocaDemo.get(mQuestionIndex).getMean());
    }
    private void getWidgets(){
        tvShow = (TextView)findViewById(R.id.tv_writing_show);
        tvThutu = (TextView)findViewById(R.id.tv_writing_tuthu);
        btNext = (Button)findViewById(R.id.bt_writing_next);
        editText = (EditText)findViewById(R.id.edit_writing);
    }
    private void setWidgets(){

        showQuestion();
    }
    private void addWidgetsListener(){
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuestionIndex == (mListVoca.size() - 1)){
                    showdialog();
                }
                else if(editText.getText().toString().toLowerCase().equals(mListVocaDemo.get(mQuestionIndex).getWord().toLowerCase())){
                    mQuestionIndex ++;
                    showQuestion();
                }
            }
        });
    }
    private void showdialog(){
        LayoutInflater inflater = getLayoutInflater();
        View layoutHeader = inflater.inflate(R.layout.dialog_custum_header_memorytest, null);
        mTvCongratulation = (TextView)layoutHeader.findViewById(R.id.tvCongratulation);
        mTvCongratulation.setText(R.string.congratu);
        View layout = inflater.inflate(R.layout.dialog_body_memory_test, null);
        mBtAgain = (Button)layout.findViewById(R.id.bt_test_again);
        mBtMemory = (Button)layout.findViewById(R.id.bt_memory_test);
        mBtWriting = (Button)layout.findViewById(R.id.bt_writing_test);
        mBtAgain.setText(R.string.testAgain);
        mBtMemory.setText(R.string.memoryTest);
        mBtWriting.setText(R.string.listeningTest);
        AlertDialog.Builder builder = new AlertDialog.Builder(WritingActivity.this);
        builder.setCustomTitle(layoutHeader);
        builder.setView(layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        mBtWriting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mActivity.finish();
                Intent intent = new Intent(WritingActivity.this, ListeningActivity.class);
                intent.putExtra(Global.mKey_Main_Memory_Voca, mListVoca);
                startActivity(intent);

            }
        });
        mBtMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  mActivity.finish();
                Intent intent = new Intent(WritingActivity.this, MemoryTextActivity.class);
                intent.putExtra(Global.mKey_Main_Memory_Voca, mListVoca);
                startActivity(intent);

            }
        });
        mBtAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mActivity.finish();
                Intent intent = new Intent(WritingActivity.this, WritingActivity.class);
                intent.putExtra(Global.mKey_Main_Memory_Voca, mListVoca);
                startActivity(intent);
            }
        });

    }
}
