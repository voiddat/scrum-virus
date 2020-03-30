package com.madsoft.scrumvirus.command.domain.course.events.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@EnableJms
@Configuration
public class MQConfig {
    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory() {
        return new DefaultJmsListenerContainerFactory();
    }
}
