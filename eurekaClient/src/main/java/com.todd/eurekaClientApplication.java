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
@RestController
@EnableDiscoveryClient
//断路器支持
@EnableHystrix
//开启HystrixDashboard
@EnableHystrixDashboard
//启动断路器
@EnableCircuitBreaker
public class eurekaClientApplication {
  public static void main(String[] args) {
    SpringApplication.run( eurekaClientApplication.class, args );
  }

  @Value("${server.port}")
  protected String port;

  @RequestMapping("/hi")
  @HystrixCommand(fallbackMethod = "hiError")
  public String home(@RequestParam(value="name",defaultValue = "todd") String name){
    return "hi " + name + " ,i am from port:" + port;
  }
  public String hiError(String name) {
    return "hi,"+name+",sorry,error!";
  }
}
