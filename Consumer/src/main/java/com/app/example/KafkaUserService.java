package com.app.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
 
@Service

public class KafkaUserService {
    @Autowired
    private KafkaUserRepository edao;
   
    public void saveUser(User user) {
        edao.save(user);
    }
   
    public Iterable<User> findAllUsers() {
    return edao.findAll();
    }   
}