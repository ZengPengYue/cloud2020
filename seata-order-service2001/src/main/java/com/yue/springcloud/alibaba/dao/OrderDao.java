package com.yue.springcloud.alibaba.dao;

import com.yue.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //新建订单
    void create(Order order);

    //修改订单状态
    void update(@Param("userId")Long userId,@Param("status")Integer status);
}
