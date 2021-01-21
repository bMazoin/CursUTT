package com.example.cursutt.Models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName="SemestreEntity")
public class SemestreEntity implements Parcelable {

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

    protected SemestreEntity(Parcel in) {
        sigle = in.readString();
        modules = in.createTypedArrayList(ModuleEntity.CREATOR);
    }

    public static final Creator<SemestreEntity> CREATOR = new Creator<SemestreEntity>() {
        @Override
        public SemestreEntity createFromParcel(Parcel in) {
            return new SemestreEntity(in);
        }

        @Override
        public SemestreEntity[] newArray(int size) {
            return new SemestreEntity[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sigle);
        dest.writeTypedList(modules);
    }
}
