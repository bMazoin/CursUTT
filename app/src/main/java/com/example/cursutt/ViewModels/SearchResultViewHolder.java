package com.example.cursutt.ViewModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cursutt.Models.ModuleEntity;
import com.example.cursutt.R;

public class SearchResultViewHolder extends RecyclerView.ViewHolder {

    public CheckBox cb;
    public TextView tv_sigle;
    public TextView tv_categorie;
    public TextView tv_credit;

    public SearchResultViewHolder(View itemView){
        super(itemView);

        cb = (CheckBox) itemView.findViewById(R.id.searchresult_cb);
        tv_sigle = (TextView) itemView.findViewById(R.id.searchresult_sigle_tv);
        tv_categorie = (TextView) itemView.findViewById(R.id.searchresult_categorie_tv);
        tv_credit = (TextView) itemView.findViewById(R.id.searchresult_credit_tv);
    }

    public void display(ModuleEntity module){
        tv_sigle.setText(module.getSigle());
        tv_categorie.setText(module.getTypeUE());
        tv_credit.setText(Integer.toString(module.getCredit()));
    }

    static SearchResultViewHolder create(ViewGroup parent){
        LayoutInflater layout = LayoutInflater.from(parent.getContext());
        View v = layout.inflate(R.layout.searchresult_item, parent, false);
        return new SearchResultViewHolder(v);
    }


}
