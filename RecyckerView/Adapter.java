package com.example.ka.recycleview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ka.recycleview.R;
import com.example.ka.recycleview.models.Contact;

import java.util.ArrayList;

/**
 * Created by KA on 1/18/2018.
 */

public class ContactAdapter  extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    public final static int MENU_ITEM_ADD = 1000;
    public final static int MENU_ITEM_EDIT = 1001;
    public final static int MENU_ITEM_DELETE = 1002;

    private Context mContext;
    private int mLayout;
    private ArrayList<Contact> mList;
    public ContactAdapter(Context context, int layout, ArrayList<Contact> list){
        this.mContext = context;
        this.mLayout = layout;
        this.mList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(mLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = mList.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvPhone.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        public TextView tvName, tvPhone;

        public ViewHolder(View itemView){
            super(itemView);
            tvName = itemView.findViewById(R.id.tvname);
            tvPhone = itemView.findViewById(R.id.tvphone);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(tvName.getText());
            menu.add(0, MENU_ITEM_ADD, 0, "Add");
            menu.add(0, MENU_ITEM_EDIT, 0, "Edit");
            menu.add(0, MENU_ITEM_DELETE, 0, "Delete");
        }
    }
}
