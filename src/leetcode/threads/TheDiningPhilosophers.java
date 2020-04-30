package leetcode.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）
 *
 * 所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，而同一根叉子在同一时间只能被一个哲学家使用。
 * 每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。
 *
 * 假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。
 *
 * 设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。
 *
 * 问题描述和图片来自维基百科 wikipedia.org
 *
 * 哲学家从 0 到 4 按 顺时针 编号。请实现函数 void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork)：
 *
 * philosopher 哲学家的编号。
 * pickLeftFork 和 pickRightFork 表示拿起左边或右边的叉子。
 * eat 表示吃面。
 * putLeftFork 和 putRightFork 表示放下左边或右边的叉子。
 * 由于哲学家不是在吃面就是在想着啥时候吃面，所以思考这个方法没有对应的回调。
 * 给你 5 个线程，每个都代表一个哲学家，请你使用类的同一个对象来模拟这个过程。在最后一次调用结束之前，可能会为同一个哲学家多次调用该函数。
 *
 * 示例：
 *
 * 输入：n = 1
 * 输出：[[4,2,1],[4,1,1],[0,1,1],[2,2,1],[2,1,1],[2,0,3],[2,1,2],[2,2,2],[4,0,3],[4,1,2],[0,2,1],[4,2,2],[3,2,1],[3,1,1],[0,0,3],[0,1,2],[0,2,2],[1,2,1],[1,1,1],[3,0,3],[3,1,2],[3,2,2],[1,0,3],[1,1,2],[1,2,2]]
 * 解释:
 * n 表示每个哲学家需要进餐的次数。
 * 输出数组描述了叉子的控制和进餐的调用，它的格式如下：
 * output[i] = [a, b, c] (3个整数)
 * - a 哲学家编号。
 * - b 指定叉子：{1 : 左边, 2 : 右边}.
 * - c 指定行为：{1 : 拿起, 2 : 放下, 3 : 吃面}。
 * 如 [4,2,1] 表示 4 号哲学家拿起了右边的叉子。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 60
 *
 * @description:
 * @author: za-hejin
 * @time: 2020/1/23 11:03
 */
public class TheDiningPhilosophers {
    public static void main(String[] args) {
        DiningPhilosopher diningPhilosopher = new DiningPhilosopher();

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,10, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
//        for(int i=0;i<5;i++){
//            int philosopher = i;
//            executor.submit(new Runnable() {
//                @Override
//                public void run() {
//                    diningPhilosopher.wantsToEat(philosopher,
//                            ()-> System.out.print("["+philosopher+",1,1]"),
//                            ()->System.out.print("["+philosopher+",2,1]"),
//                            ()->System.out.print("["+philosopher+",0,3]"),
//                            ()->System.out.print("["+philosopher+",1,2]"),
//                            ()->System.out.print("["+philosopher+",2,2]"));
//                }
//            });
//        }

        //模拟参数输入
        args = new String[1];
        args[0] = "30";


        for(int j=0; j<5; j++){
            int philosopher = j;
            int n = Integer.parseInt(args[0]);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0; i<n; i++){
                        diningPhilosopher.wantsToEat(philosopher,
                                ()-> System.out.print("["+philosopher+",1,1]"),
                                ()->System.out.print("["+philosopher+",2,1]"),
                                ()->System.out.print("["+philosopher+",0,3]"),
                                ()->System.out.print("["+philosopher+",1,2]"),
                                ()->System.out.print("["+philosopher+",2,2]"));
                    }
                }
            }).start();
        }

    }
}


class DiningPhilosopher{

    /**
     * 5个叉子的锁，索引从0-4，
     * 约定对应哲学家编号0-4的索引的叉子为该哲学家左手边的叉子
     * */
    Lock[] forkLocks = new Lock[5];

//    Semaphore[] forkLocks = new Semaphore[5];

    DiningPhilosopher(){
        for(int i = 0; i< 5; i++){
//            forkLocks[i] = new Semaphore(1);
            forkLocks[i] = new ReentrantLock();
        }
    }


