package com.example.cursutt.ViewModels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.cursutt.Models.CursusEntity;
import com.example.cursutt.Models.SemestreEntity;
import com.example.cursutt.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    RecyclerView recyclerView;
    CursusViewModel cursusViewModel;
    AdaptateurCursusRecycler adaptateurCursusRecycler;

    private List<CursusEntity> cursusList;
    private CursusEntity selectedCursus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.main_addButton);
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);
        cursusViewModel = new ViewModelProvider(this).get(CursusViewModel.class);

        cursusViewModel.getCursus().observe(this, oCursus -> {
            cursusList = oCursus;
        });

        //Clone
        adaptateurCursusRecycler = new AdaptateurCursusRecycler(new AdaptateurCursusRecycler.CursusDiff(), position -> { //Del
            cursusList.remove(position);
            //cursusViewModel.deleteCursus(position);
            refreshList();
        }, position -> { //Clone
            CursusEntity clonedCursus = (CursusEntity) position.clone();
            if (!cursusList.contains(clonedCursus)) {
                clonedCursus.setSigle(clonedCursus.getSigle() + "_cloned");
                cursusList.add(clonedCursus);
                //cursusViewModel.insertCursus(clonedCursus);
                refreshList();
            }
        }, position -> {
            selectedCursus = position;
            openModule();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adaptateurCursusRecycler);

        addButton.setOnClickListener(v -> createModule());
        refreshList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            String name = data.getStringExtra("cursusName");
            List<SemestreEntity> semestres = data.getParcelableArrayListExtra("semestres");

            CursusEntity cursus = new CursusEntity();
            cursus.setSemestres(semestres);
            cursus.setSigle(name);
            //cursusViewModel.insertCursus(cursus);
            cursusList.add(cursus);
        }
        else if(requestCode == 1){
            String name = data.getStringExtra("cursusName");
            List<SemestreEntity> semestres = data.getParcelableArrayListExtra("semestres");

            if(name != selectedCursus.getSigle()){
                //cursusViewModel.updateName(selectedCursus, name);
                cursusList.get(cursusList.indexOf(selectedCursus)).setSigle(name);
            }
            if(!(semestres.containsAll(selectedCursus.getSemestres()) && selectedCursus.getSemestres().containsAll(semestres))){
                //cursusViewModel.updateSemestres(selectedCursus, semestres);
                cursusList.get(cursusList.indexOf(selectedCursus)).setSemestres(semestres);
            }
        }
        refreshList();
    }

    private void createModule(){
        Intent intent = new Intent(this, NewCursusActivity.class);
        intent.putExtra("isCreate", "true");
        startActivityForResult(intent, 0);
    }

    private void openModule(){
        Intent intent = new Intent(this, NewCursusActivity.class);
        intent.putExtra("isCreate", "false");
        intent.putParcelableArrayListExtra("semestres", (ArrayList) selectedCursus.getSemestres());
        intent.putExtra("cursusName", selectedCursus.getSigle());
        startActivityForResult(intent, 1);
    }

    private void refreshList(){
        adaptateurCursusRecycler.submitList(cursusList);
        adaptateurCursusRecycler.notifyDataSetChanged();
    }
}