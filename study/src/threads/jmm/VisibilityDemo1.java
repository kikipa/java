package threads.jmm;

public class VisibilityDemo1 {
	int i = 0;
	public boolean isRunning = true;
	
	public static void main(String[] args) throws InterruptedException {
		VisibilityDemo1 demo = new VisibilityDemo1();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Here I am..");
				while(demo.isRunning) {
					demo.i++;
				}
				System.out.println(demo.i);
			}
		}).start();
		
		Thread.sleep(3000L);
		demo.isRunning = false;
		System.out.println("Shutdown..");
	}
}
