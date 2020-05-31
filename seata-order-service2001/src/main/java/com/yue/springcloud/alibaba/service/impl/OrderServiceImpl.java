package com.yue.springcloud.alibaba.service.impl;

import com.yue.springcloud.alibaba.dao.OrderDao;
import com.yue.springcloud.alibaba.domain.Order;
import com.yue.springcloud.alibaba.service.AccountService;
import com.yue.springcloud.alibaba.service.OrderService;
import com.yue.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单 => 调用库存服务扣减库存 => 调用账户服务扣减账户余额 => 修改订单状态
     * @param order
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("-----> 开始新建订单 start");
        orderDao.create(order);

        log.info("-----> 订单微服务开始调用库存，做扣减Count start");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("-----> 订单微服务开始调用库存，做扣减Count end");

        log.info("-----> 订单微服务开始调用账户，做扣减Money start");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("-----> 订单微服务开始调用账户，做扣减Money end");

        log.info("-----> 修改订单状态 start");
        orderDao.update(order.getUserId(),0);
        log.info("-----> 修改订单状态 end");

        log.info("-----> 订单结束");
    }
}
