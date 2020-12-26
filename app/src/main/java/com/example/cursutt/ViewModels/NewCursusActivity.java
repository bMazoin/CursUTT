package com.example.cursutt.ViewModels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cursutt.R;

public class NewCursusActivity extends AppCompatActivity {

    Button addButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cursus);

        addButton = (Button) findViewById(R.id.newCursus_addButton);
        recyclerView = (RecyclerView) findViewById(R.id.newCursus_recyclerView);

        addButton.setOnClickListener(v -> createModule());
    }

    private void createModule(){
        Intent intent = new Intent(this, NewSemestreActivity.class);
        startActivity(intent);
    }
}