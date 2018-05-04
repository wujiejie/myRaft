package raft.wujj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import raft.wujj.config.AllPropertiesConfig;
import raft.wujj.controller.input.HelloControllerVO;



@RestController
public class HelloController {
	
	@Autowired
	private AllPropertiesConfig allPropertiesConfig;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public String hello(@ModelAttribute HelloControllerVO.HelloVO hello) {
		
		return " 你好 "+ allPropertiesConfig.getDestination()+"  little rabbit! ";
	}

}
