package com.example.cursutt.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ModuleDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ModuleEntity module);

    @Query("DELETE FROM ModuleEntity")
    void deleteAll();

    @Query("DELETE FROM ModuleEntity WHERE sigle = :module")
    void delete(String module);

    @Query("SELECT * FROM ModuleEntity ORDER BY sigle ASC")
    LiveData<List<ModuleEntity>> getModules();

}
