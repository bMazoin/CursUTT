package com.example.cursutt.ViewModels;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.cursutt.Models.BrancheEntity;

import java.util.List;

public class AdaptateurBrancheRecycler extends ListAdapter<BrancheEntity, BrancheViewHolder> {
    List<BrancheEntity> branches;

    public AdaptateurBrancheRecycler(@NonNull DiffUtil.ItemCallback<BrancheEntity> diffCallback){
        super(diffCallback);
    }

    @Override
    public BrancheViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return BrancheViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(BrancheViewHolder holder, int position){
        BrancheEntity current = getItem(position);
        holder.display(current);
    }

    static class BrancheDiff extends DiffUtil.ItemCallback<BrancheEntity>{
        @Override
        public boolean areItemsTheSame(@NonNull BrancheEntity oldItem, @NonNull BrancheEntity newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull BrancheEntity oldItem, @NonNull BrancheEntity newItem){
            return oldItem.getSigle().equals(newItem.getSigle());
        }
    }
}
