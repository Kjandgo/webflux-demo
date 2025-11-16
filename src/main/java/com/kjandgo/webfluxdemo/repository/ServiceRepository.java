package com.kjandgo.webfluxdemo.repository;

import com.kjandgo.webfluxdemo.domain.GroceryItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ServiceRepository extends ReactiveMongoRepository<GroceryItem, String> {


}
