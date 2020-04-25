package threads.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/4/14 10:33
 */
public class CallableTest {

    public static void main(String[] args) {
        //每个线程都会执行一次Runnable任务
//        RunnableTask task = new RunnableTask();
        //传递给FutureTask的Callable任务只会执行一次
//        FutureTask task = new FutureTask(new CallableTask());
        YvesFutureTask task = new YvesFutureTask(new CallableTask());

        Thread th = new Thread(task);
        th.start();

        Thread th1 = new Thread(task);
        th1.start();


        System.out.println("begin to get");
        Object result  = task.get();
        System.out.println("got it");
        System.out.println(result);


    }

    static class CallableTask implements Callable {

        @Override
        public Object call() throws Exception {
            Thread.sleep(5000);
            System.out.println("11111");
            return "Yves";
        }
    }

    static class RunnableTask implements Runnable{

        @Override
        public void run() {
            System.out.println("Yves");
        }
    }

}
