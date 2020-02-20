package pl.kukla.krzys.sfgjms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//if we are sending as Jms object message we need to Serialize
//here we can omit that, because we will send it as JSON message
public class HelloWorldMessage implements Serializable {

    private static final long serialVersionUID = -6017621880186666064L;

    private UUID id;
    private String message;

}
