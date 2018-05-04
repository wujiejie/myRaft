package raft.wujj.core.config;

import java.util.List;

public class MachineConfig {
	
    private static String selfHost;
    private static List<String> otherHost;
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
