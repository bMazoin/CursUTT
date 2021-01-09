package com.example.cursutt.ViewModels;

import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.cursutt.Models.BrancheEntity;
import com.example.cursutt.Models.ModuleEntity;
import com.example.cursutt.Models.SemestreEntity;

public class AdaptateurSemestreRecycler extends ListAdapter<SemestreEntity, SemestreViewHolder> {

    public AdaptateurSemestreRecycler(@NonNull DiffUtil.ItemCallback<SemestreEntity> diffCallback){
        super(diffCallback);
    }

    @Override
    public SemestreViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return SemestreViewHolder.create(parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(SemestreViewHolder holder, int position){
        SemestreEntity current = getItem(position);
        holder.display(current);
    }

    static class SemestreDiff extends DiffUtil.ItemCallback<SemestreEntity>{
        @Override
        public boolean areItemsTheSame(@NonNull SemestreEntity oldItem, @NonNull SemestreEntity newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SemestreEntity oldItem, @NonNull SemestreEntity newItem){
            return oldItem.getSigle().equals(newItem.getSigle());
        }
    }

}
