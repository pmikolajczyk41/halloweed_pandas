package com.example.studly;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PostView extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        //pobranie wartosci z komentarza
        String comment;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                comment = null;
            } else {
                comment= extras.getString("comment");
            }
        } else {
            comment = (String) savedInstanceState.getSerializable("comment");
        }

        if(comment != null){
            TextView cmntxt = findViewById(R.id.comment_text);
            cmntxt.setText(comment);

            TextView cmnlogin = findViewById(R.id.comment_login);
            cmnlogin.setText("user");

            TextView cmndate = findViewById(R.id.comment_date);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.now();
            cmndate.setText(dtf.format(localDate));
        }

        FloatingActionButton addComment = findViewById(R.id.addComment);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(PostView.this, CommentActivity.class), 1);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK ){
            ConstraintLayout comment = ((ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.comment_full_view, null));
            ((TextView)comment.getChildAt(0)).setText("ja");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.now();
            ((TextView)comment.getChildAt(1)).setText(dtf.format(localDate));
            ((TextView)comment.getChildAt(2)).setText(data.getExtras().getString("comment"));

            ((LinearLayout)findViewById(R.id.comments_container)).addView(comment);
        }
    }
}
