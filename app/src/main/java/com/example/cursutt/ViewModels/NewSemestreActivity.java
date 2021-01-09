package com.example.cursutt.ViewModels;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cursutt.Models.BrancheEntity;
import com.example.cursutt.Models.ModuleEntity;
import com.example.cursutt.Models.SemestreEntity;
import com.example.cursutt.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NewSemestreActivity extends AppCompatActivity {

    //region Variables

    private AdaptateurBrancheRecycler adaptateurBrancheRecycler;
    private AdaptateurSearchResultRecycler adaptateurSearchResultRecycler;
    private AdaptateurModuleRecycler adaptateurUEs;

    private BrancheViewModel brancheViewModel;
    private ModuleViewModel moduleViewModel;
    private SemestreViewModel semestreViewModel;

    private RecyclerView recycler_branchs;
    private RecyclerView recycler_search;
    private RecyclerView recycler_ues;

    private List<ModuleEntity> selectedSearchModules;
    private List<ModuleEntity> selectedStoredModules;
    private List<BrancheEntity> selectedBranches;
    private List<ModuleEntity> searchModules;
    private List<ModuleEntity> searchResultDisplayedModules;
    private List<ModuleEntity> storedModules;

    private TextView tv_CS;
    private TextView tv_TM;
    private TextView tv_ST;
    private TextView tv_EC;
    private TextView tv_ME;
    private TextView tv_CT;
    private TextView tv_HP;
    private TextView tv_TOT;

    private EditText et_nom;
    private EditText et_sigleUE;
    private EditText et_nbCredits;

    private Spinner spinner_typeUE;

    private Button bt_search;
    private Button bt_add;
    private Button bt_create;
    private Button bt_reset;
    private Button bt_del;
    private Button bt_remove;
    private Button bt_save;

    //endregion

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_semestre);

        //region Instanciation des variables

        selectedSearchModules = new ArrayList<>();
        selectedStoredModules = new ArrayList<>();
        storedModules = new ArrayList<>();
        selectedBranches = new ArrayList<>();
        searchResultDisplayedModules = new ArrayList<>();

        brancheViewModel = new ViewModelProvider(this).get(BrancheViewModel.class);
        moduleViewModel = new ViewModelProvider(this).get(ModuleViewModel.class);
        semestreViewModel = new ViewModelProvider(this).get(SemestreViewModel.class);

        tv_CS = (TextView) findViewById(R.id.newSemestre_tvCS);
        tv_TM = (TextView) findViewById(R.id.newSemestre_tvTM);
        tv_ST = (TextView) findViewById(R.id.newSemestre_tvST);
        tv_EC = (TextView) findViewById(R.id.newSemestre_tvEC);
        tv_ME = (TextView) findViewById(R.id.newSemestre_tvME);
        tv_CT = (TextView) findViewById(R.id.newSemestre_tvCT);
        tv_HP = (TextView) findViewById(R.id.newSemestre_tvHP);
        tv_TOT = (TextView) findViewById(R.id.newSemestre_tvTOT);
        et_nom = (EditText) findViewById(R.id.newSemestre_etNom);
        et_sigleUE = (EditText) findViewById(R.id.newSemestre_etSigleUE);
        et_nbCredits = (EditText) findViewById(R.id.newSemestre_etCreditsUE);

        spinner_typeUE = (Spinner) findViewById(R.id.newSemestre_spinnerTypeUE);

        recycler_branchs = (RecyclerView) findViewById(R.id.newSemestre_recycler);
        recycler_search = (RecyclerView) findViewById(R.id.newSemestre_recyclerSearchResult);
        recycler_ues = (RecyclerView) findViewById(R.id.newSemestre_recyclerViewUEs);

        bt_search = (Button) findViewById(R.id.newSemestre_btnSearch);
        bt_add = (Button) findViewById(R.id.newSemestre_btnAdd);
        bt_create = (Button) findViewById(R.id.newSemestre_btnCreate);
        bt_reset = (Button) findViewById(R.id.newSemestre_btnReset);
        bt_del = (Button) findViewById(R.id.newSemestre_btnDel);
        bt_remove = (Button) findViewById(R.id.newSemestre_btnRemove);
        bt_save = (Button) findViewById(R.id.newSemestre_btnSave);

        //endregion

        //region Callbacks Buttons

        bt_search.setOnClickListener(v -> searchModule());
        bt_add.setOnClickListener(v -> addModule());
        bt_create.setOnClickListener(v -> createModule());
        bt_reset.setOnClickListener(v -> resetModule());
        bt_del.setOnClickListener(v -> deleteModule());
        bt_remove.setOnClickListener(v -> removeModule());
        bt_save.setOnClickListener(v -> saveSemestre());

        //endregion

        //region Adaptateurs

        adaptateurBrancheRecycler = new AdaptateurBrancheRecycler(new AdaptateurBrancheRecycler.BrancheDiff(), new AdaptateurBrancheRecycler.OnItemCheckListener() {
            @Override
            public void onItemCheck(BrancheEntity item) {
                selectedBranches.add(item);
            }

            @Override
            public void onItemUncheck(BrancheEntity item) {
                selectedBranches.remove(item);
            }
        });
        brancheViewModel.getBranches().observe(this, branche -> {
            adaptateurBrancheRecycler.submitList(branche);
        });

        adaptateurSearchResultRecycler = new AdaptateurSearchResultRecycler(new AdaptateurSearchResultRecycler.ModuleDiff(), new AdaptateurSearchResultRecycler.OnItemCheckListener() {
            @Override
            public void onItemCheck(ModuleEntity item) {
                selectedSearchModules.add(item);
            }

            @Override
            public void onItemUncheck(ModuleEntity item){
                selectedSearchModules.remove(item);
            }
        });
        moduleViewModel.getModules().observe(this, module -> {
            searchModules = module;
        });

        adaptateurUEs = new AdaptateurModuleRecycler(new AdaptateurModuleRecycler.ModuleDiff(), new AdaptateurModuleRecycler.OnItemCheckListener() {
            @Override
            public void onItemCheck(ModuleEntity item) {
                selectedStoredModules.add(item);
            }

            @Override
            public void onItemUncheck(ModuleEntity item) {
                selectedStoredModules.remove(item);
            }
        });

        recycler_branchs.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_branchs.setAdapter(adaptateurBrancheRecycler);

        recycler_search.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_search.setAdapter(adaptateurSearchResultRecycler);

        recycler_ues.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_ues.setAdapter(adaptateurUEs);

        ArrayAdapter<CharSequence> arrayAdapter_create_TypeUE = ArrayAdapter.createFromResource(this, R.array.types_UE, android.R.layout.simple_spinner_item);
        arrayAdapter_create_TypeUE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typeUE.setAdapter(arrayAdapter_create_TypeUE);
        spinner_typeUE.setMinimumHeight(et_sigleUE.getHeight());

        //endregion
    }

    //region Callback events

    private void createModule(){
        if(et_sigleUE.getText().toString().equals("")
            || et_nbCredits.getText().toString().equals("")
            || spinner_typeUE.getSelectedItem().toString().equals(spinner_typeUE.getItemAtPosition(0))){

            Toast.makeText(this, "Vous devez renseigner tous les champs nécessaires", Toast.LENGTH_LONG).show();
            return;
        }

        String sigle = et_sigleUE.getText().toString();
        int creds = (int) Integer.parseInt(et_nbCredits.getText().toString());
        String typeUe = spinner_typeUE.getSelectedItem().toString();

        ModuleEntity module = new ModuleEntity(sigle, selectedBranches, creds, typeUe);

        moduleViewModel.insertModule(module);
        resetModule();
    }

    private void resetModule(){
        et_sigleUE.setText("");
        et_nbCredits.setText("");
        spinner_typeUE.setSelection(0);
        adaptateurSearchResultRecycler.submitList(new ArrayList<>());
        adaptateurUEs.submitList(new ArrayList<>());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void removeModule(){
        storedModules.removeAll(selectedStoredModules);
        refreshListView();
        refreshTextView();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addModule(){
        storedModules.addAll(selectedSearchModules.stream().filter(oModule -> !storedModules.contains(oModule)).collect(Collectors.toList()));
        refreshListView();
        refreshTextView();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void searchModule(){
        String sigle = et_sigleUE.getText().toString();
        int creds;
        String sc = et_nbCredits.getText().toString();
        String typeUe = spinner_typeUE.getSelectedItem().toString();

        if(sc.length() != 0){
            creds = (int) Integer.parseInt(sc);
        }
        else {
            creds = 0;
        }

        searchResultDisplayedModules = searchModules.stream()
                                        .filter(mod -> mod.getCredit() >= creds)
                                        .collect(Collectors.toList());

        if(selectedBranches.size() != 0){
            searchResultDisplayedModules = searchResultDisplayedModules.stream().filter(mod -> selectedBranches.stream().anyMatch(m -> mod.getBranche().equals(m))).collect(Collectors.toList());
        }
        if(!sigle.equals("")){
            searchResultDisplayedModules = searchResultDisplayedModules.stream().filter(mod -> mod.getSigle().equals(sigle)).collect(Collectors.toList());
        }
        if(!typeUe.equals(spinner_typeUE.getItemAtPosition(0))){
            searchResultDisplayedModules = searchResultDisplayedModules.stream().filter(mod -> mod.getTypeUE().equals(typeUe)).collect(Collectors.toList());
        }

        refreshListView();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void deleteModule(){
        selectedSearchModules.forEach(oModule -> {
            moduleViewModel.deleteModule(oModule);
            searchResultDisplayedModules.remove(oModule);
        });
        refreshListView();
    }

    private void refreshListView(){
        adaptateurUEs.submitList(storedModules);
        adaptateurUEs.notifyDataSetChanged();
        adaptateurSearchResultRecycler.submitList(searchResultDisplayedModules);
        adaptateurSearchResultRecycler.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshTextView(){
        int cs = storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("CS")).mapToInt(ModuleEntity::getCredit).sum();
        int tm = storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("TM")).mapToInt(ModuleEntity::getCredit).sum();
        int st = storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("ST")).mapToInt(ModuleEntity::getCredit).sum();
        int ec = storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("EC")).mapToInt(ModuleEntity::getCredit).sum();
        int me = storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("ME")).mapToInt(ModuleEntity::getCredit).sum();
        int ct = storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("CT")).mapToInt(ModuleEntity::getCredit).sum();
        int hp = storedModules.stream().filter(oModule -> oModule.getTypeUE().equals("HP")).mapToInt(ModuleEntity::getCredit).sum();
        int tot = storedModules.stream().mapToInt(ModuleEntity::getCredit).sum();

        tv_CS.setText(Integer.toString(cs));
        tv_TM.setText(Integer.toString(tm));
        tv_ST.setText(Integer.toString(st));
        tv_EC.setText(Integer.toString(ec));
        tv_ME.setText(Integer.toString(me));
        tv_CT.setText(Integer.toString(ct));
        tv_HP.setText(Integer.toString(hp));
        tv_TOT.setText(Integer.toString(tot));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveSemestre() {

        if(et_nom.getText().equals("")){
            Toast.makeText(this, "Vous devez rentrer un nom pour enregistrer le semestre", Toast.LENGTH_LONG).show();
            return;
        }

        Intent returnIntent = new Intent();
        returnIntent.putStringArrayListExtra("modulesSigle", (ArrayList) storedModules.stream().map(oModule -> oModule.getSigle()).collect(Collectors.toList()));
        returnIntent.putExtra("modulesCreds", storedModules.stream().map(oModule -> oModule.getCredit()).reduce(0, Integer::sum));
        returnIntent.putStringArrayListExtra("modulesTypes", (ArrayList) storedModules.stream().map(oModule -> oModule.getTypeUE()).collect(Collectors.toList()));
        returnIntent.putExtra("semestreName", et_nom.getText());
        setResult(0, returnIntent);
        finish();
    }

    //endregion

}