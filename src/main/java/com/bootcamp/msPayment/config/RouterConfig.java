package com.bootcamp.msPayment.config;

import com.bootcamp.msPayment.handler.PaymentHandler;
import com.bootcamp.msPayment.handler.PaymentProductHandler;
import com.bootcamp.msPayment.handler.ProductDTOHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(PaymentHandler paymentHandler,
                                                 PaymentProductHandler paymentProductHandler,
                                                 ProductDTOHandler productDTOHandler){

        return route(GET("/api/payment"), paymentHandler::findAll)
                .andRoute(GET("/api/payment/{id}"), paymentHandler::findPayment)
                .andRoute(POST("/api/payment"), paymentHandler::newPayment)
                .andRoute(PUT("/api/payment/{id}"), paymentHandler::updatePayment)
                .andRoute(DELETE("/api/payment/{id}"), paymentHandler::deletePayment)
                .andRoute(GET("/api/paymentproduct"), paymentProductHandler::findAll)
                .andRoute(GET("/api/paymentproduct/{id}"), paymentProductHandler::findPaymentProduct)
                .andRoute(POST("/api/paymentproduct"), paymentProductHandler::newPaymentProduct)
                .andRoute(PUT("/api/paymentproduct/{id}"), paymentProductHandler::updatePaymentProduct)
                .andRoute(DELETE("/api/paymentproduct/{id}"), paymentProductHandler::deletePaymentProduct)
                .andRoute(GET("/api/productodto"), productDTOHandler::findAll)
                .andRoute(GET("/api/productodto/{id}"), productDTOHandler::findProductDTO)
                .andRoute(POST("/api/productodto"), productDTOHandler::newProductDTO)
                .andRoute(PUT("/api/productodto/{id}"), productDTOHandler::updateProductDTO)
                .andRoute(DELETE("/api/productodto/{id}"), productDTOHandler::deleteProductDTO);

    }
}
