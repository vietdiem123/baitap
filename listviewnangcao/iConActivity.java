package com.example.ka.listviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import com.example.ka.listviewnangcao.adapters.ContactArrayAdapter;
import com.example.ka.listviewnangcao.adapters.iConArrayAdapter;
import com.example.ka.listviewnangcao.model.Contact;
import com.example.ka.listviewnangcao.model.iConmodel;

import java.util.ArrayList;

public class iConActivity extends AppCompatActivity {

    private GridView gridView;
    private iConArrayAdapter mAdapter;
    private ArrayList<iConmodel> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_con);

        mList = new ArrayList<>();
        mList.add(new iConmodel(R.drawable.c, "A"));
        mList.add(new iConmodel(R.drawable.done, "sdfasf"));
        mList.add(new iConmodel(R.drawable.fr, "diem"));
        mList.add(new iConmodel(R.drawable.c, "A"));
        mList.add(new iConmodel(R.drawable.done, "sdfasf"));
        mList.add(new iConmodel(R.drawable.fr, "diem"));
        mList.add(new iConmodel(R.drawable.c, "A"));
        mList.add(new iConmodel(R.drawable.done, "sdfasf"));
        mList.add(new iConmodel(R.drawable.fr, "diem"));
        mList.add(new iConmodel(R.drawable.c, "A"));
        mList.add(new iConmodel(R.drawable.done, "sdfasf"));
        mList.add(new iConmodel(R.drawable.fr, "diem"));
        mList.add(new iConmodel(R.drawable.c, "A"));
        mList.add(new iConmodel(R.drawable.done, "sdfasf"));
        mList.add(new iConmodel(R.drawable.fr, "diem"));

        gridView = (GridView)findViewById(R.id.gridview);
        mAdapter = new iConArrayAdapter(this, R.layout.item_icon, mList);

        gridView.setAdapter(mAdapter);

    }
}
