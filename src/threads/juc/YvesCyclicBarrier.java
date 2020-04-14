package threads.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/4/14 9:49
 */
public class YvesCyclicBarrier {

    private final int parties;
    private volatile int count;
    private Object generation;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public YvesCyclicBarrier(int parties){
        this.parties = parties;
        this.count = 0;
        generation = new Object();
    }

    private void nextGeneration(){
        generation = new Object();
        count = 0;
        condition.signalAll();
    }


    public void await(){

        //集齐parties个就结束等待
        lock.lock();
        try{
            Object myGeneration = generation;
            //判断数量集满
            count++;
            if(count == parties){
                //直接进入下一个generation
                nextGeneration();
                return;
            }

            //如果没有集齐，则线程等待
            for(;;){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(myGeneration != generation){
                    return;
                }
            }

        }finally {
            lock.unlock();
        }

    }



}
