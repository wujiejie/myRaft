package raft.wujj.core.thread;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

import raft.wujj.client.HttpUtil;
import raft.wujj.core.config.MachineConfig;
import raft.wujj.core.event.ExchangeEvent;
import raft.wujj.core.event.ResponseEvent;
import raft.wujj.core.record.NotePad;
import raft.wujj.core.state.NodeState;

public class EmitThread extends Thread{
	
	private NodeState state;
	
	private int cirleTime = 150;
	
	private int heartBeatTime = 50;
	
    private static AtomicInteger total = new AtomicInteger(0); //文件系统里面的配置，可以持久化。
	
	public EmitThread(NodeState state) {
		this.state = state;
	}
	
	public void run() {
		
		while(true) {
			//state == FOLLOWER , emmit event to others.
		    //reload from file.
		  total.set(0);
		  NodeState.incrementMySeq();
			if(state.getState() == NodeState.FOLLOWER) {
				try {
					int randomTime = cirleTime+((int)(Math.random()*cirleTime));
					Thread.sleep(randomTime);
					List<String> otherMachines = MachineConfig.getOtherHost();
					for(String url : otherMachines) {
						new Thread(new Runnable() {
							public void run() {
								ExchangeEvent event = new ExchangeEvent(null,ExchangeEvent.ELECTION,NotePad.list.size(),NodeState.getMySeq());
								Gson gson = new Gson();
								String temp = gson.toJson(event);
								try {
									String rep = HttpUtil.post(url, temp);
									Gson gson2 = new Gson();
									ResponseEvent resEvent = gson2.fromJson(rep, ResponseEvent.class);
									
									if("OK".equals(resEvent.getType())) {
										total.incrementAndGet();
										if(total.get() > ((MachineConfig.getOtherHost().size()+1)/2+1))
										state.setState(NodeState.LEADER);
									}
		                            if("NO".equals(resEvent.getType())) {
		                            	state.setState(NodeState.FOLLOWER);
									}
									
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					).start();
					
				}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

		}//state.getState() == 0
			
		if(state.getState() == NodeState.LEADER) {
			//倒着发，复制。 从第一个不是提交状态的节点开始发射。
			int randomTime = (int)(Math.random()*heartBeatTime);
			try {
				Thread.sleep(randomTime);
				List<String> otherMachines = MachineConfig.getOtherHost();
				for(String url : otherMachines) {
					new Thread(new Runnable() {
						public void run() {			
							for(int i=0; i< NotePad.list.size();i++) {
								ExchangeEvent event = NotePad.list.get(i);
								if(event.getCommitStatus() == ExchangeEvent.COMMITED) {
									continue;
								}
								ExchangeEvent denyEvent = new ExchangeEvent(null,ExchangeEvent.LEADER_DENY,event.recordIndex,NodeState.getMySeq());
								Gson gson = new Gson();
								String temp = gson.toJson(denyEvent);
								try {
									String rep = HttpUtil.post(url, temp);
									Gson gson2 = new Gson();
									ResponseEvent resEvent = gson2.fromJson(rep, ResponseEvent.class);
									
									if("OK".equals(resEvent.getType())) {
										
										if(total.get() > ((MachineConfig.getOtherHost().size()+1)/2+1))
										event.setCommitStatus(ExchangeEvent.COMMITED);
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Gson gson2 = new Gson();
							}
							
							
						}
					}).start();
				}
				
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}//end_of_while
	

  }
	
}
