package raft.wujj;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({Configuration.class})
@EnableFeignClients
public class Application {
	
	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

}
