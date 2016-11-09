/**
 * Assignment 4 Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Contains all the details related to all stats
 */
package edu.utdallas.mxd153730.pedometer;


public class Stat
{
    // Data fields:
    private String statName;
    private String statValue;

    // Written By Melissa Dagley
    public Stat()
    {
        // empty constructor
    }

    // Written By Melissa Dagley and ScottFontenarosa
    // Constructor with template
    // Creates a new class based on provide name, phone number, and email
    public Stat(String name, String value)
    {
        this.statName = name;
        this.statValue = value;
    }

    // Written By Melissa Dagley and ScottFontenarosa
    // Setter/Getters
    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public String getStatValue() {return statValue;}

    public void setStatValue(String statValue) {
        this.statValue = statValue;
    }


    // Written By Melissa Dagley
    // Used to sort the list of contacts by first name


    // Written by Scott Fontenarosa
    // Returns string of all the data fields in the form:
    // first name <space> last name <new line>
    // phone number <new line>
    // email <new line>
    public String toString()
    {
        return statName + ": " + statValue + "\n";
    }

}
