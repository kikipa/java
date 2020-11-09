package threads.patterns.future;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.LockSupport;

public class JrFutureTask<V> implements JrRunnableFuture<V> {

    private int state;

    private final int NEW = 1;
    private final int COMPLETING = 2;
    private final int FINISHED = 3;

    private Object outcome;

    private Callable<V> callable;

    private BlockingQueue<Thread> waitQueue;

    public JrFutureTask(Callable<V> callable){
        this.callable = callable;
        this.state = NEW;
    }

    @Override
    public void run() {
        try {
            V result = callable.call();
            set(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void set(V v) throws InterruptedException {
        Object outcome = v;
        completion();
    }

    private void completion() throws InterruptedException {
        state = FINISHED;
        LockSupport.unpark(waitQueue.take());
    }

    public V get(){
        int s = this.state;
        if(s<=COMPLETING){
            awaitDone();
        }
        return report(s);
    }

    private V report(int s) {

        return (V)outcome;
    }

    private void awaitDone() {
        if(state<=COMPLETING){
            LockSupport.park();
        }
    }

    @Override
    public boolean isDone() {

        return false;
    }
}
