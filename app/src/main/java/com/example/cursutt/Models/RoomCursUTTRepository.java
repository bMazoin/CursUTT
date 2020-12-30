package com.example.cursutt.Models;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cursutt.R;

import java.util.ArrayList;
import java.util.List;

public class RoomCursUTTRepository {
    private BrancheDAO brancheDAO;
    private ModuleDAO moduleDAO;
    private SemestreDAO semestreDAO;
    private CursusDAO cursusDAO;

    private MutableLiveData<List<BrancheEntity>> mAllBranches;
    private LiveData<List<ModuleEntity>> mAllModules;
    private LiveData<List<SemestreEntity>> mAllSemestre;
    private LiveData<List<CursusEntity>> mAllCursus;

    public RoomCursUTTRepository(Application application){
        RoomCursUTTDatabase db = RoomCursUTTDatabase.getDatabase(application);

        brancheDAO = db.brancheDAO();
        moduleDAO = db.moduleDAO();
        semestreDAO = db.semestreDAO();
        cursusDAO = db.cursusDAO();

        ArrayAdapter<CharSequence> array = ArrayAdapter.createFromResource(application, R.array.branches, android.R.layout.simple_spinner_item);
        List<BrancheEntity> listBranches = new ArrayList<>();
        for(int i = 0; i < array.getCount(); i++){
            listBranches.add(new BrancheEntity(array.getItem(i).toString()));
        }

        //Remove "Branche"
        listBranches.remove(0);

        mAllBranches = new MutableLiveData<>();
        mAllBranches.setValue(listBranches);
        mAllModules = moduleDAO.getModules();
        mAllSemestre = semestreDAO.getSemestres();
        mAllCursus = cursusDAO.getCursus();
    }

    public LiveData<List<BrancheEntity>> getBranches(){
        return mAllBranches;
    }

    public LiveData<List<ModuleEntity>> getModules(){
        return mAllModules;
    }

    public void insertModule(ModuleEntity module){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            insertAsyncTask insertTask = new insertAsyncTask(moduleDAO);
            insertTask.doInBackground(module);
        });
    }

    public void deleteModule(ModuleEntity module){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            deleteAsyncTask deleteAsyncTask = new deleteAsyncTask(moduleDAO);
            deleteAsyncTask.doInBackground(module.getSigle());
        });
    }

    private static class insertAsyncTask extends AsyncTask<ModuleEntity, Void, Void>{
        private ModuleDAO asyncTask;

        insertAsyncTask(ModuleDAO asyncTask){
            this.asyncTask = asyncTask;
        }

        @Override
        protected Void doInBackground(final ModuleEntity... moduleEntities){
            asyncTask.insert(moduleEntities[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends  AsyncTask<String, Void, Void>{

        private ModuleDAO asyncTask;

        deleteAsyncTask(ModuleDAO task){
            asyncTask = task;
        }

        @Override
        protected Void doInBackground(final String... strings) {
            asyncTask.delete(strings[0]);
            return null;
        }
    }
}
