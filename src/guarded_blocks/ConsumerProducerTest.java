package guarded_blocks;

import java.util.ArrayList;
import java.util.List;

/* Object.wait() */

// Causes the current thread to wait until another thread invokes the notify() method or the notifyAll() method for this object.
// The current thread must own this object's monitor. The thread releases ownership of this monitor and waits
// until another thread notifies threads waiting on this object's monitor to wake up either through a call to the notify method or
// the notifyAll method. The thread then waits until it can re-obtain ownership of the monitor and resumes execution.

/*
interrupts and spurious wake ups are possible, and this method should always be used in a loop:

synchronized (obj) {
    while (<condition does not hold>)
        obj.wait();
        ... // Perform action appropriate to condition
    }
*/


/* Object.notify() */

// Wakes up a single thread that is waiting on this object's monitor. If any threads are waiting on this object,
// one of them is chosen to be awakened. The choice is arbitrary and occurs at the discretion of the implementation.
// A thread waits on an object's monitor by calling one of the wait methods.
// The awakened thread will not be able to proceed until the current thread relinquishes the lock on this object.

// This method should only be called by a thread that is the owner of this object's monitor.
// A thread becomes the owner of the object's monitor in one of three ways:

//    By executing a synchronized instance method of that object.
//    By executing the body of a synchronized statement that synchronizes on the object.
//    For objects of type Class, by executing a synchronized static method of that class.

// Only one thread at a time can own an object's monitor.

public class ConsumerProducerTest {

    public static void main(String[] args) throws InterruptedException {

        List<Integer> list = new ArrayList();
        int size = 3;

        Thread consumer = new Thread(new Consumer(list, size));
        Thread producer = new Thread(new Producer(list, size));

        consumer.setName("Consumer");
        producer.setName("Producer");

        consumer.start();
        Thread.sleep(100);
        producer.start();
    }
}
