package com.app.example;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepo extends MongoRepository<User,Integer> {

}
