/**
 * Assignment 4 Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Used to inject the list view with existing contacts' first and last name and phone number
 */
package edu.utdallas.mxd153730.pedometer;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class StatAdapter extends ArrayAdapter<Stat>
{

    // Declaring our ArrayList of stats
    private ArrayList<Stat> stats;

    /**
     * Written By Melissa Dagley
     *
     * Here we must override the constructor for ArrayAdapter
     * the only variable we care about now is ArrayList<Stat> objects,
     * because it is the list of objects we want to display.
     */
    public StatAdapter(Context context, int textViewResourceId, ArrayList<Stat> stats)
    {
        super(context, textViewResourceId, stats);
        this.stats = stats;
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
            localView = inflater.inflate(R.layout.list_item, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list.
		 * (The ArrayAdapter iterates through the list we sent it)
		 *
		 * Therefore, currentContactPosition refers to the current Contact object's index in the list
		 */
        Stat currentStatPosition = stats.get(position);

        if (currentStatPosition != null)
        {
            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.
            TextView statName = (TextView)localView.findViewById(R.id.statName);
            TextView statValue = (TextView)localView.findViewById(R.id.statValue);

            // Check to see if each individual TextView is null.
            // If not, assign some text.
            if (statName != null)
            {
                // Set Name
                String name = currentStatPosition.getStatName();



                statName.setText(name);
            }

            if (statValue != null)
            {
                // Set Phone Number
                String value = currentStatPosition.getStatValue();



                statValue.setText(value);
            }
        }

        // The view must be returned to our list activity
        return localView;
    }
}