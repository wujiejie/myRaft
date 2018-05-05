package raft.wujj.core.thread;

import java.util.List;

import raft.wujj.client.OrderClient;
import raft.wujj.client.UrlThreadLocal;
import raft.wujj.core.ApplicationContextHolder;
import raft.wujj.core.config.MachineConfig;
import raft.wujj.core.state.NodeState;

public class EmitThread extends Thread{
	
	private NodeState state;
	
	private int cirleTime = 150;
	
	private int heartBeatTime = 50;
	
	public EmitThread(NodeState state) {
		this.state = state;
	}
	
	public void run() {
		
		//while(true) {
			//state ==0 时候, emmit event to others.
			if(state.getState() == 0) {
				try {
					int randomTime = cirleTime+((int)(Math.random()*cirleTime));
					Thread.sleep(randomTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				List<String> otherMachines = MachineConfig.getOtherHost();
				for(String one : otherMachines) {
					new Thread(new Runnable() {
						public void run() {
							UrlThreadLocal.stashUrl(one);
							OrderClient client = ApplicationContextHolder.getApplicationContext().getBean(OrderClient.class);
							String result = client.getBaiDuIndex(null);
							System.out.println(Thread.currentThread() + "  "+one+ " " +result);
						}
					}
				).start();
				
			}
		}//state.getState() == 0
	//}//end_of_while
	

  }
	
}
