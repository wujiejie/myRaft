package raft.wujj.core.state;

public class NodeState {
	
	private volatile int state = 0;
	
	public  synchronized void setState(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	

}
