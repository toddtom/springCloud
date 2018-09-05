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
 * @Description:用于进行服务相互调用的zipkin学习模型
 *
 * @since 1.0
 * @version 1.0
 * @author: tao kai (tao.kai@sccaptain.com.cn)
 * @Date: 2018/8/23 14:52
 */
@SpringBootApplication
@RestController
public class ServiceMiyaApplication {
  public static void main(String[] args) {
    SpringApplication.run(ServiceMiyaApplication.class,args);
  }
  private static final Logger log= Logger.getLogger(ServiceMiyaApplication.class.getName());
  @Autowired
  private RestTemplate restTemplate;
  @Bean
  public RestTemplate getRestTemplate(){
    return new RestTemplate();
  }
  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }
  @RequestMapping("/hi")
  public String home(){
    log.log(Level.INFO, "hi is being called");
    return "hi i'm miya!";
  }

  @RequestMapping("/miya")
  public String info(){
    log.log(Level.INFO,"info is being called");
    return restTemplate.getForObject("http://localhost:8988/info",String.class);
  }
}
