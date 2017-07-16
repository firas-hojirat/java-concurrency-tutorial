package synchronized_blocks;

//Every object has an intrinsic lock associated with it.
//By convention, a thread that needs exclusive and consistent access to an object's fields
//has to acquire the object's intrinsic lock before accessing them, and then release the intrinsic lock when it's done with them.
//A thread is said to own the intrinsic lock between the time it has acquired the lock and released the lock.
//As long as a thread owns an intrinsic lock, no other thread can acquire the same lock.
//The other thread will block when it attempts to acquire the lock.

public class Test implements Runnable{

    private static SynchronizedCounter sc = new SynchronizedCounter();

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Test()).start();


        Thread t = new Thread(new ThreadA(sc));
        t.setName("ThreadA");
        t.start();

        while(true)
            System.out.println(sc.integerValue());
    }

    @Override
    public void run() {

        Thread t = new Thread(new ThreadB(sc));
        t.setName("ThreadB");
        t.start();

        while(true)
            System.out.println(sc.floatValue());
    }
}
