package threads.juc.queue;

import java.util.List;
import java.util.Queue;

/**
 * @Author: Jin.HE
 * @Date: 2021/1/5 10:41
 */
public class ProducerTest implements Runnable{

  private Queue<Integer> queue;
  private List<Integer> data;

  public ProducerTest(Queue<Integer> queue, List<Integer> data){
    this.queue = queue;
    this.data = data;
  }

  @Override
  public void run() {
    long begin;
    long end;
    begin = System.nanoTime();
    System.out.println("producer begin at " + begin);
    for(int i=0; i<data.size(); i++){
      while(!queue.offer(data.get(i)));
    }
    end = System.nanoTime();
    System.out.println("producer end at " + end);

    System.out.println("producer spends "+ (end-begin));
  }
}
