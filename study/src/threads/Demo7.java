package threads;

public class Demo7 {
	/**每个线程都有一个ThreadLocal变量的副本，互不影响*/
	private ThreadLocal<String> value = new ThreadLocal<String>();

	public void threadLocalTest() throws InterruptedException {
		value.set("这是主线程设置的123");  //主线程设置value的值
		String v = value.get();
		System.out.println("线程1执行前，主线程获取的value的值为："+ v);
		new Thread(new Runnable() {
			@Override
			public void run() {
				String v = value.get();
				System.out.println("线程1获取的value的值为："+ v);
				
				value.set("这是线程1设置的456");
				v = value.get();
				System.out.println("线程1设置的value的值为："+ v);
				System.out.println("线程1执行结束");
			}
		}).start();
		
		//主线程等待3秒，等所有线程执行完
		Thread.sleep(3000L);
		
		v = value.get();
		System.out.println("在线程1执行完后，主线程获取的value的值为："+ v);
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Demo7().threadLocalTest();
	}
}
