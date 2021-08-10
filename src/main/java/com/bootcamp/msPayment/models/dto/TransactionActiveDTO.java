package com.bootcamp.msPayment.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionActiveDTO {
    private String typeoftransaction;

    private String identityNumber;

    private double transactionAmount;

    private String idProductoCredit;

    private String transactionDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOperation = LocalDateTime.now();
}
