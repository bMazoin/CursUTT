package com.example.cursutt.Models;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BrancheEntity.class, CursusEntity.class, ModuleEntity.class, SemestreEntity.class}, version = 1, exportSchema = false)
@TypeConverters({SemestreConverters.class, BrancheConverters.class, ModuleConverters.class})
public abstract class RoomCursUTTDatabase extends RoomDatabase {

    public abstract ModuleDAO moduleDAO();
    public abstract BrancheDAO brancheDAO();
    public abstract SemestreDAO semestreDAO();
    public abstract CursusDAO cursusDAO();

    private static volatile RoomCursUTTDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RoomCursUTTDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (RoomCursUTTDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomCursUTTDatabase.class, "cursutt_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                ModuleDAO moduleDAO = INSTANCE.moduleDAO();
                BrancheDAO brancheDAO = INSTANCE.brancheDAO();
                SemestreDAO semestreDAO = INSTANCE.semestreDAO();
                CursusDAO cursusDAO = INSTANCE.cursusDAO();

                moduleDAO.deleteAll();
                brancheDAO.deleteAll();
                semestreDAO.deleteAll();
                cursusDAO.deleteAll();
            });
        }
    };

}
