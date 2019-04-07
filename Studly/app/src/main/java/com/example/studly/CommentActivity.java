package com.example.studly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button send = findViewById(R.id.sendBtn);
        send.setOnClickListener(v -> {
            EditText value = (EditText) findViewById(R.id.commentBox);
            String comment  = value.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("comment",comment);
            setResult(Activity.RESULT_OK,intent);
            finish();
        });

    }

}
