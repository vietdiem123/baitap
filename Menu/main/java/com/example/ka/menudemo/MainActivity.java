package com.example.ka.menudemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void optionMenu(View view){
        startActivity( new Intent(MainActivity.this, OptionMenuActivity.class));
    }

    public void btcentex(View view){
        startActivity( new Intent(MainActivity.this, ContextActivity.class));
    }
}
