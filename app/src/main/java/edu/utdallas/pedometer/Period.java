/**
 * Pedometer Project
 * Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Contains all the details related to one period such as
 * The number of steps taken
 * The distance traveled
 * The duration of a period
 * The start and end times of a period
 */

package edu.utdallas.pedometer;

import java.util.Date;

public class Period //implements Comparable<Period>
{
    // Data fields:
    private long steps;
    private double distance;
    private long duration; // (can be different than periodLength due to pausing)/ in milliseconds
    private Date startTime; //including date values
    private Date endTime;

    // Written By Melissa Dagley
    public Period()
    {
        // empty constructor
    }

    // Written By Melissa Dagley and ScottFontenarosa
    // Constructor with template
    // Creates a new class based on provide name, phone number, and email
    public Period(long s, double dist, long dur, Date start, Date end)
    {
        this.steps = s;
        this.distance = dist;
        this.duration = dur;
        this.startTime = start;
        this.endTime = end;
    }

    // Written By Melissa Dagley and Scott Fontenarosa
    // Setter/Getters
    public long getPeriodLength()
    {
        return endTime.getTime() - startTime.getTime(); // in milliseconds; (endTime - startTime)
    }

    public long getSteps()
    {
        return steps;
    }

    public void setSteps(long steps)
    {
        this.steps = steps;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public long getDuration()
    {
        return duration;
    }

    public void setDuration(long duration)
    {
        this.duration = duration;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    // Written By Melissa Dagley
    // Used to sort the list of periods by first name
    /*
    @Override
    public int compareTo(Period otherPeriod)
    {
        String otherPeriodFirstName = otherPeriod.getFirstName().toUpperCase();
        return this.getFirstName().toUpperCase().compareTo(otherPeriodFirstName); // for ascending order
    }
    */

    // Written by Scott Fontenarosa
    // Returns string of all the data fields in the form:
    // first name <space> last name <new line>
    // phone number <new line>
    // email <new line>
    public String toString()
    {
        return steps + "\n" +
                distance + "\n" +
                duration + "\n" +
                startTime.toString() + "\n" +
                endTime.toString();
    }

    // Written by Scott Fontenarosa
    // Tab delimited form used for saving to file
    public String toTabFormString()
    {
        return steps + "\t" +
                distance + "\t" +
                duration + "\t" +
                startTime.getTime() + "\t" +
                endTime.getTime() + "\n";
    }
}