package com.example.alzawadimidt1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q=athens&appid=96bf76f06d33bd08a65dfc1b092065fb&units=metric";
    ImageView weatherBackground;
    TextView temperature, humidity, city;

    static String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CalendarView cal = (CalendarView) findViewById(R.id.calendarView);
        TextView date = (TextView) findViewById(R.id.date);
        temperature = (TextView) findViewById(R.id.temperature);
        humidity = (TextView) findViewById(R.id.humidity);
        city = (TextView) findViewById(R.id.city);
        weatherBackground = (ImageView) findViewById(R.id.weatherbackground);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                s = "Date: ";
                s += "" + i;
                s += "/" + (i1+1) ;
                s += "/" + i2;
                date.setText(s);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            date.setText(bundle.get("Date").toString());

        Button main2 = (Button)findViewById(R.id.main2);
        Button main3 = (Button)findViewById(R.id.main3);

        main2.setOnClickListener(e-> {
            Intent intent = new Intent(MainActivity.this, Insert.class);
            intent.putExtra("Date", s);
            startActivity(intent);
        });

        main3.setOnClickListener(e-> {
            Intent intent = new Intent(MainActivity.this, Fetch.class);
            intent.putExtra("Date", s);
            startActivity(intent);
        });
        weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q="+"jeddah"+"&appid=96bf76f06d33bd08a65dfc1b092065fb&units=metric";
        weather(weatherWebserviceURL);

    }

    public static String getS(){
        return s;
    }
    public void weather(String url){
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Owais",response.toString());
                try{
                    JSONObject jsonMain = response.getJSONObject("main");

                    String town = response.getString("name");
                    city.setText(town);

                    double temp = jsonMain.getDouble("temp");
                    temperature.setText(temp+"°C");

                    humidity.setText("Humidity: " + jsonMain.getDouble("humidity") + "%");

                    String wStatus = response.getJSONArray("weather").getJSONObject(0).getString("main");
                    weatherPic(wStatus);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Owais","JSON Error: " + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Owais","Error in URL: " + error);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }

    public void weatherPic(String weatherCondition) {
        if (weatherCondition.equals("Clear"))
            weatherBackground.setImageResource(R.drawable.sunny);
        if (weatherCondition.equals("Clouds"))
            weatherBackground.setImageResource(R.drawable.clouds);
        if (weatherCondition.equals("Rain"))
            weatherBackground.setImageResource(R.drawable.rain);
        if (weatherCondition.equals("Snow"))
            weatherBackground.setImageResource(R.drawable.snowy);
        if (weatherCondition.equals("Fog"))
            weatherBackground.setImageResource(R.drawable.fog);
        if (weatherCondition.equals("Thunderstorm"))
            weatherBackground.setImageResource(R.drawable.storm);
    }
}