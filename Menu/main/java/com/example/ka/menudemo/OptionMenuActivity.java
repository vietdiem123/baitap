package com.example.ka.menudemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by KA on 10/15/2017.
 */

public class OptionMenuActivity extends AppCompatActivity {
    private Menu menu1, menu2;
    private boolean isCheckAll = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_option_menu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();

        switch (id){
            case R.id.group1_item2:
            case R.id.group1_item1:
            case R.id.group2_item1:
            case R.id.group2_item2:
                boolean ischeck = item.isChecked();
                item.setChecked(!ischeck);
                return true;
            case R.id.checkAll:
                isCheckAll = true;
                item.setChecked(!isCheckAll);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override   // load lai onCreateoptionMenu de cos gia tri
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);

    }
}
