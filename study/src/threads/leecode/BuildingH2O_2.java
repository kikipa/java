package threads.leecode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/1/21 11:57
 */
public class BuildingH2O_2 {
    public static void main(String[] args) {
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

class H2O_2 {
    private Semaphore s1 ,s2,c1,c2;

    public H2O_2() {
        s1 = new Semaphore(2);  // H线程信号量
        s2 = new Semaphore(1);  // O线程信号量
        c1 = new Semaphore(0);  // H反应条件信号量
        c2 = new Semaphore(0);  // O反应条件信号量
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        s1.acquire(1);  // 保证只有2个H线程进入执行
        c1.release(1);  // 释放H原子到达信号
        c2.acquire(1);  // 等待O原子到达
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        s1.release(1);  // 相当于唤醒1个H线程
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        s2.acquire(1);  // 保证只有1个O线程进入执行
        c2.release(2);  // 释放O原子到达信号，因为有2个H线程等待所以释放2个
        c1.acquire(2);  // 等待H原子到达，2个原因同上
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        s2.release(1);  // 相当于唤醒1个O线程
    }
}