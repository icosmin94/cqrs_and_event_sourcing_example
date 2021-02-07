package cqrs.event.sourcing.example.user_cmd_api.config;

import cqrs.event.sourcing.example.usercore.configuration.AxonConfig;
import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({AxonConfig.class})
@Configuration
public class ApplicationConfiguration {


}
