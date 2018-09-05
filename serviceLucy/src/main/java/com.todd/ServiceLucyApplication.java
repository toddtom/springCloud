package com.todd;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
/**
 * @Description:用于进行hystrix聚合监控的学习
 *
 * @since 1.0
 * @version 1.0
 * @author: tao kai (tao.kai@sccaptain.com.cn)
 * @Date: 2018/9/5 10:39
 */
public class ServiceLucyApplication {
  public static void main(String[] args) {
    SpringApplication.run(ServiceLucyApplication.class,args);
  }
  @Value("${server.port}")
  String port;
  @RequestMapping("/hi")
  @HystrixCommand(fallbackMethod = "hiError")
  public String home(@RequestParam(value="name",defaultValue = "todd") String name){
    return "hi"+name+",i am from port:"+port;
  }
  public String hiError(String name){
    return "hi,"+name+",sorry error!";
  }
}
