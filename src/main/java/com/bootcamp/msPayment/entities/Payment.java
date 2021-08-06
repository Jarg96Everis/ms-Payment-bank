package com.bootcamp.msPayment.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    private String id;

    private ProductDTO product;

    private double amount;

    private String debtNumber;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;


}
