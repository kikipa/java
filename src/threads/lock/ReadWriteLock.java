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
			//������������ʧ��
			waiters.add(Thread.currentThread());
		}
	}
	
	public boolean tryReadLock() {
		int w = writeCount.get();
		if(w==0) {
			//д��û�б�ռ�� ����Գ�����ռ�ö���
			int r = readCount.get();
			if(r==0) {
				//����δ��ռ��
				//CAS ����
				readCount.compareAndSet(0, 1);
				return true;
			}else {
				//�����ѱ�ռ�� ��ǰ�߳�Ҳ���õ����� �����ǹ����
				readCount.compareAndSet(r, r+1);
				return true;
			}
		}else {
			//д����ռ�� ������ռʧ��
			return false;
		}
	}
	
	public void readUnlock() {
		boolean unlockOper = tryReadUnlock();
		if(unlockOper) {
			//���������ɹ�
			//֪ͨ���ص�һ���߳�
			
		}
	}
	
	public boolean tryReadUnlock() {
		int r = readCount.getAndDecrement();
		if(r==0) {
			//���������ɹ�
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
