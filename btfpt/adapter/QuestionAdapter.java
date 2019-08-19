package com.example.ka.btfpt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ka.btfpt.R;
import com.example.ka.btfpt.controller.QuestionSqlHelperController;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{

    private Context mContext;
    private int mLayout;
    private List<String> mList;
    private List<Integer> mVitri;
    public QuestionAdapter(Context context, int layout, ArrayList<String> list, ArrayList<Integer> vitri){
        this.mContext = context;
        this.mLayout = layout;
        this.mList = list;
        this.mVitri = vitri;
    }

    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  // chi chay 1 lan
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(mLayout, parent, false);
        QuestionAdapter.ViewHolder viewHolder = new QuestionAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder holder, int position) { // set gia tri vaf chay nhiu lan

        String str = mList.get(position);
        holder.tvQuestion.setText(str);
        holder.tvContent.setText(QuestionSqlHelperController.getsInstance().getmList().get(position).getmQuestion());
        for (int i = 0; i < mVitri.size(); i++){
            if(position == mVitri.get(i)){
                holder.imgShow.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvQuestion;
        public TextView tvContent;
        public ImageView imgShow;


        public ViewHolder(View itemView) {
            super(itemView);
            tvQuestion = (TextView) itemView.findViewById(R.id.tv_list_item);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content_item);
            imgShow = (ImageView) itemView.findViewById(R.id.img_item);

//            itemView.setOnClickListener();   // set onclick
        }
    }
}
