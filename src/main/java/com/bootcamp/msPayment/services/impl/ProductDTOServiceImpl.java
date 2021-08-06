package com.bootcamp.msPayment.services.impl;

import com.bootcamp.msPayment.entities.ProductDTO;
import com.bootcamp.msPayment.repositories.ProductDTORepository;
import com.bootcamp.msPayment.services.IProductDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductDTOServiceImpl implements IProductDTOService {

    @Autowired
    private ProductDTORepository repository;

    @Override
    public Mono<ProductDTO> create(ProductDTO o) {
        return repository.save(o);
    }

    @Override
    public Flux<ProductDTO> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<ProductDTO> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<ProductDTO> update(String s, ProductDTO o) {
        return repository.findById(s).flatMap( c -> {
            if (c == null){
                return null;
            }
            c.setProductName(o.getProductName());
            c.setIdenNumber(o.getIdenNumber());

            return Mono.just(c);
        });
    }

    @Override
    public Mono<Void> delete(ProductDTO o) {
        return repository.delete(o);
    }
}
