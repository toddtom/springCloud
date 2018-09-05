package com.todd;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Description:用于进行zipkin相关的数据收集学习模型
 *
 * @since 1.0
 * @version 1.0
 * @author: tao kai (tao.kai@sccaptain.com.cn)
 * @Date: 2018/8/23 14:25
 */
@SpringBootApplication
@RestController
public class ServiceHiApplication {
  public static void main(String[] args) {
    SpringApplication.run(ServiceHiApplication.class,args);
  }
  private static final Logger log= Logger.getLogger(ServiceHiApplication.class.getName());
  @Autowired
  private RestTemplate restTemplate;
  @Bean
  public RestTemplate getRestTemplate(){
    return new RestTemplate();
  }
  @RequestMapping("/hi")
  public String callHome(){
    log.log(Level.INFO,"calling trace service-hi");
    return restTemplate.getForObject("http://localhost:8989/miya",String.class);
  }
  @RequestMapping("/info")
  public String info(){
    log.log(Level.INFO,"calling trace service-hi");
    return "i'm service-hi";
  }
  @Bean
  public Sampler defaultSampler(){
    return Sampler.ALWAYS_SAMPLE;
  }

}
