package com.example.ka.listviewnangcao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ka.listviewnangcao.R;
import com.example.ka.listviewnangcao.model.Contact;
import com.example.ka.listviewnangcao.model.Image;

import java.util.ArrayList;

/**
 * Created by KA on 10/22/2017.
 */

public class ImageArrayAdapter extends ArrayAdapter<Image> {
    private Context mContext;
    private int mLayout;
    private ArrayList<Image> mList;
    public ImageArrayAdapter(Context context, int resource, ArrayList<Image> list) {
        super(context, resource);
        mContext = context;
        mLayout = resource;
        mList = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){

            System.out.println("new create");
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mLayout, parent, false);

        }
        else{
            System.out.println(" Re- use");
        }

        ImageView imgvid = (ImageView)convertView.findViewById(R.id.imageview);
        TextView tvName = (TextView)convertView.findViewById(R.id.textviewIcon);

        Image image = mList.get(position);
        imgvid.setBackgroundResource(image.getId());
        tvName.setText(image.getName());

        return convertView;
    }
    public class ViewHolder{
        public TextView tvName, tvPhon;
    }
}
