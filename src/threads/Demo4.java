package threads;

public class Demo4 {
	private volatile static boolean flag = true;
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(()->{
			while(flag) {//判断标志位关联的线程是否还在运行
				try {
					System.out.println("运行中");
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		//3秒后，另一个线程将标志位改为false，表示不继续运行
		Thread.sleep(3000L);
		flag = false;
		System.out.println("程序运行结束");
	}
}
