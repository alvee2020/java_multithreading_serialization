/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: Terminate.java
 * description: a basic Terminate class
 * date: April 25, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * Terminate
 *
 * Purpose and Description
 *
 * A class to run Terminate at a given time, End all of the executable tasks here.
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
 * public class Terminate extends Event implements Runnable, Serializable
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public Terminate(long delayTime, GreenhouseControls gc)
 *
 * public void action()
 *  - Override from Event class
 *
 * public String toString()
 *  - Show the terminate on the screen
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
 *  class Terminate
 */

public class Terminate extends Event implements Runnable, Serializable {

    /**
     * Parameterised constructor
     * @param delayTime - long value
     * @param gc - GreenhouseControls value
     */
    public Terminate(long delayTime, GreenhouseControls gc) {
        super(delayTime, gc);
    }

    @Override
    public void action() {
        super.mainGreen.screen.printStream.println("System exiting");
        System.exit(0);
    }
    public String toString() { return "Terminating";  }

    /**
     * stores the last time that this Event happens, System time, Event's Delay time
     * Sends to screen that green house is done. no more running event to run.
     * Shows the Database as key:pair value
     * shutdown the system
     */
    @Override
    public void run() {

        mainGreen.runTimeCurrentTime = System.currentTimeMillis();

        super.mainGreen.screen.printStream.println("Terminating");

        //super.mainGreen.showDB();

        super.mainGreen.getScreen().greenHouseEndRunning();
        super.mainGreen.getScreen().changeImage("terminate");
        super.mainGreen.getScreen().setThreadRunning(false);
        super.mainGreen.getPauseThread().shutdownNow();

    } // run



    @Override
    public long getDelayTime() {
        return super.delayTime;
    }

    public void setGC(GreenhouseControls gc){
        super.mainGreen = gc;
    }

}