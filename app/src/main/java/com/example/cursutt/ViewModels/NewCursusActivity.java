package com.example.cursutt.ViewModels;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.example.cursutt.Models.ModuleEntity;
import com.example.cursutt.Models.SemestreEntity;
import com.example.cursutt.R;

import java.util.ArrayList;
import java.util.List;

public class NewCursusActivity extends AppCompatActivity {

    Button addButton;
    Button refreshButton;
    RecyclerView recyclerView;

    AdaptateurSemestreRecycler adaptateurSemestreRecycler;
    SemestreViewModel semestreViewModel;
    ModuleViewModel moduleViewModel;
    List<SemestreEntity> semestres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cursus);

        semestres = new ArrayList<>();
        semestreViewModel = new ViewModelProvider(this).get(SemestreViewModel.class);
        moduleViewModel = new ViewModelProvider(this).get(ModuleViewModel.class);

        addButton = (Button) findViewById(R.id.newCursus_addButton);
        refreshButton = (Button) findViewById(R.id.newCursus_refreshButton);
        recyclerView = (RecyclerView) findViewById(R.id.newCursus_recyclerView);
        adaptateurSemestreRecycler = new AdaptateurSemestreRecycler(new AdaptateurSemestreRecycler.SemestreDiff());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adaptateurSemestreRecycler);
        semestreViewModel.getSemestres().observe(this, oSemestre -> {
            semestres = oSemestre;
        });

        adaptateurSemestreRecycler.submitList(semestres);
        addButton.setOnClickListener(v -> createModule());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            String name = data.getStringExtra("semestreName");
            List<String> modulesSigle = data.getStringArrayListExtra("modulesSigle");
            List<String> modulesTypes = data.getStringArrayListExtra("modulesTypes");
            int modulesCreds = data.getIntExtra("modulesCreds",0);

            //modulesSigle.forEach(moduleViewModel.getModule(""));

            SemestreEntity semestre = new SemestreEntity();
            semestre.setSigle(name);
            semestreViewModel.insertSemestre(semestre);
        }
    }

    private void createModule(){
        Intent intent = new Intent(this, NewSemestreActivity.class);
        startActivityForResult(intent,0);
    }
}