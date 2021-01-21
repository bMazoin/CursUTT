package com.example.cursutt.ViewModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cursutt.Models.CursusEntity;
import com.example.cursutt.R;

public class CursusViewHolder extends RecyclerView.ViewHolder{

    private TextView tv_nom;
    private TextView tv_info;

    public Button bt_del;
    public Button bt_clone;
    public Button bt_open;

    private CursusViewHolder(View viewItem){
        super(viewItem);

        tv_nom = (TextView) viewItem.findViewById(R.id.cursus_name);
        tv_info = (TextView) viewItem.findViewById(R.id.cursus_info);
        bt_del = (Button) viewItem.findViewById(R.id.cursus_btnDelete);
        bt_clone = (Button) viewItem.findViewById(R.id.cursus_btnClone);
        bt_open = (Button) viewItem.findViewById(R.id.cursus_btnOpen);
    }

    public void display(CursusEntity cursus){
        tv_nom.setText(cursus.getSigle());
        tv_info.setText(cursus.getSemestres().size() + " semestres enregistrés");
    }

    public static CursusViewHolder create(ViewGroup parent){
        LayoutInflater layout = LayoutInflater.from(parent.getContext());
        View v = layout.inflate(R.layout.cursus_item, parent, false);
        return new CursusViewHolder(v);
    }

}
