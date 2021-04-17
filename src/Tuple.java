/**
 * Name: Alvee Hassan Akash
 * Student ID: 3433334
 * Program: 01
 * Course: COMP 308
 * Athabasca University
 */

/**
 * title: Tuple.java
 * description: a basic Tuple class to simplify the collection
 * date: March 25, 2020
 * @author Alvee Hassan Akash
 * @version 1.0
 * @copyright 2020 Alvee Hassan Akash
 */

/**
 * DOCUMENTATION...
 */

/**
 * Tuple
 *
 * Purpose and Description
 *
 * a class simplifies list of collection of different types of object into list again.
 *
 * This program does not use User input, all inputs are in Main.java file.
 *
 * Sun Java SDK version 1.3 or better.
 *
 * Compiling and running instructions
 * Assuming SDK 1.3 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 *
 * Compile:    See Client class file
 * Run:        See Client class file
 *
 */

/**
 * Classes
 *
 * public class Tuple<T, Long>
 *
 * Methods (Method documentations are with the methods)
 *
 * Constructors
 * public Tuple(T p, Long l)
 *
 *
 * Instance Variables
 *
 * private final T product;
 * private final Long id;
 *
 *
 */

import java.io.Serializable;

/**
 * CODE...
 */

public class Tuple<K, V> implements Serializable {
    /**
     * Instance variables to store the data type and order id of the Generic Order
     */
    private String key;
    private String value;

    /**
     * Parameterised Constructor
     * @param k - String type value
     * @param v - String value
     */
    public Tuple(String k, String v) {
        this.key = k;
        this.value = v;
    }

    public void setKey(String k){
        this.key = k;
    }

    public void setValue(String v) {
        this.value = v;
    }

    /**
     * Return the element of that specific T type
     * @return T type element
     */
    public String getKey() {
        return key;
    }

    /**
     * Return the Long type value in the tuple
     * @return - Long type value
     */
    public String getValue() {
        return value;
    }


}///:~
