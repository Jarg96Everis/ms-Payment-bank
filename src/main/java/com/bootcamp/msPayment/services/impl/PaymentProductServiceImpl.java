package com.bootcamp.msPayment.services.impl;

import com.bootcamp.msPayment.entities.PaymentProduct;
import com.bootcamp.msPayment.repositories.PaymentProductRepository;
import com.bootcamp.msPayment.services.IPaymentProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentProductServiceImpl implements IPaymentProductService {

    @Autowired
    private PaymentProductRepository repository;

    @Override
    public Mono<PaymentProduct> create(PaymentProduct o) {
        return repository.save(o);
    }

    @Override
    public Flux<PaymentProduct> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<PaymentProduct> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<PaymentProduct> update(String s, PaymentProduct o) {
        return repository.findById(s).flatMap( c -> {
            if (c == null){
                return null;
            }
            c.setProduct(o.getProduct());
            c.setPayment(o.getPayment());

            return Mono.just(c);
        });
    }

    @Override
    public Mono<Void> delete(PaymentProduct o) {
        return repository.delete(o);
    }
}
