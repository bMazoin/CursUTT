package com.example.cursutt.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cursutt.Models.BrancheEntity;
import com.example.cursutt.Models.RoomCursUTTRepository;

import java.util.List;

public class BrancheViewModel extends AndroidViewModel {

    private RoomCursUTTRepository repository;
    private final LiveData<List<BrancheEntity>> branches;

    public BrancheViewModel(Application application){
        super(application);
        repository = new RoomCursUTTRepository(application);
        branches = repository.getBranches();
    }

    public LiveData<List<BrancheEntity>> getBranches(){
        return branches;
    }


}
