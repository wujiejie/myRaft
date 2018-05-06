package raft.wujj.core.event;

public class ResponseEvent {
	
	public static final String OK ="OK";
	public static final String NO = "NO";
	
	private int seq;
	private String type;
	
	public ResponseEvent(int seq, String type) {
		this.seq = seq;
		this.type = type;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
