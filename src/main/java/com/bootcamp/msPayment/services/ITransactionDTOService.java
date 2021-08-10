package com.bootcamp.msPayment.services;

import com.bootcamp.msPayment.models.dto.TransactionActiveDTO;
import reactor.core.publisher.Mono;

public interface ITransactionDTOService {
    public Mono<TransactionActiveDTO> saveTransaction(TransactionActiveDTO transaction);
}
