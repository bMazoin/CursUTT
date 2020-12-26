package com.example.cursutt.ViewModels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cursutt.R;

public class NewSemestreActivity extends AppCompatActivity {

    TextView et_CS;
    TextView et_TM;
    TextView et_ST;
    TextView et_EC;
    TextView et_ME;
    TextView et_CT;
    TextView et_HP;
    TextView et_NPML;
    TextView et_TOT;
    TextView et_sigleUE;
    TextView et_nbCredits;

    Spinner spinner_search_typeUE;
    Spinner spinner_search_branche;
    Spinner spinner_create_typeUE;
    Spinner spinner_create_branche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_semestre);

        et_CS = (TextView) findViewById(R.id.newSemestre_tvCS);
        et_TM = (TextView) findViewById(R.id.newSemestre_tvTM);
        et_ST = (TextView) findViewById(R.id.newSemestre_tvST);
        et_EC = (TextView) findViewById(R.id.newSemestre_tvEC);
        et_ME = (TextView) findViewById(R.id.newSemestre_tvME);
        et_CT = (TextView) findViewById(R.id.newSemestre_tvCT);
        et_HP = (TextView) findViewById(R.id.newSemestre_tvHP);
        et_NPML = (TextView) findViewById(R.id.newSemestre_tvNPML);
        et_TOT = (TextView) findViewById(R.id.newSemestre_tvTOT);
        et_sigleUE = (TextView) findViewById(R.id.newSemestre_search_etSigleUE);
        et_nbCredits = (TextView) findViewById(R.id.newSemestre_create_etCreditsUE);

        spinner_search_typeUE = (Spinner) findViewById(R.id.newSemestre_search_spinnerTypeUE);
        spinner_search_branche = (Spinner) findViewById(R.id.newSemestre_search_spinnerBranche);

        ArrayAdapter<CharSequence> arrayAdapter_search_TypeUE = ArrayAdapter.createFromResource(this, R.array.types_UE, android.R.layout.simple_spinner_item);
        arrayAdapter_search_TypeUE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_search_typeUE.setAdapter(arrayAdapter_search_TypeUE);
        spinner_search_typeUE.setMinimumHeight(et_sigleUE.getHeight());

        ArrayAdapter<CharSequence> arrayAdapter_search_Branche = ArrayAdapter.createFromResource(this, R.array.branches, android.R.layout.simple_spinner_item);
        arrayAdapter_search_Branche.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_search_branche.setAdapter(arrayAdapter_search_Branche);
        spinner_search_branche.setMinimumHeight(et_sigleUE.getHeight());

        ArrayAdapter<CharSequence> arrayAdapter_create_TypeUE = ArrayAdapter.createFromResource(this, R.array.types_UE, android.R.layout.simple_spinner_item);
        arrayAdapter_create_TypeUE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_create_typeUE.setAdapter(arrayAdapter_create_TypeUE);
        spinner_create_typeUE.setMinimumHeight(et_sigleUE.getHeight());

        ArrayAdapter<CharSequence> arrayAdapter_create_Branche = ArrayAdapter.createFromResource(this, R.array.branches, android.R.layout.simple_spinner_item);
        arrayAdapter_create_Branche.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_create_branche.setAdapter(arrayAdapter_create_Branche);
        spinner_create_branche.setMinimumHeight(et_sigleUE.getHeight());


    }
}