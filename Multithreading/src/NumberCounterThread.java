
public class NumberCounterThread extends Thread {
	
	private int fromNumber;
	private int toNumber;
	
	public NumberCounterThread(int fromNumber, int toNumber) {
		this.fromNumber = fromNumber;
		this.toNumber = toNumber;
	}
	
	@Override
	public void run() {
		super.run();
		
		for(int i = fromNumber; i < toNumber; i++) {
			System.out.println(i);
		}
	}

}
