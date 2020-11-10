package com.services;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.common.AppConstants;
import com.controller.KafkaProducerController;
 
@Service
public class KafKaConsumerService 
{
    private final Logger logger = 
            LoggerFactory.getLogger(KafKaConsumerService.class);
 
    @Autowired
    KafkaProducerController kpc;
    
    @KafkaListener(topics = AppConstants.TOPIC_NAME, 
            groupId = AppConstants.GROUP_ID)
    public void consume(@RequestBody ArrayList<Integer> idList)  throws IOException
    {
        logger.info(String.format("Message recieved -> %s", idList));
        kpc.estimatedDetails(idList);
        
    }
}