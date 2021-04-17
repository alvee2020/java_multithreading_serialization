/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: Restore.java
 * description: a basic Restore class to retrieve the serializable object
 * date: April 25, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * Restore
 *
 * Purpose and Description
 *
 * A class to run Restore at a given time to deserialize the GreenhouseControls object and
 * run it from where it left before, and fix the error.
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
 * public class Restore extends Event implements Runnable, Serializable
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public Restore(long delayTime, GreenhouseControls gc)
 *
 * public void action()
 *  - Override from Event class
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;



/**
 *  class Restore.java
 *  - restore the system from console given file (dump.out)
 *  - writes the fix.log file
 *
 * Note: idea taken from see {@link} https://www.geeksforgeeks.org/object-serialization-inheritance-java/
 */
public class Restore extends Event implements Runnable, Serializable {

    /**
     * Parameterised constructor
     * @param delayTime - long value
     * @param gc - GreenhouseControls value
     */
    public Restore(long delayTime, GreenhouseControls gc){
        super(delayTime, gc);
        //this.filename = filename;

    } // public Restore(String filename)

    /**
     * Fix the Error code in the system
     * Execute it fixable Event
     * and starts scheduling for the rest of the Events
     */
    @Override
    public void action() {

        Event event = (Event) GreenhouseControls.getFixable(mainGreen.errorCode, mainGreen);

        mainGreen.pauseThread.execute(event);

        super.mainGreen.scheduleNextEvent();

    } // action


    @Override
    public void run() {
        super.mainGreen.screen.printStream.println("Restoring GreenhouseControls");
        action();
    }

    @Override
    public long getDelayTime() {
        return super.delayTime;
    }

    public void setGC(GreenhouseControls gc){
        super.mainGreen = gc;
    }
} // Restore