package com.yue.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yue.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FabllbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable(value = "id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
//        log.info("paymentInfo_OK result : " + result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
//    @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod",
//    commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500"))
    public String paymentInfo_Timeout(@PathVariable(value = "id") Integer id){
        int age = 10/0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
//        log.info("paymentInfo_Timeout result : " + result);
        return result;
    }

    public String paymentTimeoutFallbackMethod(@PathVariable(value = "id") Integer id){
        return "我是消费者80，对方支付系统繁忙，请10秒钟后再试。或者自己运行出错请检查自己( ▼-▼ )";
    }

    /**
     * 全局fallback
     */
    public String payment_Global_FabllbackMethod(){
        return  "Global异常处理信息，请稍后再试！";
    }
}
