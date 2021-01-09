package com.example.cursutt.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cursutt.Models.ModuleEntity;
import com.example.cursutt.Models.RoomCursUTTRepository;
import com.example.cursutt.Models.SemestreEntity;

import java.util.List;

public class SemestreViewModel extends AndroidViewModel {

    private RoomCursUTTRepository repository;
    private LiveData<List<SemestreEntity>> semestres;

    public SemestreViewModel(Application application){
        super(application);
        repository = new RoomCursUTTRepository(application);
        semestres = repository.getSemestres();
    }

    public LiveData<List<SemestreEntity>> getSemestres() { return this.semestres; }

    public void insertSemestre(SemestreEntity semestre) { repository.insertSemestre(semestre);}

    public void deleteSemestre(SemestreEntity semestre) { repository.deleteSemestre(semestre);}

    public void updateModuleList(List<ModuleEntity> modules, String semestreName) {repository.insertModuleInSemestre(modules, semestreName);}

}
