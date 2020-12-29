package com.example.cursutt.ViewModels;

import android.content.ClipData;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.cursutt.Models.BrancheEntity;

import java.util.List;

public class AdaptateurBrancheRecycler extends ListAdapter<BrancheEntity, BrancheViewHolder> {

    interface OnItemCheckListener{
        void onItemCheck(BrancheEntity item);
        void onItemUncheck(BrancheEntity item);
    }

    @NonNull
    private OnItemCheckListener onItemCheckListener;

    public AdaptateurBrancheRecycler(@NonNull DiffUtil.ItemCallback<BrancheEntity> diffCallback, @NonNull OnItemCheckListener onItemCheckListener){
        super(diffCallback);
        this.onItemCheckListener = onItemCheckListener;
    }

    @Override
    public BrancheViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return BrancheViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(BrancheViewHolder holder, int position){
        BrancheEntity current = getItem(position);
        holder.display(current);
        holder.cbBranche.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(holder.cbBranche.isChecked()){
                    onItemCheckListener.onItemCheck(getItem(position));
                }
                else{
                    onItemCheckListener.onItemUncheck(getItem(position));
                }
            }
        });
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
