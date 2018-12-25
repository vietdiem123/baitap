package com.example.ka.recycleview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ka.recycleview.adapters.ContactAdapter;
import com.example.ka.recycleview.listener.RecyclerTouchListener;
import com.example.ka.recycleview.models.Contact;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ArrayList<Contact> list = new ArrayList();
        list.add(new Contact("tran viet a", "01223234234"));
        list.add(new Contact("tran viet b",  "01223234234"));
        list.add(new Contact("tran viet c", "01223234234"));
        list.add(new Contact("tran viet d", "01223234234"));
        list.add(new Contact("tran viet e", "01223234234"));

        ContactAdapter adapter = new ContactAdapter(this, R.layout.item_contact, list);
      //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        RecyclerTouchListener.ClickListener clickListener = new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Click " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "LongClick " + position, Toast.LENGTH_SHORT).show();
                System.out.println("long Click " + position);
            }
        };
        RecyclerTouchListener listener = new RecyclerTouchListener(this, recyclerView, clickListener);
        recyclerView.addOnItemTouchListener(listener);
      //  recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case ContactAdapter.MENU_ITEM_ADD :
                Toast.makeText(getApplicationContext(), "add " , Toast.LENGTH_SHORT).show();
                break;
            case ContactAdapter.MENU_ITEM_EDIT:
                Toast.makeText(getApplicationContext(), "edit " , Toast.LENGTH_SHORT).show();
                break;
            case ContactAdapter.MENU_ITEM_DELETE:
                Toast.makeText(getApplicationContext(), "delete " , Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}