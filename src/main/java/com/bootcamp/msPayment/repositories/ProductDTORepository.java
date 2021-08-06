package com.bootcamp.msPayment.repositories;

import com.bootcamp.msPayment.entities.ProductDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductDTORepository extends ReactiveMongoRepository<ProductDTO,String> {
}
