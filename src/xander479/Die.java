package xander479;

import java.util.Random;

public class Die implements Comparable<Die> {
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

	@Override
	public int compareTo(Die o) {
		return this.value - o.value;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Die)) return false;
		Die other = (Die) o;
		return this.value == other.value;
	}
	
	@Override
	public int hashCode() {
		return value;
	}
}
