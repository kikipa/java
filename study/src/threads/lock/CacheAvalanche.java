package threads.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheAvalanche {
    static volatile boolean cacheValid = false;
    static final ReadWriteLock rwl = new ReentrantReadWriteLock();

    static void processCacheData(String dataKey){
        rwl.readLock().lock();  //加读锁防止读的时候数据被写入
        try{
            if(cacheValid){
                System.out.println("read from cache");
            }else {
                //缓存雪崩情况
                rwl.readLock().unlock();  //释放读锁，才能让某个线程拿到写锁
                rwl.writeLock().lock();
                try {
                    if(!cacheValid){  //双重检查，防止前面阻塞的线程拥入这里访问DB
                        System.out.println("read from db");
                        System.out.println("update data to cache");
                        cacheValid = true;  //更新缓存，其他线程可见
                    }
                } finally {
                    rwl.readLock().lock();  //锁降级（拿到写锁的线程可以再拿到读锁，其他线程不能拿读锁）;与外层finally读锁释放成对出现
                    rwl.writeLock().unlock();
                }
            }
            //使用数据（当前线程保持着读锁）
        }finally {
            rwl.readLock().unlock();  //else代码块读锁释放了，这里就没有对应加读锁的地方了。
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            new Thread(()->{
                CacheAvalanche.processCacheData("key");
            }).start();
        }
    }
}
