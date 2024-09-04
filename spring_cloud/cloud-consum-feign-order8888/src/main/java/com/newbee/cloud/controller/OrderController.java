package com.newbee.cloud.controller;

import com.newbee.cloud.apis.PayFeignApi;
import com.newbee.cloud.entities.PayDTO;
import com.newbee.cloud.resp.ResultData;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;


/**
 * @auther zzyy
 * @create 2023-11-04 16:00
 */
@RestController
public class OrderController{
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping ("feign/pay/add")
    public ResultData addOrder (@RequestBody PayDTO payDTO){

        System.out.println("第一步，模拟本地addOrder 新增订单成功，第二部开启addPay微服务远程调用" );
        ResultData resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }
    @GetMapping("/feign/pay/get/{id}")
    public ResultData getOrder(@PathVariable("id") Integer id){

            System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
            ResultData resultData = payFeignApi.getPayInfo(id);
            return resultData;
    }
    @GetMapping("/feign/pay/getAll")
    public ResultData getOrders(){
        System.out.println("===支付微服务调用，查询全部订单流水信息");
        ResultData payAll = payFeignApi.getPayAll();
        return payAll;
    }
    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping(value = "/feign/pay/mylb")
    public String mylb()
    {
        return payFeignApi.mylb();
    }

}





