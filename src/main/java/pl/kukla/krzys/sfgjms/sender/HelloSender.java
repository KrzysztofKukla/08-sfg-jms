package pl.kukla.krzys.sfgjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.kukla.krzys.sfgjms.config.JmsConfig;
import pl.kukla.krzys.sfgjms.model.HelloWorldMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    //this method will be call per 8 seconds ( 8000 milliseconds )
    @Scheduled(fixedRate = 20000)
    public void sendMessage() {

        HelloWorldMessage helloWorldMessage = HelloWorldMessage.builder()
            .id(UUID.randomUUID())
            .message("dupa")
            .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, helloWorldMessage);

    }

    //this method will be call per 8 seconds ( 8000 milliseconds )
    @Scheduled(fixedRate = 20000)
    public void sendAndReceiveMessage() throws JMSException {
        System.out.println("I am sending the message...");

        HelloWorldMessage message = HelloWorldMessage.builder()
            .id(UUID.randomUUID())
            .message("Hello ")
            .build();

        //it setting temporally queue on the message and going up to the queue
        //and then it's returning back to different queue
        Message receiveMessage = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RECEIVE_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                try {
                    String valueAsString = objectMapper.writeValueAsString(message);
                    Message helloMessage = session.createTextMessage(valueAsString);
                    helloMessage.setStringProperty("_type", "pl.kukla.krzys.sfgjms.model.HelloWorldMessage");

                    System.out.println("Sending Hello");
                    return helloMessage;
                } catch (JsonProcessingException e) {
                    throw new JMSException("boom");
                }

            }
        });

        System.out.println("Received message back-> " + receiveMessage.getBody(String.class));
    }

}
