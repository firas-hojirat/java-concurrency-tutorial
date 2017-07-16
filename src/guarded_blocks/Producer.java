package guarded_blocks;

import java.util.List;

/**
 * Created by root on 1/31/17.
 */
public class Producer implements Runnable{

    private List<Integer> list;
    private int size;

    public Producer(List<Integer> list, int size){
        this.list = list;
        this.size = size;
    }

    @Override
    public void run() {

        for(int i = 0; i < 100; i++) {

            System.out.println("Produced " + i);
            produce(i);
        }
    }

    private void produce(int p) {

        synchronized (list){

            while(list.size() == size) {

                System.out.println("List is Full " + Thread.currentThread().getName() + " is waiting...");

                try{ // wait (block) until the list is not full.
                    list.wait();
                }
                catch (InterruptedException e) { System.out.println(e.getMessage()); }
            }
            list.add(p);

            list.notifyAll(); //notify the Consumer that the list is not empty.
        }
    }
}
