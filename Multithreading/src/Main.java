
public class Main {

	public static void main(String[] args) {
		
		Thread thread1 = new NumberCounterThread(0, 10);
		thread1.start();
		
		Thread thread2 = new NumberCounterThread(-10, 0);
		thread2.start();
		
		Thread thread3 = new Thread(new NumberCounterRunnable(10, 20));
		thread3.start();
		
		Thread thread4 = new Thread(new NumberCounterRunnable(-20, -10));
		thread4.start();
		
		System.out.println("Hello world!");
		System.out.println("How are you?");
				
	}
	
}
