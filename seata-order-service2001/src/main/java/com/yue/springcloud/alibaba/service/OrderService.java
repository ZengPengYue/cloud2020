package com.yue.springcloud.alibaba.service;

import com.yue.springcloud.alibaba.domain.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void create(Order order);
}
