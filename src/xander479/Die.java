package xander479;

import java.util.Random;

public class Die {
	private static Random r = new Random();
	private int value;
	private boolean isLocked;
	
	public Die() {
		value = 0;
		isLocked = false;
	}
	
	public int roll() {
		value = r.nextInt(6) + 1;
		return value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setLock(boolean lock) {
		isLocked = lock; 
	}
	
	public boolean isLocked() {
		return isLocked;
	}
}
