package com.example.cursutt.ViewModels;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cursutt.Models.ModuleEntity;
import com.example.cursutt.Models.SemestreEntity;
import com.example.cursutt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NewCursusActivity extends AppCompatActivity {

    private Button addButton;
    private Button refreshButton;
    private Button saveButton;
    private RecyclerView recyclerView;

    private TextView tv_CS;
    private TextView tv_TM;
    private TextView tv_ST;
    private TextView tv_EC;
    private TextView tv_ME;
    private TextView tv_CT;
    private TextView tv_HP;
    private TextView tv_TOT;

    private EditText et_nom;

    private AdaptateurSemestreRecycler adaptateurSemestreRecycler;
    private SemestreViewModel semestreViewModel;
    private ModuleViewModel moduleViewModel;
    private List<SemestreEntity> semestres;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cursus);

        semestreViewModel = new ViewModelProvider(this).get(SemestreViewModel.class);
        moduleViewModel = new ViewModelProvider(this).get(ModuleViewModel.class);

        addButton = (Button) findViewById(R.id.newCursus_addButton);
        refreshButton = (Button) findViewById(R.id.newCursus_refreshButton);
        saveButton = (Button) findViewById(R.id.newCursus_btnSave);
        recyclerView = (RecyclerView) findViewById(R.id.newCursus_recyclerView);

        tv_CS = (TextView) findViewById(R.id.newCursus_tvCS);
        tv_TM = (TextView) findViewById(R.id.newCursus_tvTM);
        tv_ST = (TextView) findViewById(R.id.newCursus_tvST);
        tv_EC = (TextView) findViewById(R.id.newCursus_tvEC);
        tv_ME = (TextView) findViewById(R.id.newCursus_tvME);
        tv_CT = (TextView) findViewById(R.id.newCursus_tvCT);
        tv_HP = (TextView) findViewById(R.id.newCursus_tvHP);
        tv_TOT = (TextView) findViewById(R.id.newCursus_tvTOT);

        et_nom = (EditText) findViewById(R.id.newCursus_etNom);

        adaptateurSemestreRecycler = new AdaptateurSemestreRecycler(new AdaptateurSemestreRecycler.SemestreDiff());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adaptateurSemestreRecycler);

        if(getIntent().getStringExtra("isCreate").equals("false")){
            semestres = getIntent().getParcelableArrayListExtra("semestres");
            et_nom.setText(getIntent().getStringExtra("cursusName"));
        }
        else{
            semestres = new ArrayList<>();
        }

        addButton.setOnClickListener(v -> createModule());
        refreshButton.setOnClickListener(v -> refreshInfos());
        saveButton.setOnClickListener(v -> saveCursus());

        refreshInfos();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            String name = data.getStringExtra("semestreName");
            List<ModuleEntity> modules = data.getParcelableArrayListExtra("modules");

            SemestreEntity semestre = new SemestreEntity();
            semestre.setSigle(name);
            semestre.setModules(modules);
            semestres.add(semestre);
            //semestreViewModel.insertSemestre(semestre);
            refreshInfos();
        }
    }

    private void createModule(){
        Intent intent = new Intent(this, NewSemestreActivity.class);
        startActivityForResult(intent,0);
    }

    private void saveCursus(){

        if(!et_nom.getText().toString().equals("")) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("cursusName", et_nom.getText().toString());
            returnIntent.putParcelableArrayListExtra("semestres", (ArrayList) semestres);
            setResult(0, returnIntent);
            finish();
        }
        else{
            Toast.makeText(this, "Vous devez rentrer un nom pour enregistrer le cursus", Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshInfos(){
        adaptateurSemestreRecycler.submitList(semestres);
        adaptateurSemestreRecycler.notifyDataSetChanged();

        AtomicInteger cs = new AtomicInteger(0);
        AtomicInteger tm = new AtomicInteger(0);
        AtomicInteger st = new AtomicInteger(0);
        AtomicInteger ec = new AtomicInteger(0);
        AtomicInteger me = new AtomicInteger(0);
        AtomicInteger ct = new AtomicInteger(0);
        AtomicInteger hp = new AtomicInteger(0);
        AtomicInteger tot = new AtomicInteger(0);

        semestres.forEach(oSemestre -> {
            List<ModuleEntity> storedModules = oSemestre.getModules();

            cs.getAndAdd(storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("CS")).mapToInt(ModuleEntity::getCredit).sum());
            tm.getAndAdd(storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("TM")).mapToInt(ModuleEntity::getCredit).sum());
            st.getAndAdd(storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("ST")).mapToInt(ModuleEntity::getCredit).sum());
            ec.getAndAdd(storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("EC")).mapToInt(ModuleEntity::getCredit).sum());
            me.getAndAdd(storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("ME")).mapToInt(ModuleEntity::getCredit).sum());
            ct.getAndAdd(storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("CT")).mapToInt(ModuleEntity::getCredit).sum());
            hp.getAndAdd(storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("HP")).mapToInt(ModuleEntity::getCredit).sum());
            tot.getAndAdd(storedModules.stream().mapToInt(ModuleEntity::getCredit).sum());
        });

        tv_CS.setText(cs.get() + "/24");
        tv_TM.setText(tm.get() + "/24");
        tv_ST.setText(st.get() + "/60");
        tv_EC.setText(ec.get() + "/12");
        tv_ME.setText(me.get() + "/4");
        tv_CT.setText(ct.get() + "/4");
        tv_HP.setText(hp.get()+"");
        tv_TOT.setText(tot.get() + "/180");

        if(cs.get() < 24){
            tv_CS.setTextColor(Color.rgb(195,49,73));
        }
        if(tm.get() < 24){
            tv_TM.setTextColor(Color.rgb(195, 49, 73));
        }
        if(st.get() < 60){
            tv_ST.setTextColor(Color.rgb(195, 49, 73));
        }
        if(ec.get() < 12){
            tv_EC.setTextColor(Color.rgb(195, 49, 73));
        }
        if(me.get() < 4){
            tv_ME.setTextColor(Color.rgb(195, 49, 73));
        }
        if(ct.get() < 4){
            tv_CT.setTextColor(Color.rgb(195, 49, 73));
        }
        if(tot.get() < 180){
            tv_TOT.setTextColor(Color.rgb(195, 49, 73));
        }

    }
}