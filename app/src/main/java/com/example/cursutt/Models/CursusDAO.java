package com.example.cursutt.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CursusDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CursusEntity cursus);

    @Query("DELETE FROM CursusEntity")
    void deleteAll();

    @Query("DELETE FROM CursusEntity WHERE sigle = :cursus")
    void delete(String cursus);

    @Query("SELECT * FROM CursusEntity ORDER BY sigle ASC")
    LiveData<List<CursusEntity>> getCursus();

    @Query("UPDATE CursusEntity SET sigle = :newName WHERE sigle = :oldName")
    void updateName(String oldName, String newName);

    @Query("UPDATE CursusEntity SET semestres = :semestres WHERE sigle = :cursusName")
    void updateSemestres(String cursusName, List<SemestreEntity> semestres);
}
