package com.example.cursutt.ViewModels;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.cursutt.Models.ModuleEntity;

public class AdaptateurModuleRecycler extends ListAdapter<ModuleEntity, ModuleViewHolder> {
    interface OnItemCheckListener{
        void onItemCheck(ModuleEntity item);
        void onItemUncheck(ModuleEntity item);
    }

    @NonNull
    private AdaptateurModuleRecycler.OnItemCheckListener onItemCheckListener;

    public AdaptateurModuleRecycler(@NonNull DiffUtil.ItemCallback<ModuleEntity> diffCallback, @NonNull AdaptateurModuleRecycler.OnItemCheckListener onItemCheckListener){
        super(diffCallback);
        this.onItemCheckListener = onItemCheckListener;
    }

    @Override
    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return ModuleViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder holder, int position){
        ModuleEntity current = getItem(position);
        holder.display(current);
        holder.cb.setOnClickListener(v -> {
            if(holder.cb.isChecked()){
                onItemCheckListener.onItemCheck(getItem(position));
            }
            else{
                onItemCheckListener.onItemUncheck(getItem(position));
            }
        });
    }

    static class ModuleDiff extends DiffUtil.ItemCallback<ModuleEntity>{
        @Override
        public boolean areItemsTheSame(@NonNull ModuleEntity oldItem, @NonNull ModuleEntity newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ModuleEntity oldItem, @NonNull ModuleEntity newItem){
            return oldItem.getSigle().equals(newItem.getSigle());
        }
    }
}
