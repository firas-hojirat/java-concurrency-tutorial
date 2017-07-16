package ExecutorFramework;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 2/24/17.
 */
public class FixedThreadPool {

    private ExecutorService executor;
    private static final int COUNT = 1;

    public FixedThreadPool(){
        executor = Executors.newFixedThreadPool(COUNT); //number of COUNT active concurrent threads at a time.
    }

    public void start(int nThreads){
        for(int i = 0 ; i < nThreads; i++) //remaining threads will wait until the pool has a free place.
            executor.execute(new Task(i)); //execute the task / start the threads.
    }

    private class Task implements Runnable{

        private int id;

        private Task(int id){
            this.id = id;
        }

        @Override
        public void run() {
            for(int i = 0; i < 1000; i++)
                System.out.println("Task<" + id + ">: " + i);
        }
    }

    public void shutdown(){

        executor.shutdown(); //stop accepting any more tasks(threads).
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES); //wait for the tasks to finish executing or for the timeout to occur.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        FixedThreadPool ftp = new FixedThreadPool();

        ftp.start(4);

        for(int i = 0; i < 1000; i++)
            System.out.println("Main Thread");

        ftp.shutdown();
    }
}
