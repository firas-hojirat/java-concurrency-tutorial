package guarded_blocks;

import java.util.List;

/**
 * Created by root on 1/31/17.
 */
public class Consumer implements Runnable {

    private final int size;
    private List<Integer> list;

    public Consumer(List<Integer> list, int size) {
        this.list = list;
        this.size = size;
    }

    @Override
    public void run() {

        for(int i = 0; i < 100; i++){

            int c = consume();
            System.out.println("Consumed " + c);
        }
    }

    private int consume() {

        int i = 0;

        synchronized (list){

            while(list.isEmpty()){

                System.out.println("List is Empty " + Thread.currentThread().getName() + " is waiting...");

                try { //wait (block) until the list is not empty.
                    list.wait();
                }
                catch (InterruptedException e) { System.out.println(e.getMessage()); }
            }
            i = list.remove(0);

            list.notifyAll(); //notify the producer that the list is not full anymore.
        }
        return i;
    }
}
