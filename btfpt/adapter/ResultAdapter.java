package com.example.ka.btfpt.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ka.btfpt.R;
import com.example.ka.btfpt.controller.QuestionSqlHelperController;
import com.example.ka.btfpt.model.Question;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by HCD-Fresher079 on 1/24/2019.
 */


public class ResultAdapter extends ArrayAdapter<Question> {
    private Context mContext;
    private int mLayout;
    private ArrayList<Question> mList;

    String[] listAnswerUser;
    ArrayList<String> arrayList;
    ViewHolder viewHolder;

    public ResultAdapter(Context context, int resource, ArrayList<Question> list) {
        super(context, resource, list);
        mContext = context;
        mLayout = resource;
        mList = list;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mLayout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvPosition = (TextView) convertView.findViewById(R.id.tv_position_result );
            viewHolder.tvQuestion = (TextView)convertView.findViewById(R.id.tv_question_result );

            viewHolder.linearLayout2 = (LinearLayout)convertView.findViewById(R.id.linearlayout_2_result);
            viewHolder.linearLayout5 = (LinearLayout)convertView.findViewById(R.id.linearlayout_5_result);

            viewHolder.checkBoxA = (CheckBox)convertView.findViewById(R.id.checkbox_A_result);
            viewHolder.checkBoxB = (CheckBox)convertView.findViewById(R.id.checkbox_B_result);
            viewHolder.checkBoxC = (CheckBox)convertView.findViewById(R.id.checkbox_C_result);
            viewHolder.checkBoxD = (CheckBox)convertView.findViewById(R.id.checkbox_D_result);
            viewHolder.checkBoxE = (CheckBox)convertView.findViewById(R.id.checkbox_E_result);

            viewHolder.radioButtonA = (RadioButton) convertView.findViewById(R.id.radio_btn_A_result);
            viewHolder.radioButtonB = (RadioButton) convertView.findViewById(R.id.radio_btn_B_result);
            viewHolder.radioGroup = (RadioGroup) convertView.findViewById(R.id.radio_group_result);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        resetBackgroup();
        final Question question = mList.get(position);
        ArrayList<Integer> list = QuestionSqlHelperController.getsInstance().getListID();
        viewHolder.tvQuestion.setText(question.getmQuestion());
        viewHolder.tvPosition.setText("Cau hoi " + (list.indexOf(question.getmId()) +1));

        if(question.getmAnswerD().length()!= 0 && "".equals(question.getmAnswerE().trim())){
            viewHolder.linearLayout5.setVisibility(View.VISIBLE);
            viewHolder.linearLayout2.setVisibility(View.GONE);
            viewHolder.checkBoxA.setText(question.getmAnswerA());
            viewHolder.checkBoxB.setText(question.getmAnswerB());
            viewHolder.checkBoxC.setText(question.getmAnswerC());
            viewHolder.checkBoxD.setText(question.getmAnswerD());
            viewHolder.checkBoxC.setVisibility(View.VISIBLE);
            viewHolder.checkBoxD.setVisibility(View.VISIBLE);
            viewHolder.checkBoxE.setVisibility(View.GONE);
        } else {
            if(question.getmAnswerE().length()!= 0){
                viewHolder.linearLayout5.setVisibility(View.VISIBLE);
                viewHolder.linearLayout2.setVisibility(View.GONE);
                viewHolder.checkBoxA.setText(question.getmAnswerA());
                viewHolder.checkBoxB.setText(question.getmAnswerB());
                viewHolder.checkBoxC.setText(question.getmAnswerC());
                viewHolder.checkBoxD.setText(question.getmAnswerD());
                viewHolder.checkBoxE.setText(question.getmAnswerE());
                viewHolder.checkBoxC.setVisibility(View.VISIBLE);
                viewHolder.checkBoxD.setVisibility(View.VISIBLE);
                viewHolder.checkBoxE.setVisibility(View.VISIBLE);
            } else{
                viewHolder.linearLayout2.setVisibility(View.VISIBLE);
                viewHolder.linearLayout5.setVisibility(View.GONE);
                viewHolder.radioButtonA.setText(question.getmAnswerA());
                viewHolder.radioButtonB.setText(question.getmAnswerB());
            }
        }

        listAnswerUser = question.getmAnswerUser().split(",");
        String[] listAnswer = question.getmAnswer().split(",");
        arrayList = new ArrayList<>(Arrays.asList(listAnswer));

        for (int i = 0; i < listAnswerUser.length; i++){
            showResult(i, viewHolder.checkBoxA);
            showResult(i, viewHolder.checkBoxB);
            showResult(i, viewHolder.checkBoxC);
            showResult(i, viewHolder.checkBoxD);
            showResult(i, viewHolder.checkBoxE);
            if(listAnswerUser[i].equals(viewHolder.radioButtonA.getText().toString())){
                viewHolder.radioButtonA.setChecked(true);
                if(arrayList.indexOf(listAnswerUser[i])!= -1){
                    viewHolder.radioButtonA.setBackgroundColor(Color.BLUE);
                } else {
                    viewHolder.radioButtonA.setBackgroundColor(Color.RED);
                }
            }
            if(listAnswerUser[i].equals(viewHolder.radioButtonB.getText().toString())){
                viewHolder.radioButtonB.setChecked(true);
                if(arrayList.indexOf(listAnswerUser[i])!= -1){
                    viewHolder.radioButtonB.setBackgroundColor(Color.BLUE);
                } else {
                    viewHolder.radioButtonB.setBackgroundColor(Color.RED);
                }
            }
        }


        return convertView;
    }
    public class ViewHolder{
        public TextView tvPosition;
        public TextView tvQuestion;

        public CheckBox checkBoxA;
        public CheckBox checkBoxB;
        public CheckBox checkBoxC;
        public CheckBox checkBoxD;
        public CheckBox checkBoxE;

        public RadioGroup radioGroup;
        public RadioButton radioButtonA;
        public RadioButton radioButtonB;

        public LinearLayout linearLayout2;
        public LinearLayout linearLayout5;
    }

    public void showResult(int i, CheckBox checkBox){
        if(listAnswerUser[i].equals(checkBox.getText().toString())){ // dap an cua user
            checkBox.setChecked(true);
            if(arrayList.indexOf(checkBox.getText().toString())!= -1){
                checkBox.setBackgroundColor(Color.BLUE);
            } else {
                checkBox.setBackgroundColor(Color.RED);
            }
        }
    }
    public void resetBackgroup(){
        viewHolder.checkBoxA.setBackgroundColor(Color.WHITE);
        viewHolder.checkBoxB.setBackgroundColor(Color.WHITE);
        viewHolder.checkBoxC.setBackgroundColor(Color.WHITE);
        viewHolder.checkBoxD.setBackgroundColor(Color.WHITE);
        viewHolder.checkBoxE.setBackgroundColor(Color.WHITE);
        viewHolder.radioButtonA.setBackgroundColor(Color.WHITE);
        viewHolder.radioButtonB.setBackgroundColor(Color.WHITE);

        viewHolder.checkBoxA.setChecked(false);
        viewHolder.checkBoxB.setChecked(false);
        viewHolder.checkBoxC.setChecked(false);
        viewHolder.checkBoxD.setChecked(false);
        viewHolder.checkBoxE.setChecked(false);
        viewHolder.radioButtonA.setChecked(false);
        viewHolder.radioButtonB.setChecked(false);
    }
}