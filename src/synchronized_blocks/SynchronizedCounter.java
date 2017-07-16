package synchronized_blocks;

//Only one thread can execute inside a Java code block synchronized on the same monitor object.
public class SynchronizedCounter {

    //the two fields are not related thus each field requires its synchronize block.
    private int c1;
    private float c2;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public synchronized void incrementInteger(int i){

        synchronized (lock1) {
            c1+= i;
        }
    }

    public synchronized void incrementFloat(float f) {

        synchronized (lock2) {
            c2+=f;
        }
    }

    public synchronized int integerValue() {
        return c1;
    }

    public synchronized float floatValue() {
        return c2;
    }
}
