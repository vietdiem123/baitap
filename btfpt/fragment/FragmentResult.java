package com.example.ka.btfpt.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ka.btfpt.R;
import com.example.ka.btfpt.adapter.ResultAdapter;
import com.example.ka.btfpt.controller.QuestionSqlHelperController;
import com.example.ka.btfpt.model.Question;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by HCD-Fresher079 on 1/24/2019.
 */

public class FragmentResult extends Fragment {

    private ResultAdapter mAdapter;
    private ListView mListView;
    private TextView mTextViewResult;
    private ArrayList<Question> mList;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        mListView = (ListView) view.findViewById(R.id.listview_result);
        mTextViewResult = (TextView) view.findViewById(R.id.tv_result);
        Bundle bundle = getArguments();

        int tong = QuestionSqlHelperController.getsInstance().getmList().size();
        if(bundle != null){
            mList = bundle.getParcelableArrayList("key_submit");
            int result = 0;
            for (int i = 0; i < mList.size(); i++){
                String[] strAnswer = mList.get(i).getmAnswerUser().split(",");
                ArrayList<String> mListAnswer = new  ArrayList<>(Arrays.asList(strAnswer));
                if(mListAnswer.size() == mList.get(i).getmAnswer().split(",").length){
                    boolean check = true;
                    for (int j = 0; j < mListAnswer.size(); j++){
                        if(mListAnswer.indexOf(mList.get(i).getmAnswer().split(",")[j]) == -1){
                            check = false;
                            break;
                        }
                    }
                    if(check){
                        ++result;
                    }
                }
            }

            float kq = ((float)result/tong)*100;
            mTextViewResult.setText("Ket Qua: " + kq + "%");
            mAdapter = new ResultAdapter(getContext(), R.layout.item_result, mList);
            mListView.setAdapter(mAdapter);
        }
        return view;
    }
}
