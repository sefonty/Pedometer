/**
 * Final Project Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Used to inject the list view with saved goals
 */
package edu.utdallas.pedometer;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class GoalAdapter extends ArrayAdapter<Goals>
{

    // Declaring our ArrayList of goals
    private ArrayList<Goals> goals;

    /**
     * Written By Melissa Dagley
     *
     * Here we must override the constructor for ArrayAdapter
     * the only variable we care about now is ArrayList<Goals> objects,
     * because it is the list of objects we want to display.
     */
    public GoalAdapter(Context context, int textViewResourceId, ArrayList<Goals> goals)
    {
        super(context, textViewResourceId, goals);
        this.goals = goals;
    }

    /**
     * Written By Melissa Dagley
     *
     * We are overriding the getView method here - this is what defines how each
     * list contact will look.
     */
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Assign the view we are converting to a local variable
        View localView = convertView;

        // First check to see if the view is null. If so, we have to inflate it.
        // To inflate it basically means to render, or show, the view.
        if (localView == null)
        {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(R.layout.saved_goal_item, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list.
		 * (The ArrayAdapter iterates through the list we sent it)
		 *
		 * Therefore, currentGoalPosition refers to the current Goal object's index in the list
		 */
        Goals currentGoalPosition = goals.get(position);

        if (currentGoalPosition != null)
        {
            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.
            TextView goalName = (TextView)localView.findViewById(R.id.goalName);


            // Check to see if each individual TextView is null.
            // If not, assign some text.
            if (goalName != null)
            {
                // Set Name
                String name = currentGoalPosition.goalName();
                goalName.setText(name);

                System.out.println(currentGoalPosition.toString());
            }


        }

        // The view must be returned to our list activity
        return localView;
    }
}