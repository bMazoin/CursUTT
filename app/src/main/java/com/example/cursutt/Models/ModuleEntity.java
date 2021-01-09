package com.example.cursutt.Models;


import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName="ModuleEntity")
public class ModuleEntity{

    @PrimaryKey
    @NonNull
    private String sigle;

    @NonNull
    private List<BrancheEntity> branche;

    @NonNull
    private int credit;

    @NonNull
    private String typeUE;

    public ModuleEntity(){
        this.sigle = "?";
        this.branche = new ArrayList<>();
        this.credit = 0;
        this.typeUE = "?";
    }

    public ModuleEntity(String sigle, List<BrancheEntity> branches, int creds, String typeUE){
        this.sigle = sigle;
        this.branche = branches;
        this.credit = creds;
        this.typeUE = typeUE;
    }

    @NonNull
    public String getSigle() {
        return sigle;
    }

    public void setSigle(@NonNull String sigle) {
        this.sigle = sigle;
    }

    @NonNull
    public List<BrancheEntity> getBranche() {
        return branche;
    }

    public void setBranche(@NonNull List<BrancheEntity> branche) {
        this.branche = branche;
    }

    @NonNull
    public int getCredit() {
        return credit;
    }

    public void setCredit(@NonNull int credit) {
        this.credit = credit;
    }

    @NonNull
    public String getTypeUE(){
        return this.typeUE;
    }

    public void setTypeUE(String typeUE){
        this.typeUE = typeUE;
    }
}
