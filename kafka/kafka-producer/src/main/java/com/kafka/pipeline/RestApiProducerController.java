package com.kafka.pipeline;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class RestApiProducerController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping("/api/atm")
    public ResponseEntity<?> selectColor(@RequestBody String receiveMessage){

        log.info(receiveMessage);
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
        Date now = new Date();
        Gson gson = new Gson();

        String json = gson.toJson(receiveMessage);

        try{
            kafkaTemplate.send("atm", json).addCallback(
                    new ListenableFutureCallback<SendResult<String, String>>() {
                        @Override
                        public void onFailure(Throwable ex) {
                            log.info(ex.getMessage(),ex);
                        }

                        @Override
                        public void onSuccess(SendResult<String, String> result) {
                            log.info(result.toString());
                        }
                    }
            );
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/atm")
    public String selectColor1(@RequestBody String receiveMessage){

        log.info(receiveMessage);

        return "0K";
    }

}
