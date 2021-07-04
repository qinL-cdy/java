package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private KafkaTemplate<String, String> template;

    public void send(String foo) {
        this.template.send(this.configProperties.getTopic(), foo);
    }
}