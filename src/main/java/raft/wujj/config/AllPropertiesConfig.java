package raft.wujj.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="business")
@PropertySource("file:/home/config.properties")
@Component("allPropertiesConfig")
public class AllPropertiesConfig {
	
	private String destination;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	

}
