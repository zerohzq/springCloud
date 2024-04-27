package com.newbee.cloud.controller;




import com.newbee.cloud.entities.PayDTO;
import com.newbee.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @auther zzyy
 * @create 2023-11-04 16:00
 */
@RestController
public class OrderController{
    public static final String PaymentSrv_URL = "http://cloud-payment-service";//先写死，硬编码
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     * @param payDTO
     * @return
     */
    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add",payDTO,ResultData.class);
    }
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/getOne/"+id, ResultData.class,id);
    }

//    @GetMapping("/consumer/pay/update")
//    public ResultData updatePayInfo(PayDTO payDTO){
//        return restTemplate.postForObject(PaymentSrv_URL + "/pay/update",payDTO,ResultData.class);
//    }

}




