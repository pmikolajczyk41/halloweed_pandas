package com.example.studly;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WallActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_wall);
        List<String> tags = new ArrayList<String>();
        tags.add("statystyka");
        tags.add("label");
        linearLayout = findViewById(R.id.posts);
        linearLayout.addView(TakeArguments("Statystyka", "Nie umiem, pomocy!", "Emilka", "10.03.2019", tags));
        linearLayout.addView(TakeArguments("Całki", "Nie umiem tego też, pomocy!", "Stanisław", "13.03.2019", new ArrayList<>()));
        linearLayout.addView(TakeArguments("Programowanie", "Send help", "Kuba", "15.03.2019", new ArrayList<>()));
        linearLayout.addView(TakeArguments("Macierze", "Nie umiem w macierz", "Piotr", "19.03.2019", new ArrayList<>()));


     /*   String text;
        try {
            text = new String(Files.readAllBytes(Paths.get("C:\\Users\\Laura\\Documents\\App\\halloweed_pandas-android_app\\Studly\\posty.txt")));
            JSONObject obj = new JSONObject(text);
            System.out.println(text);
            JSONArray arr = obj.getJSONArray("posts");
       //     System.out.println(arr.getJSONObject(i).getString("login"));

            for (int i = 0; i < arr.length(); i++){
                linearLayout.addView(TakeArguments(
                        arr.getJSONObject(i).getString("title"),
                        arr.getJSONObject(i).getString("content"),
                        arr.getJSONObject(i).getString("login"),
                        arr.getJSONObject(i).getString("date"),
                        Collections.singletonList(arr.getJSONObject(i).getString("tags"))
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        FloatingActionButton addPost = findViewById(R.id.add_post);
        addPost.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddPostActivity.class);
            startActivity(intent);
        });
    }


    protected ConstraintLayout TakeArguments(String title, String content, String login, String date, List<String> tags){
        ConstraintLayout post = ((ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.post_model, null));
        ((TextView) post.getChildAt(0)).setText(title);
        ((TextView) post.getChildAt(1)).setText(content.substring(0, Math.min(60, content.length())));
        ((TextView) post.getChildAt(2)).setText(login);
        ((TextView) post.getChildAt(3)).setText(date);
        for(int i = 0; i < tags.size(); i++){
            ((TextView)((LinearLayout)post.getChildAt(4)).getChildAt(i)).setText(tags.get(i));
        }
        for(int i = tags.size(); i < 3; i++)
            ((LinearLayout)post.getChildAt(4)).getChildAt(i).setVisibility(View.GONE);

    post.setOnClickListener(v -> {
            Intent intent = new Intent(this, PostView.class);
            intent.putExtra("title" , ((TextView) post.getChildAt(0)).getText());
            intent.putExtra("content" , ((TextView) post.getChildAt(1)).getText());
            intent.putExtra("login" , ((TextView) post.getChildAt(2)).getText());
            intent.putExtra("date" , ((TextView) post.getChildAt(3)).getText());
            startActivity(intent);
        });
        return post;
    }



}
