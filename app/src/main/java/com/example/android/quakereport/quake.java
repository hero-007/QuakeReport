package com.example.android.quakereport;

/**
 * Created by HP on 7/2/2017.
 */

public class quake {

    private double magnitude;
    private String cityName;
    private long Time;
    private String urlE;

    public quake(double A, String B, long C, String urls){
        magnitude = A;
        cityName = B;
        Time = C;
        urlE = urls;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public String getCityName(){
        return cityName;
    }

    public long getTime(){
        return Time;
    }

    public String getUrlE(){
        return urlE;
    }

}
