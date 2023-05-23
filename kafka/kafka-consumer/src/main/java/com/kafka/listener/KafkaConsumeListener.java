package com.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumeListener {

    @KafkaListener(groupId = "atm-group", topics = "atm",
                concurrency = "3")
    public void listen(String messageValue){
        log.info("data {}",messageValue);

       // ack.acknowledge();
    }

}
