package threads;

public class Demo3 {
	public static void main(String[] args) throws Exception{
		StopThread sthread = new StopThread();
		sthread.start();
		//休眠1秒，确保i自增成功
		Thread.sleep(1000L);
//		sthread.stop();  //错误的中止方法
		sthread.interrupt();  //正确的中止方法
		while(sthread.isAlive()) {
			//确保线程已经终止
			//输出打印结果
		}
		sthread.print();
	}
}
