package com.example.cursutt.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="BrancheEntity")
public class BrancheEntity implements Parcelable {

    @PrimaryKey
    @NonNull
    private String sigle;

    public BrancheEntity(){
        this.sigle = "?";
    }

    public BrancheEntity(@NonNull String sigle){
        this.sigle = sigle;
    }

    protected BrancheEntity(Parcel in) {
        sigle = in.readString();
    }

    public static final Creator<BrancheEntity> CREATOR = new Creator<BrancheEntity>() {
        @Override
        public BrancheEntity createFromParcel(Parcel in) {
            return new BrancheEntity(in);
        }

        @Override
        public BrancheEntity[] newArray(int size) {
            return new BrancheEntity[size];
        }
    };

    @NonNull
    public String getSigle(){
        return this.sigle;
    }

    public void setSigle(@NonNull String sigle){
        this.sigle = sigle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sigle);
    }
}
