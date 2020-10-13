package threads.other.threadlocal;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @description:
 * @author: hejin
 * @time: 2020/10/13 13:54
 */
public class ThreadLocalTest {

    public final static int THREAD_COUNT = 4;
    public final static int TASK_COUNT = 10000000;
    static ExecutorService exe = Executors.newFixedThreadPool(THREAD_COUNT);
    static Random rnd = new Random(123);
    static ThreadLocal<Random> tRnd = new ThreadLocal<Random>(){
        @Override
        protected Random initialValue(){
            return new Random(123);
        }
    };

    public static class RndTask implements Callable<Long>{
        private int mode = 0;

        public RndTask(int mode){
            this.mode = mode;
        }

        public Random getRandom(){
            if(mode==0){
                return rnd;
            }else if(mode==1){
                return tRnd.get();
            }else {
                return null;
            }
        }

        @Override
        public Long call() throws Exception {
            long b = System.currentTimeMillis();
            for(long i = 0; i< TASK_COUNT; i++){
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" spend " + (e-b) + " ms");
            return e-b;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<Long>[] futs = new Future[THREAD_COUNT];
        for(int i=0; i<THREAD_COUNT; i++){
            futs[i] = exe.submit(new RndTask(0));
        }
        long totalTime = 0;
        for(int i=0; i<THREAD_COUNT; i++){
            totalTime += futs[i].get();
        }
        System.out.println("multi threads share the same Random instance: " + totalTime + " ms");
        for(int i=0; i<THREAD_COUNT; i++){
            futs[i] = exe.submit(new RndTask(1));
        }
        totalTime = 0;
        for(int i=0; i<THREAD_COUNT; i++){
            totalTime += futs[i].get();
        }
        System.out.println("each thread has its Random instance: " + totalTime + " ms");
        exe.shutdown();
    }

}
