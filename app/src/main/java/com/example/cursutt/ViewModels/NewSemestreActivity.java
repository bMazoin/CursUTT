package com.example.cursutt.ViewModels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cursutt.Models.ModuleEntity;
import com.example.cursutt.R;

import java.util.ArrayList;
import java.util.List;

public class NewSemestreActivity extends AppCompatActivity {

    private AdaptateurBrancheRecycler adaptateurBrancheRecycler;
    private BrancheViewModel brancheViewModel;
    private RecyclerView recycler_branchs;

    private List<ModuleEntity> modules;

    private TextView tv_CS;
    private TextView tv_TM;
    private TextView tv_ST;
    private TextView tv_EC;
    private TextView tv_ME;
    private TextView tv_CT;
    private TextView tv_HP;
    private TextView tv_NPML;
    private TextView tv_TOT;

    private EditText et_search_sigleUE;
    private EditText et_create_sigleUE;
    private EditText et_create_nbCredits;

    private Spinner spinner_typeUE;

    private Button bt_search;
    private Button bt_add;
    private Button bt_create;
    private Button bt_reset;
    private Button bt_del;
    private Button bt_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_semestre);

        modules = new ArrayList<>();

        tv_CS = (TextView) findViewById(R.id.newSemestre_tvCS);
        tv_TM = (TextView) findViewById(R.id.newSemestre_tvTM);
        tv_ST = (TextView) findViewById(R.id.newSemestre_tvST);
        tv_EC = (TextView) findViewById(R.id.newSemestre_tvEC);
        tv_ME = (TextView) findViewById(R.id.newSemestre_tvME);
        tv_CT = (TextView) findViewById(R.id.newSemestre_tvCT);
        tv_HP = (TextView) findViewById(R.id.newSemestre_tvHP);
        tv_NPML = (TextView) findViewById(R.id.newSemestre_tvNPML);
        tv_TOT = (TextView) findViewById(R.id.newSemestre_tvTOT);
        et_search_sigleUE = (EditText) findViewById(R.id.newSemestre_etSigleUE);
        et_create_nbCredits = (EditText) findViewById(R.id.newSemestre_etCreditsUE);
        et_create_sigleUE = (EditText) findViewById(R.id.newSemestre_etSigleUE);

        spinner_typeUE = (Spinner) findViewById(R.id.newSemestre_spinnerTypeUE);

        bt_search = (Button) findViewById(R.id.newSemestre_btnSearch);
        bt_add = (Button) findViewById(R.id.newSemestre_btnAdd);
        bt_create = (Button) findViewById(R.id.newSemestre_btnCreate);
        bt_reset = (Button) findViewById(R.id.newSemestre_btnReset);
        bt_del = (Button) findViewById(R.id.newSemestre_btnDel);
        bt_remove = (Button) findViewById(R.id.newSemestre_btnRemove);

        bt_search.setOnClickListener(v -> searchModule());
        bt_add.setOnClickListener(v -> addModule());
        bt_create.setOnClickListener(v -> createModule());
        bt_reset.setOnClickListener(v -> resetModule());
        bt_del.setOnClickListener(v -> deleteModule());
        bt_remove.setOnClickListener(v -> removeModule());

        brancheViewModel = new ViewModelProvider(this).get(BrancheViewModel.class);
        adaptateurBrancheRecycler = new AdaptateurBrancheRecycler(new AdaptateurBrancheRecycler.BrancheDiff());
        brancheViewModel.getBranches().observe(this, branche -> {
            adaptateurBrancheRecycler.submitList(branche);
        });

        recycler_branchs = (RecyclerView) findViewById(R.id.newSemestre_recycler);
        recycler_branchs.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_branchs.setAdapter(adaptateurBrancheRecycler);

        ArrayAdapter<CharSequence> arrayAdapter_create_TypeUE = ArrayAdapter.createFromResource(this, R.array.types_UE, android.R.layout.simple_spinner_item);
        arrayAdapter_create_TypeUE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typeUE.setAdapter(arrayAdapter_create_TypeUE);
        spinner_typeUE.setMinimumHeight(et_search_sigleUE.getHeight());
    }

    private void createModule(){
        String sigle = et_create_sigleUE.getText().toString();
        int creds = (int) Integer.parseInt(et_create_nbCredits.getText().toString());
    }

    private void resetModule(){

    }

    private void removeModule(){

    }

    private void addModule(){

    }

    private void searchModule(){

    }

    private void deleteModule(){

    }

    private void refreshListView(){

    }

}