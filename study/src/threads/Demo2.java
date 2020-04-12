package threads;

public class Demo2 {
	public static Thread thread1;
	public static Demo2 obj;
	
	public static void main(String[] arsg) throws Exception{
		System.out.println("###第一种状态切换：NEW -> RUNNABLE -> TERMINATED ###");
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread1 当前状态："+Thread.currentThread().getState().toString());
				System.out.println("thread1 执行了");
			}
		});
		System.out.println("没调用start方法,thread1的当前状态："+thread1.getState().toString());
		thread1.start();
		//主线程等待2秒，等待thread1线程执行结束
		Thread.sleep(2000L);
		System.out.println("主线程等待2秒后,thread1的当前状态："+thread1.getState().toString());
		//TODO 注意：线程终止之后，再调用start方法，会抛出IllegalThreadStateException异常
//		thread1.start();
		
		System.out.println();
		System.out.println("###第二种状态切换：NEW -> RUNNABLE -> WAITING -> RUNNABLE -> TERMINATED (sleep方式) ###");
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//将线程2移动到等待状态，1500毫秒后自动唤醒
					Thread.sleep(1500L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread2 当前状态："+Thread.currentThread().getState().toString());
				System.out.println("thread2 执行了");
			}
		});
		System.out.println("没调用start方法,thread2的当前状态："+thread2.getState().toString());
		thread2.start();
		System.out.println("调用start方法，thread2当前状态：" + thread2.getState().toString());
		Thread.sleep(200L); // 等待200毫秒，再看状态
		System.out.println("等待200毫秒，再看thread2当前状态：" + thread2.getState().toString());
		Thread.sleep(3000L); // 再等待3秒，让thread2执行完毕，再看状态
		System.out.println("等待3秒，再看thread2当前状态：" + thread2.getState().toString());
		
		System.out.println();
		System.out.println("###第三种状态切换：NEW -> RUNNABLE -> WAITING -> RUNNABLE -> TERMINATED (wait方式) ###");
		obj = new Demo2();
		Thread thread3 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(obj) {
					//将线程3移动到等待状态，等待其他线程唤醒
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("thread3 当前状态："+Thread.currentThread().getState().toString());
				System.out.println("thread3 执行了");
			}
		});
		System.out.println("没调用start方法,thread3的当前状态："+thread3.getState().toString());
		thread3.start();
		System.out.println("调用start方法,thread3的当前状态："+thread3.getState().toString());
		Thread.sleep(2000L);
		System.out.println("主线程等2秒后,thread3的当前状态："+thread3.getState().toString());
		Thread notifyThread = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(obj) {
					//唤醒等待的线程
					obj.notify();
				}
			}
		});
		notifyThread.start();
		
		System.out.println();
		System.out.println("###第四种：新建 -> 运行 -> 阻塞 -> 运行 -> 终止 ###");
		Thread thread4 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(Demo2.class) {
					System.out.println("thread4 当前状态："+Thread.currentThread().getState().toString());
					System.out.println("thread4 执行了");
				}
			}
		});
		synchronized(Demo2.class) {
			System.out.println("没调用start方法,thread4的当前状态："+thread4.getState().toString());
			thread4.start();
			System.out.println("调用start方法,thread4的当前状态："+thread4.getState().toString());
			Thread.sleep(200L);
			System.out.println("主线程等200毫秒后,thread4的当前状态："+thread4.getState().toString());
		}
		Thread.sleep(3000L);
		System.out.println("主线程等3秒后,thread4的当前状态："+thread4.getState().toString());
	}
}
