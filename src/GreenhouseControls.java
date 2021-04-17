/***********************************************************************
 * Adapated for COMP308 Java for Programmer, 
 *		SCIS, Athabasca University
 *
 * Assignment: TME3
 *
 * @date  : Oct 21, 2005
 *
 */
/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01 - Greenhouse Controls by abstract class, inheritance, File I/O
 * Course: COMP 308
 * Athabasca University
 *
 * NOTE: Most of the programing is self-documenting or self-describing.
 * Note: The base file was already coded by creator, I am just adding some features only for assignment.
 */

/**
 * title: GreenhouseControls.java
 * description: A basic GreenhouseControls class controls I/O, inheritance.
 * date: April 12, 2020
 * @author: Steve Leung (main Author)
 * Note: Alvee Hassan Akash added functionality to complete assignment (only this edition)
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 *
 * // innerclasses/GreenhouseControls.java
 * // This produces a specific application of the
 * // control system, all in a single class. Inner
 * // classes allow you to encapsulate different
 * // functionality for each type of event.
 * // From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
 * // www.BruceEckel.com. See copyright notice in CopyRight.txt.
 *
 * I declare that this assignment is my own work which will be differently formatted in the folder
 * and that all material previously written or published in any source
 * by any other person has been duly acknowledged in the assignment. I have not submitted this work, or a significant
 * part thereof, previously as part of any academic program. In submitting this assignment I give permission to copy
 * it for assessment purposes only.
 */

/**
 * DOCUMENTATION...
 */

/**
 * GreenhouseControls
 *
 * Purpose and Description
 *
 * a class to run the complete program. It has many functionality - inner classes, interface, etc
 * Inner classes to run that helps to complete the Events and GreenhouseControls with and without
 * errors according to the assignment requirements.
 *
 * This program does use User input, all inputs are from directory based shown in the RUN-TIME.
 *
 * Sun Java SDK version 1.3 or better.
 *
 * Compiling and running instructions
 * Assuming SDK 1.3 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 *
 *
 * Compile:    See Application class
 *
 * Run:        See Application class
 *
 */

/**
 * Classes
 *
 * public class GreenhouseControls extends Controller implements Serializable
 *
 * Interface
 *
 * interface Fixable
 *  - help to fix the errors in the GreenhouseControls Events such as Power out and Window Malfunction
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public GreenhouseControls(String startCode, String fileName, Screen window)
 *
 *
 * public void shutdown(String problem)
 *
 * public Fixable getFixable (int errorcode)
 *
 * public int getError()
 *
 * public void setVariable(Tuple<String, String> tuple)
 *
 * public void showDB()
 *
 * public List<Tuple<String, String>> getDatabase()
 *
 * public PausableThreadPoolExecutor getPauseThread()
 *
 * public void scheduleToThread(Runnable runnable, long time)
 *
 * protected void addTerminateEvent(Event event)
 *
 *
 *
 *
 * Instance Variables
 *
 *     private static final long serialVersionUID = 4L;
 *          - Serial version ID
 *
 *     protected transient Screen screen;
 *          - Screen to associate with All types events
 *
 *     protected transient PausableThreadPoolExecutor pauseThread;
 *          - ScheduledThreadPoolExecutor to run the application
 *
 *     protected int errorCode;
 *          - stores the error code if system shuts down
 *
 *     protected List<Event> eventListFromFile = new ArrayList<>();
 *          - stores the Events list that needs to be run in the green house
 *
 *     protected List<Tuple<String, String>> database = Collections.synchronizedList(new ArrayList<>());
 *          - Stores the "key:Pair" value of this system. As it was mentioned from TA Steve
 *          - Example:
 *                      key     value
 *                      -----   ------
 *                      light   off
 *                      water  on
 *
 *     protected long previousEventDelayTime;
 *          - last Event's delay time
 *
 *     protected transient Event eventWillRunNext;
 *          - Event that will run next in the system
 *
 *     protected long systemPauseTime;
 *          - When the system triggered the pause Time
 *
 *     protected long runTimeCurrentTime;
 *          - last Event's happened, when it happened, it was that time's System Current time
 *
 *
 *
 *
 *
 */

/**
 * CODE...
 */

