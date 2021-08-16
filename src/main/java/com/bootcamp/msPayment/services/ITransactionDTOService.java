package com.bootcamp.msPayment.services;

import com.bootcamp.msPayment.models.dto.TransactionDTO;
import reactor.core.publisher.Mono;

public interface ITransactionDTOService {
    public Mono<TransactionDTO> saveTransaction(TransactionDTO transaction);
}
