package pl.kukla.krzys.sfgjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {

		//for embedded server
//		ActiveMQServer mqServer = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//			.setPersistenceEnabled(false)
//			.setJournalDirectory("target/data/journal")
//			.setSecurityEnabled(false)
//			.addAcceptorConfiguration("invm", "vm://0")
//		);
//		mqServer.start();

		SpringApplication.run(Application.class, args);
	}

}
