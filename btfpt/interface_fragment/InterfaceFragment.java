package com.example.ka.btfpt.interface_fragment;

import com.example.ka.btfpt.model.Question;

import java.util.ArrayList;

public interface InterfaceFragment {
    public void onClickRecycler(Question question);
    public void onClickNext(int i, Question question, Question questionAnswer);
    public void onClickSubmit();
}
