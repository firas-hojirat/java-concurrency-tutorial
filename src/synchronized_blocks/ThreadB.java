package synchronized_blocks;

/**
 * Created by root on 1/30/17.
 */
public class ThreadB implements Runnable {

    private SynchronizedCounter sc = new SynchronizedCounter();

    ThreadB(SynchronizedCounter sc) {
        this.sc = sc;
    }

    @Override
    public void run() {

        for(float f = 0.5F; f < 100F; f++)
            sc.incrementFloat(f);
    }
}
