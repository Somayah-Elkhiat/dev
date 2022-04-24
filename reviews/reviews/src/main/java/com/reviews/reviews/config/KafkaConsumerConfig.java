package com.reviews.reviews.config;

import java.util.HashMap;
import java.util.Map;

import com.reviews.reviews.model.ReviewRequestModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConsumerConfig {

	@Value("${spring.kafka.bootsrap-servers}")
	private String bootstrapServers;

	public Map<String, Object> consumerConfig() {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

		return properties;

	}

	@Bean
	public ConsumerFactory<String, ReviewRequestModel> reviewConsumerFactory() {
		JsonDeserializer<ReviewRequestModel> jsonDeserializer = new JsonDeserializer<>();
		jsonDeserializer.addTrustedPackages("*");

		return new DefaultKafkaConsumerFactory<>(consumerConfig(),
				new StringDeserializer()
				,jsonDeserializer


		);
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ReviewRequestModel>> factory(
			ConsumerFactory<String, ReviewRequestModel> reviewConsumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, ReviewRequestModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(reviewConsumerFactory);
		factory.setMessageConverter(new StringJsonMessageConverter());
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		return factory;
	}

}
