package pl.kukla.krzys.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * @author Krzysztof Kukla
 */
@Configuration
public class JmsConfig {

    public static final String MY_QUEUE = "Hello new world QUEUE";
    public static final String MY_SEND_RECEIVE_QUEUE = "replyBackToMe QUEUE";

    //when we send the message to JMS, Spring converts that message to JMS text message
    //and the payload takes Java object and converts to JSON payload
    //conversion between JMS message and from Java Object into serialized JSON into JMS message
    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        return converter;
    }

}
