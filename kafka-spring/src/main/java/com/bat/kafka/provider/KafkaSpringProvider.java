package com.bat.kafka.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KafkaSpringProvider {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @ResponseBody
    @RequestMapping("/provider")
    public String provider(String msg){
        kafkaTemplate.send("test-topic",msg);
        return "ok";
    }

}
