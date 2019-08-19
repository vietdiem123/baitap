package com.example.ka.listviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.ka.listviewnangcao.R;
import com.example.ka.listviewnangcao.adapters.ImageArrayAdapter;
import com.example.ka.listviewnangcao.model.Image;
import com.example.ka.listviewnangcao.model.iConmodel;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {

    private GridView gridView;
    private ImageArrayAdapter adapter;
    private ArrayList<Image> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_con);
        gridView = (GridView)findViewById(R.id.gridview) ;

        mList = new ArrayList<>();
        mList.add(new Image(R.drawable.done, "Done"));
        mList.add(new Image(R.drawable.c, "A"));
        mList.add(new Image(R.drawable.done, "sdfasf"));
        mList.add(new Image(R.drawable.fr, "diem"));

        adapter = new ImageArrayAdapter(this, R.layout.item_icon, mList);
        gridView.setAdapter(adapter);
    }
}
