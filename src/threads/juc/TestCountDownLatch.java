package threads.juc;

import java.util.concurrent.CountDownLatch;


public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        test01();
//        test02();
    }

    public static void test01() throws InterruptedException{
//        CountDownLatch ctl = new CountDownLatch(11);
        YvesCountDownLatch ctl = new YvesCountDownLatch(11);
        for(int i=10; i>=0; i--){
            int n = i;
            new Thread(()->{
                System.out.println(">>>"+n);
                ctl.countDown();
            }).start();
            Thread.sleep(1000L);
        }
        ctl.await();
        System.out.println("Latch...");
    }

    public static void test02() throws InterruptedException{
        CountDownLatch ctl = new CountDownLatch(1);
        for(int i=0; i<6; i++){
            int n = i;
            new Thread(()->{
                try {
                    ctl.await();
                    System.out.println(String.format("Runner %d starts to run", n));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("Ready...?");
        Thread.sleep(3000L);
        ctl.countDown();
        System.out.println("Go!!!");
    }
}
