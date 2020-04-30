package threads.juc;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 将大的任务拆分到多个线程 fork
 *
 * 将线程执行结果汇总 join
 *
 * @description:
 * @author: za-hejin
 * @time: 2020/4/14 15:29
 */
public class ForkJoinTest {


    static class Job extends RecursiveTask<String>{
        List<String> urls;
        int start;
        int end;

        public Job(List<String> urls, int start, int end){
            this.urls = urls;
            this.start = start;
            this.end = end;
        }

        @Override
        protected String compute() {
            //指定任务划分的规则
            int count = end - start;

            if(count<=10){
                //执行任务
                String result = "";
                for(int i=start; i<end; i++){
                    String response = doRequest(urls.get(i));
                    result += response;
                }
                return result;
            }else {
                //任务拆分

                //拆分规则
                int x = (start + end) / 2;

                Job job1 = new Job(urls, start, x);
                job1.fork();

                Job job2 = new Job(urls, x, end);
                job2.fork();

                //聚合结果
                String result = "";
                result += job1.join();
                result += job2.join();

                return result;
            }

        }

        private String doRequest(String url) {
            return url;
        }
    }

}
