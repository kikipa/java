package threads.leecode;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

/**
 * @description:
 * @author: za-hejin
 * @time: 2020/1/21 14:37
 */
public class FizzBuzzMultiThreaded {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(10000);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8,8,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        for(int i=0;i<10000;i++){
            if(i%3==0&&i%5==0){

            }else if(i%3==0){

            }else if(i%5==0){

            }else {

            }

        }
    }
}

class FizzBuzz {
    private int n;


    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

    }
}