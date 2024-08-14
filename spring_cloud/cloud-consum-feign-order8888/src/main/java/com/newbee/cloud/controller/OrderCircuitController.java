package com.newbee.cloud.controller;

import com.newbee.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author zero
 * @Description :
 * @CreateTime 2024/8/11 , 15:31
 */
@RestController
public class OrderCircuitController {
        @Resource
        private PayFeignApi payFeignApi;

        @GetMapping(value = "/feign/pay/circuit/{id}")
        @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
        public String myCircuitBreaker(@PathVariable("id") Integer id)
        {
            return payFeignApi.myCircuit(id);
        }
        //myCircuitFallback就是服务降级后的兜底处理方法
        public String myCircuitFallback(Integer id,Throwable t) {
            // 这里是容错处理逻辑，返回备用结果
            return "myCircuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~"+id;
        }

 
//        /**
//         *(船的)舱壁,隔离
//         * @param id
//         * @return
//         */
//        @GetMapping(value = "/feign/pay/bulkhead/{id}")
//        @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.SEMAPHORE)
//        public String myBulkhead(@PathVariable("id") Integer id)
//        {
//                return payFeignApi.myBulkhead(id);
//        }
//        public String myBulkheadFallback(Throwable t)
//        {
//                return "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
//        }
        /**
         *(船的)舱壁,隔离，线程池
         * @param id
         * @return
         */
        @GetMapping(value = "/feign/pay/bulkhead/{id}")
        @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadThreadPool",type = Bulkhead.Type.THREADPOOL)
        public CompletableFuture<String> myBulkheadThreadPool(@PathVariable("id") Integer id)
        {
                System.out.println(Thread.currentThread().getName()+"\n"+"线程池开始执行");
                try {
                        TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+"\n"+"线程池执行结束");

                return CompletableFuture.supplyAsync( ()-> payFeignApi.myBulkhead(id)+"\t\n"+"线程池执行结束");
        }
        public CompletableFuture<String> myBulkheadThreadPool(Throwable t)
        {
                return CompletableFuture.supplyAsync( ()-> "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
        }




}
