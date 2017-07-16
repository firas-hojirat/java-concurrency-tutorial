package synchronized_methods;

/**
 * Created by root on 1/30/17.
 */
public class ThreadA implements Runnable{

    private SynchronizedCounter sc = new SynchronizedCounter();

    public ThreadA(SynchronizedCounter sc){
        this.sc = sc;
    }

    @Override
    public void run() {

        for(int i = 0; i < 100; i++)
            sc.increment();
    }
}
