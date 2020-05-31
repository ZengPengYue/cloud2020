package com.yue.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yue.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.yue.springcloud.entities.CommonResult;
import com.yue.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handlerException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }

    public CommonResult handlerException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"  服务不可用！");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl") //没有写 blockHandler，使用系统自带的提示
    public CommonResult byUrl(){
        return new CommonResult(200,"按URL限流测试OK",new Payment(2020L,"serial002"));
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2") //只对按资源限流起作用
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按客户自定义限流处理",new Payment(2020L,"serial003"));
    }
}
