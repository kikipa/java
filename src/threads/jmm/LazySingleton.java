package threads.jmm;

public class LazySingleton {
    private static volatile LazySingleton instance;

    private LazySingleton(){}

    public static LazySingleton getInstance(){
        //第一次检查
        if(instance==null){
            //多线程并发情况下，需要用synchronized关键字加锁，保证只有获取锁的线程才进入同步块
            synchronized (LazySingleton.class){
                //第二次检查，目的是防止有多个进入同步块的线程都创建对象
                if(instance==null){
                    //volatile关键字使得instance修改之后，其他线程立马能知道这个修改，这里配合第一次检查防止后续线程抢锁进入同步块
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
