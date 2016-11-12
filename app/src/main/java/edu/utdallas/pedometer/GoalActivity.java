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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class GoalActivity extends AppCompatActivity
{
    ListView listView;
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

        //Sets up the spinner for the units selection for distance goals
        Spinner spinner = (Spinner) findViewById(R.id.units_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.suggested_goal_list);

        // Defined Array values to show in ListView
        String[] values = new String[] { "10,000 Steps",
                "1 Mile",
                "5 K",
                "10 K",
                "24 Hours",
                "30 Minutes",
                "15,000 Steps",
                "1 Hour"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> suggestedGoalsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(suggestedGoalsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //Written by Melissa Dagley
            //Used to populate fields with a selected suggested goal
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                //Find all of the text fields
                TextView step_goal = (TextView)findViewById(R.id.steps_value);
                TextView time_goal = (TextView)findViewById(R.id.time_value);
                TextView distance_goal = (TextView)findViewById(R.id.distance);

                //Find all of the check boxes
                CheckBox step_goal_checkbox = (CheckBox)findViewById(R.id.step_checkbox);
                CheckBox time_goal_checkbox = (CheckBox)findViewById(R.id.time_checkbox);
                CheckBox distance_goal_checkbox = (CheckBox)findViewById(R.id.distance_checkbox);

                //Find the units spinner for distance
                Spinner units_spinner = (Spinner)findViewById(R.id.units_spinner);


                //reset all of the field values
                step_goal.setText("");
                time_goal.setText("");
                distance_goal.setText("");

                //reset all of the check boxes
                step_goal_checkbox.setChecked(false);
                time_goal_checkbox.setChecked(false);
                distance_goal_checkbox.setChecked(false);

                //reset the spinner
                units_spinner.setSelection(0);

                //check to see which goal was selected and populate the form accordingly
                if(itemPosition == 0)
                {
                    step_goal.setText("10000");
                    step_goal_checkbox.setChecked(true);
                }

                if(itemPosition == 1)
                {
                    distance_goal.setText("1");
                    units_spinner.setSelection(0);
                    distance_goal_checkbox.setChecked(true);
                }

                if(itemPosition == 2)
                {
                    distance_goal.setText("5");
                    units_spinner.setSelection(3);
                    distance_goal_checkbox.setChecked(true);
                }

                if(itemPosition == 3)
                {
                    distance_goal.setText("10");
                    units_spinner.setSelection(3);
                    distance_goal_checkbox.setChecked(true);
                }

                if(itemPosition == 4)
                {
                    time_goal.setText("24:00");
                    time_goal_checkbox.setChecked(true);
                }

                if(itemPosition == 5)
                {
                    time_goal.setText("00:30");
                    time_goal_checkbox.setChecked(true);
                }

                if(itemPosition == 6)
                {
                    step_goal.setText("15000");
                    step_goal_checkbox.setChecked(true);
                }

                if(itemPosition == 7)
                {
                    time_goal.setText("01:00");
                    time_goal_checkbox.setChecked(true);
                }

            }

        });
    }

    //Written by Melissa Dagley
    //Action for the cancel button, exits goal screen and returns to main screen
    public void cancelGoal(View mainView)
    {
        // Create an intent to tell the main view to open
        Intent intent = new Intent(this, MainActivity.class);

        // Bring up the stats view
        startActivity(intent);
    }

    //Written by Melissa Dagley
    //Action for set goal button, allows user to set different types of goals
    //Step goals, time goals, distance goals, or any combination
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
                stepGoal = Long.valueOf((String) step.getText().toString());

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

            //Check if the field is blank or in not in HH:MM format
            if(
                    time.getText().toString().equals("")
                    || !(time.getText().toString()).matches("([0-9]{2}):([0-9]{2})")) {

                //For Debugging
                System.out.println("Time goal field was blank or did not contain a valid entry \n");
            }

            //if the field is not blank
            else{

                //get the time input into the field
                String inputTime = time.getText().toString();

                //Split the string to find the hours and minutes entered
                String[] split = inputTime.split(":");
                long hours = Integer.valueOf(split[0]);
                long minutes = Integer.valueOf(split[1]);

                //convert the time entered to minutes and set it to the timeGoal variable
                timeGoal = (hours*60)+minutes;

                //for debugging purposes
                System.out.println("Time Goal: " + hours + " hour(s) " + minutes + " minute(s)\n");
                System.out.println("Time Goal: " + timeGoal + " minutes\n");
                System.out.println("Time Goal: " + timeGoal * 60 + " seconds\n");
                System.out.println("Time Goal: " + timeGoal * 60 * 1000 + " milliseconds\n");

                //convert time goal to milliseconds
                timeGoal = (timeGoal * 60) * 1000;

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

                Spinner spinner = (Spinner)findViewById(R.id.units_spinner);
                String text = spinner.getSelectedItem().toString();

                //set distance goal to field value
                distanceGoal = Double.parseDouble((String) distance.getText().toString());

                //for debugging
                System.out.println("Distance Goal: " + distanceGoal + " " + text+ "\n");

                if(text.equals("Meters"))
                {
                    //convert from meters to miles
                    distanceGoal = distanceGoal/1609.344;
                }

                if(text.equals("Yards"))
                {
                    //convert from yards to miles
                    distanceGoal = distanceGoal*0.00056818;
                }

                if(text.equals("KM"))
                {
                    //convert from kilometers to miles
                    distanceGoal = distanceGoal*0.62137;
                }

                if(text.equals("Feet"))
                {
                    //convert from feet to miles
                    distanceGoal = distanceGoal/5280;
                }

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
