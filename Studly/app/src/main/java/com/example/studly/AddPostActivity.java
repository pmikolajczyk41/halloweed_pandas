package com.example.studly;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class AddPostActivity extends AppCompatActivity {

    WallActivity wallActivity = new WallActivity();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_post);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText titleBlock = (EditText)findViewById(R.id.titleAdded);
        String title = titleBlock.getText().toString();

        EditText contentBlock = (EditText)findViewById(R.id.contentAdded);
        String content = contentBlock.getText().toString(); //cos jest nie tak z tymi wartosciami

        EditText tagsBlock = (EditText) findViewById(R.id.tags);
        String tagging = tagsBlock.getText().toString();
        String[] tagger = tagging.split(",");
        ArrayList<String> tags = new ArrayList<>(tagger.length);
        Collections.addAll( tags, tagger );

        Button addButton = findViewById(R.id.addBtn2);
        addButton.setOnClickListener(v -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.now();
            Intent it = new Intent(this, WallActivity.class);
            it.putExtra("tytul",title);
            it.putExtra("content", content);
            it.putExtra("date", dtf.format(localDate));
            it.putExtra("tags", tags);
            startActivity(it);
        });
    }
}