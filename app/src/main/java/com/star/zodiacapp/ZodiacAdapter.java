package com.star.zodiacapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class ZodiacAdapter extends RecyclerView.Adapter<ZodiacAdapter.ZodiacHolder> {

    private final LayoutInflater mInflater;
    private final ArrayList<Zodiac> mZodiacs;

    public ZodiacAdapter(Context context, ArrayList<Zodiac> zodiacs) {
        this.mInflater = LayoutInflater.from(context);
        this.mZodiacs = zodiacs;
    }

    @NonNull
    @Override
    public ZodiacAdapter.ZodiacHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ZodiacHolder(mInflater.inflate(R.layout.zodiac_item_holder,
                parent, false), mZodiacs, mInflater.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ZodiacAdapter.ZodiacHolder holder, int position) {
        Zodiac currentZodiac = mZodiacs.get(position);
        // set image
        Glide.with(mInflater.getContext()).load(
                currentZodiac.getImage()).into(holder.pictureImage);
        // set title
        holder.titleText.setText(currentZodiac.getTitle());
        // set date
        holder.dateText.setText(currentZodiac.getDate());
        // set info
        holder.infoText.setText(currentZodiac.getInfo());
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public static class ZodiacHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView titleText;
        private final TextView dateText;
        private final TextView infoText;
        private final ImageView pictureImage;
        private final ArrayList<Zodiac> arrayList;
        private final Context context;

        public ZodiacHolder(@NonNull View itemView, ArrayList<Zodiac> zodiacArrayList, Context contextCurrent) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleText = itemView.findViewById(R.id.item_title_zodiac);
            dateText = itemView.findViewById(R.id.item_date_zodiac);
            infoText = itemView.findViewById(R.id.item_info_zodiac);
            pictureImage = itemView.findViewById(R.id.item_image_zodiac);
            arrayList = zodiacArrayList;
            context = contextCurrent;
        }

        @Override
        public void onClick(View view) {
            Zodiac zodiac = arrayList.get(getItemNumber(titleText.getText().toString()));
            Context aplContext = context.getApplicationContext();

            Intent intentFullZodiacActivity = new Intent(aplContext, ZodiacFullActivity.class);
            intentFullZodiacActivity.putExtra("zodiacCurrent", zodiac);
            context.startActivity(intentFullZodiacActivity);



        }

        private int getItemNumber(String zodiac) {
            switch (zodiac) {
                case "Овен":
                    return 0;
                case "Телец":
                    return 1;
                case "Близнецы":
                    return 2;
                case "Рак":
                    return 3;
                case "Лев":
                    return 4;
                case "Дева":
                    return 5;
                case "Весы":
                    return 6;
                case "Скорпион":
                    return 7;
                case "Стрелец":
                    return 8;
                case "Козерог":
                    return 9;
                case "Водолей":
                    return 10;
                case "Рыбы":
                    return 11;
                default:
                    return 777;
            }
        }

    }
}
