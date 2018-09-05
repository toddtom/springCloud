package com.todd.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
  @Autowired
  RestTemplate restTemplate;
  //为该方法创建熔断器功能，指定fallbackMethod熔断方法
  @HystrixCommand(fallbackMethod = "hiError")
  public String hiService(String name){
    return restTemplate.getForObject("http://EUREKA-CLIENT/hi?name="+name,String.class);
  }
  public String hiError(String name){
    return "hi,"+name+",sorry,error!";
  }
}
