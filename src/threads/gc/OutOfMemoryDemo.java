package threads.gc;

import pattern.state.demo.ThreadContext;

import java.util.ArrayList;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/9 20:55
 *
 * 资源占用过多或资源未释放 内存溢出
 * -Xmx512m -server -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\tmp\dump1.hprof
 */
public class OutOfMemoryDemo {
    static ArrayList<Object> space = new ArrayList<Object>();
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<1000;i++){
            space.add(new byte[1024*1024*64]);
            Thread.sleep(500L);
        }
    }
}
