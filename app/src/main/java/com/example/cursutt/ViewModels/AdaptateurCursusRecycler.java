package com.example.cursutt.ViewModels;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.cursutt.Models.CursusEntity;

public class AdaptateurCursusRecycler extends ListAdapter<CursusEntity, CursusViewHolder> {

    public interface OnClickListener {
        void onClick(CursusEntity position);
    }

    @NonNull
    private OnClickListener onClickListenerDel;

    @NonNull
    private OnClickListener onClickListenerClone;

    @NonNull
    private OnClickListener onClickListenerOpen;

    public AdaptateurCursusRecycler(@NonNull DiffUtil.ItemCallback<CursusEntity> diffCallback, @NonNull OnClickListener onClickListenerDel, @NonNull OnClickListener onClickListenerClone,
                                    @NonNull OnClickListener onClickListenerOpen){
        super(diffCallback);
        this.onClickListenerDel = onClickListenerDel;
        this.onClickListenerClone = onClickListenerClone;
        this.onClickListenerOpen = onClickListenerOpen;
    }

    @Override
    public CursusViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return CursusViewHolder.create(parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(CursusViewHolder holder, int position){
        CursusEntity current = getItem(position);
        holder.display(current);
        holder.bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerDel.onClick(getItem(position));
            }
        });
        holder.bt_clone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerClone.onClick(getItem(position));
            }
        });
        holder.bt_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerOpen.onClick(getItem(position));
            }
        });
    }

    static class CursusDiff extends DiffUtil.ItemCallback<CursusEntity>{
        @Override
        public boolean areItemsTheSame(@NonNull CursusEntity oldItem, @NonNull CursusEntity newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CursusEntity oldItem, @NonNull CursusEntity newItem){
            return oldItem.getSigle().equals(newItem.getSigle());
        }
    }
}
