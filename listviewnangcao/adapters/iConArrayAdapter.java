package com.example.ka.listviewnangcao.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ka.listviewnangcao.R;
import com.example.ka.listviewnangcao.model.Contact;
import com.example.ka.listviewnangcao.model.iConmodel;

import java.util.ArrayList;

/**
 * Created by KA on 10/18/2017.
 */

public class iConArrayAdapter extends ArrayAdapter<iConmodel> {

    private Context mContext;
    private  int mLayout;
    private ArrayList<iConmodel> mList;

    public iConArrayAdapter(Context context, int resource, ArrayList<iConmodel> list) {
        super(context, resource, list);
        mContext = context;
        mLayout = resource;
        mList = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder ;
        if(convertView == null){
            System.out.println("new create");
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mLayout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.imgImg = (ImageView)convertView.findViewById(R.id.imageview );
            viewHolder.tvText = (TextView)convertView.findViewById(R.id.textviewIcon );
            convertView.setTag(viewHolder);
        }
        else{
            System.out.println("Re - use");
            viewHolder = (ViewHolder)convertView.getTag();
        }

        iConmodel img = mList.get(position);
        viewHolder.imgImg.setBackgroundResource(img.getImg());
        viewHolder.tvText.setText(img.getText());

        return convertView;
    }
    public class ViewHolder{
        TextView tvText;
        ImageView imgImg;
    }
}
