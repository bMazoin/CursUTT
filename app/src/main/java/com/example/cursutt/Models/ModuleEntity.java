package com.example.cursutt.Models;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName="ModuleEntity")
public class ModuleEntity implements Parcelable{

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

    protected ModuleEntity(Parcel in) {
        sigle = in.readString();
        branche = in.createTypedArrayList(BrancheEntity.CREATOR);
        credit = in.readInt();
        typeUE = in.readString();
    }

    public static final Creator<ModuleEntity> CREATOR = new Creator<ModuleEntity>() {
        @Override
        public ModuleEntity createFromParcel(Parcel in) {
            return new ModuleEntity(in);
        }

        @Override
        public ModuleEntity[] newArray(int size) {
            return new ModuleEntity[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sigle);
        dest.writeTypedList(branche);
        dest.writeInt(credit);
        dest.writeString(typeUE);
    }
}
