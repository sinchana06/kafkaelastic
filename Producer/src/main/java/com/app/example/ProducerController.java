package com.app.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
	@Autowired
	KafkaService kafkaProducer;
	@Autowired
	ProducerRepo prodRepo;

	@PostMapping("/producer")
	public String sendMessage(@RequestBody User user) {
		prodRepo.save(user);
		kafkaProducer.send(user);
		return "Message sent successfully to the Kafka and db";
	}
	@PostMapping("/producerlist")
	public String sendMessage(@RequestBody List<User> user) {
		kafkaProducer.sendList(user);
		return "Message sent successfully to the Kafka";
	}
	
	@GetMapping("/get/{id}")
	 @Cacheable(key = "#id",value = "users") 
	public Optional<User> getUsers(@PathVariable int id) {
		  
	    return prodRepo.findById(id);
	}
}