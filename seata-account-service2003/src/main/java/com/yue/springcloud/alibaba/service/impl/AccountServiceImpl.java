package com.yue.springcloud.alibaba.service.impl;

import com.yue.springcloud.alibaba.dao.AccountDao;
import com.yue.springcloud.alibaba.service.AccountService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("----> account-service中扣减用户余额开始");
        // 模拟超时异常，全局事务回滚
        // 暂停20秒钟线程
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        accountDao.decrease(userId, money);
        LOGGER.info("----> account-service中扣减用户余额开始");
    }
}
