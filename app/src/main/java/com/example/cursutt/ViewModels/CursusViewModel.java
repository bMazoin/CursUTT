package com.example.cursutt.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cursutt.Models.CursusEntity;
import com.example.cursutt.Models.ModuleEntity;
import com.example.cursutt.Models.RoomCursUTTRepository;
import com.example.cursutt.Models.SemestreEntity;

import java.lang.annotation.Documented;
import java.util.List;

public class CursusViewModel extends AndroidViewModel {

    private RoomCursUTTRepository repository;
    private LiveData<List<CursusEntity>> cursus;

    public CursusViewModel(Application application){
        super(application);
        repository = new RoomCursUTTRepository(application);
        cursus = repository.getCursus();
    }

    public LiveData<List<CursusEntity>> getCursus() { return this.cursus; }

    public void insertCursus(CursusEntity oCursus) { repository.insertCursus(oCursus);}

    public void deleteCursus(CursusEntity oCursus) { repository.deleteCursus(oCursus);}

    public void updateName(CursusEntity actualCursus, String name){ repository.updateName(actualCursus, name);}

    public void updateSemestres(CursusEntity actualCursus, List<SemestreEntity> semestres){ repository.updateSemestres(actualCursus, semestres);}
}
