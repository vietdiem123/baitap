package com.example.ka.myvocabulary;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ka.myvocabulary.configs.Appconfigs;
import com.example.ka.myvocabulary.controller.TopicManager;
import com.example.ka.myvocabulary.models.Topic;
import com.example.ka.myvocabulary.models.Vocabulary;
import com.example.ka.myvocabulary.utils.Global;
import com.example.ka.myvocabulary.utils.TextSpeech;
import com.example.ka.myvocabulary.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private String[]mLanguage ;
    private String[]topicsName;
    private boolean[] mSelectedTopic, mSelectedBackup;
    private ArrayList<Topic> mListTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        Download download = new Download();
        download.execute(getString(R.string.data_url));

        Appconfigs.init(this);
        Utils.setLanguage(this, Appconfigs.getInstance().getLanguage());
        setContentView(R.layout.activity_main);
        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

    }

    public void btPractice(View view){
        Intent intent = new Intent(MainActivity.this, PracticeActivity.class);
        startActivity(intent);
    }

    public void btTest(View view){

        AlertDialog.Builder sBuilder = new AlertDialog.Builder(MainActivity.this);
        sBuilder.setTitle(R.string.test)
                .setCancelable(false)
                .setItems(mLanguage, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showChoiceTopics(which);
                    }
                });
        AlertDialog dialog = sBuilder.create();
        dialog.show();
    }

    public void btLean(View view){
        Intent intent = new Intent(this, TopicLeanActivity.class);
        startActivity(intent);
    }
    public void changelanguage(View view){
        String []str = getResources().getStringArray(R.array.language);
        AlertDialog.Builder sBuilder = new AlertDialog.Builder(MainActivity.this);
        sBuilder.setTitle(R.string.language);
        sBuilder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String language = which == Global.intLanguageEN ? "en" : "vi";
                Utils.setLanguage(MainActivity.this, language);
                Appconfigs.getInstance().setLanguage(language);
                setContentView(R.layout.activity_main);
                getWidgets();
                setWidgets();
                addWidgetsListener();
            }
        });
        sBuilder.create()
                .show();
    }

    private void showChoiceTopics(final int testType){
        for (int i =0; i < mSelectedTopic.length; i++){
            mSelectedBackup[i] = mSelectedTopic[i];
        }
        final LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.dialog_custum_header, null);
        TextView tvTitle = (TextView)layout.findViewById(R.id.tv);
        final CheckBox checkBox = (CheckBox)layout.findViewById(R.id.cbSelected);
        tvTitle.setText(R.string.topic);
        checkBox.setChecked(isCheckAll(mSelectedTopic));
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                ArrayList<Vocabulary> list = new ArrayList<Vocabulary>();
                for(int i =0; i< mSelectedTopic.length; i++){
                    if(mSelectedTopic[i]){
                        list.addAll(TopicManager.getInstance().getVocabularies(mListTopic.get(i)));
                    }
                }
                if(list.size()!=0) {
                    Intent intent = null;
                    switch (testType){
                        case Global.intMemory:
                            intent = new Intent(MainActivity.this, MemoryTextActivity.class);
                            break;
                        case Global.intListen:
                            intent = new Intent(MainActivity.this, ListeningActivity.class);
                            break;
                        case Global.intWrite:
                            intent = new Intent(MainActivity.this, WritingActivity.class);
                            break;

                    }
                    if(intent != null) {
                        intent.putExtra(Global.mKey_Main_Memory_Voca, list);
                        startActivity(intent);
                    }
                }
                else Toast.makeText(getApplication(), R.string.notTopic, Toast.LENGTH_SHORT).show();
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

    private boolean isCheckAll(boolean []check){
        for (int i = 0; i < check.length; i++){
            if(!check[i]){
                return false;
            }
        }
        return true;
    }
    private void init(){
        TextSpeech.init(this);
        TextSpeech.getsInstance().speak("");
        mLanguage = getResources().getStringArray(R.array.test_type);
        topicsName = TopicManager.getInstance().getTopicsName();
        mSelectedTopic = new boolean[topicsName.length];
        mSelectedBackup = new boolean[topicsName.length];
        mListTopic = TopicManager.getInstance().getTopicList();
    }
    private void getWidgets(){

    }
    private void setWidgets(){

    }
    private void addWidgetsListener(){

    }

    private class Download extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) { // lam owr nen(ngam)
            String strUrl = strings[0];
            try {
                URL url = new URL(strUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                String content = "";
                while (line!= null){
                    content += line + "\n";
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                return content;
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {  // dien ra trong qua trinh down
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) { // ket thuc
            super.onPostExecute(s);
            progressDialog.dismiss();
            TopicManager.getInstance().load(s);
        }
    }
}
