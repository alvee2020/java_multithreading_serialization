/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: WaterOn.java
 * description: a basic WaterOn class
 * date: April 25, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * WaterOn
 *
 * Purpose and Description
 *
 * A class to run WaterOn at a given time.
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
 * public class WaterOn extends Event implements Runnable, Serializable
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public WaterOn(long delayTime, GreenhouseControls gc)
 *
 * public void action()
 *  - Override from Event class
 *
 * public String toString()
 *  - Show the water on on the screen
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
 *  class WaterOn
 */

public class WaterOn extends Event implements Runnable, Serializable {

    /**
     * Parameterised Constructor
     * @param delayTime - long value
     * @param gc - GreenhouseControls value
     */
    public WaterOn(long delayTime, GreenhouseControls gc) {
        super(delayTime, gc);
    }

    @Override
    public void action() {
        // Put hardware control code here.
        /**
         * Assignment requirements done
         */
        //water = true;
    }


    public String toString() {
        return "Greenhouse water is on";
    }

    @Override
    public void run() {

        mainGreen.runTimeCurrentTime = System.currentTimeMillis();

        super.mainGreen.setVariable(new Tuple<String, String>("water", "On"));
        super.mainGreen.getScreen().changeImage("wateron");
        super.mainGreen.screen.printStream.println("Greenhouse water is on");

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

}///:~