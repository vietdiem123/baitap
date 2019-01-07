package com.example.ka.menudemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ContextActivity extends AppCompatActivity {

    private String [] array ;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private Button btA, btB;

    private ActionMode.Callback mActionModeCallback;
    private ActionMode mActionMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        array = getResources().getStringArray(R.array.menu);
        listView = (ListView)findViewById(R.id.listview);
        btA = (Button)findViewById(R.id.bt1);
        btB = (Button)findViewById(R.id.bt2);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
        registerForContextMenu(btA);
        registerForContextMenu(btB);

        mActionModeCallback = new ActionMode.Callback() {
            @Override // menu
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                MenuInflater inflater = getMenuInflater();
//                inflater.inflate(R.menu.menu_action_model, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                mActionMode = null;
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
               // Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                if(item.getItemId() == R.id.item3){
                    mode.finish();
                }
                return false;
            }

            @Override  // huy
            public void onDestroyActionMode(ActionMode mode) {
                mActionMode = null;
            }
        };

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {// mActionModeCallback dung trong listView
//
//                if(mActionMode != null){
//                    return false;
//                }
//                else {
//                    mActionMode = startActionMode(mActionModeCallback);
//                    return false;
//                }
//            }
//        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        if(v.getId() == R.id.bt1){
            inflater.inflate(R.menu.menu_a, menu);
            menu.setHeaderTitle("Setting for A");
        }
        if(v.getId() == R.id.bt2) {
            inflater.inflate(R.menu.menu_option, menu);
            menu.setHeaderTitle("Setting for B");
        }
        if(v.getId() == R.id.listview){
            inflater.inflate(R.menu.menu_option, menu);
            menu.setHeaderTitle("List view");
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    public void popupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_a, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
