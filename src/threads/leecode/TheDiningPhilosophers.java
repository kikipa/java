package threads.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/1/23 11:03
 */
public class TheDiningPhilosophers {
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        for(int i=0;i<5;i++){
            DiningPhilosophers diningPhilosophers = new DiningPhilosophers(1);
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        diningPhilosophers.wantsToEat(0,
                                ()-> System.out.print("["+0+"1,1]"),
                                ()->{},
                                ()->{},
                                ()->{},
                                ()->{});
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

class DiningPhilosophers {
    int n;
    List<ReentrantLock> forkLocks;
    List<Condition> leftConditions;
    List<Condition> rightConditions;

    public int getN(){
        return n;
    }

    public DiningPhilosophers(int n) {
        this.n = n;
        forkLocks = new ArrayList<>();
        forkLocks.add(new ReentrantLock());
        forkLocks.add(new ReentrantLock());
        forkLocks.add(new ReentrantLock());
        forkLocks.add(new ReentrantLock());
        forkLocks.add(new ReentrantLock());

        leftConditions = new ArrayList<>();
        rightConditions = new ArrayList<>();
        for(int i=0;i<forkLocks.size();i++){
            leftConditions.add(forkLocks.get(i).newCondition());
            if(i==forkLocks.size()){
                leftConditions.add(forkLocks.get(0).newCondition());
            }else {
                leftConditions.add(forkLocks.get(i+1).newCondition());
            }
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        for(int i=0;i<n;i++){
            boolean eaten = false;
            while (!eaten){
                ReentrantLock leftLock = forkLocks.get(philosopher);
                ReentrantLock rightLock = forkLocks.get(philosopher+1);
                if(leftLock.tryLock()){
                    pickLeftFork.run();
                    eat.run();
                    putLeftFork.run();
                    leftLock.unlock();
                }else if(rightLock.tryLock()){
                    pickRightFork.run();
                    eat.run();
                    putRightFork.run();
                    rightLock.unlock();
                }
            }
        }

    }
}