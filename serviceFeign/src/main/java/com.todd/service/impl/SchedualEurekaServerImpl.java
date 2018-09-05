package com.todd.service.impl;

import com.todd.service.SchedualEurekaServer;
import org.springframework.stereotype.Component;

@Component
public class SchedualEurekaServerImpl implements SchedualEurekaServer {
  @Override
  public String sayHiFromClientOne(String name){
    return "sorry "+name;
  }
}
