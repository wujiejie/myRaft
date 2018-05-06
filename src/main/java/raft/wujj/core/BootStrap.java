package raft.wujj.core;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import raft.wujj.core.state.CircleThreadHolder;
import raft.wujj.core.state.NodeState;
import raft.wujj.core.thread.EmitThread;

@Component
public class BootStrap implements InitializingBean{

	
	public void afterPropertiesSet() throws Exception {
		EmitThread thread = new EmitThread(new NodeState());
		CircleThreadHolder.cirleThread.set(thread);
		thread.start();
		
		
	}
	
	
	
	

}
