package threads.juc.queue;

import java.util.List;
import java.util.Queue;

/**
 * @Author: Jin.HE
 * @Date: 2021/1/5 10:41
 */
public class ConsumerTest implements Runnable{

  private Queue<Integer> queue;
  private List<Integer> data;

  public ConsumerTest(Queue<Integer> queue, List<Integer> data){
    this.queue = queue;
    this.data = data;
  }

  @Override
  public void run() {
    long begin = 0;
    long end;
    for(int i=0; i<data.size(); i++){
      while (queue.poll()==null);
      if(i==0){
        begin = System.nanoTime();
        System.out.println("consumer begin at " + begin);
      }
    }
    end = System.nanoTime();
    System.out.println("consumer end at " + end);

    System.out.println("consumer spends "+ (end-begin));
  }

}
