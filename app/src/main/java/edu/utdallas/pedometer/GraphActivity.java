/**
 * Pedometer Project
 * Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Manages the graph screen for the pedometer app.
 *
 * The user is sees previous period data visually and adjust the granularity of how data is presented
 */

package edu.utdallas.pedometer;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class GraphActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // Create back button
        ActionBar actionBar = getSupportActionBar(); // not getActionBar, because using newer ActionBarActivity (version 7)
        actionBar.setDisplayHomeAsUpEnabled(true);
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
