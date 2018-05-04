package raft.wujj.core.event;

import java.util.concurrent.atomic.AtomicInteger;
import raft.wujj.core.record.NotePad;

public class ExchangeEvent {
	
	
	private CustomEvent userEvent;
	
	private int recordIndex = NotePad.list.size();
	
	private static AtomicInteger ai = new AtomicInteger(1);
	
	
	public ExchangeEvent(CustomEvent event) {
		this.userEvent = event;
		recordIndex = NotePad.list.size();
		ai.getAndIncrement();
	}
	

	

}
