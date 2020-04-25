package leetcode.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/1/21 10:39
 */
public class BuildingH2O {
    public static void main(String[] args) throws InterruptedException {
        String str = "OOOHHOHOOHHHOHHHHOOHOHOHOOOHHHOHOHOOOOH";
        H2O h2o = new H2O();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

        for(int i=0;i<str.length();i++){
            if('H'==str.charAt(i)){
                new Thread(()->{
                    try{
                        h2o.hydrogen(()->System.out.print("H"));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }).start();
            }
            if('O'==str.charAt(i)){
                new Thread(()->{
                    try{
                        h2o.oxygen(()->System.out.print("O"));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }).start();
            }
        }
    }
}

class H2O {
    private Semaphore hydrogenSemaphore;
    private Semaphore oxygenSemaphore;
    private AtomicInteger barrierCount;

    public H2O() {
        hydrogenSemaphore = new Semaphore(2);
        oxygenSemaphore = new Semaphore(1);
        barrierCount = new AtomicInteger(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire(1);
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        barrierCount.incrementAndGet();
        resetIfNeeded();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSemaphore.acquire(1);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        barrierCount.incrementAndGet();
        resetIfNeeded();
    }

    private void resetIfNeeded() {
        if(barrierCount.compareAndSet(3,0)){
            hydrogenSemaphore.release(2);
            oxygenSemaphore.release(1);
        }
    }
}