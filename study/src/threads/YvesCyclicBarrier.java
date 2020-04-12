package threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class YvesCyclicBarrier {

    private final int parties;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private int count;

    private void nextGeneration(){
        count = 0;
        condition.signalAll();
    }

    public YvesCyclicBarrier(int parties){
        this.parties = parties;
    }

    public void await(){
        //集齐parties个，就结束等待
        lock.lock();
        try{
            count++;
            if(count==parties){
                nextGeneration();
                return;
            }
            for(;;) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }
}

