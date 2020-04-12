package threads;

public class StopThread extends Thread{
	private int i=0, j =0;
	
	@Override
	public void run() {
		//增加同步锁，确保线程安全
		synchronized(this) {
			++i;
			try {
				//休眠10秒，模拟耗时操作
				Thread.sleep(10000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			++j;
		}
	}
	
	public void print() {
		System.out.println("i=" + i + " j=" + j);
	}
}
