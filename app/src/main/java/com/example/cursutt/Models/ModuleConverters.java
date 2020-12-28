package com.example.cursutt.Models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ModuleConverters {

    @TypeConverter
    public static List<ModuleEntity> fromString(String value){
        Type list = new TypeToken<List<ModuleEntity>>() {}.getType();
        return new Gson().fromJson(value, list);
    }

    @TypeConverter
    public static String fromArrayList(List<ModuleEntity> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
