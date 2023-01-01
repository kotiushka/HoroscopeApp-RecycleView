package com.star.zodiacapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button see_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        see_button = findViewById(R.id.button_see);
        see_button.setOnClickListener(view -> {
            see_button.setEnabled(false);
            AsyncTasker asyncTask = new AsyncTasker();
            String result = null;
            try {
                result = asyncTask.execute().get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            if (result != null) {
                Intent zodiacIntent = new Intent(getApplicationContext(), ZodiacActivity.class);
                zodiacIntent.putExtra("JSON_info", result);
                startActivity(zodiacIntent);
            } else {
                Toast.makeText(this, getString(R.string.not_internet_connect), Toast.LENGTH_LONG).show();
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        see_button.setEnabled(true);
    }
}