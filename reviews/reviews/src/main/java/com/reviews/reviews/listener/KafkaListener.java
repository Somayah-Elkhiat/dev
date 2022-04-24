package com.reviews.reviews.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reviews.reviews.controller.MailController;
import com.reviews.reviews.model.ReviewRequestModel;
import com.reviews.reviews.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class KafkaListener {

	@Autowired
	MailService mailService;
	
	@org.springframework.kafka.annotation.KafkaListener(
			topics = "order", 
			groupId = "groupId",
			containerFactory = "kafkaListenerContainerFactory"

			)
	public void listener(String data) throws IOException {
		System.out.println(data.toString());
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewRequestModel reviewRequestModel = objectMapper.readValue(data, ReviewRequestModel.class);
		mailService.sendTextEmail(reviewRequestModel.getEmail(), reviewRequestModel.getSku());

	}

}
