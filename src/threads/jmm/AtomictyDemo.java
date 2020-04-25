package threads.jmm;

import java.util.concurrent.CountDownLatch;

public class AtomictyDemo {
    private static volatile int count = 0;

    protected static void increase(){
        count++;
    }

    public static void main(String[] args) {
        int threads = 20;
        CountDownLatch cdl = new CountDownLatch(threads);
        for (int i=0; i<threads; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0; i<10000; i++){
                        AtomictyDemo.increase();
                    }
                    cdl.countDown();
                }
            }).start();
        }

        try {
            cdl.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(AtomictyDemo.count);
    }
}
