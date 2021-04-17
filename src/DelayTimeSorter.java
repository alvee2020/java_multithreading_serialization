/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: DelayTimeSorter.java
 * description: a basic DelayTimeSorter class to sort in the list.
 * date: April 25, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * DelayTimeSorter
 *
 * Purpose and Description
 *
 * A class to sort the list in a order that scheduled thread pool can grab on after one.
 * See the link "//https://howtodoinjava.com/java/collections/arraylist/arraylist-sort-objects-by-field/"
 * this idea was taken from this web site.
 *
 * This program does not use User input, all inputs are from directory based.
 *
 * Sun Java SDK version 1.3 or better.
 *
 * Compiling and running instructions
 * Assuming SDK 1.3 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 *
 * Compile:    See Application class file
 * Run:        See Application class file
 *
 */

/**
 * Classes
 *
 * public class DelayTimeSorter implements Comparator<Event>
 *
 * Methods (Method documentations are with the methods)
 *
 * public int compare(Event o1, Event o2)
 *
 *
 */

/**
 * CODE...
 */



import java.util.Comparator;

public class DelayTimeSorter implements Comparator<Event> {
    /**
     * sorting the two events in ascending order
     * @param o1 - Event object
     * @param o2 - Event object
     * @return - Boolean value
     */
    @Override
    public int compare(Event o1, Event o2) {
        long l1 = o1.getDelayTime();
        long l2 = o2.getDelayTime();
        return Long.compare(l1, l2);
    }

}///:~
