package threads.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/4/14 10:15
 */
public class TestCyclicBarrer {
    public static void main(String[] args) {
        YvesCyclicBarrier barrier = new YvesCyclicBarrier(4);
        for (int i=0; i<100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    barrier.await();
                    System.out.println("上摩天轮...");
                }
            }).start();
            LockSupport.parkNanos(1000*1000*1000L);
        }
    }
}
