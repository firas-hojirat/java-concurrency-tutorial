package synchronized_methods;

//When one thread is executing a synchronized method for an object,
//all other threads that invoke synchronized methods for the same object block (suspend execution)
//until the first thread is done with the object.
public class SynchronizedCounter {

    private int c;

    public synchronized void increment(){
        c++;
    }

    public synchronized int value(){
        return c;
    }
}
