package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HP on 7/2/2017.
 */

public class quakeAdapter extends ArrayAdapter<quake> {


    public quakeAdapter(Activity ert, ArrayList<quake> quakes){
        super(ert,0,quakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View quakeItemView = convertView;
        if(quakeItemView == null){
            quakeItemView = LayoutInflater.from(getContext()).inflate(R.layout.quake_item,parent,false);
        }

        quake Quake = getItem(position);

        double mMag = Quake.getMagnitude();
        TextView Magnitude = (TextView)quakeItemView.findViewById(R.id.quake_item_mag);
        Magnitude.setText(""+mMag);

        String mPlace = Quake.getCityName();

        int in = mPlace.indexOf('f');
        String subName = getSubString(mPlace,0,++in);
        TextView subPlaceName = (TextView)quakeItemView.findViewById(R.id.quake_item_sub);
        subPlaceName.setText(subName);

        mPlace += " ";
        int lengthOfString = mPlace.length();
        String Stk = mPlace.substring(in,lengthOfString);
        TextView placeName = (TextView)quakeItemView.findViewById(R.id.quake_item_name);
        placeName.setText(Stk);

        Date date =new Date(Quake.getTime());

        String mDate = dateFormat(date);
        TextView Date = (TextView)quakeItemView.findViewById(R.id.quake_item_date);
        Date.setText(mDate);

        String mTime = timeFormat(date);
        TextView Time = (TextView)quakeItemView.findViewById(R.id.quake_item_time);
        Time.setText(mTime);

     return  quakeItemView;
    }

    private String dateFormat(Date dt){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormatter.format(dt);
    }

    private String timeFormat(Date dt){
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        return timeFormatter.format(dt);
    }

    private String getSubString(String str,int first,int last){
        return str.substring(first,last);
    }
}
