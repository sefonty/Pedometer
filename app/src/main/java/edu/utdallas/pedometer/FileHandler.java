/**
 * Pedometer Project
 * Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Handles file saving and file loading of the ArrayList<Period> structure into a text file
 * Also handles file saving and file loading of Goals object into a text file
 */

package edu.utdallas.pedometer;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Date;

public class FileHandler
{
    private String periodsFileName = "periods.txt"; // name of file to save data to
    private String goalsFileName = "goals.txt";
    private String periodsFilePath;
    private String goalsFilePath;

    // Written By Scott Fontenarosa
    // Constructor
    public FileHandler()
    {
        System.out.println("File handler created.");
    }

    // Written By Scott Fontenarosa
    // Sets the context of the app to help create the correct file path to where periods are saved
    public void setContext(Context inputContext)
    {
        String baseFilePath = inputContext.getFilesDir() + "/"; // get internal storage file structure
        periodsFilePath = baseFilePath + periodsFileName; // set path for periods data
        goalsFilePath = baseFilePath + goalsFileName; // set path for goals data
        System.out.println("Name of periods file: " + periodsFileName);
        System.out.println("File handler periods path ready: " + periodsFilePath);
        System.out.println("Name of goals file: " + goalsFileName);
        System.out.println("File handler goals path ready: " + goalsFilePath);
    }

    // Written By Scott Fontenarosa
    // Return periods file name
    public String getPeriodsFileName()
    {
        return periodsFileName;
    }

    // Written By Scott Fontenarosa
    // Return goals file name
    public String getGoalsFileName()
    {
        return goalsFileName;
    }

    // Written By Scott Fontenarosa
    // Returns the full periods path name
    public String getPeriodsFilePath()
    {
        return periodsFilePath;
    }

    // Written By Scott Fontenarosa
    // Returns the full goals path name
    public String getGoalsFilePath()
    {
        return goalsFilePath;
    }

    // Written By Scott Fontenarosa
    // Saves the list of periods from a provided list
    public void savePeriods(ArrayList<Period> periods)
    {
        String stringToSave = "";

        // produce tab delimited string to save to file
        for (int eachPeriod = 0; eachPeriod < periods.size(); eachPeriod++)
        {
            stringToSave += periods.get(eachPeriod).toTabFormString();
        }

        // save string to file (Java approach)
        // NOTE: overwrites file if it already exists
        PrintWriter out;
        try
        {
            out = new PrintWriter(periodsFilePath);
            out.print(stringToSave);
            out.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    // Written By Scott Fontenarosa
    // Loads the list of periods and returns the data in the form of an ArrayList<Period> structure
    public ArrayList<Period> loadPeriods()
    {
        // Array list of period objects to return
        ArrayList<Period> loadedPeriodList = new ArrayList<>();

        // Extract the data lines from the file into a list string structure
        ArrayList<String> contents = new ArrayList<>();

        // Read from the file line by line using InputStream, InputStreamReader, BufferedReader
        String line;

        try
        {
            InputStream inputStream = new FileInputStream(periodsFilePath);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            line = bufferedReader.readLine(); // try to read the first line if any

            while (line != null)
            {
                // Add each line to the contents list string list
                contents.add(line);

                // Read next line
                line = bufferedReader.readLine();
            }

            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        }
        catch (Exception e)
        {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }

        // decode the layout of the data into the form of Period objects and add to list
        // note each line is tab delimited and each line is one period
        for (int wLine = 0; wLine < contents.size(); wLine++) // wLine, short for "which line?"
        {
            // split each line by the tab delimiter
            String eachLine = contents.get(wLine);
            String[] eachLineDelimited = eachLine.split("\t");

            // Order data is saved int text file: 1) first 2) last  3) phone  4) email
            long steps;
            double distance;
            long duration;
            Date startTime;
            Date endTime;

            try
            {
                steps = Long.parseLong(eachLineDelimited[0]);
                distance =  Double.parseDouble(eachLineDelimited[1]);
                duration = Long.parseLong(eachLineDelimited[2]);
                startTime = new Date(Long.parseLong(eachLineDelimited[3]));
                endTime = new Date(Long.parseLong(eachLineDelimited[4]));
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                // Array ended up being shorter than expected
                System.out.println("ERROR: (PERIODS) Probably tried to read an empty string i.e. \"\"");

                // empty period object set as placeholder, runtime error
                steps = 0;
                distance = 0.0;
                duration = 0;
                startTime = new Date();
                endTime = new Date();
            }

            // create period object to hold each period
            Period eachPeriod = new Period(steps, distance, duration, startTime, endTime);

            // add period object to list being returned
            loadedPeriodList.add(eachPeriod);
        }

        return loadedPeriodList;
    }

    // Written By Scott Fontenarosa
    // Saves the goals from a provided goals object
    public void saveGoals(Goals goals)
    {
        String stringToSave;

        // produce tab delimited string to save to file
        stringToSave = goals.toTabFormString();

        // save string to file (Java approach)
        // NOTE: overwrites file if it already exists
        PrintWriter out;
        try
        {
            out = new PrintWriter(goalsFilePath);
            out.print(stringToSave);
            out.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    // Written By Scott Fontenarosa
    // Loads the data for goals and returns the data in the form of a Goals object
    public Goals loadGoals()
    {
        // Array list of period objects to return
        Goals loadedGoals;

        // Extract the data lines from the file into a list string structure
        ArrayList<String> contents = new ArrayList<>();

        // Read from the file line by line using InputStream, InputStreamReader, BufferedReader
        String line;

        try
        {
            InputStream inputStream = new FileInputStream(periodsFilePath);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            line = bufferedReader.readLine(); // try to read the first line if any

            while (line != null)
            {
                // Add each line to the contents list string list
                contents.add(line);

                // Read next line
                line = bufferedReader.readLine();
            }

            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        }
        catch (Exception e)
        {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }

        // decode the layout of the data into the form of a Goals object
        // note each line is tab delimited and there should only be one line of data

        // split first line by the tab delimiter
        String firstLine = contents.get(0);
        String[] lineDelimited = firstLine.split("\t");

        // Order data is saved int text file: 1) first 2) last  3) phone  4) email
        long goalSteps;
        long goalTime;
        double goalDistance;

        try
        {
            goalSteps = Long.parseLong(lineDelimited[0]);
            goalTime = Long.parseLong(lineDelimited[1]);
            goalDistance =  Double.parseDouble(lineDelimited[2]);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            // Array ended up being shorter than expected
            System.out.println("ERROR: (GOALS) Probably tried to read an empty string i.e. \"\"");

            // empty period object set as placeholder, runtime error
            goalSteps = 0L;
            goalTime = 0L;
            goalDistance = 0.0;
        }

        // create period object to hold each period
        loadedGoals = new Goals(goalSteps, goalTime, goalDistance);

        return loadedGoals;
    }
}