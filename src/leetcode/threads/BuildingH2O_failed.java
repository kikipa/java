package leetcode.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/1/20 9:52
 */
public class BuildingH2O_failed {
    public static void main(String[] args) throws InterruptedException {
        String input = "OOHHHH";
        H2O h2o = new H2O();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(5,10,600, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='O'){
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            h2o.oxygen(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.print("O");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            if(input.charAt(i)=='H'){
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            h2o.hydrogen(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.print("H");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}

//class H2O {
//    Semaphore hydrogenSemaphore = new Semaphore(2);
//    Semaphore oxygenSemaphore = new Semaphore(1);
//    public H2O() {
//
//    }
//
//    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
//
//        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
//        releaseHydrogen.run();
//    }
//
//    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
//
//        // releaseOxygen.run() outputs "O". Do not change or remove this line.
//        releaseOxygen.run();
//    }
//}

class H2O_failed{
    class Barrier{
        CountDownLatch hydrogenCount;
        CountDownLatch oxygenCount;
        //barriers
        Barrier(){
            hydrogenCount = new CountDownLatch(2);
            oxygenCount = new CountDownLatch(1);
        }
    }
    volatile Barrier h2oBarrier;

    public H2O_failed(){
        h2oBarrier = new Barrier();
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException{
        h2oBarrier.oxygenCount.await();
        if(h2oBarrier.hydrogenCount.getCount()>0){
            h2oBarrier.hydrogenCount.countDown();
            releaseHydrogen.run();
        }
        resetIfNeeded();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException{
        h2oBarrier.hydrogenCount.await();
        if(h2oBarrier.oxygenCount.getCount()>0){
            h2oBarrier.oxygenCount.countDown();
            releaseOxygen.run();
        }
        resetIfNeeded();
    }

    private void resetIfNeeded(){
        if(h2oBarrier.hydrogenCount.getCount()==0&&h2oBarrier.oxygenCount.getCount()==0){
            h2oBarrier = new Barrier();
        }
    }
}
