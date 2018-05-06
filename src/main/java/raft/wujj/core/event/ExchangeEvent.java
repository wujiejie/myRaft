package raft.wujj.core.event;

import java.util.concurrent.atomic.AtomicInteger;
import raft.wujj.core.record.NotePad;
import raft.wujj.core.state.NodeState;

public class ExchangeEvent {
	
	public static final int ELECTION = 1;
	public static final int LEADER_DENY = 2;
	
	
	public static final int READY_COMMIT = 1;
	public static final int COMMITED = 2;
	
	
	private CustomEvent userEvent;
	
	public int type;
	
	public int recordIndex = NotePad.list.size();
	
	public int value = 0;
		
	
	public int commitStatus;
	
	
	public ExchangeEvent(CustomEvent event,int type, int index, int seq) {
		this.type = type;
		this.userEvent = event;
		recordIndex = NotePad.list.size();
		value = NotePad.list.get(recordIndex-1).value;
		NodeState.incrementMySeq();
	}


	public int getCommitStatus() {
		return commitStatus;
	}


	public void setCommitStatus(int commitStatus) {
		this.commitStatus = commitStatus;
	}
	
	
	

	

}
