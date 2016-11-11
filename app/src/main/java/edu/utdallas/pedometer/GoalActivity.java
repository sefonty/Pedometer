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
        String listViewTitle = ("New Goal");
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
        //initialize all values to 0
        long stepGoal = 0;
        long timeGoal = 0;
        double distanceGoal = 0.0;

        //Find all of the text fields
        TextView step = (TextView)findViewById(R.id.steps_value);
        TextView time = (TextView)findViewById(R.id.time_value);
        TextView distance = (TextView)findViewById(R.id.distance);

        //Find all of the check boxes
        CheckBox setStepGoal = (CheckBox)findViewById(R.id.step_checkbox);
        CheckBox setTimeGoal = (CheckBox)findViewById(R.id.time_checkbox);
        CheckBox setDistanceGoal = (CheckBox)findViewById(R.id.distance_checkbox);

        //Run if the step goal check box is checked
        if(setStepGoal.isChecked()) {

            //Check if the field is blank
            if(step.getText().toString().equals("")) {

                System.out.println("Step goal field was blank\n");
            }

            //If the field is not blank
            else{
                //set the step goal value equal to the field value
                stepGoal = Long.valueOf((String) step.getText().toString()).longValue();

                //for debugging
                System.out.println("Step Goal: " + stepGoal + "\n");
            }

        }

        else{

            //for debugging
            System.out.println("No Step Goal \n");
        }

        //Run if time goal check box is checked
        if(setTimeGoal.isChecked()) {

            //Check if the field is blank
            if(time.getText().toString().equals("")) {

                System.out.println("Time goal field was blank\n");
            }

            //if the field is not blank
            else{

                //Set time goal equal to field value
                timeGoal = Long.valueOf((String) time.getText().toString()).longValue();

                //for debugging purposes
                System.out.println("Time Goal: " + timeGoal + " hours\n");
                System.out.println("Time Goal: " + timeGoal * 60 + " minutes\n");
                System.out.println("Time Goal: " + timeGoal * 60 * 60 + " seconds\n");
                System.out.println("Time Goal: " + timeGoal * 60 * 60 * 1000 + " milliseconds\n");

                //convert time goal to milliseconds
                timeGoal = ((timeGoal * 60) * 60) * 1000;

            }
        }

        else {

            //for debugging
            System.out.println("No Time Goal \n");
        }

        //Run if distance goal check box is checked
        if(setDistanceGoal.isChecked()) {

            //Check if the field is blank
            if(distance.getText().toString().equals("")) {

                System.out.println("Distance goal field was blank\n");

            }

            //If the field is not blank
            else{

                //set distance goal to field value
                distanceGoal = Double.parseDouble((String) distance.getText().toString());

                //for debugging
                System.out.println("Distance Goal: " + distanceGoal + " miles\n");

            }
        }

        else {

            //for debugging
            System.out.println("No Distance Goal \n");
        }

        //Create a new goal object with the values from the fields, use 0 values for fields not checked
        Goals newGoal = new Goals(stepGoal, timeGoal, distanceGoal);

        //for debugging
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
