package com.star.zodiacapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ZodiacFullActivity extends AppCompatActivity {

    private TextView info;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zodiac_full_activity);


        Zodiac zodiac = (Zodiac) getIntent().getSerializableExtra("zodiacCurrent");

        ImageView image = findViewById(R.id.imageZodiacMore);
        TextView name = findViewById(R.id.nameZodiacMore);
        TextView dateShort = findViewById(R.id.dateShortZodiacMore);
        TextView dateFull = findViewById(R.id.dateFullZodiacMore);
        info = findViewById(R.id.infoZodiacMore);

        button = findViewById(R.id.buttonZodiacMore);


        if (savedInstanceState != null) {
            if (!savedInstanceState.getString("textLength").equals("Подробнее")) {
                button.setText(getString(R.string.returnZodiacMore));
                info.setText(zodiac.getFullText());
            }
        } else info.setText(zodiac.getShortText());

        image.setImageResource(zodiac.getImage());
        name.setText(zodiac.getTitle());
        dateShort.setText(zodiac.getDateMore());
        dateFull.setText(zodiac.getCurrentDate());


        button.setOnClickListener(v -> {
            if (button.getText().toString().equals("Подробнее")) {
                info.setText(zodiac.getFullText());
                button.setText(getString(R.string.returnZodiacMore));
            } else {
                info.setText(zodiac.getShortText());
                button.setText(getString(R.string.get_more_info_full));
            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingButtonZodiacMore);
        floatingActionButton.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);

            String sendString;
            if (button.getText().toString().equals("Подробнее")) {
                sendString = zodiac.getTitle() + "\n\n" + zodiac.getCurrentDate() + "\n\n" + zodiac.getShortText() + "\n\nby https://github.com/kotiushka";
            } else {
                sendString = zodiac.getTitle() + "\n\n" + zodiac.getCurrentDate() + "\n\n" + zodiac.getFullText() + "\n\nby https://github.com/kotiushka";
            }

            sendIntent.putExtra(Intent.EXTRA_TEXT, sendString);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);

        });


    }

    public void returnButton(View view) {
        finish();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textLength", button.getText().toString());
    }
}