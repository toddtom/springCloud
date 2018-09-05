package com.todd.service;

import com.todd.service.impl.SchedualEurekaServerImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//在此注解中加上fallback，并配置其指定的类，即可达到熔断器的作用
@FeignClient(value = "eureka-client",fallback=SchedualEurekaServerImpl.class)
public interface SchedualEurekaServer {
  @RequestMapping(value = "/hi",method= RequestMethod.GET)
  String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
