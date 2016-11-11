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
import android.widget.CheckBox;
import android.widget.TextView;

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

    //Written by Melissa Dagley
    public void cancelGoal(View mainView)
    {
        // Create an intent to tell the main view to open
        Intent intent = new Intent(this, MainActivity.class);

        // Bring up the stats view
        startActivity(intent);
    }

    //Written by Melissa Dagley
    public void setGoal(View view)
    {
        long stepGoal = 0;
        long timeGoal = 0;
        double distanceGoal = 0.0;

        //Find all of the text fields
        TextView step = (TextView)findViewById(R.id.steps_value);
        TextView time = (TextView)findViewById(R.id.time_value);
        TextView distance = (TextView)findViewById(R.id.distance);

        CheckBox setStepGoal = (CheckBox)findViewById(R.id.step_checkbox);
        CheckBox setTimeGoal = (CheckBox)findViewById(R.id.time_checkbox);
        CheckBox setDistanceGoal = (CheckBox)findViewById(R.id.distance_checkbox);

        if(setStepGoal.isChecked()) {

            stepGoal = Long.valueOf((String)step.getText().toString()).longValue();
            System.out.println("Step Goal: " + stepGoal + "\n");


        }

        else{

            System.out.println("No Step Goal \n");
        }

        if(setTimeGoal.isChecked()) {
            timeGoal = Long.valueOf((String)time.getText().toString()).longValue();
            System.out.println("Time Goal: " + timeGoal + " hours\n");
            System.out.println("Time Goal: " + timeGoal*60 + " minutes\n");
            System.out.println("Time Goal: " + timeGoal*60*60 + " seconds\n");
            System.out.println("Time Goal: " + timeGoal*60*60*1000 + " milliseconds\n");
            timeGoal = ((timeGoal*60)*60)*1000;
        }

        else {

            System.out.println("No Time Goal \n");
        }

        if(setDistanceGoal.isChecked()) {

            distanceGoal = Double.parseDouble((String)distance.getText().toString());
            System.out.println("Distance Goal: " + distanceGoal + " miles\n");
        }

        else {

            System.out.println("No Distance Goal \n");
        }


        Goals newGoal = new Goals(stepGoal, timeGoal, distanceGoal);
        System.out.println(newGoal.toString());


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
