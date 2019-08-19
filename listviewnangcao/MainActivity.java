package com.example.ka.listviewnangcao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void singleChoice(View view){
        Intent intent = new Intent(MainActivity.this, SingleChoiceActivity.class);
        startActivity(intent);
    }

    public void multiChoice(View view){
        Intent intent = new Intent(MainActivity.this, MultiChoiceActivity.class);
        startActivity(intent);
    }

    public void custom(View view){
        Intent intent = new Intent(MainActivity.this, CustomActivity.class);
        startActivity(intent);
    }

    public void image(View view){
        Intent intent = new Intent(this, iConActivity.class);
        startActivity(intent);
    }

    public void Imageview(View view){
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }

}
