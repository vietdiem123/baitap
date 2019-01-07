package com.example.ka.myvocabulary;

import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ka.myvocabulary.adapter.VocabularyArrayAdapter;
import com.example.ka.myvocabulary.controller.TopicManager;
import com.example.ka.myvocabulary.models.Topic;
import com.example.ka.myvocabulary.models.Vocabulary;
import com.example.ka.myvocabulary.utils.Global;

import java.util.ArrayList;
import java.util.Locale;

public class LeanActivity extends AppCompatActivity {

    private ListView mListView;
    private VocabularyArrayAdapter mAdapter;
    private ArrayList<Vocabulary> mList, mListNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lean);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();
    }
    private void init(){
        ArrayList<Topic> topics = TopicManager.getInstance().getTopicList();
        Intent intent = getIntent();
        int index = intent.getIntExtra(Global.mKey_Vocabulary, 0);
        Topic topic = topics.get(index);
        setTitle(topic.getName());

        mList = TopicManager.getInstance().getVocabularies(topic);
        mListNew = new ArrayList<Vocabulary>();

        mAdapter = new VocabularyArrayAdapter(this, R.layout.item_vocabulary, mList);
    }
    private void getWidgets(){
        mListView = (ListView)findViewById(R.id.lvVocabulary);
    }
    private void setWidgets(){
        mListView.setAdapter(mAdapter);
    }
    private void addWidgetsListener(){

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position >= 3) {
                    String []str = getResources().getStringArray(R.array.test_type);
                    AlertDialog.Builder sBuilder = new AlertDialog.Builder(LeanActivity.this);
                    String title = "Test from " + mList.get(0).getWord() + " to " + mList.get(position).getWord();
                    sBuilder.setTitle(title);
                    for (int i =0; i<= position; i++){
                        mListNew.add(mList.get(i));
                    }
                    sBuilder.setItems(str, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = null;
                            switch (which){
                                case 0:
                                    intent = new Intent(LeanActivity.this, MemoryTextActivity.class);
                                    break;
                                case 1:
                                    intent = new Intent(LeanActivity.this, ListeningActivity.class);
                                    break;
                                case 2:
                                    intent = new Intent(LeanActivity.this, WritingActivity.class);
                                    break;

                            }
                            if(intent != null) {
                                intent.putExtra(Global.mKey_Main_Memory_Voca, mListNew);
                                startActivity(intent);
                            }
                        }
                    })
                    .create()
                    .show();
                }
            }
        });
    }
}
