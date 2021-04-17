/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: Application.java
 * description: a basic Application class to run the system
 * date: April 25, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * Application
 *
 * Purpose and Description
 *
 * a class to initiate other classes object.
 *
 * This program takes user input such as file. it can go wrong with a slight misspell.
 *
 * Sun Java SDK version 1.3 or better.
 *
 * Compiling and running instructions
 * Assuming SDK 1.3 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 *
 * Compile:    javac tme3/*.java
 *             javac *.java
 *
 * Run:        java Application
 *
 */

/**
 * Classes
 *
 * public class Application
 *
 * Methods (Method documentations are with the methods)
 *
 * public static void main(String[] args)
 *  - to run the entire application.
 *
 *
 */


import javax.swing.*;

public class Application {

    public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
           @Override public void run() {
               Screen screen = new Screen();
           }});

        //GreenhouseControls gc = new GreenhouseControls("-f", "1_1examples1.txt");
    }
}
