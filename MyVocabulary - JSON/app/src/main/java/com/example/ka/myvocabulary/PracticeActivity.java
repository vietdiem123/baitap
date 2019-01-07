package com.example.ka.myvocabulary;

import android.content.DialogInterface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ka.myvocabulary.adapter.VocabularyPagerAdapter;
import com.example.ka.myvocabulary.controller.TopicManager;
import com.example.ka.myvocabulary.models.Topic;
import com.example.ka.myvocabulary.models.Vocabulary;
import com.example.ka.myvocabulary.utils.TextSpeech;
import com.example.ka.myvocabulary.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

public class PracticeActivity extends AppCompatActivity {

    private boolean[] mSelectedTopic, mSelectedBackup;
    private String[]topicsName;
    private TextView tvTopic, tvThutu;

    private CheckBox mCbWord, mCbPronun, mCbMean;
    private Button mBtSpeech;

    private ArrayList<Topic> mListTopic;

    private ViewPager mViewPager;
    private PagerAdapter mAdapterPager;
    private ArrayList<Vocabulary> mListVoca, mListVocaRandom;

    private int vocabularyID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();
        selectedPractice();

    }
    public void LinearTopics(View view){

        for (int i =0; i < mSelectedTopic.length; i++){
            mSelectedBackup[i] = mSelectedTopic[i];
        }
        final LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.dialog_custum_header, null);
        TextView tvTitle = (TextView)layout.findViewById(R.id.tv);
        final CheckBox checkBox = (CheckBox)layout.findViewById(R.id.cbSelected);
        tvTitle.setText(R.string.topic);
        checkBox.setChecked(isCheckAll(mSelectedTopic));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMultiChoiceItems(topicsName, mSelectedTopic, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkBox.setChecked(isCheckAll(mSelectedTopic));
            }
        });
        builder.setCustomTitle(layout);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = "";
                for(int i = 0; i < mSelectedTopic.length; i++){
                    if(mSelectedTopic[i]){
                        str += topicsName[i] + " ";
                    }
                }
                if(str.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.notTopic, Toast.LENGTH_SHORT).show();
                }
                tvTopic.setText(str);
                selectedPractice();
                tvThutu.setText(1+"/" + mListVoca.size());

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i =0; i < mSelectedTopic.length; i++){
                    mSelectedTopic[i] = mSelectedBackup[i];
                }
            }
        });
        final AlertDialog dialogg = builder.create();
        dialogg.show();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView listView = dialogg.getListView();
                boolean isChecked = checkBox.isChecked();
                for(int i = 0; i < listView.getCount(); i++ ){
                    listView.setItemChecked(i, isChecked);
                    mSelectedTopic[i] = isChecked;
                }
            }
        });

    }

    public void cb_onclick(View view){

        mAdapterPager = new VocabularyPagerAdapter(this, R.layout.item_practice, mListVocaRandom,
                mCbWord.isChecked(), mCbPronun.isChecked(), mCbMean.isChecked());
        mViewPager.setAdapter(mAdapterPager);
        mViewPager.setCurrentItem(vocabularyID);

    }

    private boolean isCheckAll(boolean []check){
        for (int i = 0; i < check.length; i++){
            if(!check[i]){
                return false;
            }
        }
        return true;
    }
    private void selectedPractice(){

        mListVoca = new ArrayList<>();
        for (int i =0; i < mSelectedTopic.length; i++){
            if(mSelectedTopic[i]){
                ArrayList<Vocabulary> listVoca = TopicManager.getInstance().getVocabularies(mListTopic.get(i));
                mListVoca.addAll(listVoca);
            }
        }

        mListVocaRandom = Utils.getListVocaRandom(mListVoca);
        mAdapterPager = new VocabularyPagerAdapter(this, R.layout.item_practice, mListVocaRandom
                , mCbWord.isChecked(), mCbPronun.isChecked(), mCbMean.isChecked());
        mViewPager.setAdapter(mAdapterPager);
    }

    private void init(){
        mListTopic = TopicManager.getInstance().getTopicList();
        topicsName = TopicManager.getInstance().getTopicsName();
        mSelectedTopic = new boolean[topicsName.length];
        mSelectedBackup = new boolean[topicsName.length];
        //mListVoca = new ArrayList<>();
        mSelectedTopic[0] =true;
    }

    private void getWidgets(){
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        tvTopic = (TextView)findViewById(R.id.tvNumberPractive);
        tvThutu = (TextView)findViewById(R.id.tvPractive);
        mCbWord = (CheckBox)findViewById(R.id.cbWord);
        mCbPronun = (CheckBox)findViewById(R.id.cbPronun);
        mCbMean = (CheckBox)findViewById(R.id.cbMean);
        mBtSpeech = (Button)findViewById(R.id.btSpeechPractice);
        selectedPractice();

    }
    private void setWidgets(){
       // mViewPager.setAdapter(mAdapterPager);
        tvTopic.setText(topicsName[0]);
    }
    private void addWidgetsListener(){
        mBtSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextSpeech.getsInstance().speak(  mListVocaRandom.get(vocabularyID).getWord());

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvThutu.setText((position+1)+"/" +  mListVoca.size());
                vocabularyID = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
