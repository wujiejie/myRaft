package raft.wujj.core.config;

import java.util.ArrayList;
import java.util.List;

public class MachineConfig {
	
    private static String selfHost = "http://127.0.0.1:8080";
    private static List<String> otherHost = new ArrayList<String>();
    static {
    	otherHost.add("http://www.baidu.com");
    	otherHost.add("http://www.suizhou.gov.cn");
    }
	public static String getSelfHost() {
		return selfHost;
	}
	public static void setSelfHost(String selfHost) {
		MachineConfig.selfHost = selfHost;
	}
	public static List<String> getOtherHost() {
		return otherHost;
	}
	public static void setOtherHost(List<String> otherHost) {
		MachineConfig.otherHost = otherHost;
	}
    

    

}
