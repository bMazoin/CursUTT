package com.example.cursutt.Models;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName="ModuleEntity")
public class ModuleEntity implements Serializable {

    @PrimaryKey
    @NonNull
    private String sigle;

    @NonNull
    private List<BrancheEntity> branche;

    @NonNull
    private int credit;

    @NonNull
    public String getSigle() {
        return sigle;
    }

    public ModuleEntity(){
        this.sigle = "?";
        this.branche = new ArrayList<>();
        this.credit = 0;
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
}
