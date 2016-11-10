/**
 * Pedometer Project
 * Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Manages the stats screen for the pedometer app.
 *
 * The user is sees previous period data visually and adjust the granularity of how data is presented
 */

package edu.utdallas.pedometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class StatsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Set title to say "Statistics"
        String listViewTitle = this.getResources().getString(R.string.title_stat);
        this.setTitle(listViewTitle);

		// Create back button
        ActionBar actionBar = getSupportActionBar(); // not getActionBar, because using newer ActionBarActivity (version 7)
        actionBar.setDisplayHomeAsUpEnabled(true);

		// Create list view object and link to list in UI by its ID
        final android.widget.ListView listView;
        listView = (ListView)findViewById(R.id.list);

		// Create stat data for list view to display
        ArrayList<Stat> values = new ArrayList<Stat>();
        Stat totalSteps = new Stat("Total Steps", "6,472");
        Stat avgSteps = new Stat("Average Steps per hour", "500");
        Stat totalDistance = new Stat("Total Distance", "3 mi");
        Stat avgMPH = new Stat("Avg MPH", "2 mph");
        Stat time = new Stat("Time", "6 hr 42 m");
        Stat goalMet = new Stat("Goal Met", "No");

		// Add stat data to array list to inject to an adapter for the list view
        values.add(totalSteps);
        values.add(avgSteps);
        values.add(totalDistance);
        values.add(avgMPH);
        values.add(time);
        values.add(goalMet);

        // Define a new adapter for the stat data
        // first parameter - context
        // second parameter - layout for the row
        // third parameter - the array of stat data
        StatAdapter adapter = new StatAdapter(this, R.layout.list_item, values);

        // Assign stat adapter to list view
        listView.setAdapter(adapter);

        // ListView item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) 
			{

                // List view clicked item index
                int itemPosition = position;

                // List view clicked item value
                String  itemValue = (String) listView.getItemAtPosition(position);

                // Show alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue,
						Toast.LENGTH_LONG)
                        .show();

            }
        });
	}
	
	// Written By ScottFontenarosa
    // Handles what happens when the back button is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Determine what menu item was selected
        switch (item.getItemId())
        {
            // Back button was selected
            case android.R.id.home:
                // Go back to list view
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}





