package threads;

import java.util.concurrent.locks.LockSupport;

public class Demo6 {
	private static Object baozidian = null;  
	
	public static void main(String[] args) throws InterruptedException {
		//被弃用的suspend和resume方法正常使用情况
//		suspendResumeTest();
		//suspend和resume在同步代码中产生的死锁
//		suspendResumeSyncDeadLockTest();
		//suspend在resume之后执行，导致了死锁
//		suspendResumeSequnceDeadLockTest();
		//wait/notify机制正常使用
//		waitNotifyTest();
		//notify/notifyAll在wait在之前执行，wait方法无法执行，产生死锁
//		waitNotifySequnceDeadLockTest();
		//park/unpark机制正常使用（对先后调用顺序没有要求）
//		parkUnparkTest();
		//park/unpark在同步代码中产生死锁
		parkUnparkSyncDeadLockTest();
	}
	
	static void suspendResumeTest() throws InterruptedException {
		Thread consumerThread = new Thread(()->{
			//1.消费者线程去买包子
			System.out.println("1.消费者线程去买包子");
			
			//2.没有包子，消费者线程挂起
			if(baozidian==null) {
				System.out.println("2.没有包子，消费者线程挂起");
				Thread.currentThread().suspend();
			}
			
			//5.消费者线程买到包子，执行结束
			System.out.println("5.消费者线程买到包子，执行结束");
		});
		consumerThread.start();
		
		//3.过3秒后生产者线程生产包子
		Thread.sleep(3000L);
		System.out.println("3.过3秒后生产者线程生产包子");
		baozidian = new Object();
		
		//4.生产者线程通知消费者线程生产了包子
		System.out.println("4.生产者线程通知消费者线程生产了包子");
		consumerThread.resume();
	}
	
	static void suspendResumeSyncDeadLockTest() throws InterruptedException {
		Thread consumerThread = new Thread(()->{
			//1.消费者线程去买包子
			System.out.println("1.消费者线程去买包子");
			
			//2.没有包子，消费者线程挂起
			if(baozidian==null) {
				System.out.println("2.没有包子，消费者线程挂起");
				//当前线程拿到锁之后，再调suspend挂起
				synchronized(Demo6.class) {
					Thread.currentThread().suspend();
				}
			}
			
			//5.消费者线程买到包子，执行结束
			System.out.println("5.消费者线程买到包子，执行结束");
		});
		consumerThread.start();
		
		//3.过3秒后生产者线程生产包子
		Thread.sleep(3000L);
		System.out.println("3.过3秒后生产者线程生产包子");
		baozidian = new Object();
		
		//4.生产者线程通知消费者线程生产了包子
		//生产者要拿到锁才能通知消费者线程
		synchronized(Demo6.class) {
			consumerThread.resume();
		}
		System.out.println("4.生产者线程通知消费者线程生产了包子");
	}
	
