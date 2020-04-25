package threads.jmm;


import java.util.concurrent.TimeUnit;

public class VisibilityDemo {
    private static volatile boolean is = true;

    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(VisibilityDemo.is){
//                    synchronized (this){
//                        i++;
//                    }
                    i++;
                }
                System.out.println(i);
            }
        }).start();

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        VisibilityDemo.is = false;
        System.out.println("被设置为false了");
    }
}
