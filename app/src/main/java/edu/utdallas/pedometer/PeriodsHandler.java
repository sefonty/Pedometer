/**
 * Pedometer Project
 * Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Contains the list of periods and provides service functions to sort and modify the list
 */

package edu.utdallas.pedometer;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class PeriodsHandler
{
    private ArrayList<Period> periods;
    private int selectedIndex = -1; // keeps track of which item selected in list view in list activity
    private FileHandler fileHandler;
    private Boolean needContext = true;

    // Written By Scott Fontenarosa
    // Constructor, creates an empty period list
    public PeriodsHandler()
    {
        periods = new ArrayList<Period>();
        fileHandler = new FileHandler();
    }

    // Written By Scott Fontenarosa
    // Sends application context to FileHandler instance
    public void setFileHandlerContext(Context inputContext)
    {
        if (needContext)
        {
            // help FileHandler determine the app's internal local directory
            fileHandler.setContext(inputContext);
            needContext = false; // only set file path once

            // load up periods if periods file exist
            File periodFile = new File(fileHandler.getPeriodsFilePath());
            if (periodFile.exists())
            {
                System.out.println("File does exist");
                loadPeriods();
            }
            else
            {
                System.out.println("File does NOT exist");
            }
        }
    }

    // Written By Scott Fontenarosa
    // Determine if still need to set context
    public boolean getNeedContext()
    {
        return needContext;
    }

    // Written By Scott Fontenarosa
    // Create a period object to the list of periods; all details must be provided
    public void createNewPeriod(long steps, double distance, long duration, Date startTime, Date endTime)
    {
        Period newPeriod = new Period(steps, distance, duration, startTime, endTime);
        periods.add(newPeriod);

        //sortPeriods(); // no need to sort, newer periods are added to end of list
        savePeriods();
    }

    // Written By Scott Fontenarosa
    // Add a period object to the list of periods; must provide Period object
    public void addPeriod(Period period)
    {
        // create new memory address for input period
        Period newPeriod = new Period(period.getSteps(),
                period.getDistance(),
                period.getDuration(),
                period.getStartTime(),
                period.getEndTime());

        periods.add(newPeriod);

        //sortPeriods(); // no need to sort, newer periods are added to end of list
        savePeriods();
    }

    // Written By Scott Fontenarosa
    // Delete a period in the list of periods. The index to be deleted is provided by selectedIndex.
    public void deletePeriod()
    {
        // Only allow to delete from the list if it is non-empty
        // and there's a selected index within a possible range of indices
        if (periods.size() > 0 && selectedIndex > -1 && selectedIndex < periods.size())
        {
            // Delete period at selectedIndex
            periods.remove(selectedIndex);
            selectedIndex = -1;

            // no need to sort the list before saving after doing a delete action
            savePeriods();
        }
    }

    // Written By Scott Fontenarosa
    // Delete a period in the list of periods.
    // The index to be deleted is provided by the argument.
    public void deletePeriod(int indexToDelete)
    {
        setSelectedIndex(indexToDelete);
        deletePeriod();
    }

    // Written By Scott Fontenarosa
    // Modifies an existing period object in the list of periods. All details must be provided.
    // The index to be modified is provided by selectedIndex
    public void modifyPeriod(long steps, double distance, long duration, Date startTime, Date endTime)
    {
        // Only allow to modify an period in the list if it is non-empty
        // and there's a selected index within a possible range of indices
        if (periods.size() > 0 && selectedIndex > -1 && selectedIndex < periods.size())
        {
            // update period details
            periods.get(selectedIndex).setSteps(steps);
            periods.get(selectedIndex).setDistance(distance);
            periods.get(selectedIndex).setDuration(duration);
            periods.get(selectedIndex).setStartTime(startTime);
            periods.get(selectedIndex).setEndTime(endTime);
            selectedIndex = -1;

            //sortPeriods(); // might need to sort later, by start or end time
            savePeriods();
        }
    }

    // Written By Scott Fontenarosa
    // Modifies an existing period object in the list of periods. A period object must be provided.
    // The index to be modified is provided by the argument
    public void modifyPeriod(int indexToModify, Period inputPeriod)
    {
        setSelectedIndex(indexToModify);

        modifyPeriod(inputPeriod.getSteps(),
                inputPeriod.getDistance(),
                inputPeriod.getDuration(),
                inputPeriod.getStartTime(),
                inputPeriod.getEndTime());
    }

    // Sets the selected index to modify or delete.
    // OLD STRATEGY: This is done separately since the period to modify/delete
    // is first selected in the list activity then displayed in the details activity
    //
    // CURRENT STRATEGY: Despite the fact we might need access to period data from
    // more than one activity, we can pass needed data through intents
    public void setSelectedIndex(int index)
    {
        selectedIndex = index;
    }

    // Written By Scott Fontenarosa
    // Get a specified period from the period list based on the index desired.
    // Useful for displaying specific data from the period list.
    public Period getPeriod(int index)
    {
        return periods.get(index);
    }

    // Written By Scott Fontenarosa
    // Gives the entire period list structure. Useful for performing operations that require the whole list
    // like backing up the period onto a local file.
    public ArrayList<Period> getPeriodList()
    {
        return periods;
    }

    // Written By Scott Fontenarosa
    // Returns string of all the periods in the form:
    // index # - steps - distance - duration - start time - end time<new line>
    public String toString()
    {
        String stringPeriods = ""; // string to return

        // go through all the periods in the periods list
        for (int eachPeriod = 0; eachPeriod < periods.size(); eachPeriod++)
        {
            // display only the first and last name
            stringPeriods += "index " + eachPeriod + " - " +
                    periods.get(eachPeriod).getSteps() + " " +
                    periods.get(eachPeriod).getDistance() + " - " +
                    periods.get(eachPeriod).getDuration() + " - " +
                    periods.get(eachPeriod).getStartTime().toString() + " - " +
                    periods.get(eachPeriod).getEndTime().toString() + "\n";
        }

        return stringPeriods;
    }

    // Written By Scott Fontenarosa
    public void sortPeriods()
    {
        // do merge sort based on first names on the periods list structure
        periods = PeriodSort.sort(periods);
    }

    // Written By Scott Fontenarosa
    // Saves list of periods to file using the FileHandler class
    public void savePeriods()
    {
        fileHandler.savePeriods(periods);
    }

    // Written By Scott Fontenarosa
    // Loads list of periods to memory (ArrayList periods) using the FileHandler class
    public void loadPeriods()
    {
        periods = fileHandler.loadPeriods();
    }
}

