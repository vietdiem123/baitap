package com.example.ka.listviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SingleChoiceActivity extends AppCompatActivity {

    String[] str ;
    ArrayAdapter<String> adapter;
    ListView listView;
    private  int mCurrendChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choice);
        listView = (ListView)findViewById(R.id.listview);
        str = getResources().getStringArray(R.array.Language);

        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, str);
        listView.setAdapter(adapter);

        listView.setItemChecked(2, true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mCurrendChoice = position;
            }
        });
    }
    public void submit(View view){
        Toast.makeText(this, "Selected " + str[mCurrendChoice], Toast.LENGTH_SHORT).show();
    }
}
