package com.example.ka.myvocabulary.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ka.myvocabulary.R;
import com.example.ka.myvocabulary.models.Vocabulary;
import com.example.ka.myvocabulary.utils.TextSpeech;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by KA on 10/25/2017.
 */

public class VocabularyArrayAdapter extends ArrayAdapter<Vocabulary> {
    private Context mContext;
    private int mLayout;
    private ArrayList<Vocabulary> mList;

    public VocabularyArrayAdapter(Context context, int resource, ArrayList<Vocabulary> list) {
        super(context, resource, list);

        mContext = context;
        mLayout = resource;
        mList = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mLayout, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.tvWord = (TextView)convertView.findViewById(R.id.tvWord );
            viewHolder.tvPronunciation = (TextView)convertView.findViewById(R.id.tvPronunciation );
            viewHolder.tvMean = (TextView)convertView.findViewById(R.id.tvMean);
            viewHolder.btSpeech = (Button)convertView.findViewById(R.id.btSpeech);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final Vocabulary voca = mList.get(position);
        viewHolder.tvWord.setText(voca.getWord());
        viewHolder.tvPronunciation.setText(voca.getPronunciation());
        viewHolder.tvMean.setText(voca.getMean());
        viewHolder.btSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextSpeech.getsInstance().speak(voca.getWord());
            }
        });
        return convertView;

    }
    public class ViewHolder{
        public TextView tvWord, tvMean, tvPronunciation;
        public Button btSpeech;
    }
}