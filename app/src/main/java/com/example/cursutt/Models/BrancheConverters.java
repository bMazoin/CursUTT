package com.example.cursutt.Models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class BrancheConverters {

    @TypeConverter
    public static List<BrancheEntity> fromString(String value){
        Type list = new TypeToken<List<BrancheEntity>>() {}.getType();
        return new Gson().fromJson(value, list);
    }

    @TypeConverter
    public static String fromArrayList(List<BrancheEntity> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
