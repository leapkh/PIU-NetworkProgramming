
public class NumberCounterRunnable implements Runnable {

	private int fromNumber;
	private int toNumber;
	
	public NumberCounterRunnable(int fromNumber, int toNumber) {
		this.fromNumber = fromNumber;
		this.toNumber = toNumber;
	}
	
	@Override
	public void run() {
		for(int i = fromNumber; i < toNumber; i++) {
			System.out.println(i);
		}
	}
	
}
