package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.AppConstants;
import com.exceptions.DetailsNotFoundException;
import com.model.EstimateModel;
import com.services.KafKaConsumerService;
import com.services.KafKaProducerService;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {
	private final KafKaProducerService producerService;

	@Autowired
	public KafkaProducerController(KafKaProducerService producerService) {
		this.producerService = producerService;
	}

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.producerService.sendMessage(message);
	}

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	KafKaProducerService kfs;

	// private static final String TOPIC = "selected-products";

	@PostMapping(value = "/publish")
	public void estimatedDetails(@RequestBody ArrayList<Integer> idList) {

		ArrayList<EstimateModel> list = new ArrayList<EstimateModel>();

		Map<Integer, EstimateModel> map_new = kfs.SetData();

		for (int i = 0; i < idList.size(); i++) {

			int id = idList.get(i);
			if (map_new.get(id) != null)
				list.add(map_new.get(id));
			else
				throw new DetailsNotFoundException("id " + idList);
		}

		kafkaTemplate.send(AppConstants.TOPIC_NAME, list.toString());

	}

}