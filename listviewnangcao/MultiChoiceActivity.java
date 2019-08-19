package com.example.ka.listviewnangcao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MultiChoiceActivity extends AppCompatActivity {

    String[] str ;
    ArrayAdapter<String> adapter;
    ListView listView;
    private boolean[] mMultiChoice ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choice);
        listView = (ListView)findViewById(R.id.listview);
        str = getResources().getStringArray(R.array.Language);
        mMultiChoice = new boolean [str.length];

        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, str);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mMultiChoice[position] = ! mMultiChoice[position];
            }
        });
    }
    public void submit(View view){
        String s = "";
        for (int i = 0; i < str.length; i++){
            if(mMultiChoice[i]){
                s += str[i]+ "\n";
            }
        }
        Toast.makeText(this,
                "Selected " + s, Toast.LENGTH_SHORT).show();
    }
}
