/**
 * Pedometer Project
 * Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Manages the main screen for the pedometer app.
 * The user is able to enter the graph view by clicking the "MORE" button on the bottom of the screen
 * The user is able to enter the goal options view by clicking the "Goal options" action in the action bar
 */

package edu.utdallas.pedometer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
 */

public class MainActivity extends AppCompatActivity
{
    public static PeriodsHandler periodsHandler = new PeriodsHandler();
    Date endTime = new Date();
    Date curTime = new Date();
    Date startTime = new Date();
    Date curStartTime = new Date();
    double distance = 0;
    long duration = 0;
    long currentSteps = 0;
    int inputProgress = (int) ((6472.0f / 10000.0f) * 100);

    // Written By Melissa Dagley
    // Called when the main activity is created or every time it is navigated to
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        // Set layout to be activity_main.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference to progress bar in XML
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(inputProgress);


        // Set title to say "Pedometer"
        String listViewTitle = this.getResources().getString(R.string.main_label);
        this.setTitle(listViewTitle);

        // Send application context to FileHandler helper class if needed
        // Creates file path once and initiates load from file
        if (periodsHandler.getNeedContext())
        {
            periodsHandler.setFileHandlerContext(getApplicationContext());
        }

        // Is there data being sent to this list view activity?
        if (getIntent()!= null)
        {

            TextView goal = (TextView)findViewById(R.id.goalSteps);
            View view = (View)findViewById(android.R.id.content);
            resetGoal(view);


            // If so, retrieve the data
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();

            if (bundle != null)
            {
                // Retrieve action performed in details view (modify/delete/new)
                boolean stepFlag = bundle.getBoolean("Step Flag");
                boolean timeFlag = bundle.getBoolean("Time Flag");
                boolean distanceFlag = bundle.getBoolean("Distance Flag");

                // New period action was performed
                if (stepFlag)
                {
                    Long stepGoal = bundle.getLong("Steps");
                    int inputProgress = (int)((0.0f/stepGoal)*100);
                    progressBar.setProgress(inputProgress);
                    goal.setText(stepGoal.toString());


                }
                // Modify period action was performed
                if (timeFlag)
                {
                    if(!stepFlag)
                    {
                        Long timeGoal = bundle.getLong("Time");
                        int inputProgress = (int)((0.0f/timeGoal)*100);
                        progressBar.setProgress(inputProgress);

                        //get the hours
                        long hours = (timeGoal/(1000*60*60));

                        //get the minutes
                        long minutes = (timeGoal/(1000*60)) % 60;

                        //if at least 1 hour
                        if(hours > 0)
                        {
                            if(minutes > 0) {
                                //print hours and minutes

                                if(hours == 1)
                                    goal.setText(hours + " hour " + minutes + " mins");
                                else
                                   goal.setText(hours + " hours " + minutes + " mins");
                            }

                            //just print hours if no minutes
                            else {
                                if (hours == 1)
                                   goal.setText(hours + " hour");
                                else
                                    goal.setText(hours + " hours");
                            }
                        }

                        else{

                            //if less than 1 hour, print minutes
                            goal.setText(minutes + " mins ");
                        }

                    }
                }
                // Delete period action was performed
                if (distanceFlag)
                {
                    if(!stepFlag && !timeFlag)
                    {
                        Double distanceGoal = bundle.getDouble("Distance");
                        String units = bundle.getString("Units");
                        Goals tempGoal = new Goals(0, 0, distanceGoal, units);
                        int inputProgress = (int)((0.0f/distanceGoal)*100);
                        progressBar.setProgress(inputProgress);
                        String tempString = tempGoal.goalName();
                        goal.setText(tempString);
                    }
                }
            }
        }

        // Used for Debugging
        //System.out.println(periods);

        // Create PeriodAdapter object to inject the list view UI element with list of period objects
        // Essentially refreshes the list based on the data in the list of period objects
        //PeriodAdapter periodAdapter = new PeriodAdapter(this, R.layout.list_item, periodsHandler.getPeriodList());
        //final android.widget.ListView listView;

        // Get ListView object from XML
        //listView = (android.widget.ListView) findViewById(R.id.list);

        // Assign adapter to ListView
        //listView.setAdapter(periodAdapter);

        // Display "No Periods Message" if list is empty
        /*
        if (periodsHandler.getPeriodList().size() == 0)
        {
            // Programmatically create a TextView and set its size and message
            TextView textView = new TextView(this);
            textView.setTextSize(20);
            String noPeriods = this.getResources().getString(R.string.no_periods_to_display);
            textView.setText(noPeriods);

            // Center TextView
            textView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER);

            // add TextView to the layout identified by R.id.activity_display_message
            // cast layout to ViewGroup because its the superclass of all layouts and contains the addView method
            ViewGroup layout = (ViewGroup) findViewById(R.id.activity_list_view);
            layout.addView(textView);
        }
        */

