package com.example.cursutt.ViewModels;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cursutt.Models.SemestreEntity;
import com.example.cursutt.R;

public class SemestreViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_nom;
    public TextView tv_info;

    public SemestreViewHolder(View itemView){
        super(itemView);

        tv_nom = (TextView) itemView.findViewById(R.id.semestre_name);
        tv_info = (TextView) itemView.findViewById(R.id.semestre_info);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void display(SemestreEntity semestre){
        final StringBuilder modulesInfosStringBuilder = new StringBuilder();
        semestre.getModules().forEach(oModule -> {
            modulesInfosStringBuilder.append(oModule.getSigle() + " (" +
                    oModule.getTypeUE() + " : " + oModule.getCredit() + ") |");
        });
        tv_nom.setText(semestre.getSigle());
        tv_info.setText("Il y a " + semestre.getModules().size() + " de modules dans ce semestre\n " + modulesInfosStringBuilder.toString() +
                "\nCrédits : " + Integer.toString(semestre.getCredits()) );
    }

    static SemestreViewHolder create(ViewGroup parent){
        LayoutInflater layout = LayoutInflater.from(parent.getContext());
        View v = layout.inflate(R.layout.semestre_item, parent, false);
        return new SemestreViewHolder(v);
    }
}
