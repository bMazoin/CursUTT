package com.example.cursutt.Models;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName="CursusEntity")
public class CursusEntity {

    @PrimaryKey
    @NonNull
    private String sigle;

    @NonNull
    private List<SemestreEntity> semestres;

    public CursusEntity(){
        this.sigle = "?";
        this.semestres = new ArrayList<SemestreEntity>();
    }

    public CursusEntity(@NonNull String sigle, @NonNull List<SemestreEntity> semestres){
        this.sigle = sigle;
        this.semestres = semestres;
    }

    @NonNull
    public String getSigle(){
        return this.sigle;
    }

    public void setSigle(@NonNull String sigle){
        this.sigle = sigle;
    }

    @NonNull
    public List<SemestreEntity> getSemestres(){
        return this.semestres;
    }

    public void setSemestres(@NonNull List<SemestreEntity> semestres){
        this.semestres = semestres;
    }



}
