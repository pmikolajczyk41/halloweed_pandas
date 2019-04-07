package com.example.studly;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddPostActivity extends AppCompatActivity {

    WallActivity wallActivity = new WallActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView titleBlock = findViewById(R.id.titleAdded);
        String title = (String) titleBlock.getText();

        TextView contentBlock = findViewById(R.id.contentAdded);
        String content = (String) contentBlock.getText();

        Button addButton = findViewById(R.id.add_post);
        addButton.setOnClickListener(v -> {
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c);
            wallActivity.linearLayout.addView(wallActivity.TakeArguments(title, content, "Ja", formattedDate, new ArrayList<>()));
            Intent intent = new Intent(this, WallActivity.class);
            startActivity(intent);
        });
    }
}