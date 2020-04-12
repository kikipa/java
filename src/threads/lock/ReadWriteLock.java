package threads.lock;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ReadWriteLock {
	
	private AtomicInteger writeCount = new AtomicInteger(0);
	private AtomicInteger readCount = new AtomicInteger(0);
	AtomicReference<Thread> owner = null;
	
	Stack<Thread> waiters = new Stack<Thread>();
	
	public void readLock() {
		boolean lockOper = tryReadLock();
		if(!lockOper) {
			//抢锁或重入锁失败
			waiters.add(Thread.currentThread());
		}
	}
	
	public boolean tryReadLock() {
		int w = writeCount.get();
		if(w==0) {
			//写锁没有被占用 则可以尝试抢占用读锁
			int r = readCount.get();
			if(r==0) {
				//读锁未被占用
				//CAS 抢锁
				readCount.compareAndSet(0, 1);
				return true;
			}else {
				//读锁已被占用 当前线程也能拿到读锁 读锁是共享的
				readCount.compareAndSet(r, r+1);
				return true;
			}
		}else {
			//写锁被占用 读锁抢占失败
			return false;
		}
	}
	
	public void readUnlock() {
		boolean unlockOper = tryReadUnlock();
		if(unlockOper) {
			//读锁解锁成功
			//通知锁池的一个线程
			
		}
	}
	
	public boolean tryReadUnlock() {
		int r = readCount.getAndDecrement();
		if(r==0) {
			//读锁解锁成功
			return true;
		}else {
			return false;
		}
	}
	
	public void writeLock() {
		
		
	}
	
	public boolean tryWriteLock() {
		
		
		return false;
	}
	
	public void writeUnlock() {
		
	}
	
	public boolean tryWriteUnlock() {
		
		return false;
	}

}
