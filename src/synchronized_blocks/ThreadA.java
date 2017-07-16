package synchronized_blocks;

public class ThreadA implements Runnable{

    private SynchronizedCounter sc = new SynchronizedCounter();

    public ThreadA(SynchronizedCounter sc){
        this.sc = sc;
    }

    @Override
    public void run() {

        for(int i = 0; i < 100; i++)
            sc.incrementInteger(i);
    }
}
