package com.example.cursutt.Models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SemestreConverters {

    @TypeConverter
    public static List<SemestreEntity> fromString(String value){
        Type list = new TypeToken<List<SemestreEntity>>() {}.getType();
        return new Gson().fromJson(value, list);
    }

    @TypeConverter
    public static String fromArrayList(List<SemestreEntity> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
