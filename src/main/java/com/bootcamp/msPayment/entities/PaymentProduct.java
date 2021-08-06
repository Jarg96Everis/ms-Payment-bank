package com.bootcamp.msPayment.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "paymentproduct")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentProduct {

    @Id
    private String id;

    private Payment payment;

    private ProductDTO product;
}
