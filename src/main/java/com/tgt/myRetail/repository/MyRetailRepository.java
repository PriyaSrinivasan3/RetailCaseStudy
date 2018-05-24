package com.tgt.myRetail.repository;

import com.tgt.myRetail.domain.Pricing;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyRetailRepository extends MongoRepository<Pricing, String> {
    Pricing findByitem(String item);
}