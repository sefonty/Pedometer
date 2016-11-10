/**
 * Pedometer Project
 * Team Members: Melissa Dagley and Scott Fontenarosa
 *
 * Performs a merge sort on a list of Period objects
 */

package edu.utdallas.pedometer;

import java.util.ArrayList;

public class PeriodSort
{
    private static Period[] periods;
    private static Period[] tempPeriods;

    // Written By ScottFontenarosa
    // Starts sorting process on input periods list
    public static ArrayList<Period> sort(ArrayList<Period> inputPeriods)
    {
        periods = new Period[inputPeriods.size()];
        tempPeriods = new Period[inputPeriods.size()];

        // copy period list contents into temporary list
        for (int copyIndex = 0; copyIndex < inputPeriods.size(); copyIndex++)
        {
            periods[copyIndex] = inputPeriods.get(copyIndex);
        }

        // start the sorting
        doMergeSort(0, periods.length - 1);

        // create sorted list to return
        ArrayList<Period> sortedPeriods = new ArrayList<Period>();

        for (int periodIndex = 0; periodIndex < periods.length; periodIndex++)
        {
            sortedPeriods.add(periods[periodIndex]);
        }

        return sortedPeriods;
    }

    // Written By ScottFontenarosa
    // (Divide) implements recursion
    private static void doMergeSort(int lowerIndex, int higherIndex)
    {
        // keep performing divide until elements are separated
        if (lowerIndex < higherIndex)
        {
            // determining the middle index
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;

            // sorts the left side of the array
            doMergeSort(lowerIndex, middle);

            // sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);

            // merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    // Written By ScottFontenarosa
    // (Conquer) combines left and right arrays
    private static void mergeParts(int lowerIndex, int middle, int higherIndex)
    {
        // copy period list contents into temporary list
        for (int i = lowerIndex; i <= higherIndex; i++)
        {
            tempPeriods[i] = periods[i];
        }

        int i = lowerIndex; // keeps track of previous element
        int j = middle + 1; // keeps track of next element
        int k = lowerIndex; // used to order and insert element in correct order

        // sorts elements based on start time in ascending order
        while (i <= middle && j <= higherIndex)
        {
            // the comparison: if i lexicographically less than j, insert it as the lower element
            // otherwise, j is less, insert j instead as the lower element
            if (tempPeriods[i].getStartTime().getTime() <= tempPeriods[j].getStartTime().getTime())
            {
                periods[k] = tempPeriods[i];
                i++;
            }
            /*
            // values are the same
            else if (tempPeriods[i].getFirstName().compareTo(tempPeriods[j].getFirstName()) == 0)
            {
                // (not required) compare last names as a tie breaker
                if (tempPeriods[i].getLastName().compareTo(tempPeriods[j].getLastName()) < 1)
                {
                    periods[k] = tempPeriods[i];
                    i++;
                }
                else // other last name is lexicographically smaller or the same
                {
                    periods[k] = tempPeriods[j];
                    j++;
                }
            }
            */
            else // other time value is smaller
            {
                periods[k] = tempPeriods[j];
                j++;
            }

            k++;
        }

        // j is out of range, just copy the rest of the elements
        while (i <= middle)
        {
            periods[k] = tempPeriods[i];
            k++;
            i++;
        }
    }
}
