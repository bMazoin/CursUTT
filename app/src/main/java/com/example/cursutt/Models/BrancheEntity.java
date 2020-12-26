package com.example.cursutt.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="BrancheEntity")
public class BrancheEntity {

    @PrimaryKey
    @NonNull
    private String sigle;

    public BrancheEntity(){
        this.sigle = "?";
    }

    public BrancheEntity(@NonNull String sigle){
        this.sigle = sigle;
    }

    @NonNull
    public String getSigle(){
        return this.sigle;
    }

    public void setSigle(@NonNull String sigle){
        this.sigle = sigle;
    }

}