/**
 * Important imported packages to run this file
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
;
import java.util.concurrent.TimeUnit;


public class GreenhouseControls extends Controller implements Serializable, Runnable {
    // added for some unique id, (I had only One problem at first, so I included)
    private static final long serialVersionUID = 4L;

    protected transient Screen screen;

    protected transient PausableThreadPoolExecutor pauseThread;

    protected int errorCode;

    protected List<Event> eventListFromFile = new ArrayList<>();

    protected List<Tuple<String, String>> database = Collections.synchronizedList(new ArrayList<>());

    protected long previousEventDelayTime;

    protected transient Event eventWillRunNext;

    protected long systemPauseTime;

    protected long runTimeCurrentTime;


    /**
     * Algorithm for calculating "pause" and "resume" time calculation:
     *
     * First: It takes Current time of previous Event time happened
     * Second: Adds next Event's delay time
     * Third: Subtracts it upper these numbers from "Pause" time happens current time
     * Fourth: Subtracts it again from Previous Event Delay time
     * Fifth: the result of this calculation is the new delay of the next Event when it is paused.
     * Note: it does not count "Elapsed time", (how long it wad paused in the system)
     * @return - long value
     */
    protected long nextEventDelayTime(){
        if (!eventListFromFile.isEmpty()){
            long inBetweenTime = runTimeCurrentTime + eventWillRunNext.getDelayTime();
            long pastRunAt = inBetweenTime - systemPauseTime - previousEventDelayTime;
            return pastRunAt;
        }
        else
            // in case something goes wrong, it will start in 2 seconds
            // in my testing, it has not failed. but I am sure there are bugs.
            return 2000;
    } // nextEventDelayTime


    /**
     * Schedule next from other classes. look into at if there is any event left to run or not.
     */
    public void scheduleNextEvent(){
        if (!eventListFromFile.isEmpty()){
            eventWillRunNext = eventListFromFile.remove(0);
            pauseThread.eventScheduledForNextRun = pauseThread.schedule(eventWillRunNext, eventWillRunNext.getDelayTime()-previousEventDelayTime, TimeUnit.MILLISECONDS);
        }
    } // scheduleNextEvent


    /**
     * interface fixable type classes to fix the error and start the system again.
     * @param errorcode - int value
     * @param gc - GreenhouseControls value
     * @return - fixable interface type class - in this case (FixWindow or PowerOn)
     */
    public static Fixable getFixable (int errorcode, GreenhouseControls gc){
        //which returns the appropriate Fixable object to correct the error and reset the error code to zero.
        if (errorcode == 1){
            return new FixWindow(0, gc);
        }
        else return new PowerOn(0, gc);
    } // getFixable


    /**
     * If input was wrong, it will print these on screen.
     */
    public void printUsage() {
        screen.printStream.println("Correct format: ");
        screen.printStream.println("  java GreenhouseControls -f <filename>, or");
        screen.printStream.println("  java GreenhouseControls -d dump.out");
    }


    /**
     * Constructor
     * @param startCode - code to select which file to choose
     * @param fileName - file string value
     * @param window - Screen value
     */
    public GreenhouseControls(String startCode, String fileName, Screen window){

        this.screen = window;

        if (!(startCode.equals("-f")) && !(startCode.equals("-d")) ){
            printUsage();
        }

        // Creating object based on start code, -f is to make a new GreenhouseControls
        if (startCode.equals("-f")) {
            //systemStartTime = System.currentTimeMillis();
            pauseThread = new PausableThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), this);
            this.screen.greenHouseStartRunning();
            //this.screen.printStream.println("Green house class serialize -----------------------");
            createDatabase();

            Thread thread = new Thread(new Restart(0, fileName, this), "green house");
            thread.start();
        } // if

    } // GreenhouseControls


    /**
     * Sets GreenhouseControls current state as Key:Value pair in list
     * @param tuple - Tuple value - key:value pair
     */
    public void setVariable(Tuple<String, String> tuple){
        for (Tuple<String, String> originalTuple : database) {
            if (originalTuple.getKey().equals(tuple.getKey())){
                originalTuple.setValue(tuple.getValue());
            }
        }
    }

    /**
     * Shows the database in the console of print stream
     */
    public void showDB(){
        screen.getPrintStream().println("Key \t\t Value");
        screen.getPrintStream().println("____ \t\t______");
        for (Tuple<String, String> tuple : database) {
            String key = tuple.getKey();
            String value = tuple.getValue();
            String line = key + "\t\t"+ value;
            if (!value.isEmpty()){
                screen.getPrintStream().println(line);
            }

        }
    }

    /**
     * Returns the list of Tuple as a database
     * @return - List of Tuple value
     */
    public List<Tuple<String, String>> getDatabase() {
        return database;
    }

    /**
     * Returns the Thread Pool Executor from current object class,
     * so that it can be used to schedule
     * @return - PausableThreadPoolExecutor value
     */
    public PausableThreadPoolExecutor getPauseThread() {
        return pauseThread;
    }

    /**
     * it schedules our events in GreenhouseControls System
     * @param runnable - Runnable implemented object value
     * @param time - long value - schedule time to execute
     */
    public void scheduleToThread(Runnable runnable, long time){
        pauseThread.schedule(runnable, time, TimeUnit.MILLISECONDS);
    }

    /**
     * Shutdown the system. showing the reason.
     * @param problem - string value of the problem name
     */
    public void shutdown(String problem){
        this.screen.printStream.println("Emergency Shutdown !! \nReason is- " + problem +"\n");
        try {
            // writing file what causes the shutdown in the system
            WritingFile writingFile = new WritingFile(errorCode, false);
            this.screen.printStream.println(writingFile.logNeedsToPrintOnScreen);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //this.screen.printStream.println("Before shutdown");
            screen.sentBackToSerialize(this);
            this.pauseThread.shutdownNow();
        }
    } // shutdown

    /**
     * adds sudden terminate event to GreenhouseControls system.
     * checks if the scheduling system has already ended or not.
     * @param event - Event value - it receives Terminate event from Screen
     */
    protected void addTerminateEvent(Event event){
        if (this.pauseThread.getQueue().isEmpty()) return;
        this.pauseThread.schedule(event, event.getDelayTime(), TimeUnit.MILLISECONDS);
        //Restart.pauseThread.shutdownNow();
    }




    /**
     * Returns the Screen reference in GreenhouseControls object
     * @return - Screen value
     */
    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    /**
     * Returns the Error code saved in GreenhouseControls object
     * @return - int value
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the code to GreenhouseControls object
     * @param errorCode - int value
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * initializes the database with empty values
     */
    private void createDatabase() {
        database.add(new Tuple<String, String>("light", ""));
        database.add(new Tuple<String, String>("water", ""));
        database.add(new Tuple<String, String>("fan", ""));
        database.add(new Tuple<String, String>("thermostat", ""));
    }

    /**
     * list of Event's class name left to run
     * @return - String value
     */
    public List<Event> getEventListFromFile() {
        return eventListFromFile;
    }



} ///:~