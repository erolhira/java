package com.jtudy.workshop.java9.newfeatures;

/*
 * Spin-Wait Hints (JEP 285)
 * 
 * The onSpinWait() method provides a hint to the JVM that the thread is currently in a processor spin loop. 
 * If the JVM and hardware platform support optimizations when spinning, such hints can be used; otherwise, the call is ignored.
 * 
 * By calling onSpinWait, a thread tells the JVM that it is in a busy waiting process. 
 * That is, it signals that the thread is within a loop waiting for something to happen, 
 * and although it is not sleeping or blocked, it is also not making progress on the work it intends to do. 
 * Therefore, this method informs the JVM that it can perform some sort of performance optimization in this situation.
 * 
 * onSpinWait() best fits when:
 * 1. a thread is waiting for an external condition or events to occur, which might happen very frequently (i.e. at a high rate)
 * 2. and the events finish (or last) very quickly, hence the thread should not wait for a long period of time
 *  
 */
public class OnSpinWait {

	volatile boolean eventNotificationNotReceived;

	void waitForEventAndHandleIt() {
		
		/*
		 * Before Java 9, you used an empty loop with a call to sleep to wait for the condition.
		 * The new static method onSpinWait() in Java 9 can make the waiting more efficient.
		 */
		while (eventNotificationNotReceived) {
			java.lang.Thread.onSpinWait();
		}
		readAndProcessEvent();
	}

	void readAndProcessEvent() {
		// Read event from some source and process it
	}
}
