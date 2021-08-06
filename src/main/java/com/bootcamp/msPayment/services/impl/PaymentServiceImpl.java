package com.bootcamp.msPayment.services.impl;

import com.bootcamp.msPayment.entities.Payment;
import com.bootcamp.msPayment.repositories.PaymentRepository;
import com.bootcamp.msPayment.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentRepository repository;

    @Override
    public Mono<Payment> create(Payment o) {
        return repository.save(o);
    }

    @Override
    public Flux<Payment> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Payment> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public Mono<Payment> update(String s, Payment o) {
        return repository.findById(s).flatMap( c -> {
            if (c == null){
                return null;
            }
            c.setAmount(o.getAmount());
            c.setDebtNumber(o.getDebtNumber());

            return Mono.just(c);
        });
    }

    @Override
    public Mono<Void> delete(Payment o) {
        return repository.delete(o);
    }
}
