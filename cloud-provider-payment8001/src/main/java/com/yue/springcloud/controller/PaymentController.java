package com.yue.springcloud.controller;

import com.yue.springcloud.entities.CommonResult;
import com.yue.springcloud.entities.Payment;
import com.yue.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果：{}",result);
        if(result > 0){
            return new CommonResult(200,"插入数据库成功！",result);
        }
        return new CommonResult(444,"插入数据库失败！");
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：{}",payment);
        if(payment != null){
            return new CommonResult(200,"查询成功！",payment);
        }
        return new CommonResult(444,"查询失败，没有对应记录：" + id);
    }
}
