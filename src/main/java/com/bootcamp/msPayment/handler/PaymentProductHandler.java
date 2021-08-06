package com.bootcamp.msPayment.handler;

import com.bootcamp.msPayment.entities.PaymentProduct;
import com.bootcamp.msPayment.services.IPaymentProductService;
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

@Slf4j(topic = "paymentproducto_handler")
@Component
public class PaymentProductHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentProductHandler.class);

    @Autowired
    private IPaymentProductService service;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), PaymentProduct.class);
    }

    public Mono<ServerResponse> findPaymentProduct(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.findById(id).flatMap((c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c))
                .switchIfEmpty(ServerResponse.notFound().build()))
        );
    }

    public Mono<ServerResponse> newPaymentProduct(ServerRequest request){

        Mono<PaymentProduct> creditCardCustomerMono = request.bodyToMono(PaymentProduct.class);

        return creditCardCustomerMono.flatMap( c -> service.create(c)).flatMap( c -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(c)));
    }

    public Mono<ServerResponse> deletePaymentProduct(ServerRequest request){

        String id = request.pathVariable("id");

        Mono<PaymentProduct> creditCustomerMono = service.findById(id);

        return creditCustomerMono
                .doOnNext(c -> LOGGER.info("deleteConsumption: consumptionId={}", c.getId()))
                .flatMap(c -> service.delete(c).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updatePaymentProduct(ServerRequest request){
        Mono<PaymentProduct> paymentProductoMono = request.bodyToMono(PaymentProduct.class);
        String id = request.pathVariable("id");

        return service.findById(id).zipWith(paymentProductoMono, (db,req) -> {
                    db.setProduct(req.getProduct());
                    db.setPayment(req.getPayment());
                    return db;
                }).flatMap( c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.create(c),PaymentProduct.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
