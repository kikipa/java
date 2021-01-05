package threads.juc.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: Jin.HE
 * @Date: 2021/1/5 10:40
 */
public class QueueTest {

  private static Queue<Integer> queue0 = new LinkedBlockingQueue();
  private static Queue<Integer> queue1 = new LinkedBlockingQueue(10000000);
  private static Queue<Integer> queue2 = new LinkedBlockingDeque<>();
  private static Queue<Integer> queue3 = new LinkedBlockingDeque<>(10000000);
  private static Queue<Integer> queue4 = new ConcurrentLinkedQueue<>();

  public static void main(String[] args) {
    List<Integer> data = new ArrayList<>(10000000);
    for(int i=0; i<10000000; i++){
      data.add(i);
    }
//    test0(data);
//    test1(data);
//    test2(data);
//    test3(data);
    test4(data);
  }

  private static void test0(List<Integer> data){
    Thread producer = new Thread(new ProducerTest(queue0, data));
    Thread consumer =  new Thread(new ConsumerTest(queue0, data));
    producer.start();
    consumer.start();
  }

  private static void test1(List<Integer> data){
    Thread producer = new Thread(new ProducerTest(queue1, data));
    Thread consumer =  new Thread(new ConsumerTest(queue1, data));
    producer.start();
    consumer.start();
  }

  private static void test2(List<Integer> data){
    Thread producer = new Thread(new ProducerTest(queue2, data));
    Thread consumer =  new Thread(new ConsumerTest(queue2, data));
    producer.start();
    consumer.start();
  }

  private static void test3(List<Integer> data){
    Thread producer = new Thread(new ProducerTest(queue3, data));
    Thread consumer =  new Thread(new ConsumerTest(queue3, data));
    producer.start();
    consumer.start();
  }

  private static void test4(List<Integer> data){
    Thread producer = new Thread(new ProducerTest(queue4, data));
    Thread consumer =  new Thread(new ConsumerTest(queue4, data));
    producer.start();
    consumer.start();
  }
}
