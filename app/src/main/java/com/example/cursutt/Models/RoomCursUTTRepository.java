package com.example.cursutt.Models;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.content.AsyncTaskLoader;

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

    public LiveData<List<SemestreEntity>> getSemestres() { return mAllSemestre; }

    public LiveData<List<CursusEntity>> getCursus() { return mAllCursus; }

    public void insertModule(ModuleEntity module){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            insertAsyncTaskModule insertTask = new insertAsyncTaskModule(moduleDAO);
            insertTask.doInBackground(module);
        });
    }

    public void deleteModule(ModuleEntity module){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            deleteAsyncTaskModule deleteAsyncTask = new deleteAsyncTaskModule(moduleDAO);
            deleteAsyncTask.doInBackground(module.getSigle());
        });
    }

    public ModuleEntity getModule(String name){
        return moduleDAO.getModule(name);
    }

    public void insertSemestre(SemestreEntity semestre){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            insertAsyncTaskSemestre insertAsyncTask = new insertAsyncTaskSemestre(semestreDAO);
            insertAsyncTask.doInBackground(semestre);
        });
    }

    public void insertModuleInSemestre(List<ModuleEntity> modules, String semestreName){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            insertAsyncTaskModuleInSemestre insertAsyncTask = new insertAsyncTaskModuleInSemestre(semestreDAO);
            insertAsyncTask.doInBackground(new updateModuleInSemestreParams(modules, semestreName));
        });
    }

    public void deleteSemestre(SemestreEntity semestre){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            deleteAsyncTaskSemestre deleteAsyncTask = new deleteAsyncTaskSemestre(semestreDAO);
            deleteAsyncTask.doInBackground(semestre.getSigle());
        });
    }

    public void insertCursus(CursusEntity cursus){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            insertAsyncTaskCursus insertAsyncTask = new insertAsyncTaskCursus(cursusDAO);
            insertAsyncTask.doInBackground(cursus);
        });
    }

    public void deleteCursus(CursusEntity cursus){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            deleteAsyncTaskCursus deleteAsyncTask = new deleteAsyncTaskCursus(cursusDAO);
            deleteAsyncTask.doInBackground(cursus.getSigle());
        });
    }

    public void updateSemestres(CursusEntity actualCursus, List<SemestreEntity> semestres){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            updateCursusSemestresAsyncTask asyncTask = new updateCursusSemestresAsyncTask(cursusDAO);
            asyncTask.doInBackground(new updateCursusSemestresParams(actualCursus.getSigle(), semestres));
        });
    }

    public void updateName(CursusEntity actualCursus, String name){
        RoomCursUTTDatabase.databaseWriteExecutor.execute(() -> {
            updateCursusNameAsyncTask asyncTask = new updateCursusNameAsyncTask(cursusDAO);
            asyncTask.doInBackground(new updateCursusSemestresParams(actualCursus.getSigle(), name));
        });
    }

    private static class updateCursusNameAsyncTask extends AsyncTask<updateCursusSemestresParams, Void, Void>{
        private CursusDAO asyncTask;

        updateCursusNameAsyncTask(CursusDAO asyncTask) {this.asyncTask = asyncTask;}

        @Override
        protected Void doInBackground(final updateCursusSemestresParams... cursus){
            asyncTask.updateName(cursus[0].cursusName, cursus[0].newCursusName);
            return null;
        }
    }

    private static class updateCursusSemestresAsyncTask extends AsyncTask<updateCursusSemestresParams, Void, Void>{
        private CursusDAO asyncTask;

        updateCursusSemestresAsyncTask(CursusDAO asyncTask){ this.asyncTask = asyncTask;}

        @Override
        protected  Void doInBackground(final updateCursusSemestresParams... cursus){
            asyncTask.updateSemestres(cursus[0].cursusName, cursus[0].semestres);
            return null;
        }
    }

    private static class insertAsyncTaskModule extends AsyncTask<ModuleEntity, Void, Void>{
        private ModuleDAO asyncTask;

        insertAsyncTaskModule(ModuleDAO asyncTask){
            this.asyncTask = asyncTask;
        }

        @Override
        protected Void doInBackground(final ModuleEntity... moduleEntities){
            asyncTask.insert(moduleEntities[0]);
            return null;
        }
    }

    private static class insertAsyncTaskModuleInSemestre extends AsyncTask<updateModuleInSemestreParams, Void, Void>{
        private SemestreDAO asyncTask;

        insertAsyncTaskModuleInSemestre(SemestreDAO asyncTask){ this.asyncTask = asyncTask;}

        @Override
        protected Void doInBackground(final updateModuleInSemestreParams... moduleEntities){
            asyncTask.updateModuleListSemestre(moduleEntities[0].modules, moduleEntities[0].semestreName);
            return null;
        }
    }

    private static class deleteAsyncTaskModule extends  AsyncTask<String, Void, Void>{

        private ModuleDAO asyncTask;

        deleteAsyncTaskModule(ModuleDAO task){
            asyncTask = task;
        }

        @Override
        protected Void doInBackground(final String... strings) {
            asyncTask.delete(strings[0]);
            return null;
        }
    }

    private static class insertAsyncTaskSemestre extends AsyncTask<SemestreEntity, Void, Void>{
        private SemestreDAO asyncTask;

        insertAsyncTaskSemestre(SemestreDAO asyncTask){
            this.asyncTask = asyncTask;
        }

        @Override
        protected Void doInBackground(final SemestreEntity... semestreEntities){
            asyncTask.insert(semestreEntities[0]);
            return null;
        }
    }

    private static class deleteAsyncTaskSemestre extends  AsyncTask<String, Void, Void>{

        private SemestreDAO asyncTask;

        deleteAsyncTaskSemestre(SemestreDAO task){
            asyncTask = task;
        }

        @Override
        protected Void doInBackground(final String... strings) {
            asyncTask.delete(strings[0]);
            return null;
        }
    }

    private static class updateModuleInSemestreParams{
        List<ModuleEntity> modules;
        String semestreName;

        updateModuleInSemestreParams(List<ModuleEntity> modules, String semestreName){
            this.modules = modules;
            this.semestreName = semestreName;
        }
    }

    private static class updateCursusSemestresParams{
        List<SemestreEntity> semestres;
        String cursusName;
        String newCursusName;

        updateCursusSemestresParams(String cursusName, List<SemestreEntity> semestres){
            this.semestres = semestres;
            this.cursusName = cursusName;
        }

        updateCursusSemestresParams(String cursusName, String newCursusName){
            this.cursusName = cursusName;
            this.newCursusName = newCursusName;
        }
    }

    private static class insertAsyncTaskCursus extends AsyncTask<CursusEntity, Void, Void>{
        private CursusDAO asyncTask;

        insertAsyncTaskCursus(CursusDAO asyncTask){ this.asyncTask = asyncTask;}

        @Override
        protected Void doInBackground(final CursusEntity... cursusEntities){
            asyncTask.insert(cursusEntities[0]);
            return null;
        }
    }

    private static class deleteAsyncTaskCursus extends AsyncTask<String, Void, Void>{
        private CursusDAO asyncTask;

        deleteAsyncTaskCursus(CursusDAO task){asyncTask = task;}

        @Override
        protected Void doInBackground(final String... strings){
            asyncTask.delete(strings[0]);
            return null;
        }
    }

}
