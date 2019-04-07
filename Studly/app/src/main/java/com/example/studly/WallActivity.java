package com.example.studly;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WallActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_wall);

        linearLayout = findViewById(R.id.posts);
        //pobranie wartosci z addPostActivity
        String ty,conx,date;
        ArrayList<String> tags;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ty= null;
                conx= null;
                date = null;
                tags=null;
            } else {
                ty= extras.getString("tytul");
                conx = extras.getString("content");
                date = extras.getString("date");
                tags = extras.getStringArrayList("tags");
            }
        } else {
            ty = (String) savedInstanceState.getSerializable("tytul");
            conx = (String) savedInstanceState.getSerializable("content");
            date = (String) savedInstanceState.getSerializable("date");
            tags = savedInstanceState.getStringArrayList("tags");
        }

        if (ty != null && conx != null && date != null){
            System.out.println(ty);
            System.out.println(conx);
            linearLayout.addView(TakeArguments(ty, conx, "ja", date, tags));
        }

        List<String> tags1 = new ArrayList<>();
        tags1.add("statystyka");
        tags1.add("label");
        tags1.add("AGH");
        List<String> tag2 = new ArrayList<>();
        tag2.add("analiza");
        tag2.add("matematyka");
        tag2.add("UJ");

        linearLayout.addView(TakeArguments("Statystyka", "Nie umiem, pomocy!", "Emilka", "10.03.2019", tags1));
        linearLayout.addView(TakeArguments("Całki", "Nie umiem tego też, pomocy!", "Stanisław", "13.03.2019", tag2));
        linearLayout.addView(TakeArguments("Programowanie", "Send help", "Kuba", "15.03.2019", new ArrayList<>()));
        linearLayout.addView(TakeArguments("Macierze", "Nie umiem w macierz", "Piotr", "19.03.2019", new ArrayList<>()));

        //json parsing
        /*
       String text="";
        try {
            InputStream inputStream = new InputStreamReader(getAssets().open("posts.txt"));
            if (inputStream!=null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((tempString = bufferedReader.readLine())!=null){
                    stringBuilder.append(tempString);
                }
                inputStream.close();
                text = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(text);
            JSONArray arr = obj.getJSONArray("posts");
            Log.d("arr", arr.toString());
            for (int i = 0; i < arr.length(); i++){
                linearLayout.addView(TakeArguments(
                        arr.getJSONObject(i).getString("title"),
                        arr.getJSONObject(i).getString("content"),
                        arr.getJSONObject(i).getString("login"),
                        arr.getJSONObject(i).getString("date"),
                        Collections.singletonList(arr.getJSONObject(i).getString("tags"))
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
        //adding a post
        Button addPost = findViewById(R.id.addBtn);
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
