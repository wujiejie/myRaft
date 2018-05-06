package raft.wujj.core.state;

import java.util.concurrent.atomic.AtomicInteger;

public class NodeState {
	
	public static final int FOLLOWER = 0;
	public static final int CANDIDATE = 1;
	public static final int LEADER = 2;
	
	
	private static volatile int state = 0;
	
	private static AtomicInteger mySeq = new AtomicInteger(1);
	
	public  static synchronized void setState(int state1) {
		state = state1;
	}
	
	public static int getState() {
		return state;
	}
	
	public static int getMySeq() {
		return mySeq.get();
	}
	
	public static void setMySeq(int newValue) {
		 mySeq.set(newValue);
	}
	
	public static void incrementMySeq() {
		mySeq.incrementAndGet();
	}
	

}
