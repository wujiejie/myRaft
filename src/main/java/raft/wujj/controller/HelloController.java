package raft.wujj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import raft.wujj.config.AllPropertiesConfig;
import raft.wujj.controller.input.HelloControllerVO;
import raft.wujj.core.event.ExchangeEvent;
import raft.wujj.core.event.ResponseEvent;
import raft.wujj.core.record.NotePad;
import raft.wujj.core.state.CircleThreadHolder;
import raft.wujj.core.state.NodeState;



@RestController
public class HelloController {
	
	@Autowired
	private AllPropertiesConfig allPropertiesConfig;
	

	
	@RequestMapping(value="/hello",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String hello(@ModelAttribute HelloControllerVO.HelloVO hello) {
		
		return " 你好 "+ allPropertiesConfig.getDestination()+"  little rabbit! ";
	}
	
	
	@RequestMapping(value="/raft",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object recevieRaftEvent(@RequestBody ExchangeEvent event) {		
		if(NodeState.getState() != NodeState.LEADER) {
			if(event.type == ExchangeEvent.ELECTION) {
				if(event.recordIndex  < NotePad.list.size()) {
					if(event.value <= NotePad.list.get(event.recordIndex-1).value) {
						
					}else {
						NodeState.setMySeq(event.value);
						//CircleThreadHolder.cirleThread.get().interrupt();
					}
					
					return new ResponseEvent(NodeState.getMySeq(),ResponseEvent.NO);
				}
		
				if((event.recordIndex  ==  NotePad.list.size() ) && (event.value <= NotePad.list.get(event.recordIndex-1).value)) {
					return new ResponseEvent(NodeState.getMySeq(),ResponseEvent.NO);
				}
				if((event.recordIndex  ==  NotePad.list.size() ) && (event.value > NotePad.list.get(event.recordIndex-1).value)) {
					NodeState.setMySeq(event.value);
					CircleThreadHolder.cirleThread.get().interrupt();
					return new ResponseEvent(NodeState.getMySeq(),ResponseEvent.OK);
				}
			
			}
			if(event.type == ExchangeEvent.LEADER_DENY) {
				//从的仆人，去复制。，直接复制主机发送的状态，保持一致即可。
				if(event.recordIndex > NotePad.list.size()) {
					NotePad.list.add(event);
				}
				ExchangeEvent myEvent = NotePad.list.get(event.recordIndex-1);
				myEvent.value = event.value;
				
			}
			
		}
				
		return null;
	}

}