    void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putLeftFork, Runnable putRightFork){
        for(;;){
            //尝试拿起左手边叉子
            boolean leftPicked = forkLocks[philosopher].tryLock();
            //尝试拿起右手边叉子
            boolean rightPicked = forkLocks[philosopher+1==5?0:philosopher+1].tryLock();
            if(leftPicked&&rightPicked){
                pickLeftFork.run();
                pickRightFork.run();
                break;
            }else {
                if(leftPicked){
                    //没拿到右手边叉子，则放下拿到的左手边叉子
                    forkLocks[philosopher].unlock();
                }
                if(rightPicked){
                    //没拿到左手边叉子，则放下拿到的右手边叉子
                    forkLocks[philosopher+1==5?0:philosopher+1].unlock();
                }
            }
        }

        //吃面
        eat.run();

        //放下左手边叉子
        forkLocks[philosopher].unlock();
        putLeftFork.run();

        //放下右手边叉子
        forkLocks[philosopher+1==5?0:philosopher+1].unlock();
        putRightFork.run();
    }


//    void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putLeftFork, Runnable putRightFork){
//        while(times[philosopher]>0){
//            //尝试拿起左手边叉子
//            boolean leftPicked = forkLocks[philosopher].tryAcquire(1);
//            //尝试拿起右手边叉子
//            boolean rightPicked = forkLocks[philosopher+1==5?0:philosopher+1].tryAcquire(1);
//            if(leftPicked&&rightPicked){
//                pickLeftFork.run();
//                pickRightFork.run();
//            }else {
//                if(leftPicked){
//                    //没拿到右手边叉子，则放下拿到的左手边叉子
//                    forkLocks[philosopher].release();
//                }
//                if(rightPicked){
//                    //没拿到左手边叉子，则放下拿到的右手边叉子
//                    forkLocks[philosopher+1==5?0:philosopher+1].release();
//                }
//                continue;
//            }
//
//            //吃面
//            eat.run();
//            //该哲学家剩余吃面次数减1
//            times[philosopher]--;
//
//            //放下左手边叉子
//            forkLocks[philosopher].release();
//            putLeftFork.run();
//
//            //放下右手边叉子
//            forkLocks[philosopher+1==5?0:philosopher+1].release();
//            putRightFork.run();
//        }
//    }


//    void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putLeftFork, Runnable putRightFork){
//        while(n>0){
//            //拿起左手边叉子，拿不到叉子则一直等待
//            forkLocks[philosopher].lock();
//            pickLeftFork.run();
//
//            //拿起右手边叉子，拿不到叉子则一直等待，且不会释放已拿到的左手的叉子(会产生死锁)
//            forkLocks[philosopher+1==5?0:philosopher+1].lock();
//            pickRightFork.run();
//
//            //吃面
//            eat.run();
//            //剩余吃面次数减1
//            n--;
//
//            //放下左手边叉子
//            forkLocks[philosopher].unlock();
//            putLeftFork.run();
//
//            //放下右手边叉子
//            forkLocks[philosopher+1==5?0:philosopher+1].unlock();
//            putRightFork.run();
//        }
//    }

}



//class DiningPhilosophers {
//    int n;
//    List<ReentrantLock> forkLocks;
//    List<Condition> leftConditions;
//    List<Condition> rightConditions;
//
//    public int getN(){
//        return n;
//    }
//
//    public DiningPhilosophers(int n) {
//        this.n = n;
//        forkLocks = new ArrayList<>();
//        forkLocks.add(new ReentrantLock());
//        forkLocks.add(new ReentrantLock());
//        forkLocks.add(new ReentrantLock());
//        forkLocks.add(new ReentrantLock());
//        forkLocks.add(new ReentrantLock());
//
//        leftConditions = new ArrayList<>();
//        rightConditions = new ArrayList<>();
//        for(int i=0;i<forkLocks.size();i++){
//            leftConditions.add(forkLocks.get(i).newCondition());
//            if(i==forkLocks.size()){
//                leftConditions.add(forkLocks.get(0).newCondition());
//            }else {
//                leftConditions.add(forkLocks.get(i+1).newCondition());
//            }
//        }
//    }
//
//    // call the run() method of any runnable to execute its code
//    public void wantsToEat(int philosopher,
//                           Runnable pickLeftFork,
//                           Runnable pickRightFork,
//                           Runnable eat,
//                           Runnable putLeftFork,
//                           Runnable putRightFork) throws InterruptedException {
//        for(int i=0;i<n;i++){
//            boolean eaten = false;
//            while (!eaten){
//                ReentrantLock leftLock = forkLocks.get(philosopher);
//                ReentrantLock rightLock = forkLocks.get(philosopher+1);
//                if(leftLock.tryLock()){
//                    pickLeftFork.run();
//                    eat.run();
//                    putLeftFork.run();
//                    leftLock.unlock();
//                }else if(rightLock.tryLock()){
//                    pickRightFork.run();
//                    eat.run();
//                    putRightFork.run();
//                    rightLock.unlock();
//                }
//            }
//        }
//
//    }
//}