package threads.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/4/14 10:35
 */
public class YvesFutureTask<T> implements Runnable{

    //任务状态
    private volatile int state = NEW;

    private static final int NEW = 0;
    private static final int RUNNING = 1;
    private static final int FINISHED = 2;

    private Callable<T> callable;
    private T result;

    //正在执行的线程，提供并发控制
    AtomicReference<Thread> runner = new AtomicReference<>();

    LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    public YvesFutureTask(Callable<T> callable){
        this.callable = callable;
    }

    public T get(){
        if(state != FINISHED){
            waiters.offer(Thread.currentThread());
        }
        while(state!=FINISHED){
            LockSupport.park();
        }
        return result;
    }


    /**
     * FutureTask的任务只能执行一次
     * */
    @Override
    public void run() {
        if(state!=NEW||!runner.compareAndSet(null, Thread.currentThread())){
            return;
        }
        state = RUNNING;

        try {
            result = this.callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            state = FINISHED;
        }

        //唤醒所有线程
        while (true){
            Thread waiter = waiters.poll();
            if(waiter==null){
                break;
            }
            LockSupport.unpark(waiter);
        }
    }
}
