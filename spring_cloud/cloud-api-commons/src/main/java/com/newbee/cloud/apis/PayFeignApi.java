package com.newbee.cloud.apis;

import com.newbee.cloud.entities.PayDTO;
import com.newbee.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author zero
 * @Description :
 * @CreateTime 2024/6/1 , 18:32
 */
@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {
    /**
     * 新增支付流水
     * @param payDTO
     * @return
     */
    @GetMapping("pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * 按照主键记录查询支付流水信息
     * @param id
     * @return
     */
    @GetMapping("/pay/getOne/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id);

/**
 * 查询全部订单信息
 */
    @GetMapping("/pay/getAll")
    public ResultData getPayAll();
    /**
     * openfeign天然支持负载均衡演示
     * @return
     */
    @GetMapping(value = "/pay/get/info")
    public String mylb();
}
