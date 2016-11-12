package edu.utdallas.pedometer;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ScottE on 11/8/2016.
 */

public class Goals
{
    // data fields:
    long goalSteps;
    long goalTime; // in milliseconds
    double goalDistance;  //in miles
    String distanceUnits = "Miles"; //gets the units to display distance goal in

    public Goals()
    {
        // empty
    }

    public Goals(long inputSteps, long inputTime, double inputDistance, String distanceUnits)
    {
        this.goalSteps = inputSteps;
        this.goalTime = inputTime;
        this.goalDistance = inputDistance;
        this.distanceUnits = distanceUnits;
    }

    public long getGoalSteps()
    {
        return goalSteps;
    }

    public void setGoalSteps(long goalSteps)
    {
        this.goalSteps = goalSteps;
    }

    public long getGoalTime()
    {
        return goalTime;
    }

    public void setGoalTime(long goalTime)
    {
        this.goalTime = goalTime;
    }

    public double getGoalDistance()
    {
        return goalDistance;
    }

    public void setGoalDistance(double goalDistance)
    {
        this.goalDistance = goalDistance;
    }

    public String getDistanceUnits() {return distanceUnits;}

    public void setDistanceUnits(String distanceUnits) {this.distanceUnits = distanceUnits;}

    // Written by Scott Fontenarosa
    // Returns string of all the data fields in the form:
    // goal steps <new line>
    // goal time <new line>
    // goal distance <new line>
    public String toString()
    {
        return goalSteps + "\n" +
                goalTime + "\n" +
                goalDistance + "\n" +
                distanceUnits;
    }

    // Written by Scott Fontenarosa
    // Tab delimited form used for saving to file
    public String toTabFormString()
    {
        return goalSteps + "\t" +
                goalTime + "\t" +
                goalDistance + "\t" +
                distanceUnits + "\n";
    }

    //Written by Melissa Dagley
    //Returns a formatted goal for display
    public String goalName()
    {
        String name="";
        double tempGoalDistance = 0;
        //Checks if there is a step goal
        if(goalSteps != 0)
        {
            //format the steps
            DecimalFormat stepformatter = new DecimalFormat("#,###");

            //add steps goal to the string
            name = (stepformatter.format(goalSteps)) + " Steps ";
        }

        //checks if there is a time goal
        if(goalTime != 0)
        {
            //get the hours
            long hours = (goalTime/(1000*60*60));

            //get the minutes
            long minutes = (goalTime/(1000*60)) % 60;

            //if at least 1 hour
            if(hours > 0)
            {
                if(minutes > 0) {
                    //print hours and minutes

                    if(hours == 1)
                        name += hours + " hour " + minutes + " mins ";
                    else
                    name += hours + " hours " + minutes + " mins ";
                }

                //just print hours if no minutes
                else {
                    if (hours == 1)
                        name += hours + " hour ";
                    else
                        name += hours + " hours ";
                }
            }

            else{

                //if less than 1 hour, print minutes
                name += minutes + " mins ";
            }
        }

        //check if there is a distance goal
        if(goalDistance != 0)
        {
            //format the distance
            DecimalFormat formatter = new DecimalFormat("#,###.##");

            //check the units that the goal was set in
            if(distanceUnits.equals("Miles")) {
                //add goal to string in miles

                if(goalDistance == 1)
                {name+= "1 Mile ";}
                else
                {name += (formatter.format(goalDistance)) + " Miles";}
            }

            //if the goal is in another unit, then convert it to the appropriate unit and print
            //the goal and it's unit
            else
            {
                if(distanceUnits.equals("Meters"))
                {
                    System.out.println("Units is in Meters");
                    //convert from miles to meters
                    tempGoalDistance = goalDistance*1609.344;

                    if(tempGoalDistance == 1)
                    {name+= "1 Meter ";}
                    else
                    {name += (formatter.format(tempGoalDistance)) + " Meters";}
                }
                if(distanceUnits.equals("Yards"))
                {
                    System.out.println("Units is in yards");
                    //convert from miles to yards
                    tempGoalDistance = goalDistance/0.00056818;

                    if(tempGoalDistance == 1)
                    {name+= "1 Yard ";}
                    else
                    {name += (formatter.format(tempGoalDistance)) + " Yards";}
                }

                if(distanceUnits.equals("KM"))
                {
                    System.out.println("units are in km");
                    //convert from miles to km
                    tempGoalDistance = goalDistance/0.62137;

                    if(tempGoalDistance == 1)
                    {name+= "1 K ";}
                    else
                    {name += (formatter.format(tempGoalDistance)) + " K";}
                }

                if(distanceUnits.equals("Feet"))
                {
                    //convert from miles to feet
                    tempGoalDistance = goalDistance*5280;

                    if(tempGoalDistance == 1)
                    {name+= "1 Foot ";}
                    else
                    {name += (formatter.format(tempGoalDistance)) + " Feet";}
                }


            }
        }


        return name;
    }
}
