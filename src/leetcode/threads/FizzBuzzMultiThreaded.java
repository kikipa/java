package leetcode.threads;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/1/21 14:37
 */
public class FizzBuzzMultiThreaded {
    public static void main(String[] args) throws InterruptedException {
//        FizzBuzz fizzBuzz = new FizzBuzz(10000);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,4,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        FizzBuzz fizzBuzz = new FizzBuzz(8);
        executor.submit(()->{
            try {
                fizzBuzz.fizz(()-> System.out.print("fizz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.submit(()->{
            try {
                fizzBuzz.buzz(()-> System.out.print("buzz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.submit(()->{
            try {
                fizzBuzz.fizzbuzz(()-> System.out.print("fizzbuzz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.submit(()->{
            try {
                fizzBuzz.number(s -> System.out.print(s+", "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}

/**
 * CAS自旋解法 by hejin
 * */
class FizzBuzz {
    private int n;
    private AtomicInteger next;

    FizzBuzz(int n) {
        this.n = n;
        next = new AtomicInteger(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(;;){
            int value = next.get();
            if(value>n){
                Thread.currentThread().interrupt();
                return;
            }
            if(value%3==0&&value%5!=0){
                printFizz.run();
                while(!next.compareAndSet(value,value+1)){
                    //CAS自旋很消耗CPU资源，这种实现方式不是最优解
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(;;){
            int value = next.get();
            if(value>n){
                Thread.currentThread().interrupt();
                return;
            }
            if(value%5==0&&value%3!=0){
                printBuzz.run();
                while(!next.compareAndSet(value,value+1)){
                    //CAS自旋很消耗CPU资源，这种实现方式不是最优解
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(;;){
            int value = next.get();
            if(value>n){
                Thread.currentThread().interrupt();
                return;
            }
            if(value%3==0&&value%5==0){
                printFizzBuzz.run();
                while(!next.compareAndSet(value,value+1)){
                    //CAS自旋很消耗CPU资源，这种实现方式不是最优解
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(;;){
            int value = next.get();
            if(value>n){
                Thread.currentThread().interrupt();
                return;
            }
            if(value%3!=0&&value%5!=0){
                printNumber.accept(value);
                while(!next.compareAndSet(value,value+1)){
                    //CAS自旋很消耗CPU资源，这种实现方式不是最优解
                }
            }
        }
    }
}