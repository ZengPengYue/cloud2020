package com.yue.springcloud.service.impl;

import com.yue.springcloud.dao.PaymentDao;
import com.yue.springcloud.entities.Payment;
import com.yue.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public  Payment getPaymentById( Long id){
        return paymentDao.getPaymentById(id);
    }
}
