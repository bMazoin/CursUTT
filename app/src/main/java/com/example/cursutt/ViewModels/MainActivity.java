package com.example.cursutt.ViewModels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import com.example.cursutt.R;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.main_addButton);
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);

        addButton.setOnClickListener(v -> createModule());
    }

    private void createModule(){
        Intent intent = new Intent(this, NewCursusActivity.class);
        startActivity(intent);
    }
}