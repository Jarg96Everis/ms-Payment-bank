package com.bootcamp.msPayment.services;

import com.bootcamp.msPayment.models.dto.CreditDTO;
import reactor.core.publisher.Mono;

/**
 * The interface Credit dto service.
 */
public interface ICreditDTOService {
    /**
     * Update credit mono.
     *
     * @param credit the credit
     * @return the mono
     */
    public Mono<CreditDTO> updateCredit(CreditDTO credit);

    /**
     * Find by id mono.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<CreditDTO> findById(String id);
}
