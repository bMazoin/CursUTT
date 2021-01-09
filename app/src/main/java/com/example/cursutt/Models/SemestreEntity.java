package com.example.cursutt.Models;

import android.os.Build;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName="SemestreEntity")
public class SemestreEntity {

    @PrimaryKey
    @NonNull
    private String sigle;

    @NonNull
    private List<ModuleEntity> modules;

    public SemestreEntity(){
        sigle = "?";
        modules = new ArrayList<>();
    }

    public SemestreEntity(@NonNull String sigle, @NonNull List<ModuleEntity> modules){
        this.sigle = sigle;
        this.modules = modules;
    }

    @NonNull
    public String getSigle() {
        return sigle;
    }

    public void setSigle(@NonNull String sigle) {
        this.sigle = sigle;
    }

    @NonNull
    public List<ModuleEntity> getModules() {
        return modules;
    }

    public void setModules(@NonNull List<ModuleEntity> modules) {
        this.modules = modules;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getCredits(){
        return(modules.stream().mapToInt(ModuleEntity::getCredit).sum());
    }

}
