package com.example.ka.listviewnangcao;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ka.listviewnangcao.adapters.ContactArrayAdapter;
import com.example.ka.listviewnangcao.model.Contact;

import java.util.ArrayList;

public class CustomActivity extends AppCompatActivity {
    private ListView listView;
    private ContactArrayAdapter mAdapter;
    private ArrayList<Contact> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        mList = new ArrayList<>();

        mList.add(new Contact("Boss", "023492049"));
        mList.add(new Contact("Big Boss", "0345234049"));
        mList.add(new Contact("Big Big Boss", "9345234049"));


        listView = (ListView)findViewById(R.id.listviewCustom);
        mAdapter = new ContactArrayAdapter(this, R.layout.item_contact, mList);
        
        listView.setAdapter(mAdapter);
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        mList.add(new Contact("Big Big Boss", "9345234049"));
                        Thread.sleep(3000);
                       // mAdapter.notifyDataSetChanged();
                        new diem().execute();
                    } catch(Exception e) {
                        System.err.println(e);
                    }
                }
            }
        });
        b.start();



    }
    class diem extends AsyncTask<Void, Void, Void >{

        @Override
        protected Void doInBackground(Void... voids) {

            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
//            mList.add(new Contact("Big Big Boss", "9345234049"));
//            mAdapter.notifyDataSetChanged();
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Hàm này được thực hiện khi tiến trình kết thúc
            //Ở đây mình thông báo là đã "Finshed" để người dùng biết

            mAdapter.notifyDataSetChanged();
            Toast.makeText(CustomActivity.this, "diem", Toast.LENGTH_SHORT).show();
        }
    }
}
