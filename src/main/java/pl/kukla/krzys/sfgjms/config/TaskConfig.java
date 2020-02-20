package pl.kukla.krzys.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Krzysztof Kukla
 */
@Configuration
@EnableScheduling
//it allows to run async task using SimpleAsyncTaskExecutor bean defined below
@EnableAsync
public class TaskConfig {

    //bean used in Async
    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

}
