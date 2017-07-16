package synchronized_methods;

//Every object has an intrinsic lock associated with it.
//By convention, a thread that needs exclusive and consistent access to an object's fields
//has to acquire the object's intrinsic lock before accessing them, and then release the intrinsic lock when it's done with them.
//A thread is said to own the intrinsic lock between the time it has acquired the lock and released the lock.
//As long as a thread owns an intrinsic lock, no other thread can acquire the same lock.
//The other thread will block when it attempts to acquire the lock.

//When a thread invokes a synchronized method,
//it automatically acquires the intrinsic lock for that method's object and releases it when the method returns.
public class Test {

    public static void main(String[] args) throws InterruptedException {

        SynchronizedCounter sc = new SynchronizedCounter();

        Thread t = new Thread(new ThreadA(sc));
        t.setName("ThreadA");
        t.start();

        //this method will block, since it can't access a synchronized method (value) when another thread (ThreadA)
        //is accessing another synchronized method on the same object sc.
        //thus this method is executed when ThreadA dies and prints 100.
        while(true)
            System.out.println(sc.value());

    }
}