	static void suspendResumeSequnceDeadLockTest() throws InterruptedException {
		Thread consumerThread = new Thread(()->{
			//1.消费者线程去买包子
			System.out.println("1.消费者线程去买包子");
			
			//2.没有包子，消费者线程挂起
			if(baozidian==null) {
				System.out.println("2.没有包子，消费者线程准备挂起");
				//消费者线程加上延时
				try {
					Thread.sleep(5000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//这里的suspend执行在resume后面
				Thread.currentThread().suspend();
			}
			
			//5.消费者线程买到包子，执行结束
			System.out.println("5.消费者线程买到包子，执行结束");
		});
		consumerThread.start();
		
		//3.过1秒后生产者线程生产包子
		Thread.sleep(1000L);
		System.out.println("3.过1秒后生产者线程生产包子");
		baozidian = new Object();
		
		//4.生产者线程通知消费者线程生产了包子
		consumerThread.resume();  //这里的resume在suspend之前执行了
		System.out.println("4.生产者线程通知消费者线程生产了包子");
		consumerThread.join();
	}
	
	static void waitNotifyTest() throws InterruptedException {
		Thread consumerThread = new Thread(()->{
			//启动线程
			//1.消费者线程去买包子
			System.out.println("1.消费者线程去买包子");
			if(baozidian==null) {
				synchronized(Demo6.class) {
					try {
						System.out.println("2.没有包子，消费者线程进入等待");
						Demo6.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			//5.消费者线程买到包子，执行结束
			System.out.println("5.消费者线程买到包子，执行结束");
		});
		consumerThread.start();
		
		//3.过3秒后生产者线程生产包子
		Thread.sleep(3000L);
		System.out.println("3.过3秒后生产者线程生产包子");
		baozidian = new Object();
		
		//4.生产者线程通知消费者线程生产了包子
		synchronized(Demo6.class) {
			Demo6.class.notifyAll();
			System.out.println("4.生产者线程通知消费者线程生产了包子");
		}
	}
	
	static void waitNotifySequnceDeadLockTest() throws InterruptedException {
		Thread consumerThread = new Thread(()->{
			//启动线程
			//1.消费者线程去买包子
			System.out.println("1.消费者线程去买包子");
			if(baozidian==null) {
				//消费者线程增加延时
				try {
					Thread.sleep(5000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(Demo6.class) {
					try {
						System.out.println("2.没有包子，消费者线程进入等待");
						Demo6.class.wait();  //这里wait方法在notify/notifyAll之后，无法执行，产生死锁
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			//5.消费者线程买到包子，执行结束
			System.out.println("5.消费者线程买到包子，执行结束");
		});
		consumerThread.start();
		
		//3.过1秒后生产者线程生产包子
		Thread.sleep(1000L);
		System.out.println("3.过1秒后生产者线程生产包子");
		baozidian = new Object();
		
		//4.生产者线程通知消费者线程生产了包子
		synchronized(Demo6.class) {
			Demo6.class.notifyAll();  //这里notifyAll在wait方法之前执行了
			System.out.println("4.生产者线程通知消费者线程生产了包子");
		}
	}
	
	static void parkUnparkTest() throws InterruptedException {
		Thread consumerThread = new Thread(()->{
			//启动线程
			//1.消费者线程去买包子
			System.out.println("1.消费者线程去买包子");
			if(baozidian==null) {
				//给消费者线程增加延时
				try {
					Thread.sleep(5000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("2.没有包子，消费者线程准备挂起");
				LockSupport.park();
			}
			//5.消费者线程买到包子，执行结束
			System.out.println("5.消费者线程买到包子，执行结束");
		});
		consumerThread.start();
		
		//3.过1秒后生产者线程生产包子
		Thread.sleep(1000L);
		System.out.println("3.过1秒后生产者线程生产包子");
		baozidian = new Object();
		
		//4.生产者线程通知消费者线程生产了包子
		LockSupport.unpark(consumerThread);
		System.out.println("4.生产者线程通知消费者线程生产了包子");
	}
	
	static void parkUnparkSyncDeadLockTest() throws InterruptedException {
		Thread consumerThread = new Thread(()->{
			//启动线程
			//1.消费者线程去买包子
			System.out.println("1.消费者线程去买包子");
			if(baozidian==null) {
				//消费者线程抢到对象锁
				synchronized(Demo6.class) {
					System.out.println("2.没有包子，消费者线程准备挂起");
					LockSupport.park();  //park方法不会释放对象锁
				}
			}
			//5.消费者线程买到包子，执行结束
			System.out.println("5.消费者线程买到包子，执行结束");
		});
		consumerThread.start();
		
		//3.过3秒后生产者线程生产包子
		Thread.sleep(3000L);
		System.out.println("3.过3秒后生产者线程生产包子");
		baozidian = new Object();
		
		//4.生产者线程通知消费者线程生产了包子
		//争夺对象锁，但是已经被消费者线程抢到，产生死锁
		synchronized(Demo6.class) {
			LockSupport.unpark(consumerThread);
		}
		System.out.println("4.生产者线程通知消费者线程生产了包子");
	}
}
