package com.example.cursutt.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName="CursusEntity")
public class CursusEntity implements Parcelable, Cloneable {

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

    protected CursusEntity(Parcel in) {
        sigle = in.readString();
    }

    public static final Creator<CursusEntity> CREATOR = new Creator<CursusEntity>() {
        @Override
        public CursusEntity createFromParcel(Parcel in) {
            return new CursusEntity(in);
        }

        @Override
        public CursusEntity[] newArray(int size) {
            return new CursusEntity[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sigle);
    }

    @Override
    public Object clone(){
        CursusEntity cursus = null;
        try {
            cursus = (CursusEntity) super.clone();
        }
        catch (CloneNotSupportedException ex){
            ex.printStackTrace(System.err);
        }
        return cursus;
    }
}