        // ListView Item Click Listener
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // Written By Melissa Dagley
            // Handles responding to a list view item being clicked
            @Override
            public void onItemClick(AdapterView<?>parent, View view,
                                    int position, long id)
            {
                // Create intent to send to details view
                Intent appInfo = new Intent(ListView.this, DetailsView.class);
                Bundle bundle = new Bundle();

                // Prepare the period information to be displayed in the fields of the details view
                String fname = periodsHandler.getPeriod(position).getFirstName();
                String lname = periodsHandler.getPeriod(position).getLastName();
                String phone = periodsHandler.getPeriod(position).getPhoneNumber();
                String email = periodsHandler.getPeriod(position).getEmail();

                // Let the details view know which period we are view by its index
                int itemPos = position;

                // Put the period information into the bundle to be sent
                bundle.putString("First Name", fname);
                bundle.putString("Last Name", lname);
                bundle.putString("Phone", phone);
                bundle.putString("Email", email);
                bundle.putString("Mode", "modify");
                bundle.putInt("Position", itemPos);

                // Send the period information to the detail view, then bring up the details view
                appInfo.putExtras(bundle);
                startActivity(appInfo);

                // Reset the ability to create objects in Listview
                // (Relates to preventing orientation duplicate periods bug)
                alreadyUpdated = false;
            }
        });
        */
    }

    // Written By Melissa Dagley
    // Called when the user clicks on the add button
    /*
    public void sendMessage(View detailsView)
    {
        // Create an intent to tell the details view to enter new period mode
        Intent intent = new Intent(this, GraphActivity.class);
        Bundle vars = new Bundle();
        vars.putString("Mode", "new");
        intent.putExtras(vars);

        // Bring up the details view in new period mode
        startActivity(intent);

        // Reset the ability to create objects in Listview
        // (Relates to preventing orientation duplicate periods bug)
        //alreadyUpdated = false;
    }
    */

    // Written By Scott Fontenarosa
    // Link the action buttons defined in actions.xml to the list view activity action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Reveal the action buttons such as the "Add period" button
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
        return true;
    }

    // Written By Scott Fontenarosa
    // Respond to action bar buttons in the list view activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_add:
                // User chose the "Add period" item, show the details view UI...

                // Create an intent to tell the details view to enter new period mode
                Intent intent = new Intent(this, GoalActivity.class);
                Bundle vars = new Bundle();
                vars.putString("Mode", "new");
                intent.putExtras(vars);

                // Bring up the details view in new period mode
                startActivity(intent);

                // Reset the ability to create objects in Listview
                // (Relates to preventing orientation duplicate periods bug)
                //alreadyUpdated = false;

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void openStats(View statsView)
    {
        // Create an intent to tell the stats view to open
        Intent intent = new Intent(this, StatsActivity.class);

        // Bring up the stats view
        startActivity(intent);
    }

    public void newGoal(View goalView)
    {
        // Create an intent to tell the stats view to open
        Intent intent = new Intent(this, GoalActivity.class);

        // Bring up the stats view
        startActivity(intent);
    }

    //Written by Melissa Dagley
    public void resetGoal(View view) {

        //Find the progress bar
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        int inputProgress;

        //Find all of the text views
        TextView textViewSteps = (TextView)findViewById(R.id.textView_steps);
        TextView textViewcurrentSteps = (TextView)findViewById(R.id.currentSteps);
        TextView textViewStepsGoal = (TextView)findViewById(R.id.goalSteps);
        TextView textViewTimeStarted = (TextView)findViewById(R.id.textView_timeStarted);

        //Set a string to the current date/time to display at the top of the screen
        String timeStarted = new SimpleDateFormat("hh:mm a MM/dd/yyyy").format(new Date());

        //reset the current time variable
        curTime = new Date();

        //set the end time to the current time
        endTime = curTime;

        //subtract the last known start time from the end time and add it to the duration variable
        //curStartTime is set every time a goal starts or is restarted after a pause
        duration += endTime.getTime() - curStartTime.getTime();

        //Create a period object to hold the data for the current time period
        Period newPeriod = new Period(currentSteps, distance, duration, startTime, endTime);

        //Reset all of the text views, except the goal steps which stays the same
        textViewSteps.setText("0");
        textViewcurrentSteps.setText("0");

        //Display the new start time
        textViewTimeStarted.setText("Time Started: " + timeStarted);

        //reset the progress bar
        inputProgress = (int) ((0.0f / 10000.0f) * 100);
        progressBar.setProgress(inputProgress);

        //reset the variables
        currentSteps = 0;
        startTime = curTime;
    }
}
