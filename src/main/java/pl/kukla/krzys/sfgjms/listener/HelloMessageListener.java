package pl.kukla.krzys.sfgjms.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pl.kukla.krzys.sfgjms.config.JmsConfig;
import pl.kukla.krzys.sfgjms.model.HelloWorldMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Component
@RequiredArgsConstructor
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    //it tells to listen this 'MY_QUEUE' and when the message will appear on the queue than send the message to this method
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    //@Payload tells we expect to get 'helloWorldMessage' from queue in this case equivalent to JMS message properties
//    @Headers we expect to get ' message headers' in this case equivalent to JMS header properties
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) {

//        System.out.println("I've got a message-> " + helloWorldMessage);

        //if our listener ( message client ) throws Exception and does not confirm taking message
        // then 'sender' tries to send the same message again until it gets proper confirmation from the client and not throwing Exception
//        throw new RuntimeException();
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RECEIVE_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message jmsMessage,
                               org.springframework.messaging.Message springMessage) throws JMSException {

        HelloWorldMessage payLoadMsg = HelloWorldMessage.builder()
            .id(UUID.randomUUID())
            .message("World")
            .build();

        //springMessage jms - decoupling from Jms implementation
//        jmsTemplate.convertAndSend((Destination) springMessage.getHeaders().get("jms_replyTo"), "got it");

        jmsTemplate.convertAndSend(jmsMessage.getJMSReplyTo(), payLoadMsg);
    }

}
