package com.star.zodiacapp;

import java.io.Serializable;

public class Zodiac implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer image;
    private final String title;
    private final String date;
    private final String info;
    private final String currentDate;
    private final String shortText;
    private final String fullText;
    private final String dateMore;

    public Zodiac(Integer image, String title, String date, String info, String currentDate, String shortText, String fullText, String dateMore) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.info = info;
        this.currentDate = currentDate;
        this.shortText = shortText;
        this.fullText = fullText;
        this.dateMore = dateMore;
    }

    public Integer getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getInfo() {
        return info;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getShortText() {
        return shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public String getDateMore() {
        return dateMore;
    }

    public String getAllInfo() {
        return "Name: " + title + "\n" + "Date: " + date + "\n" + "dateMore: " + dateMore + "\n" +"Info: " +
                info + "\n" + "Current date: " + currentDate + "\n" + "ShortText: " +
                shortText + "\n" + "FullText: " + fullText;
    }
}
