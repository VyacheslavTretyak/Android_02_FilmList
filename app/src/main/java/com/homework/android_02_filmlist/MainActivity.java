package com.homework.android_02_filmlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnet;
    private ArrayList<Movie> movies = new ArrayList<>();
    private int idCnt = 0;
    private EditText editTitle;
    private EditText editGenre;
    private EditText editYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTitle = findViewById(R.id.editTitle);
        editGenre = findViewById(R.id.editGenre);
        editYear = findViewById(R.id.editYear);

        movies.add(new Movie("genre 1", "movie 1", 2001));
        movies.add(new Movie("genre 2", "movie 2", 2005));
        movies.add(new Movie("genre 1", "movie 3", 2007));
        movies.add(new Movie("genre 3", "movie 4", 2009));

        spinnet = (Spinner) this.findViewById(R.id.spinner);

        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for(int i = 0; i<movies.size(); i++){
            HashMap<String, Object> item = new HashMap<>();
            item.put("title", movies.get(i).title);
            item.put("genre", movies.get(i).genre);
            item.put("year", movies.get(i).year);
            list.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.activity_movie,
                new String[]{"title", "genre", "year"},
                new int[] {R.id.tvTitle, R.id.tvGenre, R.id.tvYear}
        );
        adapter.setDropDownViewResource(R.layout.activity_movie);
        spinnet.setAdapter(adapter);
        spinnet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String,Object> item = (HashMap<String,Object>) adapterView.getAdapter().getItem(i);
                Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    public void btnActionClick(View view) {
        Movie movie = new Movie(editGenre.getText().toString(),
                editTitle.getText().toString(),
                Integer.parseInt(editYear.getText().toString()));

        this.movies.add(movie);

        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for(int i = 0; i<movies.size(); i++){
            HashMap<String, Object> item = new HashMap<>();
            item.put("title", movies.get(i).title);
            item.put("genre", movies.get(i).genre);
            item.put("year", movies.get(i).year);
            list.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.activity_movie,
                new String[]{"title", "genre", "year"},
                new int[] {R.id.tvTitle, R.id.tvGenre, R.id.tvYear}
        );
        adapter.setDropDownViewResource(R.layout.activity_movie);
        spinnet.setAdapter(adapter);


    }
}