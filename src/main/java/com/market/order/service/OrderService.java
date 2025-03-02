package com.market.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Value("${message.queue.product}")
    private String productQueue;

    @Value("${message.queue.payment}")
    private String paymentQueue;

    private final RabbitTemplate rabbitTemplate;

    public void createOrder(String orderId) {
        rabbitTemplate.convertAndSend(productQueue, orderId); //queue에 orderId 전송
        rabbitTemplate.convertAndSend(paymentQueue, orderId);
    }

}