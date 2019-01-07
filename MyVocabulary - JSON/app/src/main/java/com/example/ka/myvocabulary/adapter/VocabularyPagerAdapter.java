package com.example.ka.myvocabulary.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ka.myvocabulary.R;
import com.example.ka.myvocabulary.models.Vocabulary;

import java.util.ArrayList;

/**
 * Created by KA on 10/29/2017.
 */

public class VocabularyPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int mLayout;
    private ArrayList<Vocabulary> mVocabulary;
    private Boolean mIsWord, mIsPronun, mIsMean;

    public VocabularyPagerAdapter(Context context, int layout, ArrayList<Vocabulary> voca
                                 , Boolean isWord, Boolean isPronun, Boolean isMean ){
        mContext =context;
        mLayout = layout;
        mVocabulary = voca;
        mIsMean = isMean;
        mIsWord = isWord;
        mIsPronun = isPronun;

    }
    @Override
        public int getCount() {      // co bao nhiu trang
        return mVocabulary.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {  //
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View layout = inflater.inflate(mLayout, container, false);
        TextView tvWord = (TextView)layout.findViewById(R.id.tvWord_practice);
        TextView tvPronun = (TextView)layout.findViewById(R.id.tvPronunciation_practice);
        TextView tvMean = (TextView)layout.findViewById(R.id.tvMean_practice);

        tvWord.setText("???");
        tvPronun.setText("???");
        tvMean.setText("???");

        if(mIsWord){
            tvWord.setText(mVocabulary.get(position).getWord());
        }
        if(mIsPronun){
            tvPronun.setText(mVocabulary.get(position).getPronunciation());
        }
        if(mIsMean){
            tvMean.setText(mVocabulary.get(position).getMean());
        }

        container.addView(layout);
        return layout;
    }

}
