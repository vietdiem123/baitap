package com.example.ka.myvocabulary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ka.myvocabulary.controller.TopicManager;
import com.example.ka.myvocabulary.utils.Global;

public class TopicLeanActivity extends AppCompatActivity {

    private ListView mLvTopic;
    private String[]mTopic;
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

    }
    private void init(){
        mTopic = TopicManager.getInstance().getTopicsName();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1
                , mTopic);
    }
    private void getWidgets(){
        mLvTopic = (ListView)findViewById(R.id.lvTopic);
    }
    private void setWidgets(){
        mLvTopic.setAdapter(mAdapter);
    }
    private void addWidgetsListener(){
        mLvTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentLean = new Intent(TopicLeanActivity.this, LeanActivity.class);
                intentLean.putExtra(Global.mKey_Vocabulary, position);
                startActivity(intentLean);
            }
        });
    }
}
