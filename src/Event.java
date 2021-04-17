//: innerclasses/controller/Event.java
// The common methods for any control event.
// From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

/***********************************************************************
 * Adapated for COMP308 Java for Programmer, 
 *		SCIS, Athabasca University
 *
 * Assignment: TME3
 * @author: Steve Leung
 * @date  : Oct. 21, 2006
 *
 * Description: Event abstract class
 *
 */

import java.io.*;

// added Serializable
public abstract class Event implements Runnable, Serializable {
  private long eventTime;
  protected long delayTime;
  protected GreenhouseControls mainGreen;

  public Event(long delayTime, GreenhouseControls greenhouseControls) {
    this.delayTime = delayTime;
    this.mainGreen = greenhouseControls;
    start();
  }
  public void start() { // Allows restarting
    eventTime = System.currentTimeMillis() + delayTime;
  }
  public boolean ready() {
    return System.currentTimeMillis() >= eventTime;
  }
  public abstract void action();

  public abstract long getDelayTime();
  public void setDelayTimeAgain(long nextDelayTime){
    this.delayTime += this.delayTime+nextDelayTime;
  }

  @Override
  public void run() {}

  public abstract void setGC(GreenhouseControls gc);
} ///:~
