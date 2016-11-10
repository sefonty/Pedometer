/**
 * Pedometer Project
 * Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Manages the goal screen for the pedometer app.
 *
 * The user is able set personal goals that will appear on the main view
 * and tracked by labels and a visual progress bar.
 */

package edu.utdallas.pedometer;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class GoalActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        // Set title to say "Statistics"
        String listViewTitle = this.getResources().getString(R.string.title_goals);
        this.setTitle(listViewTitle);

        // Create back button
        ActionBar actionBar = getSupportActionBar(); // not getActionBar, because using newer ActionBarActivity (version 7)
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void cancelGoal(View mainView)
    {
        // Create an intent to tell the main view to open
        Intent intent = new Intent(this, MainActivity.class);

        // Bring up the stats view
        startActivity(intent);
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
