package com.newbee.cloud.controller;

import cn.hutool.core.bean.BeanUtil;
import com.newbee.cloud.entities.Pay;
import com.newbee.cloud.entities.PayDTO;
import com.newbee.cloud.resp.ResultData;
import com.newbee.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "支付微服务模块" , description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

@PostMapping(value = "/pay/add")
@Operation(summary = "新增",description = "新增支付流水方法，json作为参数")
    public ResultData<String> addPay(@RequestBody  Pay pay){
        log.info(pay.toString());
        int i = payService.add(pay);
       return ResultData.success(0 == i ? "插入失败":"插入成功") ;
    }
    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "按照主键删除订单记录")
    public ResultData<String> delectPay(@PathVariable("id") Integer id){
        int delect = payService.delect(id);
        return ResultData.success(0 == delect ? "删除失败":"删除成功") ;
    }
    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改",description = "通过前端修改可见订单")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtil.copyProperties(pay,payDTO);
        int update = payService.update(pay);
        return ResultData.success(0 == update ? "插入失败":"插入成功") ;
    }

    @GetMapping(value = "/pay/getOne/{id}")
    @Operation(summary = "查询", description = "通过主键查询订单记录")
    public ResultData<Pay> getOne(@PathVariable("id") Integer id) {
        if (-4==id) {
           throw  new RuntimeException("主键不能为负数");
        }
         return ResultData.success(payService.getById(id)) ;
    }

    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "查询", description = "查询所有订单记录")
    public ResultData<List<Pay>> getOne() {

        return ResultData.success( payService.getAll());
    }
}
