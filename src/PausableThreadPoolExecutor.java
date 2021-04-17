/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: PausableThreadPoolExecutor.java
 * description: a basic PausableThreadPoolExecutor class, JFrame window.
 * date: April 25, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * PausableThreadPoolExecutor
 *
 * Purpose and Description
 *
 * A class to controls threads with the ability of controlling the GreenhouseControls object.
 * base class was found @link https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ThreadPoolExecutor.html
 *
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
 * public class PausableThreadPoolExecutor extends ScheduledThreadPoolExecutor implements Serializable
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public PausableThreadPoolExecutor(int numberOfThreads, GreenhouseControls gc)
 *
 *
 *     private boolean isPaused;
 *     private ReentrantLock pauseLock = new ReentrantLock();
 *     private Condition unpaused = pauseLock.newCondition();
 *
 *
 *     protected GreenhouseControls gc;
 *          - to collect the time from the Green house Controls object
 *
 *     protected transient ScheduledFuture eventScheduledForNextRun;
 *          - to store the next runnable future task on the queue
 *
 *     protected long timeToStartAgain;
 *          - new delay after resume the system
 *
 *
 *
 *
 */

/**
 * CODE...
 */


import java.io.Serializable;
import java.util.concurrent.*;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PausableThreadPoolExecutor extends ScheduledThreadPoolExecutor implements ScheduledExecutorService, Serializable {
    private boolean isPaused;
    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unpaused = pauseLock.newCondition();
    protected GreenhouseControls gc;
    protected transient ScheduledFuture eventScheduledForNextRun;
    protected long timeToStartAgain;




    /**
     * Parameterised constructor
     * @param numberOfThreads - number of threads will run the events
     * @param gc - GreenhouseControls object
     * by Alvee Hassan Akash
     */
    public PausableThreadPoolExecutor(int numberOfThreads, GreenhouseControls gc) {
        super(numberOfThreads);
        this.gc = gc;
    }


    /**
     * checks before running any event, see if the system is paused or not
     * @param t - Thread that will run the task
     * @param r - The task which will be run
     */
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        pauseLock.lock();
        try {
            while (isPaused) unpaused.await();
        } catch(InterruptedException ie) {
            t.interrupt();
        } finally {
            pauseLock.unlock();

        }
    } // beforeExecute


    /**
     * Pause the current Thread pool executor. takes the time to recalculate
     * next Event's delay time.
     */
    public void pause() {

        // when pause clicked, it takes the current time to calculate the time
        // how much time it had left to run for the next Event
        // it saves in for the resume to use that time to start.
        gc.systemPauseTime = System.currentTimeMillis();
        timeToStartAgain = gc.nextEventDelayTime();

        pauseLock.lock();
        try {
            isPaused = true;
        } finally {
            pauseLock.unlock();
        }
    } // pause


    /**
     * Resumes the current Thread pool executor and schedules the next event
     * with new calculated time from pause method.
     */
    public void resume() {

        // Cancel the next run from the queue. collects it real Event object and
        // Re Schedule it by using the time from "pause happened"
        this.eventScheduledForNextRun.cancel(false);
        this.eventScheduledForNextRun = this.schedule(gc.eventWillRunNext, timeToStartAgain, TimeUnit.MILLISECONDS);

        pauseLock.lock();
        try {
            isPaused = false;
            unpaused.signalAll();
        } finally {
            pauseLock.unlock();
        }
    } // resume


}///:~
