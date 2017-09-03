package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ListView earthquakeListView;
    ArrayList<quake> earthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.earthquake_activity);
       EarthquakeAsyncTask earthquakeAsyncTask = new EarthquakeAsyncTask();
        earthquakeAsyncTask.execute();


    }

    private class EarthquakeAsyncTask extends AsyncTask<String , Void, ArrayList<quake>>{
        @Override
        protected ArrayList<quake> doInBackground(String...Prams){
            String returnedString = QueryUtils.fetchEarthQuake("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10");
            earthquakes = QueryUtils.extractEarthquakes(returnedString);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(final ArrayList<quake> earthquakes) {
            earthquakeListView = (ListView)findViewById(R.id.list);

            quakeAdapter adapter = new quakeAdapter(EarthquakeActivity.this ,earthquakes);

            earthquakeListView.setAdapter(adapter);
            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    quake quk = earthquakes.get(position);
                    String gotoURL = quk.getUrlE();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(gotoURL));
                    startActivity(i);
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
