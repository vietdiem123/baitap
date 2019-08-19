package com.example.ka.btfpt.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ka.btfpt.R;
import com.example.ka.btfpt.adapter.QuestionAdapter;
import com.example.ka.btfpt.controller.QuestionSqlHelperController;
import com.example.ka.btfpt.interface_fragment.InterfaceFragment;
import com.example.ka.btfpt.listener.RecyclerTouchListener;
import com.example.ka.btfpt.model.Question;

import java.util.ArrayList;

public class FragmentList extends Fragment {
    private InterfaceFragment listener;
    private ArrayList<String> mList;
    private ArrayList<Question> mListQuestion;
    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InterfaceFragment) {
            listener = (InterfaceFragment) context;
        } else {
            throw new RuntimeException(context.toString() + "Can phai implement");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        mListQuestion = new ArrayList<>();
        mListQuestion = QuestionSqlHelperController.getsInstance().getmList();
        mList = new ArrayList<>();
        for (int i = 0; i < mListQuestion.size(); i++){
            mList.add("Cau hoi " + (i+1));
        }
        Bundle bundle = this.getArguments();
        if(bundle != null){
            ArrayList<Integer> vtri = bundle.getIntegerArrayList("key_position");
            mAdapter = new QuestionAdapter(getContext(), R.layout.item_question, mList, vtri);
        } else {
            mAdapter = new QuestionAdapter(getContext(), R.layout.item_question, mList, new ArrayList<Integer>());
        }

        mRecyclerView =(RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        RecyclerTouchListener.ClickListener clickListener = new RecyclerTouchListener.ClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view, int position) {
                listener.onClickRecycler(mListQuestion.get(position));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        };
        RecyclerTouchListener listener = new RecyclerTouchListener(getContext(), mRecyclerView, clickListener);
        mRecyclerView.addOnItemTouchListener(listener);
        return  view;
    }
}
