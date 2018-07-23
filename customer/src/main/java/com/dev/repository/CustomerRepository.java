package com.dev.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dev.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{

}
