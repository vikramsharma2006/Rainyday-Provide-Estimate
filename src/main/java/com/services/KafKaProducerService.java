package com.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.common.AppConstants;
import com.model.EstimateModel;


 
@Service
public class KafKaProducerService 
{
    private static final Logger logger = 
            LoggerFactory.getLogger(KafKaProducerService.class);
    
    @Autowired
    private KafkaTemplate<String, EstimateModel> userKafkaTemplate;
     
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
   
 

	public Map<Integer, EstimateModel> SetData() {
		Map<Integer, EstimateModel> map = new HashMap<Integer, EstimateModel>();
		map.put(1, new EstimateModel(1, "coffee", "Comapny1", 100, "beverage"));
		map.put(2, new EstimateModel(2, "tea", "Comapny2", 200, "beverage"));
		map.put(3, new EstimateModel(3, "Baking cake", "Comapny3", 200, "dairy"));
		map.put(4, new EstimateModel(4, "Fresh strawberry", "Comapny4", 210, "fruit"));
		map.put(5, new EstimateModel(5, "Homemade bread", "Comapny5", 150, "bakery"));
		map.put(6, new EstimateModel(6, "Oranges", "Comapny6", 50, "fruit"));
		map.put(7, new EstimateModel(7, "Honey", "Comapny7", 50, "bakery"));
		map.put(8, new EstimateModel(8, "Yogurt", "Comapny8", 20, "dairy"));
		map.put(9, new EstimateModel(9, "corn", "Comapny9", 20, "vegetable"));
		map.put(10, new EstimateModel(10, "Tomatoes", "Comapny10", 20, "vegetable"));
		return map;
		
	}
    
    
    
    
    
    
    
    public void sendMessage(String list) 
    {
        logger.info(String.format("Message sent -> %s", list));
        this.kafkaTemplate.send(AppConstants.TOPIC_NAME, list);
    }
    
	
	public void saveCreateUserLog(EstimateModel user) 
	{
		ListenableFuture<SendResult<String, EstimateModel>> future 
			= this.userKafkaTemplate.send(AppConstants.TOPIC_NAME, user);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, EstimateModel>>() {
            @Override
            public void onSuccess(SendResult<String, EstimateModel> result) {
            	logger.info("User created: " 
            			+ user + " with offset: " + result.getRecordMetadata().offset());
            }

			@Override
			public void onFailure(Throwable ex) {
				// TODO Auto-generated method stub
				
			}

         
       });
	}


}
