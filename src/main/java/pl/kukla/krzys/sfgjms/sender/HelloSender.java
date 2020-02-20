package pl.kukla.krzys.sfgjms.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.kukla.krzys.sfgjms.config.JmsConfig;
import pl.kukla.krzys.sfgjms.model.HelloWorldMessage;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    //this method will be call per 8 seconds ( 8000 milliseconds )
    @Scheduled(fixedRate = 20000)
    public void sendMessage() {
        System.out.println("I am sending the message...");

        HelloWorldMessage helloWorldMessage = HelloWorldMessage.builder()
            .id(UUID.randomUUID())
            .message("dupa")
            .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, helloWorldMessage);

        System.out.println("Message sent!");
    }

}
