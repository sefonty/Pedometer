package edu.utdallas.pedometer;

/**
 * Created by ScottE on 11/8/2016.
 */

public class Goals
{
    // data fields:
    long goalSteps;
    long goalTime; // in milliseconds
    double goalDistance;

    public Goals()
    {
        // empty
    }

    public Goals(long inputSteps, long inputTime, double inputDistance)
    {
        this.goalSteps = inputSteps;
        this.goalTime = inputTime;
        this.goalDistance = inputDistance;
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

    // Written by Scott Fontenarosa
    // Returns string of all the data fields in the form:
    // goal steps <new line>
    // goal time <new line>
    // goal distance <new line>
    public String toString()
    {
        return goalSteps + "\n" +
                goalTime + "\n" +
                goalDistance;
    }

    // Written by Scott Fontenarosa
    // Tab delimited form used for saving to file
    public String toTabFormString()
    {
        return goalSteps + "\t" +
                goalTime + "\t" +
                goalDistance + "\n";
    }
}
