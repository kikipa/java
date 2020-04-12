package threads.juc;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
//    static Semaphore sp = new Semaphore(6);
    static YvesSemaphore sp = new YvesSemaphore(6);
    public static void main(String[] args) {
        for(int i=0;i<1000;i++){
            new Thread(()->{
                try {
                    sp.acquire();
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queryDB("localhost:3306");
                sp.release();
            }).start();
        }
    }

    public static void queryDB(String url){
        System.out.println("query "+ url);
    }
}
