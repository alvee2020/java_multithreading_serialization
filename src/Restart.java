/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: Restart.java
 * description: a basic Restart class
 * date: April 25, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * Restart
 *
 * Purpose and Description
 *
 * A class to run Restart at a given time. it starts the GreenhouseControls sequence.
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
 * public class Restart extends Event implements Runnable, Serializable
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public Restart(long delayTime, GreenhouseControls gc)
 *
 * public void action()
 *  - Override from Event class
 *
 * public String toString()
 *  - Show the light off on the screen
 *
 * public long getDelayTime()
 *  - Override from Event class
 *
 * public void run()
 *  - Override from Event
 *  - Implemented from Runnable
 *
 */

/**
 * CODE...
 */

import java.io.Serializable;

/**
 *  class Restart
 */


import java.io.FileNotFoundException;
import java.lang.reflect.*;
import java.util.Iterator;

/**
 * Class Restart (Modified)
 *  - System restart GreenhouseControls and read the file from the console and detects
 *  events and time and add it to the list in "callEventClass" method.
 */
public class Restart extends Event implements Runnable, Serializable {
    /**
     * Instance variables
     * eventsFile - String value - to store the file name of the file
     * gc - GreenhouseControls value - to store the current GreenhouseControls
     * pauseThread - PausableThreadPoolExecutor value - to schedule every events
     */
    private final String eventsFile;

    /**
     * Parameterised constructor
     * @param delayTime - long value
     * @param filename - String value
     * @param gc - GreenhouseControls value
     */
    public Restart(long delayTime, String filename, GreenhouseControls gc) {
        super(delayTime, gc);
        eventsFile = filename;
    }

    @Override
    public void action() {

        try {
            // Reading the file from the console and storing the events and time in the container
            ReadingFile readingFile = new ReadingFile(eventsFile);
            Iterator<String> eventList = readingFile.getEventArrayList().iterator();
            Iterator<Long> timeList = readingFile.getTimeArrayList().iterator();


            // Equal or more than 2 rings means set the rings in the system for Bell class
            // otherwise set the number only 1 ring
            int rings = readingFile.getRingsOfBell();
            rings = rings >= 2 ? rings-1 : 1;

            // reads the list and call the method to add event
            while (eventList.hasNext()){
                String className = eventList.next();
                Long time = timeList.next();

                getObject(className, time, rings);

            } // while

        } // try

        catch (FileNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        } // catch

        finally {
            // Sorting list 0 - 2000 - 6000 - etc for fetching to SchedulePool
            super.mainGreen.eventListFromFile.sort(new DelayTimeSorter());

            super.mainGreen.previousEventDelayTime = delayTime;
            super.mainGreen.scheduleNextEvent();
        }

    } // action method


    public String toString() { return "Restarting system"; }

    /**
     * Restarting the system right away
     */
    @Override
    public void run() {

        super.mainGreen.screen.printStream.println("Restarting Green House");
        action();

    }

    /**
     * based on the string value, it creates new classes of Events
     * after that, it schedules them to run according to its delay time in the system
     * @param className - String value
     * @param delayTime - long value
     * @param rings - int value - only used when Bell is passed over 2 times.
     * @throws ClassNotFoundException - if class not found
     * @throws NoSuchMethodException - if method not found
     * @throws IllegalAccessException - if any illegal access was tried
     * @throws InvocationTargetException - if creating object invoke has problem
     * @throws InstantiationException - if instance creating encounter any problem
     */
    public void getObject(String className, long delayTime, int rings) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> eventClass = Class.forName(className);
        Constructor<?> constructor = eventClass.getConstructor(long.class, GreenhouseControls.class);
        Event newEvent = (Event) constructor.newInstance(delayTime, super.mainGreen);


        super.mainGreen.eventListFromFile.add(newEvent);


        //gc.scheduleToThread(newEvent, delayTime);
        //pauseThread.schedule((Runnable) newEvent, delayTime, TimeUnit.MILLISECONDS);

        if (className.equals("Bell") & rings > 1){
            long period = 2000;
            for (int i = 0; i < rings; i++){

                newEvent = (Event) constructor.newInstance(delayTime+period, super.mainGreen);
                super.mainGreen.eventListFromFile.add(newEvent);

                //gc.scheduleToThread(newEvent, delayTime+period);

                //pauseThread.schedule((Runnable) newEvent, delayTime+period, TimeUnit.MILLISECONDS);
                period += 2000;
            }
        } // if
    } // getObject

    @Override
    public long getDelayTime() {
        return super.delayTime;
    }

    public void setGC(GreenhouseControls gc){
        super.mainGreen = gc;
    }

} // end of public class Restart
