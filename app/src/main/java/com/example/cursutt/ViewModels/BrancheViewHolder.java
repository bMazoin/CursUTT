package com.example.cursutt.ViewModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cursutt.Models.BrancheEntity;
import com.example.cursutt.R;

public class BrancheViewHolder extends RecyclerView.ViewHolder {

    public CheckBox cbBranche;

    private BrancheViewHolder(View itemView){
        super(itemView);

        cbBranche = (CheckBox) itemView.findViewById(R.id.branche_cb);
    }

    public void display(BrancheEntity branche){
        cbBranche.setText(branche.getSigle());
    }

    public static BrancheViewHolder create(ViewGroup parent){
        LayoutInflater layout = LayoutInflater.from(parent.getContext());
        View v = layout.inflate(R.layout.branche_item, parent, false);
        return new BrancheViewHolder(v);
    }
}
