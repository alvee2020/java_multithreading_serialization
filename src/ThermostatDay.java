/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: ThermostatDay.java
 * description: a basic ThermostatDay class
 * date: April 25, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * ThermostatDay
 *
 * Purpose and Description
 *
 * A class to run ThermostatDay at a given time.
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
 * public class ThermostatDay extends Event implements Runnable, Serializable
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public ThermostatDay(long delayTime, GreenhouseControls gc)
 *
 * public void action()
 *  - Override from Event class
 *
 * public String toString()
 *  - Show the Thermostat day on the screen
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
 *  class ThermostatDay
 */

public class ThermostatDay extends Event implements Runnable, Serializable {

    /**
     * Parameterised constructor
     * @param delayTime - long value
     * @param gc - GreenhouseControl value
     */
    public ThermostatDay(long delayTime, GreenhouseControls gc) {
        super(delayTime, gc);
    }

    @Override
    public void action() {
        // Put hardware control code here.
        /**
         * Assignment requirements done
         */
        //thermostat = "Day";
    }
    public String toString() {
        return "Thermostat on day setting";
    }

    /**
     * stores the last time that this Event happens, System time, Event's Delay time
     * schedule for next Event to run
     */
    @Override
    public void run() {

        mainGreen.runTimeCurrentTime = System.currentTimeMillis();

        super.mainGreen.setVariable(new Tuple<String, String>("thermostat", "day"));
        super.mainGreen.getScreen().changeImage("thermostatday");
        super.mainGreen.screen.printStream.println("Thermostat on day setting");

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