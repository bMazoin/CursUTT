package com.example.cursutt.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cursutt.Models.ModuleEntity;
import com.example.cursutt.Models.RoomCursUTTRepository;

import java.util.List;

public class ModuleViewModel extends AndroidViewModel {

    private RoomCursUTTRepository repository;
    private LiveData<List<ModuleEntity>> modules;

    public ModuleViewModel(Application application){
        super(application);
        repository = new RoomCursUTTRepository(application);
        modules = repository.getModules();
    }

    public LiveData<List<ModuleEntity>> getModules(){
        return this.modules;
    }

    public void insertModule(ModuleEntity module){
        repository.insertModule(module);
    }

    public void deleteModule(ModuleEntity module){
        repository.deleteModule(module);
    }

}
