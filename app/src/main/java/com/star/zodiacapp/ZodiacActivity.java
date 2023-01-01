package com.star.zodiacapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ZodiacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodiac);

        RecyclerView mRecyclerView = findViewById(R.id.recycler_zodiac);
        ZodiacAdapter mAdapter = new ZodiacAdapter(this, initializeData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.grid_column_count)));


    }



    private ArrayList<Zodiac> initializeData() {
        ArrayList<Zodiac> zodiacs = new ArrayList<>();

        Bundle arguments = getIntent().getExtras();

        String jsonInfo = arguments.getString("JSON_info");

        Resources res = getResources();
        TypedArray images = res.obtainTypedArray(R.array.zodiacImages);
        String[] dates = res.getStringArray(R.array.zodiacDates);


        try {
            JSONObject object = new JSONObject(jsonInfo);
            JSONArray array = object.getJSONArray("horoscope");

            System.out.println(array);

            for (int i = 0; array.length() > i; i++) {
                JSONObject currentObject = (JSONObject) array.get(i);
                Zodiac currentZodiac = new Zodiac(images.getResourceId(i, 0), currentObject.getString("name"),
                        dates[i], currentObject.getString("title"), currentObject.getString("horoscopeDate"),
                        currentObject.getString("shortText"), currentObject.getString("fullText"), currentObject.getString("date"));
                zodiacs.add(currentZodiac);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        images.recycle();
        return zodiacs;

    }


}