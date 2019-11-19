package threads.leecode;

/**
 * 我们提供了一个类：

public class Foo {
  public void one() { print("one"); }
  public void two() { print("two"); }
  public void three() { print("three"); }
}
三个不同的线程将会共用一个 Foo 实例。

线程 A 将会调用 one() 方法
线程 B 将会调用 two() 方法
线程 C 将会调用 three() 方法
请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。

 

示例 1:

输入: [1,2,3]
输出: "onetwothree"
解释: 
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
正确的输出是 "onetwothree"。
示例 2:

输入: [1,3,2]
输出: "onetwothree"
解释: 
输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
正确的输出是 "onetwothree"。
 

注意:

尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。

你看到的输入格式主要是为了确保测试的全面性。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/print-in-order
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */
public class PrintInOrder {
	
	private int order = 1;
	
//	private static PrintInOrder singleton = new PrintInOrder(1);
	
	public PrintInOrder(int order) {
		this.order = order;
	}
	
//	public PrintInOrder getInstance() {
//		if(singleton==null) {
//			singleton = new PrintInOrder(1);
//		}
//		return singleton;
//	}
	
	public boolean printOne() {
		synchronized(this) {
			if(order==1) {
				System.out.print("One");
				order = 2;
				return true;
			}else {
				return false;
			}
		}
	}
	
	public boolean printTwo() {
		synchronized(this) {
			if(order==2) {
				System.out.print("Two");
				order = 3;
				return true;
			}else {
				return false;
			}
		}
	}
	
	public boolean printThree() {
		synchronized(this) {
			if(order==3) {
				System.out.print("Three");
				order = 1;
				return true;
			}else {
				return false;
			}
		}
	}
	
	public static void printInOrder() {
		PrintInOrder printInOrder = new PrintInOrder(1);
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
//				while(!PrintInOrder.singleton.printOne()) {
//					
//				}
				while(!printInOrder.printOne()) {
					
				}
			}
		});
		thread1.start();
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
//				while(!PrintInOrder.singleton.printThree()) {
//					
//				}
				while(!printInOrder.printThree()) {
					
				}
			}
		});
		thread2.start();
		Thread thread3 = new Thread(new Runnable() {
			@Override
			public void run() {
//				while(!PrintInOrder.singleton.printTwo()) {
//					
//				}
				while(!printInOrder.printTwo()) {
					
				}
			}
		});
		thread3.start();
	}
	
	public static void main(String[] args) {
		PrintInOrder.printInOrder();
	}

}
