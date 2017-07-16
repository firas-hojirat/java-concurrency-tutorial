package ExecutorFramework;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 2/24/17.
 */
public class SingleThreadExecutor {

    //Creates an Executor that uses a single worker thread operating off an unbounded queue.
    //Tasks are guaranteed to execute sequentially, and no more than one task will be active at any given time.

    //Unlike the otherwise equivalent newFixedThreadPool(1)
    //the returned executor is guaranteed not to be reconfigurable to use additional threads.

    //i.e newFixedThreadPool(1) can be changed to newFixedThread(N).
    //whereas newSingleThreadExecutor() will always have one thread at a time and can't be changed.
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void start(int nThreads){

        for(int i = 0; i < nThreads; i++)
            executor.execute(new Task(i));
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

        SingleThreadExecutor ftp = new SingleThreadExecutor();

        ftp.start(4);

        for(int i = 0; i < 1000; i++)
            System.out.println("Main Thread");

        ftp.shutdown();
    }
}
