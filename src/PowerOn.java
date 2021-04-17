/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: PowerOn.java
 * description: a basic PowerOn class
 * date: April 25, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * PowerOn
 *
 * Purpose and Description
 *
 * A class to run PowerOn at a given time. Fixed the problem of GreenhouseControls object last time.
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
 * public class PowerOn extends Event implements Fixable, Runnable, Serializable
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public PowerOn(long delayTime, GreenhouseControls gc)
 *
 * public void action()
 *  - Override from Event class
 *
 * public String toString()
 *  - Show the Power on on the screen
 *
 * public long getDelayTime()
 *  - Override from Event class
 *
 * public void run()
 *  - Override from Event
 *  - Implemented from Runnable
 *
 * public void log()
 *  - fix the problem in GreenhouseControls object.
 *
 */

/**
 * CODE...
 */

import java.io.IOException;
import java.io.Serializable;

/**
 * class PowerOn
 */

/**
 *  class PowerOn
 *  - sets the poweron variable
 *  - sets the errorCode
 *  - calls log
 *
 * log()
 *  - write down the fixed problem
 */
public class PowerOn extends Event implements Fixable, Runnable, Serializable {

    /**
     * Parameterised constructor
     * @param delayTime - long value
     * @param gc - GreenhouseControls value
     */
    public PowerOn(long delayTime, GreenhouseControls gc) {
        super(delayTime, gc);
        log();            // Writing the fix.log file what is fixing in the system
    }

    @Override
    public void action() {}
    public void fix(){}
    public void log() {
        try {
            // writing in the fix.log
            WritingFile writingFile = new WritingFile(2, true);
            super.mainGreen.screen.printStream.println(writingFile.logNeedsToPrintOnScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String toString() { return "Power on"; }

    /**
     * stores the last time that this Event happens, System time, Event's Delay time
     * schedule for next Event to run
     */
    @Override
    public void run() {

        mainGreen.runTimeCurrentTime = System.currentTimeMillis();

        super.mainGreen.getScreen().changeImage("poweron");
        super.mainGreen.screen.printStream.println("Power on");

        super.mainGreen.previousEventDelayTime = delayTime;
        super.mainGreen.scheduleNextEvent();
    }

    @Override
    public long getDelayTime() {
        return super.delayTime;
    }

    public void setGC(GreenhouseControls gc){
        super.mainGreen = gc;
    }
} // PowerOn
