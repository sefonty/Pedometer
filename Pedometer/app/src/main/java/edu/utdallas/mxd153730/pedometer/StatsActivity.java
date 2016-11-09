package edu.utdallas.mxd153730.pedometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        String listViewTitle = "Stats";
        this.setTitle(listViewTitle);


        final android.widget.ListView listView;
        listView=(ListView)findViewById(R.id.list);

        ArrayList<Stat> values = new ArrayList<Stat>();
        Stat totalSteps = new Stat("Total Steps", "6,472");
        Stat avgSteps = new Stat("Average Steps per hour", "500");
        Stat totalDistance = new Stat("Total Distance", "3 mi");
        Stat avgMPH = new Stat("Avg MPH", "2 mph");
        Stat time = new Stat("Time", "6 hr 42 m");
        Stat goalMet = new Stat("Goal Met", "No");

        values.add(totalSteps);
        values.add(avgSteps);
        values.add(totalDistance);
        values.add(avgMPH);
        values.add(time);
        values.add(goalMet);


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        StatAdapter adapter = new StatAdapter(this,
                R.layout.list_item, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });
    }
    }





