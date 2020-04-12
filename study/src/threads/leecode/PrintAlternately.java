package threads.leecode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class PrintAlternately {
	
	FooBar_Semaphore getFooBar_Semaphore() {
		return new FooBar_Semaphore(10);
	}

	class FooBar_Semaphore {
	    private int n;

	    private Semaphore fooSem = new Semaphore(1);
		private Semaphore barSem = new Semaphore(0);
	    
	    public FooBar_Semaphore(int n) {
	        this.n = n;
	    }

	    public void foo(Runnable printFoo) throws InterruptedException { 
	        for (int i = 0; i < n; i++) {
	            fooSem.acquire();
	            // printFoo.run() outputs "foo". Do not change or remove this line.
	    	    printFoo.run();
	            barSem.release();
	        }
	    }

	    public void bar(Runnable printBar) throws InterruptedException {
	        for (int i = 0; i < n; i++) {
	            barSem.acquire();
	            // printBar.run() outputs "bar". Do not change or remove this line.
	    	    printBar.run();
	            fooSem.release();
	        }
	    }
	}
	
	
	class FooBar_FairLock {
	    private int n;

	    private volatile boolean flag = false;
	    private Lock lock = new ReentrantLock(true);
	     
	    public FooBar_FairLock(int n) {
	        this.n = n;
	    }

	    public void foo(Runnable printFoo) throws InterruptedException { 
	        for (int i = 0; i < n;) {
	        	lock.lock();
	        	try {
	        		if(!flag) {
		        		// printFoo.run() outputs "foo". Do not change or remove this line.
			    	    printFoo.run();
			    	    i++;
			    	    flag = true;
		        	}
	        	}finally {
	        		lock.unlock();
	        	}
	        }
	    }

	    public void bar(Runnable printBar) throws InterruptedException {
	        for (int i = 0; i < n;) {
	            lock.lock();
	            try {
		            if(flag) {
			            // printBar.run() outputs "bar". Do not change or remove this line.
			    	    printBar.run();
			    	    i++;
			    	    flag = false;
		            }
	            }finally {
	            	lock.unlock();
	            }
	        }
	    }
	}
	
	
	class FooBar_NoLock {
	    private int n;

	    private volatile boolean flag = false;
	    
	    public FooBar_NoLock(int n) {
	        this.n = n;
	    }

	    public void foo(Runnable printFoo) throws InterruptedException { 
	        for (int i = 0; i < n;) {
	        	if(!flag) {
	        		// printFoo.run() outputs "foo". Do not change or remove this line.
		    	    printFoo.run();
		    	    i++;
		    	    flag = true;
	        	}
	        }
	    }

	    public void bar(Runnable printBar) throws InterruptedException {
	        for (int i = 0; i < n;) {
	        	if(flag) {
		            // printBar.run() outputs "bar". Do not change or remove this line.
		    	    printBar.run();
		    	    i++;
		    	    flag = false;
	            }
	        }
	    }
	}
	
	class FooBar_Synchronized {
	    private int n;

	    private boolean flag = false;
	    
	    public FooBar_Synchronized(int n) {
	        this.n = n;
	    }

	    public void foo(Runnable printFoo) throws InterruptedException { 
	        for (int i = 0; i < n;) {
	        	if(!flag) {
	        		// printFoo.run() outputs "foo". Do not change or remove this line.
		    	    printFoo.run();
		    	    i++;
		    	    flag = true;
	        	}
	        }
	    }

	    public void bar(Runnable printBar) throws InterruptedException {
	        for (int i = 0; i < n;) {
	        	if(flag) {
		            // printBar.run() outputs "bar". Do not change or remove this line.
		    	    printBar.run();
		    	    i++;
		    	    flag = false;
	            }
	        }
	    }
	}
	
	

	public static void main(String[] args) throws InterruptedException {
		
	}
}
