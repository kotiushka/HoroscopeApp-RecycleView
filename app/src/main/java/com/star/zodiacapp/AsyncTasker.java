package com.star.zodiacapp;

@SuppressWarnings("deprecation")
public class AsyncTasker extends android.os.AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids){
        return NetworkTool.getRequest("https://orakul.com/pwa/?command=gethoroscope&horoscope=astrologic&time=today&type=general");
    }

}
