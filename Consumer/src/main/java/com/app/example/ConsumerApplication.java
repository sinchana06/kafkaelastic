package com.app.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCaching
@RestController
public class ConsumerApplication{
	@Autowired
	KafkaUserService kafkaUserService;

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@KafkaListener(topics = "gfg", groupId = "gfg-group")
	public void listen(User user) {
		System.out.println("Received User information : " + user.toString());
		try {
			kafkaUserService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@GetMapping("/getElasticUserFromKafka")
	public Iterable<User> findAllUser() {
		return kafkaUserService.findAllUsers();
	}
}
