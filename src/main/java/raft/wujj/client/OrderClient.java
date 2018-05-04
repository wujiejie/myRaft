package raft.wujj.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wujianjun",url="http://www.baidu.com") 
public interface OrderClient {
	
	 @RequestMapping(value = "/", method = RequestMethod.GET)
	 String getBaiDuIndex(@RequestParam("q") String queryStr);

}
