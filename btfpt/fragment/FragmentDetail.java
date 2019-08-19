package com.example.ka.btfpt.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ka.btfpt.R;
import com.example.ka.btfpt.controller.QuestionSqlHelperController;
import com.example.ka.btfpt.interface_fragment.InterfaceFragment;
import com.example.ka.btfpt.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class FragmentDetail extends Fragment {

    private InterfaceFragment listener;

    private LinearLayout mLinearLayout2;
    private LinearLayout mLinearLayout5;

    private CheckBox mCheckBoxA;
    private CheckBox mCheckBoxB;
    private CheckBox mCheckBoxC;
    private CheckBox mCheckBoxD;
    private CheckBox mCheckBoxE;

    private RadioButton mRadioButtonA;
    private RadioButton mRadioButtonB;
    private RadioGroup mRadioGroup;

    private TextView mTextview;
    private TextView mTextviewPosition;

    private Button mButtonNext;
    private Button mButtonBack;
    private Button mButtonSubmit;

    ArrayList<Question> mList;
    ArrayList<Question> mListResult;
    ArrayList<Question> mListAnswerUser;
    ArrayList<Integer> mListID;
    int mId;
    String finalMAnswer ="";

    Question question ;

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
        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        init(savedInstanceState);
        getWidgets(view);
        setWidgets();
        addWidgetListener(view);

        return  view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("key_list_answer_user", mListAnswerUser);
    }

    private void showQuestion(){
        randomQuestion();

        mTextviewPosition.setText("Cau hoi " + (mList.indexOf(question) +1 ));
        mTextview.setText(question.getmQuestion());
        if(mList.indexOf(question) != 0){
            mButtonBack.setVisibility(View.VISIBLE);
        }
        if(question.getmAnswerD().length()!= 0 && "".equals(question.getmAnswerE().trim())){
            mLinearLayout5.setVisibility(View.VISIBLE);
            mLinearLayout2.setVisibility(View.GONE);
            mCheckBoxA.setText(question.getmAnswerA());
            mCheckBoxB.setText(question.getmAnswerB());
            mCheckBoxC.setText(question.getmAnswerC());
            mCheckBoxD.setText(question.getmAnswerD());
            mCheckBoxC.setVisibility(View.VISIBLE);
            mCheckBoxD.setVisibility(View.VISIBLE);
            mCheckBoxE.setVisibility(View.GONE);

        } else {
            if(question.getmAnswerE().length()!= 0){
                mLinearLayout5.setVisibility(View.VISIBLE);
                mLinearLayout2.setVisibility(View.GONE);
                mCheckBoxA.setText(question.getmAnswerA());
                mCheckBoxB.setText(question.getmAnswerB());
                mCheckBoxC.setText(question.getmAnswerC());
                mCheckBoxD.setText(question.getmAnswerD());
                mCheckBoxE.setText(question.getmAnswerE());
                mCheckBoxC.setVisibility(View.VISIBLE);
                mCheckBoxD.setVisibility(View.VISIBLE);
                mCheckBoxE.setVisibility(View.VISIBLE);
            } else{
                mLinearLayout2.setVisibility(View.VISIBLE);
                mLinearLayout5.setVisibility(View.GONE);
                mRadioButtonA.setText(question.getmAnswerA());
                mRadioButtonB.setText(question.getmAnswerB());
            }
        }
        finalMAnswer = question.getmAnswer();
        mId = question.getmId();
        mCheckBoxA.setChecked(false);
        mCheckBoxB.setChecked(false);
        mCheckBoxC.setChecked(false);
        mCheckBoxD.setChecked(false);
        mCheckBoxE.setChecked(false);
        mRadioGroup.clearCheck();
        int vtri = -1;
        if(mListAnswerUser != null) {
            for (int i = 0; i < mListAnswerUser.size(); i++) {
                if (question.getmId() == mListAnswerUser.get(i).getmId()) {
                    vtri = i;
                    break;
                }
            }
        }
        if(vtri != -1){
            String b = mListAnswerUser.get(vtri).getmAnswerUser();
            String[] answerUser = b.split(",");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(answerUser));
            backResult(mCheckBoxA, list);
            backResult(mCheckBoxB, list);
            backResult(mCheckBoxC, list);
            backResult(mCheckBoxD, list);
            backResult(mCheckBoxE, list);
            if (list.indexOf(mRadioButtonA.getText().toString()) != -1) {
                mRadioButtonA.setChecked(true);
            }
            if (list.indexOf(mRadioButtonB.getText().toString()) != -1) {
                mRadioButtonB.setChecked(true);
            }
        }

    }
    private void backResult(CheckBox checkBox, ArrayList<String> list){
        if(list.indexOf(checkBox.getText().toString())!= -1){
            checkBox.setChecked(true);
        }
    }

    private Question getQuestion(int id){
        for (int i = 0; i < mList.size(); i++){
            if(id == mList.get(i).getmId()){
                return mList.get(i);
            }
        }
        return mList.get(mList.size()-1);
    }

    private int getPosition(int id){
        for (int i = 0; i < mList.size(); i++){
            if (mList.get(i).getmId() == id){
                return i;
            }
        }
        return -1;
    }

    private void init(Bundle bundle){
        mList = QuestionSqlHelperController.getsInstance().getmList();
        mListResult = new ArrayList<>();
        if(bundle != null){
            mListAnswerUser = bundle.getParcelableArrayList("key_list_answer_user");
        } else {
            mListAnswerUser = new ArrayList<>();
        }
        mListID = QuestionSqlHelperController.getsInstance().getListID();
    }

    private void getWidgets(View view){
        mLinearLayout2 = (LinearLayout) view.findViewById(R.id.linearlayout_2);
        mLinearLayout5 = (LinearLayout) view.findViewById(R.id.linearlayout_5);

        mRadioButtonA = (RadioButton) view.findViewById(R.id.radio_btn_A);
        mRadioButtonB = (RadioButton) view.findViewById(R.id.radio_btn_B);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group);

        mCheckBoxA = (CheckBox) view.findViewById(R.id.checkbox_A);
        mCheckBoxB = (CheckBox) view.findViewById(R.id.checkbox_B);
        mCheckBoxC = (CheckBox) view.findViewById(R.id.checkbox_C);
        mCheckBoxD = (CheckBox) view.findViewById(R.id.checkbox_D);
        mCheckBoxE = (CheckBox) view.findViewById(R.id.checkbox_E);

        mTextview = (TextView) view.findViewById(R.id.tv);
        mTextviewPosition = (TextView) view.findViewById(R.id.tv_position);

        mButtonNext = (Button) view.findViewById(R.id.btn_next);
        mButtonBack = (Button) view.findViewById(R.id.btn_back);
        mButtonSubmit = (Button) view.findViewById(R.id.btn_submit);
    }
    private void setWidgets(){
        final Bundle bundle = this.getArguments();
        if(bundle != null) {
            question = (Question) bundle.getParcelable("key");
            mListAnswerUser = bundle.getParcelableArrayList("key_answer");
            showQuestion();
        }
    }
    private void addWidgetListener(final View view){
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View vieww) {
                String answer = "";
                if(mCheckBoxA.isChecked() || mCheckBoxB.isChecked() || mCheckBoxC.isChecked() || mCheckBoxD.isChecked() || mCheckBoxE.isChecked() || mRadioGroup.getCheckedRadioButtonId()!=-1){
                    if(mCheckBoxA.isChecked()){
                        answer = mCheckBoxA.getText().toString().trim() +",";
                    }
                    if(mCheckBoxB.isChecked()){
                        answer += mCheckBoxB.getText().toString().trim()+",";
                    }
                    if(mCheckBoxC.isChecked()){
                        answer += mCheckBoxC.getText().toString().trim() +",";
                    }
                    if(mCheckBoxD.isChecked()){
                        answer += mCheckBoxD.getText().toString().trim() +",";
                    }
                    if(mCheckBoxE.isChecked()){
                        answer += mCheckBoxE.getText().toString().trim() +",";
                    }
                    if(mRadioButtonA.isChecked()){
                        answer += mRadioButtonA.getText().toString().trim() +",";
                    }
                    if(mRadioButtonB.isChecked()){
                        answer += mRadioButtonB.getText().toString().trim() +",";
                    }

                    question.setmAnswerUser(answer);

                    mListAnswerUser.add(question);

                    if(mId == mListID.get(mListID.size()-1)){
                        int maxID = mListID.get(mListID.size()-1);
                        listener.onClickNext(getPosition(maxID), getQuestion(maxID), question);
                        if(mList.indexOf(question) == (mList.size()-1)){
                            mButtonNext.setVisibility(View.GONE);
                            mButtonSubmit.setVisibility(View.VISIBLE);
                        }
                    }else {
                        listener.onClickNext(getPosition(mId), getQuestion(++mId), question);
                        question = getQuestion(mId);
                        showQuestion();
                    }
                }
            }
        });

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                listener.onClickSubmit();
            }
        });

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showQuestion("back");
            }
        });
    }

    private void randomQuestion(){
        ArrayList<String> arrayRandom = new ArrayList<>();
        arrayRandom.add(question.getmAnswerA());
        arrayRandom.add(question.getmAnswerB());
        arrayRandom.add(question.getmAnswerC());
        arrayRandom.add(question.getmAnswerD());
        arrayRandom.add(question.getmAnswerE());
        for (int i = 0; i < arrayRandom.size(); i++){
            if("".equals(arrayRandom.get(i))){
                arrayRandom.remove(i);
            }
        }
        Random random = new Random();
        for (int i = 0; i < arrayRandom.size(); i++){
            int ran = random.nextInt(arrayRandom.size());
            String temp = arrayRandom.get(i);
            arrayRandom.set(i, arrayRandom.get(ran));
            arrayRandom.set(ran, temp);
        }

        switch (arrayRandom.size()){
            case 2:
                question.setmAnswerA(arrayRandom.get(0));
                question.setmAnswerB(arrayRandom.get(1));
                break;
            case 4:
                question.setmAnswerA(arrayRandom.get(0));
                question.setmAnswerB(arrayRandom.get(1));
                question.setmAnswerC(arrayRandom.get(2));
                question.setmAnswerD(arrayRandom.get(3));
                break;
            case 5:
                question.setmAnswerA(arrayRandom.get(0));
                question.setmAnswerB(arrayRandom.get(1));
                question.setmAnswerC(arrayRandom.get(2));
                question.setmAnswerD(arrayRandom.get(3));
                question.setmAnswerE(arrayRandom.get(4));
                break;
        }
    }
}
