package com.bootcamp.msPayment.handler;

import com.bootcamp.msPayment.entities.ProductDTO;
import com.bootcamp.msPayment.services.IProductDTOService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j(topic = "productdto_handler")
@Component
public class ProductDTOHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDTOHandler.class);

    @Autowired
    private IProductDTOService service;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), ProductDTO.class);
    }

    public Mono<ServerResponse> findProductDTO(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap((c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()))
        );
    }

    public Mono<ServerResponse> newProductDTO(ServerRequest request){

        Mono<ProductDTO> productDTOMono = request.bodyToMono(ProductDTO.class);

        return productDTOMono.flatMap( c -> service.create(c)).flatMap( c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)));
    }

    public Mono<ServerResponse> deleteProductDTO(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<ProductDTO> productDTOMono = service.findById(id);

        return productDTOMono
                .doOnNext(c -> LOGGER.info("deleteConsumption: consumptionId={}", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateProductDTO(ServerRequest request){
        Mono<ProductDTO> productDTOMono = request.bodyToMono(ProductDTO.class);
        String id = request.pathVariable("id");

        return service.findById(id).zipWith(productDTOMono, (db,req) -> {
                    db.setProductName(req.getProductName());
                    db.setIdenNumber(req.getIdenNumber());
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.create(c),ProductDTO.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
