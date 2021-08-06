package com.bootcamp.msPayment.repositories;

import com.bootcamp.msPayment.entities.PaymentProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PaymentProductRepository extends ReactiveMongoRepository<PaymentProduct,String> {
}
