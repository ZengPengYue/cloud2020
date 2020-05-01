package com.yue.springcloud.controller;

import com.yue.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable(value = "id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("paymentInfo_OK result : " + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable(value = "id") Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("paymentInfo_Timeout result : " + result);
        return result;
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable(value = "id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("paymentCircuitBreaker result : " + result);
        return result;
    }
}
