package com.example.studly;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class PostView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //pobranie z poprzedniegoo ekranu
        String title,content,login,date;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                title= null;
                content=null;
                login=null;
                date=null;
            } else {
                title= extras.getString("title");
                content= extras.getString("content");
                login= extras.getString("login");
                date= extras.getString("date");
            }
        } else {
            title= (String) savedInstanceState.getSerializable("title");
            content= (String) savedInstanceState.getSerializable("content");
            login= (String) savedInstanceState.getSerializable("login");
            date= (String) savedInstanceState.getSerializable("date");
        }

        //ustawienie wartosci na tym ekranie
        ConstraintLayout post = findViewById(R.id.post_full_view);
        ((TextView) post.getChildAt(0)).setText(title);
        ((TextView) post.getChildAt(1)).setText(content.substring(0, Math.min(60, content.length())));
        ((TextView) post.getChildAt(2)).setText(login);
        ((TextView) post.getChildAt(3)).setText(date);


        FloatingActionButton addComment = findViewById(R.id.addComment);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostView.this, CommentActivity.class));
            }
        });
    }

}
