package com.bootcamp.msPayment.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productDTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    @Id
    private String id;

    private String productName;

    private String idenNumber;
}
