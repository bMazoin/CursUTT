package com.example.cursutt.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SemestreDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SemestreEntity semestre);

    @Query("DELETE FROM SemestreEntity")
    void deleteAll();

    @Query("DELETE FROM SemestreEntity WHERE sigle = :semestre")
    void delete(String semestre);

    @Query("SELECT * FROM SemestreEntity ORDER BY sigle ASC")
    LiveData<List<SemestreEntity>> getSemestres();

}
