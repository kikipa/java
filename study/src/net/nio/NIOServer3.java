package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/1/14 15:41
 */
public class NIOServer3 {
    /**
     * work thread pool to handle business logic
     * */
    private static ExecutorService workPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    /**
     * encapsulate selector.select() and event loop
     * */
    abstract class ReactorThread extends Thread{
        Selector selector;
        LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        /**
         * If Selector listened any event, call this method
         * */
        public abstract  void handler(SelectableChannel channel) throws Exception;

        private ReactorThread() throws IOException{
            selector = Selector.open();
        }

        volatile boolean running = false;

        @Override
        public void run(){
            //roll polling Selector event
            while(running){
                try{
                    //execute task in queue
                    Runnable task;
                    while ((task=taskQueue.poll())!=null){
                        task.run();
                    }
                    selector.select(1000);

                    //get select result
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    //loop the Selector event
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        int readyOps = key.readyOps();

                        //concern Accept and Read events
                        if((readyOps&(SelectionKey.OP_ACCEPT|SelectionKey.OP_READ))!=0 || readyOps ==0){
                            try{
                                ServerSocketChannel channel = (ServerSocketChannel)key.attachment();
                                channel.configureBlocking(false);
                                handler(channel);
                                if(!channel.isOpen()){
                                    //if channel is closed, cancel subscription of this KEY
                                    key.cancel();
                                }
                            }catch (Exception ex){
                                //if exception occurs, cancel subscription of this KEY
                                key.cancel();
                            }
                        }
                    }
                    //Selects a set of keys whose corresponding channels are ready for I/O operations
                    selector.selectNow();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }

        private SelectionKey register(SelectableChannel channel) throws Exception{
            // 为什么register要以任务提交的形式，让reactor线程去处理？
            // 因为线程在执行channel注册到selector的过程中，会和调用selector.select()方法的线程争用同一把锁
            // 而select()方法实在eventLoop中通过while循环调用的，争抢的可能性很高，为了让register能更快的执行，就放到同一个线程来处理
            FutureTask<SelectionKey> futureTask = new FutureTask<>(()->channel.register(selector,0,channel));
            taskQueue.add(futureTask);
            return futureTask.get();
        }

        private void doStart(){
            if(!running){
                running = true;
                start();
            }
        }
    }

    private ServerSocketChannel serverSocketChannel;

    /**
     * create multiple threads: handle accept event
     * */
    private ReactorThread[] mainReactorThreads = new ReactorThread[1];
    /**
     * create multiple threads: handle IO event
     * */
    private ReactorThread[] subReactorThreads = new ReactorThread[8];

    /**
     * initialize threads group
     * */
    private void newGroup() throws IOException{

    }

}
