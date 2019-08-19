package com.example.ka.listviewnangcao.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ka.listviewnangcao.R;
import com.example.ka.listviewnangcao.model.Contact;

import java.util.ArrayList;

/**
 * Created by KA on 10/18/2017.
 */

public class ContactArrayAdapter extends ArrayAdapter<Contact> {

    private Context mContext;
    private int mLayout;
    private ArrayList<Contact> mList;

    public ContactArrayAdapter( Context context, int resource, ArrayList<Contact> list) {
        super(context, resource, list);

        mContext = context;
        mLayout = resource;
        mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // tai vi tri positon tao
        // 1 view dua len viewGroup(parent) va tra ra
        ViewHolder viewHolder;
        if(convertView == null){

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mLayout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tvName );
            viewHolder.tvPhone = (TextView)convertView.findViewById(R.id.tvPhone );
            convertView.setTag(viewHolder);

        }
        else{
            System.out.println(" Re- use");
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Contact contact = mList.get(position);
        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvPhone.setText(contact.getPhone());

        return convertView;
    }
    public class ViewHolder{
        public TextView tvName, tvPhone;
    }
}
