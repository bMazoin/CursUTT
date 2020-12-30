package com.example.cursutt.ViewModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cursutt.Models.ModuleEntity;
import com.example.cursutt.R;

public class ModuleViewHolder extends RecyclerView.ViewHolder {
    public TextView tvCategorie, tvSigle, tvCredits;
    public CheckBox cb;

    private ModuleViewHolder(View itemView){
        super(itemView);

        cb = (CheckBox) itemView.findViewById(R.id.module_cb);
        tvCategorie = (TextView) itemView.findViewById(R.id.module_categorie_tv);
        tvSigle = (TextView) itemView.findViewById(R.id.module_sigle_tv);
        tvCredits = (TextView) itemView.findViewById(R.id.module_credit_tv);
    }

    public void display(ModuleEntity module) {
        tvCategorie.setText(module.getTypeUE());
        tvSigle.setText(module.getSigle());
        tvCredits.setText(Integer.toString(module.getCredit()));
    }

    static ModuleViewHolder create(ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.module_item, parent, false);
        return new ModuleViewHolder(v);
    }
}
