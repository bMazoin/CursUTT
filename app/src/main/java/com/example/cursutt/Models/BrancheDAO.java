package com.example.cursutt.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BrancheDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BrancheEntity branche);

    @Query("DELETE FROM BrancheEntity")
    void deleteAll();

    @Query("DELETE FROM BrancheEntity WHERE sigle = :branche")
    void delete(String branche);

    @Query("SELECT * FROM BrancheEntity ORDER BY sigle ASC")
    LiveData<List<BrancheEntity>> getBranches();

}
